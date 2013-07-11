package lineOfAction;

public class MovementTree {
	public final int[] board;
	public final int player;
	public final Movement movement;

	public MovementTree(int[] board, int player) {
		this(board, player, null);
	}

	public MovementTree(int[] board, int player, Movement movement) {
		this.board = board;
		this.player = player;
		this.movement = movement;
	}
}
