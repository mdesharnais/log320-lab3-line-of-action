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

	public static Iterable<Movement> generateMovements(Board board, Player player) {
		List<Movement> list = new ArrayList<Movement>(64);

		for (Triplet<Player, Integer, Integer> tuple : board) {
			if (tuple.item1 != null && tuple.item1 == player) {
				int horizontalMoveLength = 1;
				int verticalMoveLength = 1;
				int diagonalUpperLeftToLowerRightMoveLength = 1;
				int diagonalLowerLeftToUpperRightMoveLength = 1;

				// Initial position
				int column = tuple.item2;
				int line = tuple.item3;

				// First, we make a pass to count the length of every moves
				for (int i = 1; i < 8; ++i) {
					int leftCol = column - i;
					int rightCol = column + i;
					int upLine = line + i;
					int downLine = line - i;

					// Horizontal left
					if (board.get(leftCol, line) != null) {
						horizontalMoveLength += 1;
					}

					// Horizontal right
					if (board.get(rightCol, line) != null) {
						horizontalMoveLength += 1;
					}

					// Vertical up
					if (board.get(column, upLine) != null) {
						verticalMoveLength += 1;
					}

					// Vertical down
					if (board.get(column, downLine) != null) {
						verticalMoveLength += 1;
					}

					// Diagonal upper left
					if (board.get(leftCol, upLine) != null) {
						diagonalUpperLeftToLowerRightMoveLength += 1;
					}

					// Diagonal upper right
					if (board.get(rightCol, upLine) != null) {
						diagonalLowerLeftToUpperRightMoveLength += 1;
					}

					// Diagonal lower left
					if (board.get(leftCol, downLine) != null) {
						diagonalLowerLeftToUpperRightMoveLength += 1;
					}

					// Diagonal lower right
					if (board.get(rightCol, downLine) != null) {
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
				for (int i = 1; i < 8; ++i) {
					int leftCol = column - i;
					int rightCol = column + i;
					int upLine = line + i;
					int downLine = line - i;

					if (horizontalLeft && i <= horizontalMoveLength) {
						horizontalLeft = isLegalMove(board, player, leftCol, line,
							i == horizontalMoveLength);
					}

					if (horizontalRight && i <= horizontalMoveLength) {
						horizontalRight = isLegalMove(board, player, rightCol, line,
							i == horizontalMoveLength);
					}

					if (verticalUp && i <= verticalMoveLength) {
						verticalUp = isLegalMove(board, player, column, upLine,
							i == verticalMoveLength);
					}

					if (verticalDown && i <= verticalMoveLength) {
						verticalDown = isLegalMove(board, player, column, downLine,
							i == verticalMoveLength);
					}

					if (diagonalUpperLeft && i <= diagonalUpperLeftToLowerRightMoveLength) {
						diagonalUpperLeft = isLegalMove(board, player, leftCol, upLine,
							i == diagonalUpperLeftToLowerRightMoveLength);
					}

					if (diagonalUpperRight && i <= diagonalLowerLeftToUpperRightMoveLength) {
						diagonalUpperRight = isLegalMove(board, player, rightCol, upLine,
							i == diagonalLowerLeftToUpperRightMoveLength);
					}

					if (diagonalLowerLeft && i <= diagonalLowerLeftToUpperRightMoveLength) {
						diagonalLowerLeft = isLegalMove(board, player, leftCol, downLine,
							i == diagonalLowerLeftToUpperRightMoveLength);
					}

					if (diagonalLowerRight && i <= diagonalUpperLeftToLowerRightMoveLength) {
						diagonalLowerRight = isLegalMove(board, player, rightCol, downLine,
							i == diagonalUpperLeftToLowerRightMoveLength);
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

	private static boolean isLegalMove(Board b, Player p, int column, int line, boolean isFinalDst) {
		Player currentPlayer = b.get(column, line);
		if (currentPlayer == null) {
			return true;
		}

		if (isFinalDst) {
			if (currentPlayer == p) {
				// We can not capture a friendly checker
				return false;
			} else {
				// We can capture an enemy checker
			}
		} else {
			if (currentPlayer == p) {
				// We can jump over a friendly checker
			} else {
				// We can not jump over an enemy checker
				return false;
			}
		}

		return true;
	}

	public static Player nextPlayer(Player player) {
		switch (player) {
		case Black:
			return Player.White;
		case White:
			return Player.Black;
		default:
			return null;
		}
	}
}
