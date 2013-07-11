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

		MovementTree tree = null;

		while (true) {
			cmd = (char) input.read();

			if (cmd == '1') {
				player = Player.White;
				input.read(boardBuffer, 0, input.available());
				tree = new MovementTree(Board.makeBoard(new String(boardBuffer).trim().replaceAll(" ", "")
					.toCharArray()), Player.White);
				tree = play(tree);
			} else if (cmd == '2') {
				player = Player.Black;
				input.read(boardBuffer, 0, input.available());
				tree = new MovementTree(Board.makeBoard(new String(boardBuffer).trim().replaceAll(" ", "")
					.toCharArray()), Player.Black);

			} else if (cmd == '3') {
				input.read(moveBuffer, 0, input.available());
				Movement enemyMove = Movement.decode(new String(moveBuffer));
				System.out.println(enemyMove);

				if (tree != null) {
					tree = new MovementTree(Board.applyMovement(tree.board, enemyMove), player);
					tree = play(tree);
				}
			} else if (cmd == '4') {
				System.err.println("Coup invalide. D:");
				break;
			}

			if (tree != null) {
				System.out.println(Board.toString(tree.board));
			}

		}

	}

	private static MovementTree play(MovementTree tree) throws IOException {
		MovementTree toPlay = ab(tree, null, Integer.MIN_VALUE, null, Integer.MAX_VALUE, 7, true);
		String move = toPlay.movement.toString();
		System.out.println(move);

		output.write(move.getBytes(), 0, move.length());
		output.flush();

		return new MovementTree(Board.applyMovement(tree.board, toPlay.movement), tree.player);
	}

	private static MovementTree ab(MovementTree tree, MovementTree alphaTree, int alpha,
		MovementTree betaTree, int beta, int depth,
		boolean maximize) {
		if (depth <= 0) {
			return tree;
		}

		if (maximize) {
			MovementTree child;
			while ((child = tree.nextChild()) != null) {
				int betaValue = Utils.evaluateBoard(ab(child, alphaTree, alpha, betaTree, beta, depth - 1,
					!maximize));

				if (betaValue > alpha) {
					alphaTree = child;
				}

				alpha = Math.max(alpha, betaValue);

				if (beta <= alpha) {
					break;
				}
			}

			return alphaTree;
		}

		MovementTree child;
		while ((child = tree.nextChild()) != null) {
			int alphaValue = Utils.evaluateBoard(ab(child, alphaTree, alpha, betaTree, beta, depth - 1,
				!maximize));

			if (beta >= alphaValue) {
				betaTree = child;
			}

			beta = Math.min(beta, alphaValue);

			if (beta <= alpha) {
				break;
			}
		}

		return betaTree;

	}

}
