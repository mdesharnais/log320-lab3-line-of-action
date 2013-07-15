package lineOfAction;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {

	private static Socket server;
	private static BufferedInputStream input;
	private static BufferedOutputStream output;

	public static void main(String[] args) throws Exception {
		// TODO Implement the game AI

		int player = 0;
		server = new Socket("localhost", 8888);
		input = new BufferedInputStream(server.getInputStream());
		output = new BufferedOutputStream(server.getOutputStream());

		char cmd;

		byte[] boardBuffer = new byte[1024];
		byte[] moveBuffer = new byte[16];

		int[] board = null;

		while (true) {
			cmd = (char) input.read();

			if (cmd == '1') {
				player = Player.White;
				input.read(boardBuffer, 0, input.available());
				board = Board.makeBoard(new String(boardBuffer).trim().replaceAll(" ", "").toCharArray());
				board = Board.applyMovement(board, play(board, player));
			} else if (cmd == '2') {
				player = Player.Black;
				input.read(boardBuffer, 0, input.available());
				board = Board.makeBoard(new String(boardBuffer).trim().replaceAll(" ", "").toCharArray());
				board = Board.applyMovement(board, play(board, player));
			} else if (cmd == '3') {
				input.read(moveBuffer, 0, input.available());
				int enemyMove = Movement.makeMovement(new String(moveBuffer));
				System.out.println(enemyMove);

				if (board != null) {
					board = Board.applyMovement(board, enemyMove);
					board = Board.applyMovement(board, play(board, player));
				}
			} else if (cmd == '4') {
				System.err.println("Coup invalide. D:");
				break;
			}

			if (board != null) {
				System.out.println(Board.toString(board));
			}

		}

	}

	private static int play(int[] board, int player) throws IOException {
		long bestAlpha = Integer.MIN_VALUE;

		int[] movements = Utils.generateMovements(board, player);
		int index = 0;
		int childPlayer = ~(player) & 0x6;
		int childMovement;

		// This is the first level of the alpha/beta pruning.
		// We can lauch them in separate thread and take adventage of multiple core without
		// spawning billions of thread because of the recursive ab() function.
		while ((childMovement = movements[index++]) != 0) {
			int[] childBoard = Board.applyMovement(board, childMovement);
			bestAlpha = Math.max(bestAlpha,
				ab(childBoard, // Board
					childPlayer, // Player
					(bestAlpha & 0xFFFFFFFF00000000l) & childMovement, // Alpha
					Integer.MAX_VALUE, // Beta
					7, // Depth
					false)); // optimize
		}
		int movement = (int) (bestAlpha & 0x00000000FFFFFFFF);
		String move = Movement.toString(movement);
		System.out.println(move);

		output.write(move.getBytes(), 0, move.length());
		output.flush();
		return movement;
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
	public static long ab(int[] board, int player, long alpha, long beta, int depth, boolean maximize) {
		if (depth <= 0) {
			long boardValue = (maximize ? 1 : -1) * Utils.evaluateBoard(board, player);
			// We set the int32 return by Utils.evaluateBoard()
			// as the 32 most significant bits of the alpha return value.
			// We keep the parent movement as the 32 least significant bits.
			return (boardValue << 32)
				| ((maximize ? alpha : beta) & 0x00000000FFFFFFFFl);
			// I can't even believe all this is necessary:
			//   - Utils.evaluateBoard() return an int32 and we need to explicitly cast it to int64
			//   - Even if alpha and beta are int64, we need to use explicitly specify the hexadecimal mask as int64 with "l" suffix
			// Is all this shit require because java refuse to have proper unsigned int?!?
		}

		if (maximize) {
			boolean firstRecursion = (alpha & 0x00000000FFFFFFFFl) == 0;
			int[] movements = Utils.generateMovements(board, player);
			int index = 0;

			int m;
			while ((m = movements[index++]) != 0) {
				int[] childBoard = Board.applyMovement(board, m);
				int childPlayer = ~(player) & 0x6;
				long childAlpha;

				// If the parent movement is empty, we are at the first recursion step
				if (firstRecursion) {
					// Since we are at the first recursion step, we have to insert the child movement
					childAlpha = ab(childBoard,
						childPlayer,
						(alpha & 0xFFFFFFFF00000000l) | (m & 0x00000000FFFFFFFF),
						(beta & 0xFFFFFFFF00000000l) | (m & 0x00000000FFFFFFFF),
						depth - 1, !maximize);
				} else {
					// Since we are not at the first recursion step, we keep the parent movement
					childAlpha = ab(childBoard, childPlayer, alpha, beta, depth - 1, !maximize);
				}

				// max(alpha, childAlpha)
				if (childAlpha > alpha) {
					alpha = childAlpha;
				}

				if (beta <= alpha) {
					break;
				}
			}

			// If there was not any children
			if (index == 1) {
				long boardValue = (maximize ? 1 : -1) * Utils.evaluateBoard(board, player);
				// We set the int32 return by Utils.evaluateBoard()
				// as the 32 most significant bits of the alpha return value.
				// We keep the parent movement as the 32 least significant bits.
				alpha = (boardValue << 32) | (alpha & 0x00000000FFFFFFFFl);
			}

			return alpha;
		}

		boolean firstRecursion = (beta & 0x00000000FFFFFFFFl) == 0;
		int[] movements = Utils.generateMovements(board, player);
		int index = 0;

		int m;
		while ((m = movements[index++]) != 0) {
			int[] childBoard = Board.applyMovement(board, m);
			int childPlayer = ~(player) & 0x6;
			long tempBeta;

			// If the parent movement is empty, we are at the first recursion step
			if (firstRecursion) {
				// Since we are at the first recursion step, we have to insert the child movement
				tempBeta = ab(childBoard,
					childPlayer,
					(alpha & 0xFFFFFFFF00000000l) | (m & 0x00000000FFFFFFFF),
					(beta & 0xFFFFFFFF00000000l) | (m & 0x00000000FFFFFFFF),
					depth - 1, !maximize);
			} else {
				// Since we are not at the first recursion step, we keep the parent movement
				tempBeta = ab(childBoard, childPlayer, alpha, beta, depth - 1, !maximize);
			}

			// min(beta, childBeta)
			if (tempBeta < beta) {
				beta = tempBeta;
			}

			if (beta <= alpha) {
				break;
			}
		}

		// If there was not any children
		if (index == 1) {
			long boardValue = (maximize ? 1 : -1) * Utils.evaluateBoard(board, player);
			// We set the int32 return by Utils.evaluateBoard()
			// as the 32 most significant bits of the alpha return value.
			// We keep the parent movement as the 32 least significant bits.
			beta = (boardValue << 32) | (beta & 0x00000000FFFFFFFFl);
		}

		return beta;
	}
}
