package lineOfAction;

public class Line {
	public static final int One = 0;
	public static final int Two = 1;
	public static final int Three = 2;
	public static final int Four = 3;
	public static final int Five = 4;
	public static final int Six = 5;
	public static final int Seven = 6;
	public static final int Eight = 7;

	public static String toString(int line) {
		switch (line) {
		case Line.One:
			return "1";
		case Line.Two:
			return "2";
		case Line.Three:
			return "3";
		case Line.Four:
			return "4";
		case Line.Five:
			return "5";
		case Line.Six:
			return "6";
		case Line.Seven:
			return "7";
		case Line.Eight:
			return "8";
		default:
			return "?";
		}
	}

	public static int decode(char line) {
		switch (line) {
		case '8':
			return 7;
		case '7':
			return 6;
		case '6':
			return 5;
		case '5':
			return 4;
		case '4':
			return 3;
		case '3':
			return 2;
		case '2':
			return 1;
		case '1':
			return 0;
		default:
			return -1;
		}
	}
}
