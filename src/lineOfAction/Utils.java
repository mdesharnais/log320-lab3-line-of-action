package lineOfAction;

public class Utils {

	// My eyes are bleeding, this is global state!!!
	public static final int[][] points = {
		{ 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 1, 1, 1, 1, 1, 1, 0 },
		{ 0, 1, 2, 2, 2, 2, 1, 0 },
		{ 0, 1, 2, 3, 3, 2, 1, 0 },
		{ 0, 1, 2, 3, 3, 2, 1, 0 },
		{ 0, 1, 2, 2, 2, 2, 1, 0 },
		{ 0, 1, 1, 1, 1, 1, 1, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0 } };

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

	public static int[] generateMovements(long friends, long enemies) {
		int[] list = new int[96]; // 12 checkers * 8 directions == 72 possible movements
		int index = 0;
		int[] horizontalMoveLengthArray = new int[8];
		int[] verticalMoveLengthArray = new int[8];
		int[] diagonalUpperLeftToLowerRightArray = new int[15];
		int[] diagonalLowerLeftToUpperRightArray = new int[15];

		// Pre-calculate the movement length for every line/column
		for (int i = 0; i < 64; ++i) {
			long offset = 0x1l << i;
			int n = 0;

			if ((friends & offset) != 0) {
				++n;
			}

			if ((enemies & offset) != 0) {
				++n;
			}

			if (n != 0) {
				int column = i & 0x7;
				int line = i >> 3;
				horizontalMoveLengthArray[line] += n;
				verticalMoveLengthArray[column] += n;
				diagonalUpperLeftToLowerRightArray[column + line] += n;
				diagonalLowerLeftToUpperRightArray[7 + (column - line)] += n;
			}
		}

		for (int i = 0; i < 64; ++i) {
			if ((friends & (0x1l << i)) != 0) {
				int column = i & 0x7;
				int line = (i >> 3);

				int horizontalMoveLength = horizontalMoveLengthArray[line];
				int verticalMoveLength = verticalMoveLengthArray[column];
				int diagonalUpperLeftToLowerRightMoveLength = diagonalUpperLeftToLowerRightArray[line
					+ column];
				int diagonalLowerLeftToUpperRightMoveLength = diagonalLowerLeftToUpperRightArray[7 + (column - line)];

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
				// We loop while there is one direction that could potentially be a movement
				for (int j = 1; j < 8
					&& (horizontalLeft
						|| horizontalRight
						|| verticalUp
						|| verticalDown
						|| diagonalUpperLeft
						|| diagonalUpperRight
						|| diagonalLowerLeft
						|| diagonalLowerRight); ++j) {

					int leftCol = column - j;
					int rightCol = column + j;
					int upLine = line + j;
					int downLine = line - j;

					if (horizontalLeft) {
						if (j < horizontalMoveLength) {
							horizontalLeft = (enemies & (0x1l << ((line << 3) + leftCol))) == 0;
						} else if (j == horizontalMoveLength) {
							horizontalLeft = (friends & (0x1l << ((line << 3) + leftCol))) == 0;
						}
					}

					if (horizontalRight) {
						if (j < horizontalMoveLength) {
							horizontalRight = (enemies & (0x1l << ((line << 3) + rightCol))) == 0;
						} else if (j == horizontalMoveLength) {
							horizontalRight = (friends & (0x1l << ((line << 3) + rightCol))) == 0;
						}
					}

					if (verticalUp) {
						if (j < verticalMoveLength) {
							verticalUp = (enemies & (0x1l << ((upLine << 3) + column))) == 0;
						} else if (j == verticalMoveLength) {
							verticalUp = (friends & (0x1l << ((upLine << 3) + column))) == 0;
						}
					}

					if (verticalDown) {
						if (j < verticalMoveLength) {
							verticalDown = (enemies & (0x1l << ((downLine << 3) + column))) == 0;
						} else if (j == verticalMoveLength) {
							verticalDown = (friends & (0x1l << ((downLine << 3) + column))) == 0;
						}
					}

					if (diagonalUpperLeft) {
						if (j < diagonalUpperLeftToLowerRightMoveLength) {
							diagonalUpperLeft = (enemies & (0x1l << ((upLine << 3) + leftCol))) == 0;
						} else if (j == diagonalUpperLeftToLowerRightMoveLength) {
							diagonalUpperLeft = (friends & (0x1l << ((upLine << 3) + leftCol))) == 0;
						}
					}

					if (diagonalUpperRight) {
						if (j < diagonalLowerLeftToUpperRightMoveLength) {
							diagonalUpperRight = (enemies & (0x1l << ((upLine << 3) + rightCol))) == 0;
						} else if (j == diagonalLowerLeftToUpperRightMoveLength) {
							diagonalUpperRight = (friends & (0x1l << ((upLine << 3) + rightCol))) == 0;
						}
					}

					if (diagonalLowerLeft) {
						if (j < diagonalLowerLeftToUpperRightMoveLength) {
							diagonalLowerLeft = (enemies & (0x1l << ((downLine << 3) + leftCol))) == 0;
						} else if (j == diagonalLowerLeftToUpperRightMoveLength) {
							diagonalLowerLeft = (friends & (0x1l << ((downLine << 3) + leftCol))) == 0;
						}
					}

					if (diagonalLowerRight && j <= diagonalUpperLeftToLowerRightMoveLength) {
						if (j < diagonalUpperLeftToLowerRightMoveLength) {
							diagonalLowerRight = (enemies & (0x1l << ((downLine << 3) + rightCol))) == 0;
						} else if (j == diagonalUpperLeftToLowerRightMoveLength) {
							diagonalLowerRight = (friends & (0x1l << ((downLine << 3) + rightCol))) == 0;
						}
					}
				}

				if (horizontalLeft) {
					list[index++] = (((column & 0xFF) << 24) | ((line & 0xFF) << 16)
						| ((column - horizontalMoveLength & 0xFF) << 8) | (line & 0xFF));
				}
				if (horizontalRight) {
					list[index++] = (((column & 0xFF) << 24) | ((line & 0xFF) << 16)
						| ((column + horizontalMoveLength & 0xFF) << 8) | (line & 0xFF));
				}
				if (verticalUp) {
					list[index++] = (((column & 0xFF) << 24) | ((line & 0xFF) << 16) | ((column & 0xFF) << 8) | (line
						+ verticalMoveLength & 0xFF));
				}
				if (verticalDown) {
					list[index++] = (((column & 0xFF) << 24) | ((line & 0xFF) << 16) | ((column & 0xFF) << 8) | (line
						- verticalMoveLength & 0xFF));
				}
				if (diagonalUpperLeft) {
					list[index++] = (((column & 0xFF) << 24) | ((line & 0xFF) << 16)
						| ((column - diagonalUpperLeftToLowerRightMoveLength & 0xFF) << 8) | (line
						+ diagonalUpperLeftToLowerRightMoveLength & 0xFF));
				}
				if (diagonalUpperRight) {
					list[index++] = (((column & 0xFF) << 24) | ((line & 0xFF) << 16)
						| ((column + diagonalLowerLeftToUpperRightMoveLength & 0xFF) << 8) | (line
						+ diagonalLowerLeftToUpperRightMoveLength & 0xFF));
				}
				if (diagonalLowerLeft) {
					list[index++] = (((column & 0xFF) << 24) | ((line & 0xFF) << 16)
						| ((column - diagonalLowerLeftToUpperRightMoveLength & 0xFF) << 8) | (line
						- diagonalLowerLeftToUpperRightMoveLength & 0xFF));
				}
				if (diagonalLowerRight) {
					list[index++] = (((column & 0xFF) << 24) | ((line & 0xFF) << 16)
						| ((column + diagonalUpperLeftToLowerRightMoveLength & 0xFF) << 8) | (line
						- diagonalUpperLeftToLowerRightMoveLength & 0xFF));
				}
			}
		}
		return list;
	}

	public static int evaluateBoard(long friends, long enemies) {
		int value = 0;

		for (int i = 0; i < 64; ++i) {
			long offset = 0x1l << i;

			if ((friends & offset) != 0) {
				value += pointss[i];
			}

			if ((enemies & offset) != 0) {
				value -= pointss[i];
			}
		}

		return value;

	}

	public static int checkQuads(long board) {
		int line = -1; //*8 + col
		long offset;
		boolean tLeft, tRight, bLeft, bRight;
		int n = 0;
		while (line < 8) {
			for (int j = -1; j < 8; ++j) {
				tLeft = false;
				tRight = false;
				bLeft = false;
				bRight = false;

				offset = line * 8 + j;

				if (j >= 0) {
					bLeft = (board & (0x1l << offset)) != 0;
					tLeft = (board & (0x1l << offset + 8)) != 0;
				}

				if (j < 8) {
					bRight = (board & (0x1l << offset + 1)) != 0;
					tRight = (board & (0x1l << offset + 9)) != 0;
				}

				++n;
				System.out.printf("line: %d\n", line);
				System.out.printf("%b %b\n", tLeft, tRight);
				System.out.printf("%b %b\n\n", bLeft, bRight);
			}

			++line;
		}

		System.out.printf("n=%d", n);

		return 0;
	}

	public static final int[] pointss = {
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 1, 1, 1, 1, 1, 1, 0,
		0, 1, 2, 2, 2, 2, 1, 0,
		0, 1, 2, 3, 3, 2, 1, 0,
		0, 1, 2, 3, 3, 2, 1, 0,
		0, 1, 2, 2, 2, 2, 1, 0,
		0, 1, 1, 1, 1, 1, 1, 0,
		0, 0, 0, 0, 0, 0, 0, 0
	};

	/*
		public static int evaluateBoard(long friends, long enemies) {
			int friendlyValue = 0;
			int ennemyValue = 0;
			int centralisationValue = 0;
			int quadValue = 0;

			for (int i = 0; i < 64; ++i) {
				long offset = 0x1l << i;
				int column = -1;
				int line = -1;

				if ((friends & offset) != 0) { // Permet de savoir s'il y a un pion ami à l'offset i
					column = i & 0x7; // Ici on obtient la colonne correspondant à l'offset i
					line = i >> 3; // Ici on obtient la ligne correspondante à l'offset i
					friendlyValue += points[line][column];
				}

				if ((enemies & offset) != 0) { // Permet de savoir s'il y a un pion ennemi à l'offset i
					column = i & 0x7; // Ici on obtient la colonne correspondant à l'offset i
					line = i >> 3; // Ici on obtient la ligne correspondante à l'offset i
					ennemyValue += points[line][column];
				}
			}

			//centralisationValue = friendlyValue - ennemyValue;

			return friendlyValue - ennemyValue;
		}

		public static int evaluateBoard(int[] board, int player) {
			int friendlyValue = 0;
			int ennemyValue = 0;
			for (int i = 0; i < board.length; ++i) {
				if (board[i] != 0) {
					for (int j = 0; j < points.length; ++j) {
						if (Arrays.binarySearch(points[j], i) >= 0) {
							if (board[i] == player) {
								friendlyValue += j;
							} else {
								ennemyValue += j;
							}
							break;
						}
					}
				}
			}

			return friendlyValue - ennemyValue;
		}
	*/
}
