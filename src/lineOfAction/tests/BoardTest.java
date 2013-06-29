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
import lineOfAction.Triplet;

import org.junit.Test;

@SuppressWarnings("static-method")
public class BoardTest {
	@Test
	public void testGet() {
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

		assertEquals(board.get(Column.A, Line.One), null);
		assertEquals(board.get(Column.B, Line.One), Player.Black);
		assertEquals(board.get(Column.C, Line.One), Player.Black);
		assertEquals(board.get(Column.D, Line.One), Player.Black);
		assertEquals(board.get(Column.E, Line.One), Player.Black);
		assertEquals(board.get(Column.F, Line.One), Player.Black);
		assertEquals(board.get(Column.G, Line.One), Player.Black);
		assertEquals(board.get(Column.H, Line.One), null);
		assertEquals(board.get(Column.A, Line.Two), Player.White);
		assertEquals(board.get(Column.B, Line.Two), null);
		assertEquals(board.get(Column.C, Line.Two), null);
		assertEquals(board.get(Column.D, Line.Two), null);
		assertEquals(board.get(Column.E, Line.Two), null);
		assertEquals(board.get(Column.F, Line.Two), null);
		assertEquals(board.get(Column.G, Line.Two), null);
		assertEquals(board.get(Column.H, Line.Two), Player.White);
		assertEquals(board.get(Column.A, Line.Three), Player.White);
		assertEquals(board.get(Column.B, Line.Three), null);
		assertEquals(board.get(Column.C, Line.Three), null);
		assertEquals(board.get(Column.D, Line.Three), null);
		assertEquals(board.get(Column.E, Line.Three), null);
		assertEquals(board.get(Column.F, Line.Three), null);
		assertEquals(board.get(Column.G, Line.Three), null);
		assertEquals(board.get(Column.H, Line.Three), Player.White);
		assertEquals(board.get(Column.A, Line.Four), Player.White);
		assertEquals(board.get(Column.B, Line.Four), null);
		assertEquals(board.get(Column.C, Line.Four), null);
		assertEquals(board.get(Column.D, Line.Four), null);
		assertEquals(board.get(Column.E, Line.Four), null);
		assertEquals(board.get(Column.F, Line.Four), null);
		assertEquals(board.get(Column.G, Line.Four), null);
		assertEquals(board.get(Column.H, Line.Four), Player.White);
		assertEquals(board.get(Column.A, Line.Five), Player.White);
		assertEquals(board.get(Column.B, Line.Five), null);
		assertEquals(board.get(Column.C, Line.Five), null);
		assertEquals(board.get(Column.D, Line.Five), null);
		assertEquals(board.get(Column.E, Line.Five), null);
		assertEquals(board.get(Column.F, Line.Five), null);
		assertEquals(board.get(Column.G, Line.Five), null);
		assertEquals(board.get(Column.H, Line.Five), Player.White);
		assertEquals(board.get(Column.A, Line.Six), Player.White);
		assertEquals(board.get(Column.B, Line.Six), null);
		assertEquals(board.get(Column.C, Line.Six), null);
		assertEquals(board.get(Column.D, Line.Six), null);
		assertEquals(board.get(Column.E, Line.Six), null);
		assertEquals(board.get(Column.F, Line.Six), null);
		assertEquals(board.get(Column.G, Line.Six), null);
		assertEquals(board.get(Column.H, Line.Six), Player.White);
		assertEquals(board.get(Column.A, Line.Seven), Player.White);
		assertEquals(board.get(Column.B, Line.Seven), null);
		assertEquals(board.get(Column.C, Line.Seven), null);
		assertEquals(board.get(Column.D, Line.Seven), null);
		assertEquals(board.get(Column.E, Line.Seven), null);
		assertEquals(board.get(Column.F, Line.Seven), null);
		assertEquals(board.get(Column.G, Line.Seven), null);
		assertEquals(board.get(Column.H, Line.Seven), Player.White);
		assertEquals(board.get(Column.A, Line.Eight), null);
		assertEquals(board.get(Column.B, Line.Eight), Player.Black);
		assertEquals(board.get(Column.C, Line.Eight), Player.Black);
		assertEquals(board.get(Column.D, Line.Eight), Player.Black);
		assertEquals(board.get(Column.E, Line.Eight), Player.Black);
		assertEquals(board.get(Column.F, Line.Eight), Player.Black);
		assertEquals(board.get(Column.G, Line.Eight), Player.Black);
		assertEquals(board.get(Column.H, Line.Eight), null);
	}

	@Test
	public void testToString() {
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

		assertEquals(board.toString(), ". O O O O O O .\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			". O O O O O O .\n");
	}

	@Test
	public void testIterator() {
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

		Set<Triplet<Player, Integer, Integer>> e1 = new TreeSet<Triplet<Player, Integer, Integer>>();

		e1.add(new Triplet<Player, Integer, Integer>(null, Column.A, Line.Eight));
		e1.add(new Triplet<Player, Integer, Integer>(Player.Black, Column.B, Line.Eight));
		e1.add(new Triplet<Player, Integer, Integer>(Player.Black, Column.C, Line.Eight));
		e1.add(new Triplet<Player, Integer, Integer>(Player.Black, Column.D, Line.Eight));
		e1.add(new Triplet<Player, Integer, Integer>(Player.Black, Column.E, Line.Eight));
		e1.add(new Triplet<Player, Integer, Integer>(Player.Black, Column.F, Line.Eight));
		e1.add(new Triplet<Player, Integer, Integer>(Player.Black, Column.G, Line.Eight));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.H, Line.Eight));

		e1.add(new Triplet<Player, Integer, Integer>(Player.White, Column.A, Line.Seven));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.B, Line.Seven));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.C, Line.Seven));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.D, Line.Seven));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.E, Line.Seven));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.F, Line.Seven));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.G, Line.Seven));
		e1.add(new Triplet<Player, Integer, Integer>(Player.White, Column.H, Line.Seven));

		e1.add(new Triplet<Player, Integer, Integer>(Player.White, Column.A, Line.Six));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.B, Line.Six));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.C, Line.Six));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.D, Line.Six));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.E, Line.Six));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.F, Line.Six));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.G, Line.Six));
		e1.add(new Triplet<Player, Integer, Integer>(Player.White, Column.H, Line.Six));

		e1.add(new Triplet<Player, Integer, Integer>(Player.White, Column.A, Line.Five));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.B, Line.Five));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.C, Line.Five));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.D, Line.Five));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.E, Line.Five));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.F, Line.Five));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.G, Line.Five));
		e1.add(new Triplet<Player, Integer, Integer>(Player.White, Column.H, Line.Five));

		e1.add(new Triplet<Player, Integer, Integer>(Player.White, Column.A, Line.Four));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.B, Line.Four));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.C, Line.Four));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.D, Line.Four));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.E, Line.Four));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.F, Line.Four));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.G, Line.Four));
		e1.add(new Triplet<Player, Integer, Integer>(Player.White, Column.H, Line.Four));

		e1.add(new Triplet<Player, Integer, Integer>(Player.White, Column.A, Line.Three));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.B, Line.Three));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.C, Line.Three));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.D, Line.Three));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.E, Line.Three));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.F, Line.Three));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.G, Line.Three));
		e1.add(new Triplet<Player, Integer, Integer>(Player.White, Column.H, Line.Three));

		e1.add(new Triplet<Player, Integer, Integer>(Player.White, Column.A, Line.Two));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.B, Line.Two));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.C, Line.Two));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.D, Line.Two));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.E, Line.Two));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.F, Line.Two));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.G, Line.Two));
		e1.add(new Triplet<Player, Integer, Integer>(Player.White, Column.H, Line.Two));

		e1.add(new Triplet<Player, Integer, Integer>(null, Column.A, Line.One));
		e1.add(new Triplet<Player, Integer, Integer>(Player.Black, Column.B, Line.One));
		e1.add(new Triplet<Player, Integer, Integer>(Player.Black, Column.C, Line.One));
		e1.add(new Triplet<Player, Integer, Integer>(Player.Black, Column.D, Line.One));
		e1.add(new Triplet<Player, Integer, Integer>(Player.Black, Column.E, Line.One));
		e1.add(new Triplet<Player, Integer, Integer>(Player.Black, Column.F, Line.One));
		e1.add(new Triplet<Player, Integer, Integer>(Player.Black, Column.G, Line.One));
		e1.add(new Triplet<Player, Integer, Integer>(null, Column.H, Line.One));

		Set<Triplet<Player, Integer, Integer>> e2 = new TreeSet<Triplet<Player, Integer, Integer>>();
		for (Triplet<Player, Integer, Integer> pair : board) {
			e2.add(pair);
		}

		assertEquals(e1.size(), e2.size());
		assertTrue(e1.containsAll(e2));
	}

	@Test
	public void testConstructor() {
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

		Board board2 = new Board(board, new Movement(Column.C, Line.Eight, Column.A, Line.Six));

		assertEquals(board2.toString(), ". O . O O O O .\n" +
			"X . . . . . . X\n" +
			"O . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			". O O O O O O .\n");
	}
}
