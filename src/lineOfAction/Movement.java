package lineOfAction;

public class Movement {
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
}
