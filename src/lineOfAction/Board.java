package lineOfAction;

public class Board {
	public static int[] makeBoard(char[] board) {
		int[] array = new int[64];

		for (int i = 0; i < 64; ++i) {
			switch (board[i]) {
			case '0':
				// Keep default value of 0.
				break;
			case '2':
				array[i] = Player.Black;
				break;
			case '4':
				array[i] = Player.White;
				break;
			default:
				throw new IllegalArgumentException("");
			}
		}

		return array;
	}

	public static int get(int[] board, int column, int line) {
		if (column < 0 || 7 < column || line < 0 || 7 < line) {
			return 0;
		}

		return board[((7 - line) << 3) + column];
	}

	public static String toString(int[] board) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < 64; ++i) {
			if (board[i] == 0) {
				builder.append('.');
			} else if (board[i] == Player.Black) {
				builder.append('O');
			} else if (board[i] == Player.White) {
				builder.append('X');
			}

			if (i != 0 && i % 8 == 7) {
				builder.append('\n');
			} else {
				builder.append(' ');
			}
		}

		return builder.toString();
	}

	public static int[] applyMovement(int[] board, Movement movement) {
		int[] array = new int[64];
		int sourceOffset = ((7 - movement.sourceLine) << 3) + movement.sourceColumn;
		int destinationOffset = ((7 - movement.destinationLine) << 3) + movement.destinationColumn;

		System.arraycopy(board, 0, array, 0, 64);
		array[destinationOffset] = array[sourceOffset];
		array[sourceOffset] = 0;
		return array;
	}
}
