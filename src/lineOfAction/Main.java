package lineOfAction;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {

	private static String host;
	private static Socket server;
	private static BufferedInputStream input;
	private static BufferedOutputStream output;
	public static ExecutorService s_executor = Executors.newFixedThreadPool(4);

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.print("Input server to connect to: ");
		host = sc.nextLine();
		sc.close();

		if (host.isEmpty()) {
			host = "localhost";
		}

		input = new BufferedInputStream(System.in);

		try {
			server = new Socket(host, 8888);
			input = new BufferedInputStream(server.getInputStream());
			output = new BufferedOutputStream(server.getOutputStream());
			char cmd;

			byte[] boardBuffer = new byte[1024];
			byte[] moveBuffer = new byte[16];

			long friends = 0;
			long enemies = 0;

			while (true) {
				int data = input.read();
				System.out.printf("----<<<< '%x' >>>>----\n", data);
				cmd = (char) data;

				if (cmd == '1' || cmd == '2') {
					input.read(boardBuffer, 0, input.available());
					long begin = System.currentTimeMillis();
					long[] boards = Board.makeBoard(new String(boardBuffer).trim().replaceAll(" ", "")
						.toCharArray());

					switch (cmd) {
					case '1': // White
						friends = boards[1];
						enemies = boards[0];
						int ourMovement = play(friends, enemies, begin, s_executor);
						boards = Board.applyMovement(friends, enemies, ourMovement);
						friends = boards[0];
						enemies = boards[1];
						break;
					case '2': // Black
						friends = boards[0];
						enemies = boards[1];
						break;
					}
				} else if (cmd == '3') {
					input.read(moveBuffer, 0, input.available());
					long begin = System.currentTimeMillis();
					int enemyMove = Movement.makeMovement(new String(moveBuffer));
					System.out.printf("Enemy movement: %s\n", Movement.toString(enemyMove));

					long[] boards = Board.applyMovement(enemies, friends, enemyMove);
					friends = boards[1];
					enemies = boards[0];
					int ourMovement = play(friends, enemies, begin, s_executor);
					boards = Board.applyMovement(friends, enemies, ourMovement);
					friends = boards[0];
					enemies = boards[1];
				} else if (cmd == '4') {
					System.err.println("Coup invalide. D:");
					break;
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		s_executor.shutdown();
		s_executor.awaitTermination(10, TimeUnit.MILLISECONDS);
	}

	/*
		public static class FuckingJavaThatDoNotHaveProperSupportOfClosures implements Callable<Long> {
			private long friends;
			private long enemies;
			private int depth;

			public FuckingJavaThatDoNotHaveProperSupportOfClosures(long friends, long enemies, int depth) {
				this.friends = friends;
				this.enemies = enemies;
				this.depth = depth;
			}

			@Override
			public Long call() throws Exception {
				return new AlphaBeta(this.friends, this.enemies, Long.MIN_VALUE, Long.MAX_VALUE, this.depth, true)
					.call();
			}
		}
		*/

	private static int play(final long friends, final long enemies, long beginInMilliseconds,
		ExecutorService executor) throws IOException {
		int depth = 1;
		long bestAlpha = 0;//new AlphaBeta(friends, enemies, depth - 1).call();
		//*
		do {
			long begin = System.currentTimeMillis();
			long remaining = 4900 - (begin - beginInMilliseconds);
			//System.out.println(remaining);

			Future<Long> future = executor.submit(new FuckingJavaThatDoNotHaveProperSupportOfClosures(
				friends, enemies, depth++));

			try {
				bestAlpha = future.get(remaining, TimeUnit.MILLISECONDS);
				System.out.printf("Depth: %d ; Best movement: %s ; Duration: %d ms\n", depth - 1, Movement
					.toString((int) (bestAlpha & 0x00000000FFFFFFFFl)), System.currentTimeMillis() - begin);
			} catch (Exception e) {
				future.cancel(true);
			}
		} while (System.currentTimeMillis() - beginInMilliseconds < 4900);
		//*/
		int movement = (int) (bestAlpha & 0x00000000FFFFFFFFl);
		String move = Movement.toString(movement);

		output.write(move.getBytes(), 0, move.length());
		output.flush();
		System.out.printf("Total: %d\n--\n", System.currentTimeMillis() - beginInMilliseconds);
		//executor.shutdownNow();

		//System.exit(0);
		return movement;
	}

	private static class FuckingJavaThatDoNotHaveProperSupportOfClosures implements Callable<Long> {
		private long friends;
		private long enemies;
		private int depth;

		public FuckingJavaThatDoNotHaveProperSupportOfClosures(long friends, long enemies, int depth) {
			this.friends = friends;
			this.enemies = enemies;
			this.depth = depth;
		}

		@Override
		public Long call() throws Exception {
			return Utils.alphaBeta(this.friends, this.enemies, this.depth);
		}
	}

	public static String repeat(char character, int times) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < times; ++i) {
			builder.append(character);
		}

		return builder.toString();
	}

	// alpha & beta use the following encoding:
	//
	// 64       56       48       40       32       24       16       8        0
	// +--------+--------+--------+--------+--------+--------+--------+--------+
	// | Alpha or beta value               | srcCol | srcLin | dstCol | dstLin |
	// +--------+--------+--------+--------+--------+--------+--------+--------+
	//
	// Since the alpha/beta value are the most significant bits, we cans still compare them and the position
	// will not have any effect on the result.
	public static long ab(long friends, long enemies, long alpha, long beta, int depth, boolean maximize) {
		/*System.out.printf("%s Alpha: %x Beta: %x\n%s", repeat('-', depth), alpha, beta, Board.toString(
		    friends, enemies));
		*/
		if (depth <= 0) {
			long boardValue = (maximize ? 1 : -1) * Utils.evaluateBoard(friends, enemies);

			// We set the int32 return by Utils.evaluateBoard()
			// as the 32 most significant bits of the alpha return value.
			// We keep the parent movement as the 32 least significant bits.
			return (boardValue << 32) | ((maximize ? alpha : beta) & 0x00000000FFFFFFFFl);
			// I can't even believe all this is necessary:
			//   - Utils.evaluateBoard() return an int32 and we need to explicitly cast it to int64
			//   - Even if alpha and beta are int64, we need to use explicitly specify the hexadecimal mask as int64 with "l" suffix
			// Is all this shit require because java refuse to have proper unsigned int?!?
		}

		int movementCount = 0;
		int[] horizontalMoveLengthArray = new int[8];
		int[] verticalMoveLengthArray = new int[8];
		int[] diagonalUpperLeftToLowerRightArray = new int[15];
		int[] diagonalLowerLeftToUpperRightArray = new int[15];

		// Pre-calculate the movement length for every line/column
		for (int i = 0; i < 64; ++i) {
			long offset = 0x1l << i;
			int n = 0;

			if ((friends & offset) != 0) {
				++n;
			}

			if ((enemies & offset) != 0) {
				++n;
			}

			if (n != 0) {
				int column = i & 0x7;
				int line = i >> 3;
				horizontalMoveLengthArray[line] += n;
				verticalMoveLengthArray[column] += n;
				diagonalUpperLeftToLowerRightArray[column + line] += n;
				diagonalLowerLeftToUpperRightArray[7 + (column - line)] += n;
			}
		}

		for (int i = 0; i < 64; ++i) {
			if ((friends & (0x1l << i)) != 0) {
				int column = i & 0x7;
				int line = (i >> 3);

				int horizontalMoveLength = horizontalMoveLengthArray[line];
				int verticalMoveLength = verticalMoveLengthArray[column];
				int diagonalUpperLeftToLowerRightMoveLength = diagonalUpperLeftToLowerRightArray[line
					+ column];
				int diagonalLowerLeftToUpperRightMoveLength = diagonalLowerLeftToUpperRightArray[7 + (column - line)];

				boolean horizontalLeft = (column - horizontalMoveLength) >= 0;
				boolean horizontalRight = (column + horizontalMoveLength) < 8;
				boolean verticalUp = (line + verticalMoveLength) < 8;
				boolean verticalDown = (line - verticalMoveLength) >= 0;
				boolean diagonalUpperLeft = (column - diagonalUpperLeftToLowerRightMoveLength) >= 0
					&& (line + diagonalUpperLeftToLowerRightMoveLength) < 8;
				boolean diagonalUpperRight = (column + diagonalLowerLeftToUpperRightMoveLength) < 8
					&& (line + diagonalLowerLeftToUpperRightMoveLength) < 8;
				boolean diagonalLowerLeft = (column - diagonalLowerLeftToUpperRightMoveLength) >= 0
					&& (line - diagonalLowerLeftToUpperRightMoveLength) >= 0;
				boolean diagonalLowerRight = (column + diagonalUpperLeftToLowerRightMoveLength) < 8
					&& (line - diagonalUpperLeftToLowerRightMoveLength) >= 0;

				// Second, we make a pass to generate the moves
				// We loop while there is one direction that could potentially be a movement
				for (int j = 1; j < 8
					&& (horizontalLeft || horizontalRight || verticalUp || verticalDown || diagonalUpperLeft
						|| diagonalUpperRight || diagonalLowerLeft || diagonalLowerRight); ++j) {

					int leftCol = column - j;
					int rightCol = column + j;
					int upLine = line + j;
					int downLine = line - j;

					if (horizontalLeft) {
						if (j < horizontalMoveLength) {
							horizontalLeft = (enemies & (0x1l << ((line << 3) + leftCol))) == 0;
						} else if (j == horizontalMoveLength) {
							horizontalLeft = (friends & (0x1l << ((line << 3) + leftCol))) == 0;
						}
					}

					if (horizontalRight) {
						if (j < horizontalMoveLength) {
							horizontalRight = (enemies & (0x1l << ((line << 3) + rightCol))) == 0;
						} else if (j == horizontalMoveLength) {
							horizontalRight = (friends & (0x1l << ((line << 3) + rightCol))) == 0;
						}
					}

					if (verticalUp) {
						if (j < verticalMoveLength) {
							verticalUp = (enemies & (0x1l << ((upLine << 3) + column))) == 0;
						} else if (j == verticalMoveLength) {
							verticalUp = (friends & (0x1l << ((upLine << 3) + column))) == 0;
						}
					}

					if (verticalDown) {
						if (j < verticalMoveLength) {
							verticalDown = (enemies & (0x1l << ((downLine << 3) + column))) == 0;
						} else if (j == verticalMoveLength) {
							verticalDown = (friends & (0x1l << ((downLine << 3) + column))) == 0;
						}
					}

					if (diagonalUpperLeft) {
						if (j < diagonalUpperLeftToLowerRightMoveLength) {
							diagonalUpperLeft = (enemies & (0x1l << ((upLine << 3) + leftCol))) == 0;
						} else if (j == diagonalUpperLeftToLowerRightMoveLength) {
							diagonalUpperLeft = (friends & (0x1l << ((upLine << 3) + leftCol))) == 0;
						}
					}

					if (diagonalUpperRight) {
						if (j < diagonalLowerLeftToUpperRightMoveLength) {
							diagonalUpperRight = (enemies & (0x1l << ((upLine << 3) + rightCol))) == 0;
						} else if (j == diagonalLowerLeftToUpperRightMoveLength) {
							diagonalUpperRight = (friends & (0x1l << ((upLine << 3) + rightCol))) == 0;
						}
					}

					if (diagonalLowerLeft) {
						if (j < diagonalLowerLeftToUpperRightMoveLength) {
							diagonalLowerLeft = (enemies & (0x1l << ((downLine << 3) + leftCol))) == 0;
						} else if (j == diagonalLowerLeftToUpperRightMoveLength) {
							diagonalLowerLeft = (friends & (0x1l << ((downLine << 3) + leftCol))) == 0;
						}
					}

					if (diagonalLowerRight && j <= diagonalUpperLeftToLowerRightMoveLength) {
						if (j < diagonalUpperLeftToLowerRightMoveLength) {
							diagonalLowerRight = (enemies & (0x1l << ((downLine << 3) + rightCol))) == 0;
						} else if (j == diagonalUpperLeftToLowerRightMoveLength) {
							diagonalLowerRight = (friends & (0x1l << ((downLine << 3) + rightCol))) == 0;
						}
					}
				}

				int srcOffset = (line << 3) + column;

				if (horizontalLeft) {
					++movementCount;
					int dstOffset = (column - horizontalMoveLength) + (line << 3);

					// Our friends is our child enemies
					long childFriends = enemies;
					long childEnemies = friends;

					// Clear our source position in our board
					childEnemies &= ~(0x1l << srcOffset);

					// Clear our destination position in enemies board
					childFriends &= ~(0x1l << dstOffset);

					// Set our destination position in our board
					childEnemies |= (0x1l << dstOffset);

					long childValue = ab(childFriends, childEnemies, alpha, beta, depth - 1, !maximize);

					if (maximize) {
						if (childValue > alpha) {
							alpha = childValue;
						}
					} else {
						if (childValue < beta) {
							beta = childValue;
						}
					}

					if (beta <= alpha) {
						break;
					}
				}
				if (horizontalRight) {
					++movementCount;
					int dstOffset = (column + horizontalMoveLength) + (line << 3);

					// Our friends is our child enemies
					long childFriends = enemies;
					long childEnemies = friends;

					// Clear our source position in our board
					childEnemies &= ~(0x1l << srcOffset);

					// Clear our destination position in enemies board
					childFriends &= ~(0x1l << dstOffset);

					// Set our destination position in our board
					childEnemies |= (0x1l << dstOffset);

					long childValue = ab(childFriends, childEnemies, alpha, beta, depth - 1, !maximize);

					if (maximize) {
						if (childValue > alpha) {
							alpha = childValue;
						}
					} else {
						if (childValue < beta) {
							beta = childValue;
						}
					}

					if (beta <= alpha) {
						break;
					}
				}
				if (verticalUp) {
					++movementCount;
					int dstOffset = column + ((line + verticalMoveLength) << 3);

					// Our friends is our child enemies
					long childFriends = enemies;
					long childEnemies = friends;

					// Clear our source position in our board
					childEnemies &= ~(0x1l << srcOffset);

					// Clear our destination position in enemies board
					childFriends &= ~(0x1l << dstOffset);

					// Set our destination position in our board
					childEnemies |= (0x1l << dstOffset);

					long childValue = ab(childFriends, childEnemies, alpha, beta, depth - 1, !maximize);

					if (maximize) {
						if (childValue > alpha) {
							alpha = childValue;
						}
					} else {
						if (childValue < beta) {
							beta = childValue;
						}
					}

					if (beta <= alpha) {
						break;
					}
				}
				if (verticalDown) {
					++movementCount;
					int dstOffset = column + ((line - verticalMoveLength) << 3);

					// Our friends is our child enemies
					long childFriends = enemies;
					long childEnemies = friends;

					// Clear our source position in our board
					childEnemies &= ~(0x1l << srcOffset);

					// Clear our destination position in enemies board
					childFriends &= ~(0x1l << dstOffset);

					// Set our destination position in our board
					childEnemies |= (0x1l << dstOffset);

					long childValue = ab(childFriends, childEnemies, alpha, beta, depth - 1, !maximize);

					if (maximize) {
						if (childValue > alpha) {
							alpha = childValue;
						}
					} else {
						if (childValue < beta) {
							beta = childValue;
						}
					}

					if (beta <= alpha) {
						break;
					}
				}
				if (diagonalUpperLeft) {
					++movementCount;
					int dstOffset = (column - diagonalUpperLeftToLowerRightMoveLength)
						+ ((line + diagonalUpperLeftToLowerRightMoveLength) << 3);

					// Our friends is our child enemies
					long childFriends = enemies;
					long childEnemies = friends;

					// Clear our source position in our board
					childEnemies &= ~(0x1l << srcOffset);

					// Clear our destination position in enemies board
					childFriends &= ~(0x1l << dstOffset);

					// Set our destination position in our board
					childEnemies |= (0x1l << dstOffset);

					long childValue = ab(childFriends, childEnemies, alpha, beta, depth - 1, !maximize);

					if (maximize) {
						if (childValue > alpha) {
							alpha = childValue;
						}
					} else {
						if (childValue < beta) {
							beta = childValue;
						}
					}

					if (beta <= alpha) {
						break;
					}
				}
				if (diagonalUpperRight) {
					++movementCount;
					int dstOffset = (column + diagonalLowerLeftToUpperRightMoveLength)
						+ ((line + diagonalLowerLeftToUpperRightMoveLength) << 3);

					// Our friends is our child enemies
					long childFriends = enemies;
					long childEnemies = friends;

					// Clear our source position in our board
					childEnemies &= ~(0x1l << srcOffset);

					// Clear our destination position in enemies board
					childFriends &= ~(0x1l << dstOffset);

					// Set our destination position in our board
					childEnemies |= (0x1l << dstOffset);

					long childValue = ab(childFriends, childEnemies, alpha, beta, depth - 1, !maximize);

					if (maximize) {
						if (childValue > alpha) {
							alpha = childValue;
						}
					} else {
						if (childValue < beta) {
							beta = childValue;
						}
					}

					if (beta <= alpha) {
						break;
					}
				}
				if (diagonalLowerLeft) {
					++movementCount;
					int dstOffset = (column - diagonalLowerLeftToUpperRightMoveLength)
						+ ((line - diagonalLowerLeftToUpperRightMoveLength) << 3);

					// Our friends is our child enemies
					long childFriends = enemies;
					long childEnemies = friends;

					// Clear our source position in our board
					childEnemies &= ~(0x1l << srcOffset);

					// Clear our destination position in enemies board
					childFriends &= ~(0x1l << dstOffset);

					// Set our destination position in our board
					childEnemies |= (0x1l << dstOffset);

					long childValue = ab(childFriends, childEnemies, alpha, beta, depth - 1, !maximize);

					if (maximize) {
						if (childValue > alpha) {
							alpha = childValue;
						}
					} else {
						if (childValue < beta) {
							beta = childValue;
						}
					}

					if (beta <= alpha) {
						break;
					}
				}
				if (diagonalLowerRight) {
					++movementCount;
					int dstOffset = (column + diagonalUpperLeftToLowerRightMoveLength)
						+ ((line - diagonalUpperLeftToLowerRightMoveLength) << 3);

					// Our friends is our child enemies
					long childFriends = enemies;
					long childEnemies = friends;

					// Clear our source position in our board
					childEnemies &= ~(0x1l << srcOffset);

					// Clear our destination position in enemies board
					childFriends &= ~(0x1l << dstOffset);

					// Set our destination position in our board
					childEnemies |= (0x1l << dstOffset);

					long childValue = ab(childFriends, childEnemies, alpha, beta, depth - 1, !maximize);

					if (maximize) {
						if (childValue > alpha) {
							alpha = childValue;
						}
					} else {
						if (childValue < beta) {
							beta = childValue;
						}
					}

					if (beta <= alpha) {
						break;
					}
				}
			}
		}

		// If there was not any children
		if (movementCount == 0) {
			long boardValue = (maximize ? 1 : -1) * Utils.evaluateBoard(friends, enemies);
			// We set the int32 return by Utils.evaluateBoard()
			// as the 32 most significant bits of the alpha return value.
			// We keep the parent movement as the 32 least significant bits.

			return (boardValue << 32) | ((maximize ? alpha : beta) & 0x00000000FFFFFFFFl);
		}

		return maximize ? alpha : beta;
	}

}
