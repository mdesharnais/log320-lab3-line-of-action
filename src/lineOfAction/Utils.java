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
				// TODO Generate every movement that the player is allowed to
				// perform.

				// Vertical movements
				int moveLength = 0;
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

				// Diagnoal movements
			}
		}

		return list;
	}
}
