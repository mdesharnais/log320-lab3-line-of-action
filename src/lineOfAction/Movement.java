package lineOfAction;

public class Movement implements Comparable<Movement> {
	public final Column sourceColumn;
	public final Line sourceLine;
	public final Column destinationColumn;
	public final Line destinationLine;

	public Movement(Column srcColumn, Line srcLine, Column dstColumn, Line dstLine) {
		this.sourceColumn = srcColumn;
		this.sourceLine = srcLine;
		this.destinationColumn = dstColumn;
		this.destinationLine = dstLine;
	}

	@Override
	public String toString() {
		return this.sourceColumn.toString() + this.sourceLine.toString()
			+ this.destinationColumn.toString() + this.destinationLine.toString();
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

		return Utils.equals(this.sourceColumn, other.sourceColumn)
			&& Utils.equals(this.sourceLine, other.sourceLine)
			&& Utils.equals(this.destinationColumn, other.destinationColumn)
			&& Utils.equals(this.destinationLine, other.destinationLine);
	}

	@Override
	public int hashCode() {
		int code = 0;

		if (this.sourceColumn != null) {
			code += this.sourceColumn.hashCode();
		}

		if (this.sourceLine != null) {
			code += this.sourceLine.hashCode();
		}

		if (this.destinationColumn != null) {
			code += this.destinationColumn.hashCode();
		}

		if (this.destinationLine != null) {
			code += this.destinationLine.hashCode();
		}

		return code;
	}

	@Override
	public int compareTo(Movement other) {
		if (other == null) {
			return 1;
		}

		int result = Utils.compareTo(this.sourceColumn, other.sourceColumn);

		if (result == 0) {
			result = Utils.compareTo(this.sourceLine, other.sourceLine);
		}

		if (result == 0) {
			result = Utils.compareTo(this.destinationColumn, other.destinationColumn);
		}

		if (result == 0) {
			result = Utils.compareTo(this.destinationLine, other.destinationLine);
		}

		return result;
	}
}
