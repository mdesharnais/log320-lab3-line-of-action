package lineOfAction.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import lineOfAction.Board;
import lineOfAction.Column;
import lineOfAction.Line;
import lineOfAction.Pair;
import lineOfAction.Player;
import lineOfAction.Position;

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

		assertEquals(board.get(new Position(Column.A, Line.One)), null);
		assertEquals(board.get(new Position(Column.B, Line.One)), Player.Black);
		assertEquals(board.get(new Position(Column.C, Line.One)), Player.Black);
		assertEquals(board.get(new Position(Column.D, Line.One)), Player.Black);
		assertEquals(board.get(new Position(Column.E, Line.One)), Player.Black);
		assertEquals(board.get(new Position(Column.F, Line.One)), Player.Black);
		assertEquals(board.get(new Position(Column.G, Line.One)), Player.Black);
		assertEquals(board.get(new Position(Column.H, Line.One)), null);
		assertEquals(board.get(new Position(Column.A, Line.Two)), Player.White);
		assertEquals(board.get(new Position(Column.B, Line.Two)), null);
		assertEquals(board.get(new Position(Column.C, Line.Two)), null);
		assertEquals(board.get(new Position(Column.D, Line.Two)), null);
		assertEquals(board.get(new Position(Column.E, Line.Two)), null);
		assertEquals(board.get(new Position(Column.F, Line.Two)), null);
		assertEquals(board.get(new Position(Column.G, Line.Two)), null);
		assertEquals(board.get(new Position(Column.H, Line.Two)), Player.White);
		assertEquals(board.get(new Position(Column.A, Line.Three)), Player.White);
		assertEquals(board.get(new Position(Column.B, Line.Three)), null);
		assertEquals(board.get(new Position(Column.C, Line.Three)), null);
		assertEquals(board.get(new Position(Column.D, Line.Three)), null);
		assertEquals(board.get(new Position(Column.E, Line.Three)), null);
		assertEquals(board.get(new Position(Column.F, Line.Three)), null);
		assertEquals(board.get(new Position(Column.G, Line.Three)), null);
		assertEquals(board.get(new Position(Column.H, Line.Three)), Player.White);
		assertEquals(board.get(new Position(Column.A, Line.Four)), Player.White);
		assertEquals(board.get(new Position(Column.B, Line.Four)), null);
		assertEquals(board.get(new Position(Column.C, Line.Four)), null);
		assertEquals(board.get(new Position(Column.D, Line.Four)), null);
		assertEquals(board.get(new Position(Column.E, Line.Four)), null);
		assertEquals(board.get(new Position(Column.F, Line.Four)), null);
		assertEquals(board.get(new Position(Column.G, Line.Four)), null);
		assertEquals(board.get(new Position(Column.H, Line.Four)), Player.White);
		assertEquals(board.get(new Position(Column.A, Line.Five)), Player.White);
		assertEquals(board.get(new Position(Column.B, Line.Five)), null);
		assertEquals(board.get(new Position(Column.C, Line.Five)), null);
		assertEquals(board.get(new Position(Column.D, Line.Five)), null);
		assertEquals(board.get(new Position(Column.E, Line.Five)), null);
		assertEquals(board.get(new Position(Column.F, Line.Five)), null);
		assertEquals(board.get(new Position(Column.G, Line.Five)), null);
		assertEquals(board.get(new Position(Column.H, Line.Five)), Player.White);
		assertEquals(board.get(new Position(Column.A, Line.Six)), Player.White);
		assertEquals(board.get(new Position(Column.B, Line.Six)), null);
		assertEquals(board.get(new Position(Column.C, Line.Six)), null);
		assertEquals(board.get(new Position(Column.D, Line.Six)), null);
		assertEquals(board.get(new Position(Column.E, Line.Six)), null);
		assertEquals(board.get(new Position(Column.F, Line.Six)), null);
		assertEquals(board.get(new Position(Column.G, Line.Six)), null);
		assertEquals(board.get(new Position(Column.H, Line.Six)), Player.White);
		assertEquals(board.get(new Position(Column.A, Line.Seven)), Player.White);
		assertEquals(board.get(new Position(Column.B, Line.Seven)), null);
		assertEquals(board.get(new Position(Column.C, Line.Seven)), null);
		assertEquals(board.get(new Position(Column.D, Line.Seven)), null);
		assertEquals(board.get(new Position(Column.E, Line.Seven)), null);
		assertEquals(board.get(new Position(Column.F, Line.Seven)), null);
		assertEquals(board.get(new Position(Column.G, Line.Seven)), null);
		assertEquals(board.get(new Position(Column.H, Line.Seven)), Player.White);
		assertEquals(board.get(new Position(Column.A, Line.Eight)), null);
		assertEquals(board.get(new Position(Column.B, Line.Eight)), Player.Black);
		assertEquals(board.get(new Position(Column.C, Line.Eight)), Player.Black);
		assertEquals(board.get(new Position(Column.D, Line.Eight)), Player.Black);
		assertEquals(board.get(new Position(Column.E, Line.Eight)), Player.Black);
		assertEquals(board.get(new Position(Column.F, Line.Eight)), Player.Black);
		assertEquals(board.get(new Position(Column.G, Line.Eight)), Player.Black);
		assertEquals(board.get(new Position(Column.H, Line.Eight)), null);
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

		Set<Pair<Player, Position>> e1 = new TreeSet<Pair<Player, Position>>();

		e1.add(new Pair<Player, Position>(null, new Position(Column.A, Line.Eight)));
		e1.add(new Pair<Player, Position>(Player.Black, new Position(Column.B, Line.Eight)));
		e1.add(new Pair<Player, Position>(Player.Black, new Position(Column.C, Line.Eight)));
		e1.add(new Pair<Player, Position>(Player.Black, new Position(Column.D, Line.Eight)));
		e1.add(new Pair<Player, Position>(Player.Black, new Position(Column.E, Line.Eight)));
		e1.add(new Pair<Player, Position>(Player.Black, new Position(Column.F, Line.Eight)));
		e1.add(new Pair<Player, Position>(Player.Black, new Position(Column.G, Line.Eight)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.H, Line.Eight)));

		e1.add(new Pair<Player, Position>(Player.White, new Position(Column.A, Line.Seven)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.B, Line.Seven)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.C, Line.Seven)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.D, Line.Seven)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.E, Line.Seven)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.F, Line.Seven)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.G, Line.Seven)));
		e1.add(new Pair<Player, Position>(Player.White, new Position(Column.H, Line.Seven)));

		e1.add(new Pair<Player, Position>(Player.White, new Position(Column.A, Line.Six)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.B, Line.Six)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.C, Line.Six)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.D, Line.Six)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.E, Line.Six)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.F, Line.Six)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.G, Line.Six)));
		e1.add(new Pair<Player, Position>(Player.White, new Position(Column.H, Line.Six)));

		e1.add(new Pair<Player, Position>(Player.White, new Position(Column.A, Line.Five)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.B, Line.Five)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.C, Line.Five)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.D, Line.Five)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.E, Line.Five)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.F, Line.Five)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.G, Line.Five)));
		e1.add(new Pair<Player, Position>(Player.White, new Position(Column.H, Line.Five)));

		e1.add(new Pair<Player, Position>(Player.White, new Position(Column.A, Line.Four)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.B, Line.Four)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.C, Line.Four)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.D, Line.Four)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.E, Line.Four)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.F, Line.Four)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.G, Line.Four)));
		e1.add(new Pair<Player, Position>(Player.White, new Position(Column.H, Line.Four)));

		e1.add(new Pair<Player, Position>(Player.White, new Position(Column.A, Line.Three)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.B, Line.Three)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.C, Line.Three)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.D, Line.Three)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.E, Line.Three)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.F, Line.Three)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.G, Line.Three)));
		e1.add(new Pair<Player, Position>(Player.White, new Position(Column.H, Line.Three)));

		e1.add(new Pair<Player, Position>(Player.White, new Position(Column.A, Line.Two)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.B, Line.Two)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.C, Line.Two)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.D, Line.Two)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.E, Line.Two)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.F, Line.Two)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.G, Line.Two)));
		e1.add(new Pair<Player, Position>(Player.White, new Position(Column.H, Line.Two)));

		e1.add(new Pair<Player, Position>(null, new Position(Column.A, Line.One)));
		e1.add(new Pair<Player, Position>(Player.Black, new Position(Column.B, Line.One)));
		e1.add(new Pair<Player, Position>(Player.Black, new Position(Column.C, Line.One)));
		e1.add(new Pair<Player, Position>(Player.Black, new Position(Column.D, Line.One)));
		e1.add(new Pair<Player, Position>(Player.Black, new Position(Column.E, Line.One)));
		e1.add(new Pair<Player, Position>(Player.Black, new Position(Column.F, Line.One)));
		e1.add(new Pair<Player, Position>(Player.Black, new Position(Column.G, Line.One)));
		e1.add(new Pair<Player, Position>(null, new Position(Column.H, Line.One)));

		Set<Pair<Player, Position>> e2 = new TreeSet<Pair<Player, Position>>();
		for (Pair<Player, Position> pair : board) {
			e2.add(pair);
		}

		assertEquals(e1.size(), e2.size());
		assertTrue(e1.containsAll(e2));
	}
}
