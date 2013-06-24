package lineOfAction.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import lineOfAction.Board;
import lineOfAction.Movement;
import lineOfAction.Player;
import lineOfAction.Utils;

import org.junit.Test;

public class UtilsTest {
	@Test
	public void testGenerateMovements() {
		Board board = new Board(new char[] {
				'0', '2', '2', '2', '2', '2', '2', '0',
				'4', '0', '0', '0', '0', '0', '0', '4',
				'4', '0', '0', '0', '0', '0', '0', '4',
				'4', '0', '0', '0', '0', '0', '0', '4',
				'4', '0', '0', '0', '0', '0', '0', '4',
				'4', '0', '0', '0', '0', '0', '0', '4',
				'4', '0', '0', '0', '0', '0', '0', '4',
				'0', '2', '2', '2', '2', '2', '2', '0'
		});

		Set<Movement> l1 = new HashSet<Movement>();
		Set<Movement> l2 = new HashSet<Movement>();

		for (Movement m : Utils.generateMovements(board, Player.Black)) {
			l2.add(m);
		}

		assertEquals(l1.size(), l2.size());
		for (int i = 0; i < l1.size(); ++i) {
			assertEquals(l1, l2);
			System.out.println(i);
		}
	}
}
