package lineOfAction;

public class Movement {
	public static String toString(int movement) {
		return Column.toString((movement & 0xFF000000) >> 24) + Line.toString((movement & 0x00FF0000) >> 16)
			+ Column.toString((movement & 0x0000FF00) >> 8) + Line.toString(movement & 0x000000FF);
	}

	public static int makeMovement(int srcCol, int srcLine, int dstCol, int dstLine) {
		return (((srcCol & 0xFF) << 24)
			| ((srcLine & 0xFF) << 16)
			| ((dstCol & 0xFF) << 8)
			| (dstLine & 0xFF));
	}

	public static int makeMovement(String movement) {
		int srcColumn, srcLine, dstColumn, dstLine;

		String move = movement.trim().replaceAll(" ", "");
		String[] moves = move.split("-");

		srcColumn = Column.decode(moves[0].charAt(0));
		srcLine = Line.decode(moves[0].charAt(1));
		dstColumn = Column.decode(moves[1].charAt(0));
		dstLine = Line.decode(moves[1].charAt(1));

		return Movement.makeMovement(srcColumn, srcLine, dstColumn, dstLine);
	}
}
