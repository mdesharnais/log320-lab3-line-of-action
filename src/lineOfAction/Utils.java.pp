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

	#define PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(i) if (board[i] != 0) { ++horizontalMoveLengthArray[(i >> 3)]; ++verticalMoveLengthArray[i & 0x7]; }

	public static Movement[] generateMovements(int[] board, int player) {
		// Yes, this function break the encapsulation of Board but, OMG encapsulation is so costly in Java...

		Movement[] list = new Movement[144]; // (12 white + 12 black) * 6 directions == 144 possible movements
		int index = 0;
		int[] horizontalMoveLengthArray = new int[8];
		int[] verticalMoveLengthArray = new int[8];

		// Pre-calculate the movement length for every line/column
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(0);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(1);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(2);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(3);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(4);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(5);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(6);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(7);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(8);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(9);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(10);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(11);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(12);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(13);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(14);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(15);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(16);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(17);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(18);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(19);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(20);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(21);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(22);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(23);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(24);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(25);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(26);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(27);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(28);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(29);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(30);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(31);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(32);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(33);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(34);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(35);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(36);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(37);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(38);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(39);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(40);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(41);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(42);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(43);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(44);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(45);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(46);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(47);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(48);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(49);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(50);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(51);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(52);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(53);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(54);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(55);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(56);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(57);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(58);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(59);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(60);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(61);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(62);
		PRE_CALCULATE_HORIZONTAL_AND_VERTICAL_LENGTH(63);

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
