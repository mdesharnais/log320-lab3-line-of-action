package lineOfAction;

public class Position {
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
}
