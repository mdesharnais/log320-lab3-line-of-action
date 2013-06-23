package lineOfAction;

public enum Line {
	One, Two, Three, Four, Five, Six, Seven, Eight;

	@Override
	public String toString() {
		switch (this) {
		case One:
			return "1";
		case Two:
			return "2";
		case Three:
			return "3";
		case Four:
			return "4";
		case Five:
			return "5";
		case Six:
			return "6";
		case Seven:
			return "7";
		case Eight:
			return "8";
		default:
			return "Line";
		}
	}
}
