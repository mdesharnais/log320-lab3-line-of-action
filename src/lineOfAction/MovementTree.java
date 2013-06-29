package lineOfAction;

import java.util.Iterator;
import java.util.List;

public class MovementTree implements Iterable<MovementTree> {
	// Strange, it seems require to use package visibility to let the iterator use these members.
	final Board board;
	final Player player;
	final Movement movement;

	public MovementTree(Board board, Player player) {
		this(board, player, null);
	}

	public MovementTree(Board board, Player player, Movement movement) {
		this.board = board;
		this.player = player;
		this.movement = movement;
	}

	@Override
	public Iterator<MovementTree> iterator() {
		return new Iterator<MovementTree>() {
			private final List<Movement> movements = Utils.generateMovements(
				MovementTree.this.board,
				MovementTree.this.player);
			private final int movementsSize = this.movements.size();
			private int index = 0;

			@Override
			public boolean hasNext() {
				return this.index != this.movementsSize;
			}

			@Override
			public MovementTree next() {
				Movement m = this.movements.get(this.index++);
				return new MovementTree(
					new Board(MovementTree.this.board, m),
					Utils.nextPlayer(MovementTree.this.player),
					m);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

}
