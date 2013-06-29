package lineOfAction;

import java.util.Iterator;

// Triplet<Player, Column, Line>
public class Board implements Iterable<Triplet<Integer, Integer, Integer>>, Comparable<Board> {
	// Strange, it seems require to use package visibility to let the iterator use this member.
	// If you change the way data is store in this array, Utils.generateMovements will crash.
	final int[] data = new int[64];

	public Board(char[] board) {
		for (int i = 0; i < board.length; ++i) {
			switch (board[i]) {
			case '0':
				// Keep default value of 0.
				break;
			case '2':
				this.data[i] = Player.Black;
				break;
			case '4':
				this.data[i] = Player.White;
				break;
			default:
				throw new IllegalArgumentException("");
			}
		}
	}

	public Board(Board that, Movement movement) {
		int sourceOffset = ((7 - movement.sourceLine) << 3) + movement.sourceColumn;
		int destinationOffset = ((7 - movement.destinationLine) << 3) + movement.destinationColumn;

		System.arraycopy(that.data, 0, this.data, 0, 64);
		this.data[destinationOffset] = this.data[sourceOffset];
		this.data[sourceOffset] = 0;
	}

	public int get(int column, int line) {
		if (column < 0 || 7 < column || line < 0 || 7 < line) {
			return 0;
		}

		return this.data[((7 - line) << 3) + column];
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < this.data.length; ++i) {
			if (this.data[i] == 0) {
				builder.append('.');
			} else if (this.data[i] == Player.Black) {
				builder.append('O');
			} else if (this.data[i] == Player.White) {
				builder.append('X');
			}

			if (i != 0 && i % 8 == 7) {
				builder.append('\n');
			} else {
				builder.append(' ');
			}
		}

		return builder.toString();
	}

	/**
	 * Traversal is in the following order A8, B8, C8 ... A7, B7, C7 ... F1, G1, H1
	 */
	@Override
	public Iterator<Triplet<Integer, Integer, Integer>> iterator() {
		return new Iterator<Triplet<Integer, Integer, Integer>>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				return this.index < Board.this.data.length;
			}

			@Override
			public Triplet<Integer, Integer, Integer> next() {
				int i = this.index;
				++this.index;

				return new Triplet<Integer, Integer, Integer>(
					Board.this.data[i],
					i & 0x7, // equivalent to i % 8
					7 - (i >> 3)); // equivalent to 7 - (i / 8)
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public int compareTo(Board that) {
		Iterator<Triplet<Integer, Integer, Integer>> it1 = iterator();
		Iterator<Triplet<Integer, Integer, Integer>> it2 = that.iterator();

		boolean it1HasNext = it1.hasNext();
		boolean it2HasNext = it2.hasNext();

		while ((it1HasNext = it1.hasNext()) && (it2HasNext = it2.hasNext())) {
			int result = it1.next().compareTo(it2.next());
			if (result != 0) {
				return result;
			}
		}

		if (it1HasNext && !it2HasNext) {
			return 1;
		}

		if (!it1HasNext && it2HasNext) {
			return -1;
		}

		return 0;
	}
}
