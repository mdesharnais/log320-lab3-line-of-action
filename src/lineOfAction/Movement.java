package lineOfAction;

public class Movement implements Comparable<Movement> {
	public final Position departure;
	public final Position arrival;

	public Movement(Position start, Position arrival) {
		this.departure = start;
		this.arrival = arrival;
	}

	@Override
	public String toString() {
		return this.departure.toString() + this.arrival.toString();
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

		return Utils.equals(this.departure, other.departure) && Utils.equals(this.arrival, other.arrival);
	}

	@Override
	public int hashCode() {
		int code = 0;

		if (this.departure != null) {
			code += this.departure.hashCode();
		}

		if (this.arrival != null) {
			code += this.arrival.hashCode();
		}

		return code;
	}

	@Override
	public int compareTo(Movement other) {
		if (other == null) {
			return 1;
		}

		int result = Utils.compareTo(this.departure, other.departure);

		if (result == 0) {
			result = Utils.compareTo(this.arrival, other.arrival);
		}

		return result;
	}
}
