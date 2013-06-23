package lineOfAction;

public class Board {
	private Player[] data = new Player[64];

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
}
