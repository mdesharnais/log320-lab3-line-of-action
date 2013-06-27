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
import lineOfAction.Position;
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
		e1.add(new Movement(new Position(Column.B, Line.Eight), new Position(Column.B, Line.Six)));
		e1.add(new Movement(new Position(Column.C, Line.Eight), new Position(Column.C, Line.Six)));
		e1.add(new Movement(new Position(Column.D, Line.Eight), new Position(Column.D, Line.Six)));
		e1.add(new Movement(new Position(Column.E, Line.Eight), new Position(Column.E, Line.Six)));
		e1.add(new Movement(new Position(Column.F, Line.Eight), new Position(Column.F, Line.Six)));
		e1.add(new Movement(new Position(Column.G, Line.Eight), new Position(Column.G, Line.Six)));

		e1.add(new Movement(new Position(Column.B, Line.One), new Position(Column.B, Line.Three)));
		e1.add(new Movement(new Position(Column.C, Line.One), new Position(Column.C, Line.Three)));
		e1.add(new Movement(new Position(Column.D, Line.One), new Position(Column.D, Line.Three)));
		e1.add(new Movement(new Position(Column.E, Line.One), new Position(Column.E, Line.Three)));
		e1.add(new Movement(new Position(Column.F, Line.One), new Position(Column.F, Line.Three)));
		e1.add(new Movement(new Position(Column.G, Line.One), new Position(Column.G, Line.Three)));

		// Horizontal movements
		e1.add(new Movement(new Position(Column.B, Line.Eight), new Position(Column.H, Line.Eight)));
		e1.add(new Movement(new Position(Column.G, Line.Eight), new Position(Column.A, Line.Eight)));
		e1.add(new Movement(new Position(Column.B, Line.One), new Position(Column.H, Line.One)));
		e1.add(new Movement(new Position(Column.G, Line.One), new Position(Column.A, Line.One)));

		// Diagonal movements
		e1.add(new Movement(new Position(Column.B, Line.Eight), new Position(Column.D, Line.Six)));
		e1.add(new Movement(new Position(Column.C, Line.Eight), new Position(Column.E, Line.Six)));
		e1.add(new Movement(new Position(Column.D, Line.Eight), new Position(Column.F, Line.Six)));
		e1.add(new Movement(new Position(Column.E, Line.Eight), new Position(Column.G, Line.Six)));
		e1.add(new Movement(new Position(Column.F, Line.Eight), new Position(Column.H, Line.Six)));

		e1.add(new Movement(new Position(Column.C, Line.Eight), new Position(Column.A, Line.Six)));
		e1.add(new Movement(new Position(Column.D, Line.Eight), new Position(Column.B, Line.Six)));
		e1.add(new Movement(new Position(Column.E, Line.Eight), new Position(Column.C, Line.Six)));
		e1.add(new Movement(new Position(Column.F, Line.Eight), new Position(Column.D, Line.Six)));
		e1.add(new Movement(new Position(Column.G, Line.Eight), new Position(Column.E, Line.Six)));

		e1.add(new Movement(new Position(Column.B, Line.One), new Position(Column.D, Line.Three)));
		e1.add(new Movement(new Position(Column.C, Line.One), new Position(Column.E, Line.Three)));
		e1.add(new Movement(new Position(Column.D, Line.One), new Position(Column.F, Line.Three)));
		e1.add(new Movement(new Position(Column.E, Line.One), new Position(Column.G, Line.Three)));
		e1.add(new Movement(new Position(Column.F, Line.One), new Position(Column.H, Line.Three)));

		e1.add(new Movement(new Position(Column.C, Line.One), new Position(Column.A, Line.Three)));
		e1.add(new Movement(new Position(Column.D, Line.One), new Position(Column.B, Line.Three)));
		e1.add(new Movement(new Position(Column.E, Line.One), new Position(Column.C, Line.Three)));
		e1.add(new Movement(new Position(Column.F, Line.One), new Position(Column.D, Line.Three)));
		e1.add(new Movement(new Position(Column.G, Line.One), new Position(Column.E, Line.Three)));

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
		e1.add(new Movement(new Position(Column.A, Line.Two), new Position(Column.A, Line.Eight)));
		e1.add(new Movement(new Position(Column.H, Line.Two), new Position(Column.H, Line.Eight)));
		e1.add(new Movement(new Position(Column.A, Line.Seven), new Position(Column.A, Line.One)));
		e1.add(new Movement(new Position(Column.H, Line.Seven), new Position(Column.H, Line.One)));

		// Horizontal movements
		e1.add(new Movement(new Position(Column.A, Line.Two), new Position(Column.C, Line.Two)));
		e1.add(new Movement(new Position(Column.A, Line.Three), new Position(Column.C, Line.Three)));
		e1.add(new Movement(new Position(Column.A, Line.Four), new Position(Column.C, Line.Four)));
		e1.add(new Movement(new Position(Column.A, Line.Five), new Position(Column.C, Line.Five)));
		e1.add(new Movement(new Position(Column.A, Line.Six), new Position(Column.C, Line.Six)));
		e1.add(new Movement(new Position(Column.A, Line.Seven), new Position(Column.C, Line.Seven)));

		e1.add(new Movement(new Position(Column.H, Line.Two), new Position(Column.F, Line.Two)));
		e1.add(new Movement(new Position(Column.H, Line.Three), new Position(Column.F, Line.Three)));
		e1.add(new Movement(new Position(Column.H, Line.Four), new Position(Column.F, Line.Four)));
		e1.add(new Movement(new Position(Column.H, Line.Five), new Position(Column.F, Line.Five)));
		e1.add(new Movement(new Position(Column.H, Line.Six), new Position(Column.F, Line.Six)));
		e1.add(new Movement(new Position(Column.H, Line.Seven), new Position(Column.F, Line.Seven)));

		// Diagonal movements
		e1.add(new Movement(new Position(Column.A, Line.Two), new Position(Column.C, Line.Four)));
		e1.add(new Movement(new Position(Column.A, Line.Three), new Position(Column.C, Line.Five)));
		e1.add(new Movement(new Position(Column.A, Line.Four), new Position(Column.C, Line.Six)));
		e1.add(new Movement(new Position(Column.A, Line.Five), new Position(Column.C, Line.Seven)));
		e1.add(new Movement(new Position(Column.A, Line.Six), new Position(Column.C, Line.Eight)));

		e1.add(new Movement(new Position(Column.A, Line.Seven), new Position(Column.C, Line.Five)));
		e1.add(new Movement(new Position(Column.A, Line.Six), new Position(Column.C, Line.Four)));
		e1.add(new Movement(new Position(Column.A, Line.Five), new Position(Column.C, Line.Three)));
		e1.add(new Movement(new Position(Column.A, Line.Four), new Position(Column.C, Line.Two)));
		e1.add(new Movement(new Position(Column.A, Line.Three), new Position(Column.C, Line.One)));

		e1.add(new Movement(new Position(Column.H, Line.Two), new Position(Column.F, Line.Four)));
		e1.add(new Movement(new Position(Column.H, Line.Three), new Position(Column.F, Line.Five)));
		e1.add(new Movement(new Position(Column.H, Line.Four), new Position(Column.F, Line.Six)));
		e1.add(new Movement(new Position(Column.H, Line.Five), new Position(Column.F, Line.Seven)));
		e1.add(new Movement(new Position(Column.H, Line.Six), new Position(Column.F, Line.Eight)));

		e1.add(new Movement(new Position(Column.H, Line.Seven), new Position(Column.F, Line.Five)));
		e1.add(new Movement(new Position(Column.H, Line.Six), new Position(Column.F, Line.Four)));
		e1.add(new Movement(new Position(Column.H, Line.Five), new Position(Column.F, Line.Three)));
		e1.add(new Movement(new Position(Column.H, Line.Four), new Position(Column.F, Line.Two)));
		e1.add(new Movement(new Position(Column.H, Line.Three), new Position(Column.F, Line.One)));

		Set<Movement> e2 = new TreeSet<Movement>();
		for (Movement m : Utils.generateMovements(board, Player.White)) {
			e2.add(m);
		}

		assertEquals(e1.size(), e2.size());
		assertTrue(e1.containsAll(e2));
	}
}
