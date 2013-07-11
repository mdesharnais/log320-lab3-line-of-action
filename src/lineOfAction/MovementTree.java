package lineOfAction;

public class MovementTree {
	// Strange, it seems require to use package visibility to let the iterator use these members.
	public final int[] board;
	public final int player;
	public final Movement movement;
	private Movement[] movements;
	private int index;

	public MovementTree(int[] board, int player) {
		this(board, player, null);
	}

	public MovementTree(int[] board, int player, Movement movement) {
		this.board = board;
		this.player = player;
		this.movement = movement;
	}

	public MovementTree nextChild() {
		if (this.movements == null) {
			this.movements = Utils.generateMovements(this.board, this.player);
		}

		Movement m = this.movements[this.index++];

		if (m == null) {
			return null;
		}

		return new MovementTree(
			Board.applyMovement(MovementTree.this.board, m),
			~(MovementTree.this.player) & 0x6,
			m);
	}
}
