package lineOfAction;

public class Main {
	public static void main(String[] args) {
		// TODO Implement the game AI

		Board board = new Board(new char[] {
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
		play(tree, 4);
		long end = System.nanoTime();

		long duration = end - begin;
		System.out.println(duration / 1000000);
	}

	private static void play(MovementTree tree, int depth) {
		if (depth >= 0) {
			for (MovementTree child : tree) {
				play(child, depth - 1);
			}
		}
	}
}
