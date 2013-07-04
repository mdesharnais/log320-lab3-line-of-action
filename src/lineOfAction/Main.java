package lineOfAction;

public class Main {
	public static void main(String[] args) {
		// TODO Implement the game AI

		int[] board = Board.makeBoard(new char[] {
			'0', '2', '2', '2', '2', '2', '2', '0',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'0', '2', '2', '2', '2', '2', '2', '0'
		});

		MovementTree tree = new MovementTree(board, Player.Black);

		long begin = System.nanoTime();
		MovementTree shouldPlay = ab(tree, null, Integer.MIN_VALUE, null, Integer.MAX_VALUE, 7, true);
		long end = System.nanoTime();

		System.out.println("Move selected:");
		System.out.println(Board.toString(shouldPlay.board));

		long duration = end - begin;
		System.out.println(duration / 1000000);
	}

	private static MovementTree ab(MovementTree tree, MovementTree alphaTree, int alpha,
		MovementTree betaTree, int beta, int depth,
		boolean maximize) {
		if (depth <= 0) {
			return tree;
		}

		if (maximize) {
			for (MovementTree child : tree) {
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

		for (MovementTree child : tree) {
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
