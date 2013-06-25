package lineOfAction.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import lineOfAction.Board;
import lineOfAction.Column;
import lineOfAction.Line;
import lineOfAction.Movement;
import lineOfAction.Player;
import lineOfAction.Position;
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
		l1.add(new Movement(new Position(Column.B, Line.Eight), new Position(Column.B, Line.Six)));
		l1.add(new Movement(new Position(Column.C, Line.Eight), new Position(Column.C, Line.Six)));
		l1.add(new Movement(new Position(Column.D, Line.Eight), new Position(Column.D, Line.Six)));
		l1.add(new Movement(new Position(Column.E, Line.Eight), new Position(Column.E, Line.Six)));
		l1.add(new Movement(new Position(Column.F, Line.Eight), new Position(Column.F, Line.Six)));
		l1.add(new Movement(new Position(Column.G, Line.Eight), new Position(Column.G, Line.Six)));
		l1.add(new Movement(new Position(Column.B, Line.One), new Position(Column.B, Line.Three)));
		l1.add(new Movement(new Position(Column.C, Line.One), new Position(Column.C, Line.Three)));
		l1.add(new Movement(new Position(Column.D, Line.One), new Position(Column.D, Line.Three)));
		l1.add(new Movement(new Position(Column.E, Line.One), new Position(Column.E, Line.Three)));
		l1.add(new Movement(new Position(Column.F, Line.One), new Position(Column.F, Line.Three)));
		l1.add(new Movement(new Position(Column.G, Line.One), new Position(Column.G, Line.Three)));

		Set<Movement> l2 = new HashSet<Movement>();

		for (Movement m : Utils.generateMovements(board, Player.Black)) {
			l2.add(m);
		}

		assertEquals(l1.size(), l2.size());
		assertTrue(l1.containsAll(l2));
	}
}
