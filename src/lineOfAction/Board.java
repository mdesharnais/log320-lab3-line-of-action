package lineOfAction;

import java.util.Iterator;

public class Board implements Iterable<Pair<Player, Position>>, Comparable<Board> {
	// Strange, it seems require to use package visibility to let the iterator use this member.
	final Player[] data = new Player[64];

	public Board(char[] board) {
		for (int i = 0; i < board.length; ++i) {
			switch (board[i]) {
			case '0':
				this.data[i] = null;
				break;
			case '2':
				this.data[i] = Player.Black;
				break;
			case '4':
				this.data[i] = Player.White;
				break;
			default:
				throw new IllegalArgumentException("");
			}
		}
	}

	public Board(Board that, Movement movement) {
		int departureOffset = offset(movement.departure);
		int arrivalOffset = offset(movement.arrival);

		System.arraycopy(that.data, 0, this.data, 0, 64);
		this.data[arrivalOffset] = this.data[departureOffset];
		this.data[departureOffset] = null;
	}

	public Player get(Position p) {
		if (p.line == null || p.column == null) {
			return null;
		}

		return this.data[offset(p)];
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < this.data.length; ++i) {
			if (this.data[i] == null) {
				builder.append('.');
			} else if (this.data[i] == Player.Black) {
				builder.append('O');
			} else if (this.data[i] == Player.White) {
				builder.append('X');
			}

			if (i != 0 && i % 8 == 7) {
				builder.append('\n');
			} else {
				builder.append(' ');
			}
		}

		return builder.toString();
	}

	/**
	 * Traversal is in the following order A8, B8, C8 ... A7, B7, C7 ... F1, G1, H1
	 */
	@Override
	public Iterator<Pair<Player, Position>> iterator() {
		return new Iterator<Pair<Player, Position>>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				return this.index < Board.this.data.length;
			}

			@Override
			public Pair<Player, Position> next() {
				Column c = null;

				switch (this.index % 8) {
				case 0:
					c = Column.A;
					break;
				case 1:
					c = Column.B;
					break;
				case 2:
					c = Column.C;
					break;
				case 3:
					c = Column.D;
					break;
				case 4:
					c = Column.E;
					break;
				case 5:
					c = Column.F;
					break;
				case 6:
					c = Column.G;
					break;
				case 7:
					c = Column.H;
					break;
				}

				Line l = null;

				switch (this.index / 8) {
				case 0:
					l = Line.Eight;
					break;
				case 1:
					l = Line.Seven;
					break;
				case 2:
					l = Line.Six;
					break;
				case 3:
					l = Line.Five;
					break;
				case 4:
					l = Line.Four;
					break;
				case 5:
					l = Line.Three;
					break;
				case 6:
					l = Line.Two;
					break;
				case 7:
					l = Line.One;
					break;
				}

				return new Pair<Player, Position>(Board.this.data[this.index++], new Position(c, l));
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public int compareTo(Board that) {
		Iterator<Pair<Player, Position>> it1 = iterator();
		Iterator<Pair<Player, Position>> it2 = that.iterator();

		boolean it1HasNext = it1.hasNext();
		boolean it2HasNext = it2.hasNext();

		while ((it1HasNext = it1.hasNext()) && (it2HasNext = it2.hasNext())) {
			int result = it1.next().compareTo(it2.next());
			if (result != 0) {
				return result;
			}
		}

		if (it1HasNext && !it2HasNext) {
			return 1;
		}

		if (!it1HasNext && it2HasNext) {
			return -1;
		}

		return 0;
	}

	private static int offset(Position p) {
		return offset(p.line) * 8 + offset(p.column);
	}

	private static int offset(Column c) {
		switch (c) {
		case A:
			return 0;
		case B:
			return 1;
		case C:
			return 2;
		case D:
			return 3;
		case E:
			return 4;
		case F:
			return 5;
		case G:
			return 6;
		case H:
			return 7;
		default:
			return -1;
		}
	}

	private static int offset(Line l) {
		switch (l) {
		case One:
			return 7;
		case Two:
			return 6;
		case Three:
			return 5;
		case Four:
			return 4;
		case Five:
			return 3;
		case Six:
			return 2;
		case Seven:
			return 1;
		case Eight:
			return 0;
		default:
			return -1;
		}
	}
}
