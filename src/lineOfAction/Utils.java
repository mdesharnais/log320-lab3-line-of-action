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
		List<Movement> list = new ArrayList<Movement>();

		for (Pair<Player, Position> pair : board) {
			if (pair.item1 != null && pair.item1 == player) {
				int horizontalMoveLength = 1;
				int verticalMoveLength = 1;
				int diagonalUpperLeftToLowerRightMoveLength = 1;
				int diagonalLowerLeftToUpperRightMoveLength = 1;

				// Initial position
				int x = pair.item2.column.ordinal();
				int y = pair.item2.line.ordinal();
				Position source = pair.item2;

				// First, we make a pass to count the length of every moves
				for (int i = 1; i < 8; ++i) {
					Column leftCol = x - i >= 0 ? Column.values()[x - i] : null;
					Column rightCol = x + i < 8 ? Column.values()[x + i] : null;
					Line upLine = y + i < 8 ? Line.values()[y + i] : null;
					Line downLine = y - i >= 0 ? Line.values()[y - i] : null;

					// Horizontal left
					if (board.get(new Position(leftCol, source.line)) != null) {
						horizontalMoveLength += 1;
					}

					// Horizontal right
					if (board.get(new Position(rightCol, source.line)) != null) {
						horizontalMoveLength += 1;
					}

					// Vertical up
					if (board.get(new Position(source.column, upLine)) != null) {
						verticalMoveLength += 1;
					}

					// Vertical down
					if (board.get(new Position(source.column, downLine)) != null) {
						verticalMoveLength += 1;
					}

					// Diagonal upper left
					if (board.get(new Position(leftCol, upLine)) != null) {
						diagonalUpperLeftToLowerRightMoveLength += 1;
					}

					// Diagonal upper right
					if (board.get(new Position(rightCol, upLine)) != null) {
						diagonalLowerLeftToUpperRightMoveLength += 1;
					}

					// Diagonal lower left
					if (board.get(new Position(leftCol, downLine)) != null) {
						diagonalLowerLeftToUpperRightMoveLength += 1;
					}

					// Diagonal lower right
					if (board.get(new Position(rightCol, downLine)) != null) {
						diagonalUpperLeftToLowerRightMoveLength += 1;
					}
				}

				boolean horizontalLeft = (x - horizontalMoveLength) >= 0;
				boolean horizontalRight = (x + horizontalMoveLength) < 8;
				boolean verticalUp = (y + verticalMoveLength) < 8;
				boolean verticalDown = (y - verticalMoveLength) >= 0;
				boolean diagonalUpperLeft = (x - diagonalUpperLeftToLowerRightMoveLength) >= 0
					&& (y + diagonalUpperLeftToLowerRightMoveLength) < 8;
				boolean diagonalUpperRight = (x + diagonalLowerLeftToUpperRightMoveLength) < 8
					&& (y + diagonalLowerLeftToUpperRightMoveLength) < 8;
				boolean diagonalLowerLeft = (x - diagonalLowerLeftToUpperRightMoveLength) >= 0
					&& (y - diagonalLowerLeftToUpperRightMoveLength) >= 0;
				boolean diagonalLowerRight = (x + diagonalUpperLeftToLowerRightMoveLength) < 8
					&& (y - diagonalUpperLeftToLowerRightMoveLength) >= 0;

				// Second, we make a pass to generate the moves
				for (int i = 1; i < 8; ++i) {
					Column leftCol = x - i >= 0 ? Column.values()[x - i] : null;
					Column rightCol = x + i < 8 ? Column.values()[x + i] : null;
					Line upLine = y + i < 8 ? Line.values()[y + i] : null;
					Line downLine = y - i >= 0 ? Line.values()[y - i] : null;

					if (horizontalLeft && i <= horizontalMoveLength) {
						horizontalLeft = isLegalMove(board, player,
							new Position(leftCol, source.line),
							i == horizontalMoveLength);
					}

					if (horizontalRight && i <= horizontalMoveLength) {
						horizontalRight = isLegalMove(board, player,
							new Position(rightCol, source.line),
							i == horizontalMoveLength);
					}

					if (verticalUp && i <= verticalMoveLength) {
						verticalUp = isLegalMove(board, player,
							new Position(source.column, upLine),
							i == verticalMoveLength);
					}

					if (verticalDown && i <= verticalMoveLength) {
						verticalDown = isLegalMove(board, player,
							new Position(source.column, downLine),
							i == verticalMoveLength);
					}

					if (diagonalUpperLeft && i <= diagonalUpperLeftToLowerRightMoveLength) {
						diagonalUpperLeft = isLegalMove(board, player,
							new Position(leftCol, upLine),
							i == diagonalUpperLeftToLowerRightMoveLength);
					}

					if (diagonalUpperRight && i <= diagonalLowerLeftToUpperRightMoveLength) {
						diagonalUpperRight = isLegalMove(board, player,
							new Position(rightCol, upLine),
							i == diagonalLowerLeftToUpperRightMoveLength);
					}

					if (diagonalLowerLeft && i <= diagonalLowerLeftToUpperRightMoveLength) {
						diagonalLowerLeft = isLegalMove(board, player,
							new Position(leftCol, downLine),
							i == diagonalLowerLeftToUpperRightMoveLength);
					}

					if (diagonalLowerRight && i <= diagonalUpperLeftToLowerRightMoveLength) {
						diagonalLowerRight = isLegalMove(board, player,
							new Position(rightCol, downLine),
							i == diagonalUpperLeftToLowerRightMoveLength);
					}
				}

				if (horizontalLeft) {
					list.add(new Movement(source,
						new Position(
							Column.values()[x - horizontalMoveLength],
							source.line)));
				}

				if (horizontalRight) {
					list.add(new Movement(source,
						new Position(
							Column.values()[x + horizontalMoveLength],
							source.line)));
				}

				if (verticalUp) {
					list.add(new Movement(source,
						new Position(
							source.column,
							Line.values()[y + verticalMoveLength])));
				}

				if (verticalDown) {
					list.add(new Movement(source,
						new Position(
							source.column,
							Line.values()[y - verticalMoveLength])));
				}
				if (diagonalUpperLeft) {
					list.add(new Movement(source,
						new Position(
							Column.values()[x - diagonalUpperLeftToLowerRightMoveLength],
							Line.values()[y + diagonalUpperLeftToLowerRightMoveLength])));
				}
				if (diagonalUpperRight) {
					list.add(new Movement(source,
						new Position(
							Column.values()[x + diagonalLowerLeftToUpperRightMoveLength],
							Line.values()[y + diagonalLowerLeftToUpperRightMoveLength])));
				}
				if (diagonalLowerLeft) {
					list.add(new Movement(source,
						new Position(
							Column.values()[x - diagonalLowerLeftToUpperRightMoveLength],
							Line.values()[y - diagonalLowerLeftToUpperRightMoveLength])));
				}
				if (diagonalLowerRight) {
					list.add(new Movement(source,
						new Position(
							Column.values()[x + diagonalUpperLeftToLowerRightMoveLength],
							Line.values()[y - diagonalUpperLeftToLowerRightMoveLength])));
				}
			}
		}

		return list;
	}

	private static boolean isLegalMove(Board board, Player player, Position arrival, boolean isFinalDst) {
		Player p = board.get(arrival);
		if (p == null) {
			return true;
		}

		if (isFinalDst) {
			if (p == player) {
				// We can not capture a friendly checker
				return false;
			} else {
				// We can capture an enemy checker
			}
		} else {
			if (p == player) {
				// We can jump over a friendly checker
			} else {
				// We can not jump over an enemy checker
				return false;
			}
		}

		return true;
	}
}
