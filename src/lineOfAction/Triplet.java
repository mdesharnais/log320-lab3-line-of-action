package lineOfAction;

public class Triplet<T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>>
	implements Comparable<Triplet<T1, T2, T3>> {
	public final T1 item1;
	public final T2 item2;
	public final T3 item3;

	public Triplet(T1 item1, T2 item2, T3 item3) {
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
	}

	@Override
	public String toString() {
		return "("
			+ Triplet.toStringOrDefault(this.item1, "null") + ","
			+ Triplet.toStringOrDefault(this.item2, "null") + ","
			+ Triplet.toStringOrDefault(this.item3, "null")
			+ ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final Triplet<T1, T2, T3> other = (Triplet<T1, T2, T3>) obj;

		return Utils.equals(this.item1, other.item1)
			&& Utils.equals(this.item2, other.item2)
			&& Utils.equals(this.item3, other.item3);
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

		if (this.item3 != null) {
			code += this.item3.hashCode();
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
	public int compareTo(Triplet<T1, T2, T3> that) {
		if (that == null) {
			return 1;
		}

		int result = Utils.compareTo(this.item1, that.item1);

		if (result == 0) {
			result = Utils.compareTo(this.item2, that.item2);
		}

		if (result == 0) {
			result = Utils.compareTo(this.item3, that.item3);
		}

		return result;
	}
}
