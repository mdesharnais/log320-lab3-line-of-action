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
		play(tree, 3);
		long end = System.nanoTime();

		long duration = end - begin;
		System.out.println(duration / 1000000);
	}

	private static void play(MovementTree tree, int depth) {
		if (depth >= 0) {
			int newDepth = depth - 1;
			for (MovementTree child : tree) {
				play(child, newDepth);
			}
		}
	}
}
