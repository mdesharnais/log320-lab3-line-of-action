package lineOfAction;

public class Position implements Comparable<Position> {
	public final Line line;
	public final Column column;

	public Position(Column column, Line line) {
		this.line = line;
		this.column = column;
	}

	@Override
	public String toString() {
		return this.column.toString() + this.line.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		final Position other = (Position) obj;

		return Utils.equals(this.column, other.column) && Utils.equals(this.line, other.line);
	}

	@Override
	public int hashCode() {
		int code = 0;

		if (this.column != null) {
			code += this.column.hashCode();
		}

		if (this.line != null) {
			code += this.line.hashCode();
		}

		return code;
	}

	@Override
	public int compareTo(Position other) {
		if (other == null) {
			return 1;
		}

		int result = Utils.compareTo(this.line, other.line);

		if (result == 0) {
			result = Utils.compareTo(this.column, other.column);
		}

		return result;
	}
}
