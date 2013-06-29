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

		assertEquals(board.get(Column.A, Line.One), 0);
		assertEquals(board.get(Column.B, Line.One), Player.Black);
		assertEquals(board.get(Column.C, Line.One), Player.Black);
		assertEquals(board.get(Column.D, Line.One), Player.Black);
		assertEquals(board.get(Column.E, Line.One), Player.Black);
		assertEquals(board.get(Column.F, Line.One), Player.Black);
		assertEquals(board.get(Column.G, Line.One), Player.Black);
		assertEquals(board.get(Column.H, Line.One), 0);
		assertEquals(board.get(Column.A, Line.Two), Player.White);
		assertEquals(board.get(Column.B, Line.Two), 0);
		assertEquals(board.get(Column.C, Line.Two), 0);
		assertEquals(board.get(Column.D, Line.Two), 0);
		assertEquals(board.get(Column.E, Line.Two), 0);
		assertEquals(board.get(Column.F, Line.Two), 0);
		assertEquals(board.get(Column.G, Line.Two), 0);
		assertEquals(board.get(Column.H, Line.Two), Player.White);
		assertEquals(board.get(Column.A, Line.Three), Player.White);
		assertEquals(board.get(Column.B, Line.Three), 0);
		assertEquals(board.get(Column.C, Line.Three), 0);
		assertEquals(board.get(Column.D, Line.Three), 0);
		assertEquals(board.get(Column.E, Line.Three), 0);
		assertEquals(board.get(Column.F, Line.Three), 0);
		assertEquals(board.get(Column.G, Line.Three), 0);
		assertEquals(board.get(Column.H, Line.Three), Player.White);
		assertEquals(board.get(Column.A, Line.Four), Player.White);
		assertEquals(board.get(Column.B, Line.Four), 0);
		assertEquals(board.get(Column.C, Line.Four), 0);
		assertEquals(board.get(Column.D, Line.Four), 0);
		assertEquals(board.get(Column.E, Line.Four), 0);
		assertEquals(board.get(Column.F, Line.Four), 0);
		assertEquals(board.get(Column.G, Line.Four), 0);
		assertEquals(board.get(Column.H, Line.Four), Player.White);
		assertEquals(board.get(Column.A, Line.Five), Player.White);
		assertEquals(board.get(Column.B, Line.Five), 0);
		assertEquals(board.get(Column.C, Line.Five), 0);
		assertEquals(board.get(Column.D, Line.Five), 0);
		assertEquals(board.get(Column.E, Line.Five), 0);
		assertEquals(board.get(Column.F, Line.Five), 0);
		assertEquals(board.get(Column.G, Line.Five), 0);
		assertEquals(board.get(Column.H, Line.Five), Player.White);
		assertEquals(board.get(Column.A, Line.Six), Player.White);
		assertEquals(board.get(Column.B, Line.Six), 0);
		assertEquals(board.get(Column.C, Line.Six), 0);
		assertEquals(board.get(Column.D, Line.Six), 0);
		assertEquals(board.get(Column.E, Line.Six), 0);
		assertEquals(board.get(Column.F, Line.Six), 0);
		assertEquals(board.get(Column.G, Line.Six), 0);
		assertEquals(board.get(Column.H, Line.Six), Player.White);
		assertEquals(board.get(Column.A, Line.Seven), Player.White);
		assertEquals(board.get(Column.B, Line.Seven), 0);
		assertEquals(board.get(Column.C, Line.Seven), 0);
		assertEquals(board.get(Column.D, Line.Seven), 0);
		assertEquals(board.get(Column.E, Line.Seven), 0);
		assertEquals(board.get(Column.F, Line.Seven), 0);
		assertEquals(board.get(Column.G, Line.Seven), 0);
		assertEquals(board.get(Column.H, Line.Seven), Player.White);
		assertEquals(board.get(Column.A, Line.Eight), 0);
		assertEquals(board.get(Column.B, Line.Eight), Player.Black);
		assertEquals(board.get(Column.C, Line.Eight), Player.Black);
		assertEquals(board.get(Column.D, Line.Eight), Player.Black);
		assertEquals(board.get(Column.E, Line.Eight), Player.Black);
		assertEquals(board.get(Column.F, Line.Eight), Player.Black);
		assertEquals(board.get(Column.G, Line.Eight), Player.Black);
		assertEquals(board.get(Column.H, Line.Eight), 0);
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

		Set<Triplet<Integer, Integer, Integer>> e1 = new TreeSet<Triplet<Integer, Integer, Integer>>();

		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.A, Line.Eight));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.Black, Column.B, Line.Eight));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.Black, Column.C, Line.Eight));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.Black, Column.D, Line.Eight));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.Black, Column.E, Line.Eight));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.Black, Column.F, Line.Eight));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.Black, Column.G, Line.Eight));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.H, Line.Eight));

		e1.add(new Triplet<Integer, Integer, Integer>(Player.White, Column.A, Line.Seven));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.B, Line.Seven));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.C, Line.Seven));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.D, Line.Seven));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.E, Line.Seven));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.F, Line.Seven));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.G, Line.Seven));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.White, Column.H, Line.Seven));

		e1.add(new Triplet<Integer, Integer, Integer>(Player.White, Column.A, Line.Six));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.B, Line.Six));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.C, Line.Six));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.D, Line.Six));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.E, Line.Six));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.F, Line.Six));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.G, Line.Six));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.White, Column.H, Line.Six));

		e1.add(new Triplet<Integer, Integer, Integer>(Player.White, Column.A, Line.Five));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.B, Line.Five));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.C, Line.Five));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.D, Line.Five));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.E, Line.Five));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.F, Line.Five));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.G, Line.Five));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.White, Column.H, Line.Five));

		e1.add(new Triplet<Integer, Integer, Integer>(Player.White, Column.A, Line.Four));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.B, Line.Four));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.C, Line.Four));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.D, Line.Four));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.E, Line.Four));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.F, Line.Four));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.G, Line.Four));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.White, Column.H, Line.Four));

		e1.add(new Triplet<Integer, Integer, Integer>(Player.White, Column.A, Line.Three));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.B, Line.Three));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.C, Line.Three));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.D, Line.Three));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.E, Line.Three));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.F, Line.Three));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.G, Line.Three));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.White, Column.H, Line.Three));

		e1.add(new Triplet<Integer, Integer, Integer>(Player.White, Column.A, Line.Two));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.B, Line.Two));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.C, Line.Two));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.D, Line.Two));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.E, Line.Two));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.F, Line.Two));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.G, Line.Two));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.White, Column.H, Line.Two));

		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.A, Line.One));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.Black, Column.B, Line.One));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.Black, Column.C, Line.One));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.Black, Column.D, Line.One));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.Black, Column.E, Line.One));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.Black, Column.F, Line.One));
		e1.add(new Triplet<Integer, Integer, Integer>(Player.Black, Column.G, Line.One));
		e1.add(new Triplet<Integer, Integer, Integer>(0, Column.H, Line.One));

		Set<Triplet<Integer, Integer, Integer>> e2 = new TreeSet<Triplet<Integer, Integer, Integer>>();
		for (Triplet<Integer, Integer, Integer> pair : board) {
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
