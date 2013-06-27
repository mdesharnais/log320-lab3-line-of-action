package lineOfAction;

public class Pair<T1 extends Comparable<T1>, T2 extends Comparable<T2>> implements Comparable<Pair<T1, T2>> {
	public final T1 item1;
	public final T2 item2;

	public Pair(T1 item1, T2 item2) {
		this.item1 = item1;
		this.item2 = item2;
	}

	@Override
	public String toString() {
		return "(" + Pair.toStringOrDefault(this.item1, "null") + ","
			+ Pair.toStringOrDefault(this.item2, "null") + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final Pair<T1, T2> other = (Pair<T1, T2>) obj;

		return Utils.equals(this.item1, other.item1) && Utils.equals(this.item2, other.item2);
	}

	@Override
	public int hashCode() {
		int code = 0;

		if (this.item1 != null) {
			code += this.item1.hashCode();
		}

		if (this.item2 != null) {
			code += this.item2.hashCode();
		}

		return code;
	}

	private static String toStringOrDefault(Object obj, String def) {
		if (obj == null) {
			return def;
		}
		return obj.toString();
	}

	@Override
	public int compareTo(Pair<T1, T2> other) {
		if (other == null) {
			return 1;
		}

		int result = Utils.compareTo(this.item1, other.item1);

		if (result == 0) {
			result = Utils.compareTo(this.item2, other.item2);
		}

		return result;
	}
}
