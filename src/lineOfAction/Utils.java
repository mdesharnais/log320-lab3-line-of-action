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
				int moveLength;
				// TODO Generate every movement that the player is allowed to
				// perform.

				// Vertical movements
				moveLength = 0;
				for (Line l : Line.values()) {
					if (board.get(new Position(pair.item2.column, l)) != null) {
						moveLength += 1;
					}
				}

				final int pos = pair.item2.line.ordinal();
				final int up = pos + moveLength;
				final int down = pos - moveLength;
				boolean upIsOk = (up <= 7);
				boolean downIsOk = (down >= 0);

				for (int i = 0; i < 7; ++i) {
					if (upIsOk && pos < i && i <= up) {
						Player p = board.get(new Position(pair.item2.column, Line.values()[i]));
						if (p != null) {
							// There is a checker between the current position
							// and the upper arrival position
							if (i == up) {
								if (p != player) {
									// Yes, we can capture an enemy checker
								} else {
									// No, we can not capture a friendly checker
									upIsOk = false;
								}
							} else {
								if (p != player) {
									// No, we can not jump over an enemy checker
									upIsOk = false;
								} else {
									// Yes, we can jump over a friendly checker
								}
							}
						}
					}

					if (downIsOk && up <= i && i < pos) {
						Player p = board.get(new Position(pair.item2.column, Line.values()[i]));
						if (p != null) {
							// There is a checker between the current position
							// and the upper arrival position
							if (i == down) {
								if (p != player) {
									// Yes, we can capture an enemy checker
								} else {
									// No, we can not capture a friendly checker
									downIsOk = false;
								}
							} else {
								if (p != player) {
									// No, we can not jump over an enemy checker
									downIsOk = false;
								} else {
									// Yes, we can jump over a friendly checker
								}
							}
						}
					}
				}

				if (upIsOk) {
					list.add(new Movement(pair.item2, new Position(pair.item2.column, Line.values()[up])));
				}

				if (downIsOk) {
					list.add(new Movement(pair.item2, new Position(pair.item2.column, Line.values()[down])));
				}

				// Horizontal movements

				moveLength = 0;
				for (Column c : Column.values()) {
					if (board.get(new Position(c, pair.item2.line)) != null) {
						moveLength += 1;
					}
				}

				final int columnPos = pair.item2.column.ordinal();
				final int right = columnPos + moveLength;
				final int left = columnPos - moveLength;
				boolean rightIsOk = (right <= 7);
				boolean leftIsOk = (left >= 0);

				for (int i = 0; i < 7; ++i) {
					Player p = board.get(new Position(Column.values()[i], pair.item2.line));
					if (rightIsOk && pos < i && i <= right) {
						if (p != null) {
							// There is a checker between the current position
							// and the upper arrival position
							if (i == right) {
								if (p != player) {
									// Yes, we can capture an enemy checker
								} else {
									// No, we can not capture a friendly checker
									rightIsOk = false;
								}
							} else {
								if (p != player) {
									// No, we can not jump over an enemy checker
									rightIsOk = false;
								} else {
									// Yes, we can jump over a friendly checker
								}
							}
						}
					}

					if (leftIsOk && left <= i && i < pos) {
						if (p != null) {
							// There is a checker between the current position
							// and the upper arrival position
							if (i == left) {
								if (p != player) {
									// Yes, we can capture an enemy checker
								} else {
									// No, we can not capture a friendly checker
									leftIsOk = false;
								}
							} else {
								if (p != player) {
									// No, we can not jump over an enemy checker
									leftIsOk = false;
								} else {
									// Yes, we can jump over a friendly checker
								}
							}
						}
					}
				}

				if (rightIsOk) {
					list.add(new Movement(pair.item2, new Position(Column.values()[right], pair.item2.line)));
				}

				if (leftIsOk) {
					list.add(new Movement(pair.item2, new Position(Column.values()[left], pair.item2.line)));
				}

				// Diagnoal movements
			}
		}

		return list;
	}
}
