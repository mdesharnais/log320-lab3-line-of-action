package lineOfAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
	public static int[] makeBoard(char[] board) {
		int[] array = new int[64];

		for (int i = 0; i < 64; ++i) {
			int column = i & 0x7;
			int line = 7 - (i >> 3);

			switch (board[i]) {
			case '0':
				// Keep default value of 0.
				break;
			case '2':
				array[(line << 3) + column] = Player.Black;
				break;
			case '4':
				array[(line << 3) + column] = Player.White;
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

		return board[(line << 3) + column];
	}

	public static String toString(int[] board) {
		StringBuilder builder = new StringBuilder();

		List<String> list = new ArrayList<String>();

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
				list.add(builder.toString());
				builder = new StringBuilder();
			} else {
				builder.append(' ');
			}
		}

		Collections.reverse(list);
		builder = new StringBuilder();

		for (String str : list) {
			builder.append(str);
		}

		return builder.toString();
	}

	public static int[] applyMovement(int[] board, Movement movement) {
		int[] array = new int[64];
		int sourceOffset = (movement.sourceLine << 3) + movement.sourceColumn;
		int destinationOffset = (movement.destinationLine << 3) + movement.destinationColumn;

		System.arraycopy(board, 0, array, 0, 64);
		array[destinationOffset] = array[sourceOffset];
		array[sourceOffset] = 0;
		return array;
	}
}
