package lineOfAction;

public class Movement implements Comparable<Movement> {
	public final int sourceColumn;
	public final int sourceLine;
	public final int destinationColumn;
	public final int destinationLine;

	public Movement(int srcColumn, int srcLine, int dstColumn, int dstLine) {
		this.sourceColumn = srcColumn;
		this.sourceLine = srcLine;
		this.destinationColumn = dstColumn;
		this.destinationLine = dstLine;
	}

	@Override
	public String toString() {
		return Column.toString(this.sourceColumn) + Line.toString(this.sourceLine)
			+ Column.toString(this.destinationColumn) + Line.toString(this.destinationLine);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		final Movement other = (Movement) obj;

		return this.sourceColumn == other.sourceColumn
			&& this.sourceLine == other.sourceLine
			&& this.destinationColumn == other.destinationColumn
			&& this.destinationLine == other.destinationLine;
	}

	@Override
	public int hashCode() {
		int code = 0;

		code += this.sourceColumn;
		code += this.sourceLine;
		code += this.destinationColumn;
		code += this.destinationLine;

		return code;
	}

	@Override
	public int compareTo(Movement other) {
		if (other == null) {
			return 1;
		}

		int result = this.sourceColumn - other.sourceColumn;

		if (result == 0) {
			result = this.sourceLine - other.sourceLine;
		}

		if (result == 0) {
			result = this.destinationColumn - other.destinationColumn;
		}

		if (result == 0) {
			result = this.destinationLine - other.destinationLine;
		}

		return result;
	}

	public static Movement decode(String movement) {
		int srcColumn, srcLine, dstColumn, dstLine;

		String move = movement.trim().replaceAll(" ", "");
		String[] moves = move.split("-");

		srcColumn = Column.decode(moves[0].charAt(0));
		srcLine = Line.decode(moves[0].charAt(1));
		dstColumn = Column.decode(moves[1].charAt(0));
		dstLine = Line.decode(moves[1].charAt(1));

		return new Movement(srcColumn, srcLine, dstColumn, dstLine);
	}
}
