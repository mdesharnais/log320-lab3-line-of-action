package lineOfAction;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	public static boolean equals(Object x, Object y) {
		if (x == null && y == null) {
			return true;
		} else if (x == null) {
			return false;
		} else if (y == null) {
			return false;
		} else {
			return x.equals(y);
		}
	}

	public static <T extends Comparable<T>> int compareTo(T x, T y) {
		if (x == null && y == null) {
			return 0;
		} else if (x == null) {
			return -1;
		} else if (y == null) {
			return 1;
		} else {
			return x.compareTo(y);
		}
	}

	public static List<Movement> generateMovements(Board board, int player) {
		// Yes, this function break the encapsulation of Board but, OMG encapsulation is so costly in Java...

		List<Movement> list = new ArrayList<Movement>(64);
		int[] horizontalMoveLengthArray = new int[8];
		int[] verticalMoveLengthArray = new int[8];

		// Pre-calculate the movement length for every line/column
		for (int i = 0; i < 64; ++i) {
			if (board.data[i] != 0) {
				horizontalMoveLengthArray[7 - (i >> 3)] += 1;
				verticalMoveLengthArray[i & 0x7] += 1;
			}
		}

		for (int i = 0; i < 64; ++i) {
			if (board.data[i] != 0 && board.data[i] == player) {
				int column = i & 0x7;
				int line = 7 - (i >> 3);

				int horizontalMoveLength = horizontalMoveLengthArray[line];
				int verticalMoveLength = verticalMoveLengthArray[column];
				int diagonalUpperLeftToLowerRightMoveLength = 1;
				int diagonalLowerLeftToUpperRightMoveLength = 1;

				// First, we make a pass to count the length of every moves
				for (int j = 1; j < 8; ++j) {
					int leftCol = column - j;
					int rightCol = column + j;
					int upLine = line + j;
					int downLine = line - j;

					// Diagonal upper left
					if (board.get(leftCol, upLine) != 0) {
						diagonalUpperLeftToLowerRightMoveLength += 1;
					}

					// Diagonal upper right
					if (board.get(rightCol, upLine) != 0) {
						diagonalLowerLeftToUpperRightMoveLength += 1;
					}

					// Diagonal lower left
					if (board.get(leftCol, downLine) != 0) {
						diagonalLowerLeftToUpperRightMoveLength += 1;
					}

					// Diagonal lower right
					if (board.get(rightCol, downLine) != 0) {
						diagonalUpperLeftToLowerRightMoveLength += 1;
					}
				}

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
				for (int j = 1; j < 8; ++j) {
					int leftCol = column - j;
					int rightCol = column + j;
					int upLine = line + j;
					int downLine = line - j;

					if (horizontalLeft && j <= horizontalMoveLength) {
						int p = board.get(leftCol, line);
						if (p != 0) {
							horizontalLeft = (p == player) ^ (j == horizontalMoveLength);
						}
					}

					if (horizontalRight && j <= horizontalMoveLength) {
						int p = board.get(rightCol, line);
						if (p != 0) {
							horizontalRight = (p == player) ^ (j == horizontalMoveLength);
						}
					}

					if (verticalUp && j <= verticalMoveLength) {
						int p = board.get(column, upLine);
						if (p != 0) {
							verticalUp = (p == player) ^ (j == verticalMoveLength);
						}
					}

					if (verticalDown && j <= verticalMoveLength) {
						int p = board.get(column, downLine);
						if (p != 0) {
							verticalDown = (p == player) ^ (j == verticalMoveLength);
						}
					}

					if (diagonalUpperLeft && j <= diagonalUpperLeftToLowerRightMoveLength) {
						int p = board.get(leftCol, upLine);
						if (p != 0) {
							diagonalUpperLeft = (p == player)
								^ (j == diagonalUpperLeftToLowerRightMoveLength);
						}
					}

					if (diagonalUpperRight && j <= diagonalLowerLeftToUpperRightMoveLength) {
						int p = board.get(rightCol, upLine);
						if (p != 0) {
							diagonalUpperRight = (p == player)
								^ (j == diagonalLowerLeftToUpperRightMoveLength);
						}
					}

					if (diagonalLowerLeft && j <= diagonalLowerLeftToUpperRightMoveLength) {
						int p = board.get(leftCol, downLine);
						if (p != 0) {
							diagonalLowerLeft = (p == player)
								^ (j == diagonalLowerLeftToUpperRightMoveLength);
						}
					}

					if (diagonalLowerRight && j <= diagonalUpperLeftToLowerRightMoveLength) {
						int p = board.get(rightCol, downLine);
						if (p != 0) {
							diagonalLowerRight = (p == player)
								^ (j == diagonalUpperLeftToLowerRightMoveLength);
						}
					}
				}

				if (horizontalLeft) {
					list.add(new Movement(column, line,
						column - horizontalMoveLength,
						line));
				}

				if (horizontalRight) {
					list.add(new Movement(column, line,
						column + horizontalMoveLength,
						line));
				}

				if (verticalUp) {
					list.add(new Movement(column, line,
						column,
						line + verticalMoveLength));
				}

				if (verticalDown) {
					list.add(new Movement(column, line,
						column,
						line - verticalMoveLength));
				}
				if (diagonalUpperLeft) {
					list.add(new Movement(column, line,
						column - diagonalUpperLeftToLowerRightMoveLength,
						line + diagonalUpperLeftToLowerRightMoveLength));
				}
				if (diagonalUpperRight) {
					list.add(new Movement(column, line,
						column + diagonalLowerLeftToUpperRightMoveLength,
						line + diagonalLowerLeftToUpperRightMoveLength));
				}
				if (diagonalLowerLeft) {
					list.add(new Movement(column, line,
						column - diagonalLowerLeftToUpperRightMoveLength,
						line - diagonalLowerLeftToUpperRightMoveLength));
				}
				if (diagonalLowerRight) {
					list.add(new Movement(column, line,
						column + diagonalUpperLeftToLowerRightMoveLength,
						line - diagonalUpperLeftToLowerRightMoveLength));
				}
			}
		}

		return list;
	}
}
