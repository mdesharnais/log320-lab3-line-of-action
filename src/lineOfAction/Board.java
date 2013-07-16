package lineOfAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
	public static long[] makeBoard(char[] board) {
		long whites = 0;
		long blacks = 0;

		for (int i = 0; i < 64; ++i) {
			int column = i & 0x7;
			int line = 7 - (i >> 3);

			switch (board[i]) {
			case '0':
				// Keep default value of 0.
				break;
			case '2':
				blacks |= (0x1l << ((line << 3) + column));
				break;
			case '4':
				whites |= (0x1l << ((line << 3) + column));
				break;
			default:
				throw new IllegalArgumentException("");
			}
		}

		return new long[] { blacks, whites };
	}

	public static int get(int[] board, int column, int line) {
		if (column < 0 || 7 < column || line < 0 || 7 < line) {
			return 0;
		}

		return board[(line << 3) + column];
	}

	public static String toString(long blacks, long whites) {
		StringBuilder builder = new StringBuilder();

		List<String> list = new ArrayList<String>();

		for (int i = 0; i < 64; ++i) {
			if ((blacks & (0x1l << i)) != 0) {
				builder.append('O');
			} else if ((whites & (0x1l << i)) != 0) {
				builder.append('X');
			} else {
				builder.append('.');
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

	public static long[] applyMovement(long friends, long enemies, int movement) {
		int sourceOffset = (((movement & 0x00FF0000) >> 13) + ((movement & 0xFF000000) >> 24));
		int destinationOffset = ((movement & 0x000000FF) << 3)
			+ ((movement & 0x0000FF00) >> 8);

		// Clear our source position in our board
		friends &= ~(0x1l << sourceOffset);

		// Clear our destination position in enemies board
		enemies &= ~(0x1l << destinationOffset);

		// Set our destination position in our board
		friends |= (0x1l << destinationOffset);

		return new long[] { friends, enemies };
	}
}
