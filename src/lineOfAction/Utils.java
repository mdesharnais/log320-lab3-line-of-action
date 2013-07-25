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

	public static long alphaBeta(long friends, long enemies, int depth) {
		//return alphaValue(friends, enemies, Long.MIN_VALUE, Long.MAX_VALUE, depth, 0);
		long alpha = Long.MIN_VALUE;
		long beta = Long.MAX_VALUE;

		int[] movements = generateMovements2(friends, enemies);
		long value = Long.MIN_VALUE;

		int m = 0;
		int index = 0;
		while ((m = movements[index++]) != 0) {
			int srcOffset = ((m & 0xFF000000) >> 24) + (((m & 0x00FF0000) >> 16) << 3);
			int dstOffset = ((m & 0x0000FF00) >> 8) + ((m & 0x000000FF) << 3);

			// Our friends is our child enemies
			long childFriends = enemies;
			long childEnemies = friends;

			// Clear our source position in our board
			childEnemies &= ~(0x1l << srcOffset);

			// Clear our destination position in enemies board
			childFriends &= ~(0x1l << dstOffset);

			// Set our destination position in our board
			childEnemies |= (0x1l << dstOffset);

			value = Math.max(value,
				betaValue(childFriends, childEnemies, alpha, beta, depth - 1, m));

			if (value >= beta) {
				return value;
			}
			alpha = Math.max(value, alpha);
		}

		return value;
	}

	public static long alphaValue(long friends, long enemies, long alpha, long beta, int depth, int movement) {
		int[] movements = null;

		if (depth == 0 || (movements = generateMovements2(friends, enemies)) == null) {
			long boardValue = Utils.evaluateBoard(friends, enemies);
			return ((boardValue << 32) & 0xFFFFFFFF00000000l) | (movement & 0x00000000FFFFFFFFl);
		}

		long value = Long.MIN_VALUE;

		int m = 0;
		int index = 0;
		while ((m = movements[index++]) != 0) {
			int srcOffset = ((m & 0xFF000000) >> 24) + (((m & 0x00FF0000) >> 16) << 3);
			int dstOffset = ((m & 0x0000FF00) >> 8) + ((m & 0x000000FF) << 3);

			// Our friends is our child enemies
			long childFriends = enemies;
			long childEnemies = friends;

			// Clear our source position in our board
			childEnemies &= ~(0x1l << srcOffset);

			// Clear our destination position in enemies board
			childFriends &= ~(0x1l << dstOffset);

			// Set our destination position in our board
			childEnemies |= (0x1l << dstOffset);

			value = Math.max(value,
				betaValue(childFriends, childEnemies, alpha, beta, depth - 1, movement));

			if (value >= beta) {
				return value;
			}
			alpha = Math.max(value, alpha);
		}

		return value;
	}

	private static long betaValue(long friends, long enemies, long alpha, long beta, int depth, int movement) {
		int[] movements = null;

		if (depth == 0 || (movements = generateMovements2(friends, enemies)) == null) {
			long boardValue = Utils.evaluateBoard(friends, enemies);
			return ((boardValue << 32) & 0xFFFFFFFF00000000l) | (movement & 0x00000000FFFFFFFFl);
		}

		long value = Long.MAX_VALUE;

		int m = 0;
		int index = 0;
		while ((m = movements[index++]) != 0) {
			int srcOffset = ((m & 0xFF000000) >> 24) + (((m & 0x00FF0000) >> 16) << 3);
			int dstOffset = ((m & 0x0000FF00) >> 8) + ((m & 0x000000FF) << 3);

			// Our friends is our child enemies
			long childFriends = enemies;
			long childEnemies = friends;

			// Clear our source position in our board
			childEnemies &= ~(0x1l << srcOffset);

			// Clear our destination position in enemies board
			childFriends &= ~(0x1l << dstOffset);

			// Set our destination position in our board
			childEnemies |= (0x1l << dstOffset);

			value = Math.min(value,
				alphaValue(childFriends, childEnemies, alpha, beta, depth - 1, movement));

			if (value <= alpha) {
				return value;
			}
			alpha = Math.min(value, beta);
		}

		return value;
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

		return index == 0 ? null : list;
	}

	public static int evaluateBoard(long friends, long enemies) {
		int friendCentralize = 0;
		int enemyCentralize = 0;

		int friendQuad = -Utils.checkQuads(friends);
		int enemyQuad = Utils.checkQuads(enemies);

		for (int i = 0; i < 64; ++i) {
			long offset = 0x1l << i;

			if ((friends & offset) != 0) {
				friendCentralize += pointss[i];
			}

			if ((enemies & offset) != 0) {
				enemyCentralize -= pointss[i];
			}
		}

		return friendCentralize + enemyCentralize + friendQuad + enemyQuad;

	}

	public static int checkQuads(long board) {
		int line = -1; //*8 + col
		long offset;
		boolean tLeft, tRight, bLeft, bRight;

		int eulerValue = 0;

		while (line < 8) {
			for (int j = -1; j < 8; ++j) {
				tLeft = false;
				tRight = false;
				bLeft = false;
				bRight = false;

				offset = line * 8 + j;

				if (j >= 0) {
					if (line >= 0) {
						bLeft = (board & (0x1l << offset)) != 0;
					}

					if (line < 7) {
						tLeft = (board & (0x1l << offset + 8)) != 0;
					}
				}

				if (j < 7) {
					if (line >= 0) {
						bRight = (board & (0x1l << offset + 1)) != 0;
					}

					if (line < 7) {
						tRight = (board & (0x1l << offset + 9)) != 0;
					}
				}

				//Check only one
				if (bLeft && !bRight && !tLeft && !tRight) {
					eulerValue += 25;
				} else if (!bLeft && bRight && !tLeft && !tRight) {
					eulerValue += 25;
				} else if (!bLeft && !bRight && tLeft && !tRight) {
					eulerValue += 25;
				} else if (!bLeft && !bRight && !tLeft && tRight) {
					eulerValue += 25;
				}

				//Check triple
				if (!bLeft && bRight && tLeft && tRight) {
					eulerValue += -25;
				} else if (bLeft && !bRight && tLeft && tRight) {
					eulerValue += -25;
				} else if (bLeft && bRight && !tLeft && tRight) {
					eulerValue += -25;
				} else if (bLeft && bRight && tLeft && !tRight) {
					eulerValue += -25;
				}

				//Check diagonal
				if (tLeft && bRight && !tRight && !bLeft) {
					eulerValue += -50;
				}
				if (!tLeft && !bRight && tRight && bLeft) {
					eulerValue += -50;
				}
			}

			++line;
		}

		return eulerValue / 100;
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

	public static int[] generateMovements2(long friends, long enemies) {
		int[] movements = generateMovements(friends, enemies);
		int index = 0;
		while (movements[index++] != 0) {
			// noop
		}

		if (index == 1) {
			return null;
		}

		return movements;
	}

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
