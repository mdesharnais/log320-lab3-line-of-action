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

		server = new Socket("localhost", 8888);
		input = new BufferedInputStream(server.getInputStream());
		output = new BufferedOutputStream(server.getOutputStream());

		char cmd;

		byte[] boardBuffer = new byte[1024];
		byte[] moveBuffer = new byte[16];

		long friends = 0;
		long enemies = 0;

		while (true) {
			cmd = (char) input.read();

			if (cmd == '1') {
				input.read(boardBuffer, 0, input.available());
				long[] boards = Board.makeBoard(new String(boardBuffer).trim().replaceAll(" ", "")
					.toCharArray());
				friends = boards[1]; // Whites
				enemies = boards[0]; // Blacks
				boards = Board.applyMovement(friends, enemies, play(friends, enemies));
				friends = boards[0]; // First argument
				enemies = boards[1]; // Second argument
			} else if (cmd == '2') {
				input.read(boardBuffer, 0, input.available());
				long[] boards = Board.makeBoard(new String(boardBuffer).trim().replaceAll(" ", "")
					.toCharArray());
				friends = boards[0]; // Blacks
				enemies = boards[1]; // Whites
				boards = Board.applyMovement(friends, enemies, play(friends, enemies));
				friends = boards[0]; // First argument
				enemies = boards[1]; // Second argument
			} else if (cmd == '3') {
				input.read(moveBuffer, 0, input.available());
				int enemyMove = Movement.makeMovement(new String(moveBuffer));
				System.out.println(enemyMove);

				long[] boards = Board.applyMovement(enemies, friends, enemyMove);
				friends = boards[1]; // Second argument
				enemies = boards[0]; // First argument
				boards = Board.applyMovement(friends, enemies, play(friends, enemies));
				friends = boards[0]; // First argument
				enemies = boards[1]; // Second argument
			} else if (cmd == '4') {
				System.err.println("Coup invalide. D:");
				break;
			}
		}

	}

	private static int play(long friends, long enemies) throws IOException {
		long bestAlpha = Integer.MIN_VALUE;

		int[] movements = Utils.generateMovements(friends, enemies);
		int index = 0;
		int childMovement;

		// This is the first level of the alpha/beta pruning.
		// We can lauch them in separate thread and take adventage of multiple core without
		// spawning billions of thread because of the recursive ab() function.
		while ((childMovement = movements[index++]) != 0) {
			long[] childBoards = Board.applyMovement(friends, enemies, childMovement);
			bestAlpha = Math.max(bestAlpha,
				ab(childBoards[1], // friends
					childBoards[0], // enemies
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
	public static long ab(long friends, long enemies, long alpha, long beta, int depth, boolean maximize) {
		if (depth <= 0) {
			return ((maximize ? alpha : beta) & 0xFFFFFFFF00000000l)
				& (Utils.evaluateBoard(friends, enemies) & 0x00000000FFFFFFFF);
		}

		if (maximize) {
			int[] movements = Utils.generateMovements(friends, enemies);
			int index = 0;

			int m;
			while ((m = movements[index++]) != 0) {
				int srcOffset = ((m & 0x00FF0000) >> 13) + ((m & 0xFF000000) >> 24);
				int dstOffset = ((m & 0x000000FF) << 3) + ((m & 0x0000FF00) >> 8);

				// Our friends is our child enemies
				long childFriends = enemies;
				long childEnemies = friends;

				// Clear our source position in our board
				childEnemies &= ~(0x1l << srcOffset);

				// Clear our destination position in enemies board
				childFriends &= ~(0x1l << dstOffset);

				// Set our destination position in our board
				childEnemies |= (0x1l << dstOffset);

				// Play the alpha/beta game
				long childAlpha = ab(childFriends, childEnemies, alpha, beta, depth - 1, !maximize);

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
				alpha = (alpha & 0xFFFFFFFF00000000l)
					& (Utils.evaluateBoard(friends, enemies) & 0x00000000FFFFFFFF);
			}

			return alpha;
		}

		int[] movements = Utils.generateMovements(friends, enemies);
		int index = 0;

		int m;
		while ((m = movements[index++]) != 0) {
			int srcOffset = ((m & 0x00FF0000) >> 13) + ((m & 0xFF000000) >> 24);
			int dstOffset = ((m & 0x000000FF) << 3) + ((m & 0x0000FF00) >> 8);

			// Our friends is our child enemies
			long childFriends = enemies;
			long childEnemies = friends;

			// Clear our source position in our board
			childEnemies &= ~(0x1l << srcOffset);

			// Clear our destination position in enemies board
			childFriends &= ~(0x1l << dstOffset);

			// Set our destination position in our board
			childEnemies |= (0x1l << dstOffset);

			// Play the alpha/beta game
			long tempBeta = ab(childFriends, childEnemies, alpha, beta, depth - 1, !maximize);

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
			beta = (beta & 0xFFFFFFFF00000000l)
				& (Utils.evaluateBoard(friends, enemies) & 0x00000000FFFFFFFF);
		}

		return beta;
	}

	private static void benchmark(long friends, long enemies, int depth) {
		if (depth >= 0) {
			int newDepth = depth - 1;
			int[] movements = Utils.generateMovements(friends, enemies);
			int index = 0;
			int childMovement;

			while ((childMovement = movements[index++]) != 0) {
				int sourceOffset = (((childMovement & 0x00FF0000) >> 13) + ((childMovement & 0xFF000000) >> 24));
				int destinationOffset = ((childMovement & 0x000000FF) << 3)
					+ ((childMovement & 0x0000FF00) >> 8);

				// Our friends is our child enemies
				long childFriends = enemies;
				long childEnemies = friends;

				// Clear our source position in our board
				childEnemies &= ~(0x1l << sourceOffset);

				// Clear our destination position in enemies board
				childFriends &= ~(0x1l << destinationOffset);

				// Set our destination position in our board
				childEnemies |= (0x1l << destinationOffset);

				// Play for the other player
				benchmark(childFriends, childEnemies, newDepth);
			}
		}
	}
}
