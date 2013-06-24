package lineOfAction;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		// TODO Implement the game AI
	}

	private static Iterable<Movement> generateMovements(Board board) {
		List<Movement> list = new ArrayList<Movement>();

		for (Pair<Player, Position> pair : board) {
			if (pair.item1 != null) {
				// TODO Generate every movement that the p
			}
		}

		return list;
	}
}
