package lineOfAction.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import lineOfAction.Board;
import lineOfAction.Column;
import lineOfAction.Line;
import lineOfAction.Movement;
import lineOfAction.Player;
import lineOfAction.Utils;

import org.junit.Test;

@SuppressWarnings("static-method")
public class UtilsTest {
	@Test
	public void testGenerateMovementsBlack() {
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

		Set<Movement> e1 = new TreeSet<Movement>();

		// Vertical movements
		e1.add(new Movement(Column.B, Line.Eight, Column.B, Line.Six));
		e1.add(new Movement(Column.C, Line.Eight, Column.C, Line.Six));
		e1.add(new Movement(Column.D, Line.Eight, Column.D, Line.Six));
		e1.add(new Movement(Column.E, Line.Eight, Column.E, Line.Six));
		e1.add(new Movement(Column.F, Line.Eight, Column.F, Line.Six));
		e1.add(new Movement(Column.G, Line.Eight, Column.G, Line.Six));

		e1.add(new Movement(Column.B, Line.One, Column.B, Line.Three));
		e1.add(new Movement(Column.C, Line.One, Column.C, Line.Three));
		e1.add(new Movement(Column.D, Line.One, Column.D, Line.Three));
		e1.add(new Movement(Column.E, Line.One, Column.E, Line.Three));
		e1.add(new Movement(Column.F, Line.One, Column.F, Line.Three));
		e1.add(new Movement(Column.G, Line.One, Column.G, Line.Three));

		// Horizontal movements
		e1.add(new Movement(Column.B, Line.Eight, Column.H, Line.Eight));
		e1.add(new Movement(Column.G, Line.Eight, Column.A, Line.Eight));
		e1.add(new Movement(Column.B, Line.One, Column.H, Line.One));
		e1.add(new Movement(Column.G, Line.One, Column.A, Line.One));

		// Diagonal movements
		e1.add(new Movement(Column.B, Line.Eight, Column.D, Line.Six));
		e1.add(new Movement(Column.C, Line.Eight, Column.E, Line.Six));
		e1.add(new Movement(Column.D, Line.Eight, Column.F, Line.Six));
		e1.add(new Movement(Column.E, Line.Eight, Column.G, Line.Six));
		e1.add(new Movement(Column.F, Line.Eight, Column.H, Line.Six));

		e1.add(new Movement(Column.C, Line.Eight, Column.A, Line.Six));
		e1.add(new Movement(Column.D, Line.Eight, Column.B, Line.Six));
		e1.add(new Movement(Column.E, Line.Eight, Column.C, Line.Six));
		e1.add(new Movement(Column.F, Line.Eight, Column.D, Line.Six));
		e1.add(new Movement(Column.G, Line.Eight, Column.E, Line.Six));

		e1.add(new Movement(Column.B, Line.One, Column.D, Line.Three));
		e1.add(new Movement(Column.C, Line.One, Column.E, Line.Three));
		e1.add(new Movement(Column.D, Line.One, Column.F, Line.Three));
		e1.add(new Movement(Column.E, Line.One, Column.G, Line.Three));
		e1.add(new Movement(Column.F, Line.One, Column.H, Line.Three));

		e1.add(new Movement(Column.C, Line.One, Column.A, Line.Three));
		e1.add(new Movement(Column.D, Line.One, Column.B, Line.Three));
		e1.add(new Movement(Column.E, Line.One, Column.C, Line.Three));
		e1.add(new Movement(Column.F, Line.One, Column.D, Line.Three));
		e1.add(new Movement(Column.G, Line.One, Column.E, Line.Three));

		Set<Movement> e2 = new TreeSet<Movement>();
		for (Movement m : Utils.generateMovements(board, Player.Black)) {
			e2.add(m);
		}

		assertEquals(e1.size(), e2.size());
		assertTrue(e1.containsAll(e2));
	}

	@Test
	public void testGenerateMovementsWhite() {
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

		Set<Movement> e1 = new TreeSet<Movement>();

		// Vertical movements
		e1.add(new Movement(Column.A, Line.Two, Column.A, Line.Eight));
		e1.add(new Movement(Column.H, Line.Two, Column.H, Line.Eight));
		e1.add(new Movement(Column.A, Line.Seven, Column.A, Line.One));
		e1.add(new Movement(Column.H, Line.Seven, Column.H, Line.One));

		// Horizontal movements
		e1.add(new Movement(Column.A, Line.Two, Column.C, Line.Two));
		e1.add(new Movement(Column.A, Line.Three, Column.C, Line.Three));
		e1.add(new Movement(Column.A, Line.Four, Column.C, Line.Four));
		e1.add(new Movement(Column.A, Line.Five, Column.C, Line.Five));
		e1.add(new Movement(Column.A, Line.Six, Column.C, Line.Six));
		e1.add(new Movement(Column.A, Line.Seven, Column.C, Line.Seven));

		e1.add(new Movement(Column.H, Line.Two, Column.F, Line.Two));
		e1.add(new Movement(Column.H, Line.Three, Column.F, Line.Three));
		e1.add(new Movement(Column.H, Line.Four, Column.F, Line.Four));
		e1.add(new Movement(Column.H, Line.Five, Column.F, Line.Five));
		e1.add(new Movement(Column.H, Line.Six, Column.F, Line.Six));
		e1.add(new Movement(Column.H, Line.Seven, Column.F, Line.Seven));

		// Diagonal movements
		e1.add(new Movement(Column.A, Line.Two, Column.C, Line.Four));
		e1.add(new Movement(Column.A, Line.Three, Column.C, Line.Five));
		e1.add(new Movement(Column.A, Line.Four, Column.C, Line.Six));
		e1.add(new Movement(Column.A, Line.Five, Column.C, Line.Seven));
		e1.add(new Movement(Column.A, Line.Six, Column.C, Line.Eight));

		e1.add(new Movement(Column.A, Line.Seven, Column.C, Line.Five));
		e1.add(new Movement(Column.A, Line.Six, Column.C, Line.Four));
		e1.add(new Movement(Column.A, Line.Five, Column.C, Line.Three));
		e1.add(new Movement(Column.A, Line.Four, Column.C, Line.Two));
		e1.add(new Movement(Column.A, Line.Three, Column.C, Line.One));

		e1.add(new Movement(Column.H, Line.Two, Column.F, Line.Four));
		e1.add(new Movement(Column.H, Line.Three, Column.F, Line.Five));
		e1.add(new Movement(Column.H, Line.Four, Column.F, Line.Six));
		e1.add(new Movement(Column.H, Line.Five, Column.F, Line.Seven));
		e1.add(new Movement(Column.H, Line.Six, Column.F, Line.Eight));

		e1.add(new Movement(Column.H, Line.Seven, Column.F, Line.Five));
		e1.add(new Movement(Column.H, Line.Six, Column.F, Line.Four));
		e1.add(new Movement(Column.H, Line.Five, Column.F, Line.Three));
		e1.add(new Movement(Column.H, Line.Four, Column.F, Line.Two));
		e1.add(new Movement(Column.H, Line.Three, Column.F, Line.One));

		Set<Movement> e2 = new TreeSet<Movement>();
		for (Movement m : Utils.generateMovements(board, Player.White)) {
			e2.add(m);
		}

		assertEquals(e1.size(), e2.size());
		assertTrue(e1.containsAll(e2));
	}
}
