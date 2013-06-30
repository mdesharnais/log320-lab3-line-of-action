package lineOfAction;

import java.util.Iterator;

public class MovementTree implements Iterable<MovementTree> {
	// Strange, it seems require to use package visibility to let the iterator use these members.
	final int[] board;
	final int player;
	final Movement movement;

	public MovementTree(int[] board, int player) {
		this(board, player, null);
	}

	public MovementTree(int[] board, int player, Movement movement) {
		this.board = board;
		this.player = player;
		this.movement = movement;
	}

	@Override
	public Iterator<MovementTree> iterator() {
		return new Iterator<MovementTree>() {
			private final Movement[] movements = Utils.generateMovements(
				MovementTree.this.board,
				MovementTree.this.player);
			private int index = 0;

			@Override
			public boolean hasNext() {
				return this.movements[this.index] != null;
			}

			@Override
			public MovementTree next() {
				Movement m = this.movements[this.index++];
				return new MovementTree(
					Board.applyMovement(MovementTree.this.board, m),
					~(MovementTree.this.player) & 0x6,
					m);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
