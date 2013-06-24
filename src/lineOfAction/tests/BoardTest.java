package lineOfAction.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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

		List<Pair<Player, Position>> l1 = new ArrayList<Pair<Player, Position>>();

		l1.add(new Pair<Player, Position>(null, new Position(Column.A, Line.Eight)));
		l1.add(new Pair<Player, Position>(Player.Black, new Position(Column.B, Line.Eight)));
		l1.add(new Pair<Player, Position>(Player.Black, new Position(Column.C, Line.Eight)));
		l1.add(new Pair<Player, Position>(Player.Black, new Position(Column.D, Line.Eight)));
		l1.add(new Pair<Player, Position>(Player.Black, new Position(Column.E, Line.Eight)));
		l1.add(new Pair<Player, Position>(Player.Black, new Position(Column.F, Line.Eight)));
		l1.add(new Pair<Player, Position>(Player.Black, new Position(Column.G, Line.Eight)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.H, Line.Eight)));

		l1.add(new Pair<Player, Position>(Player.White, new Position(Column.A, Line.Seven)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.B, Line.Seven)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.C, Line.Seven)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.D, Line.Seven)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.E, Line.Seven)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.F, Line.Seven)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.G, Line.Seven)));
		l1.add(new Pair<Player, Position>(Player.White, new Position(Column.H, Line.Seven)));

		l1.add(new Pair<Player, Position>(Player.White, new Position(Column.A, Line.Six)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.B, Line.Six)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.C, Line.Six)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.D, Line.Six)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.E, Line.Six)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.F, Line.Six)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.G, Line.Six)));
		l1.add(new Pair<Player, Position>(Player.White, new Position(Column.H, Line.Six)));

		l1.add(new Pair<Player, Position>(Player.White, new Position(Column.A, Line.Five)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.B, Line.Five)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.C, Line.Five)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.D, Line.Five)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.E, Line.Five)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.F, Line.Five)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.G, Line.Five)));
		l1.add(new Pair<Player, Position>(Player.White, new Position(Column.H, Line.Five)));

		l1.add(new Pair<Player, Position>(Player.White, new Position(Column.A, Line.Four)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.B, Line.Four)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.C, Line.Four)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.D, Line.Four)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.E, Line.Four)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.F, Line.Four)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.G, Line.Four)));
		l1.add(new Pair<Player, Position>(Player.White, new Position(Column.H, Line.Four)));

		l1.add(new Pair<Player, Position>(Player.White, new Position(Column.A, Line.Three)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.B, Line.Three)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.C, Line.Three)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.D, Line.Three)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.E, Line.Three)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.F, Line.Three)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.G, Line.Three)));
		l1.add(new Pair<Player, Position>(Player.White, new Position(Column.H, Line.Three)));

		l1.add(new Pair<Player, Position>(Player.White, new Position(Column.A, Line.Two)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.B, Line.Two)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.C, Line.Two)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.D, Line.Two)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.E, Line.Two)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.F, Line.Two)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.G, Line.Two)));
		l1.add(new Pair<Player, Position>(Player.White, new Position(Column.H, Line.Two)));

		l1.add(new Pair<Player, Position>(null, new Position(Column.A, Line.One)));
		l1.add(new Pair<Player, Position>(Player.Black, new Position(Column.B, Line.One)));
		l1.add(new Pair<Player, Position>(Player.Black, new Position(Column.C, Line.One)));
		l1.add(new Pair<Player, Position>(Player.Black, new Position(Column.D, Line.One)));
		l1.add(new Pair<Player, Position>(Player.Black, new Position(Column.E, Line.One)));
		l1.add(new Pair<Player, Position>(Player.Black, new Position(Column.F, Line.One)));
		l1.add(new Pair<Player, Position>(Player.Black, new Position(Column.G, Line.One)));
		l1.add(new Pair<Player, Position>(null, new Position(Column.H, Line.One)));

		List<Pair<Player, Position>> l2 = new ArrayList<Pair<Player, Position>>();
		for (Pair<Player, Position> pair : board) {
			l2.add(pair);
		}

		assertEquals(l1.size(), l2.size());
		for (int i = 0; i < l1.size(); ++i) {
			assertEquals(l1.get(i), l1.get(i));
			System.out.println(i);
		}
	}
}
