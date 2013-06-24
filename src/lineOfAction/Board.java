package lineOfAction;

import java.util.Iterator;

public class Board implements Iterable<Pair<Player, Position>> {
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

	public Player get(Position p) {
		int xoffset = 0;

		switch (p.line) {
		case One:
			xoffset = 7;
			break;
		case Two:
			xoffset = 6;
			break;
		case Three:
			xoffset = 5;
			break;
		case Four:
			xoffset = 4;
			break;
		case Five:
			xoffset = 3;
			break;
		case Six:
			xoffset = 2;
			break;
		case Seven:
			xoffset = 1;
			break;
		case Eight:
			xoffset = 0;
			break;
		}

		int yoffset = 0;

		switch (p.column) {
		case A:
			yoffset = 0;
			break;
		case B:
			yoffset = 1;
			break;
		case C:
			yoffset = 2;
			break;
		case D:
			yoffset = 3;
			break;
		case E:
			yoffset = 4;
			break;
		case F:
			yoffset = 5;
			break;
		case G:
			yoffset = 6;
			break;
		case H:
			yoffset = 7;
			break;
		}

		return this.data[xoffset * 8 + yoffset];
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
}
