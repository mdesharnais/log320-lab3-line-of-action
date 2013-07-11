package lineOfAction;

public class MovementTree {
	public final int[] board;
	public final int player;
	public final int movement;

	public MovementTree(int[] board, int player) {
		this(board, player, 0);
	}

	public MovementTree(int[] board, int player, int movement) {
		this.board = board;
		this.player = player;
		this.movement = movement;
	}
}
