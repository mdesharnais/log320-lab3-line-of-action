package lineOfAction;

import java.util.Iterator;

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
			Iterator<Movement> movements = Utils.generateMovements(
				MovementTree.this.board,
				MovementTree.this.player).iterator();

			@Override
			public boolean hasNext() {
				return this.movements.hasNext();
			}

			@Override
			public MovementTree next() {
				Movement m = this.movements.next();
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
