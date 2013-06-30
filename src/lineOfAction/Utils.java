package lineOfAction;

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

	public static Movement[] generateMovements(int[] board, int player) {
		// Yes, this function break the encapsulation of Board but, OMG encapsulation is so costly in Java...

		Movement[] list = new Movement[144]; // (12 white + 12 black) * 6 directions == 144 possible movements
		int index = 0;
		int[] horizontalMoveLengthArray = new int[8];
		int[] verticalMoveLengthArray = new int[8];

		// Pre-calculate the movement length for every line/column
		for (int i = 0; i < 64; ++i) {
			if (board[i] != 0) {
				horizontalMoveLengthArray[(i >> 3)] += 1;
				verticalMoveLengthArray[i & 0x7] += 1;
			}
		}

		for (int i = 0; i < 64; ++i) {
			if (board[i] != 0 && board[i] == player) {
				int column = i & 0x7;
				int line = (i >> 3);

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

					boolean leftColOk = leftCol >= 0;
					boolean rightColOk = rightCol < 8;
					boolean upLineOk = upLine < 8;
					boolean downLineOk = downLine >= 0;

					if (upLineOk) {
						// Diagonal upper left
						if (leftColOk && board[(upLine << 3) + leftCol] != 0) {
							diagonalUpperLeftToLowerRightMoveLength += 1;
						}

						// Diagonal upper right
						if (rightColOk && board[(upLine << 3) + rightCol] != 0) {
							diagonalLowerLeftToUpperRightMoveLength += 1;
						}
					}

					if (downLineOk) {
						// Diagonal lower left
						if (leftColOk && board[(downLine << 3) + leftCol] != 0) {
							diagonalLowerLeftToUpperRightMoveLength += 1;
						}

						// Diagonal lower right
						if (rightColOk && board[(downLine << 3) + rightCol] != 0) {
							diagonalUpperLeftToLowerRightMoveLength += 1;
						}
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
						int p = board[(line << 3) + leftCol];
						if (p != 0) {
							horizontalLeft = (p == player) ^ (j == horizontalMoveLength);
						}
					}

					if (horizontalRight && j <= horizontalMoveLength) {
						int p = board[(line << 3) + rightCol];
						if (p != 0) {
							horizontalRight = (p == player) ^ (j == horizontalMoveLength);
						}
					}

					if (verticalUp && j <= verticalMoveLength) {
						int p = board[(upLine << 3) + column];
						if (p != 0) {
							verticalUp = (p == player) ^ (j == verticalMoveLength);
						}
					}

					if (verticalDown && j <= verticalMoveLength) {
						int p = board[(downLine << 3) + column];
						if (p != 0) {
							verticalDown = (p == player) ^ (j == verticalMoveLength);
						}
					}

					if (diagonalUpperLeft && j <= diagonalUpperLeftToLowerRightMoveLength) {
						int p = board[(upLine << 3) + leftCol];
						if (p != 0) {
							diagonalUpperLeft = (p == player)
								^ (j == diagonalUpperLeftToLowerRightMoveLength);
						}
					}

					if (diagonalUpperRight && j <= diagonalLowerLeftToUpperRightMoveLength) {
						int p = board[(upLine << 3) + rightCol];
						if (p != 0) {
							diagonalUpperRight = (p == player)
								^ (j == diagonalLowerLeftToUpperRightMoveLength);
						}
					}

					if (diagonalLowerLeft && j <= diagonalLowerLeftToUpperRightMoveLength) {
						int p = board[(downLine << 3) + leftCol];
						if (p != 0) {
							diagonalLowerLeft = (p == player)
								^ (j == diagonalLowerLeftToUpperRightMoveLength);
						}
					}

					if (diagonalLowerRight && j <= diagonalUpperLeftToLowerRightMoveLength) {
						int p = board[(downLine << 3) + rightCol];
						if (p != 0) {
							diagonalLowerRight = (p == player)
								^ (j == diagonalUpperLeftToLowerRightMoveLength);
						}
					}
				}

				if (horizontalLeft) {
					list[index++] = new Movement(column, line,
						column - horizontalMoveLength,
						line);
				}

				if (horizontalRight) {
					list[index++] = new Movement(column, line,
						column + horizontalMoveLength,
						line);
				}

				if (verticalUp) {
					list[index++] = new Movement(column, line,
						column,
						line + verticalMoveLength);
				}

				if (verticalDown) {
					list[index++] = new Movement(column, line,
						column,
						line - verticalMoveLength);
				}
				if (diagonalUpperLeft) {
					list[index++] = new Movement(column, line,
						column - diagonalUpperLeftToLowerRightMoveLength,
						line + diagonalUpperLeftToLowerRightMoveLength);
				}
				if (diagonalUpperRight) {
					list[index++] = new Movement(column, line,
						column + diagonalLowerLeftToUpperRightMoveLength,
						line + diagonalLowerLeftToUpperRightMoveLength);
				}
				if (diagonalLowerLeft) {
					list[index++] = new Movement(column, line,
						column - diagonalLowerLeftToUpperRightMoveLength,
						line - diagonalLowerLeftToUpperRightMoveLength);
				}
				if (diagonalLowerRight) {
					list[index++] = new Movement(column, line,
						column + diagonalUpperLeftToLowerRightMoveLength,
						line - diagonalUpperLeftToLowerRightMoveLength);
				}
			}
		}

		return list;
	}
}
