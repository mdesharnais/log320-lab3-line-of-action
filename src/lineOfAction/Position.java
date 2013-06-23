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
}
