package lineOfAction.tests;

import static org.junit.Assert.assertEquals;
import lineOfAction.Board;
import lineOfAction.Column;
import lineOfAction.Line;
import lineOfAction.Movement;
import lineOfAction.Player;

import org.junit.Test;

@SuppressWarnings("static-method")
public class BoardTest {
	@Test
	public void testGet() {
		int[] board = Board.makeBoard(new char[] {
			'0', '2', '2', '2', '2', '2', '2', '0',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'0', '2', '2', '2', '2', '2', '2', '0'
		});

		assertEquals(Board.get(board, Column.A, Line.One), 0);
		assertEquals(Board.get(board, Column.B, Line.One), Player.Black);
		assertEquals(Board.get(board, Column.C, Line.One), Player.Black);
		assertEquals(Board.get(board, Column.D, Line.One), Player.Black);
		assertEquals(Board.get(board, Column.E, Line.One), Player.Black);
		assertEquals(Board.get(board, Column.F, Line.One), Player.Black);
		assertEquals(Board.get(board, Column.G, Line.One), Player.Black);
		assertEquals(Board.get(board, Column.H, Line.One), 0);
		assertEquals(Board.get(board, Column.A, Line.Two), Player.White);
		assertEquals(Board.get(board, Column.B, Line.Two), 0);
		assertEquals(Board.get(board, Column.C, Line.Two), 0);
		assertEquals(Board.get(board, Column.D, Line.Two), 0);
		assertEquals(Board.get(board, Column.E, Line.Two), 0);
		assertEquals(Board.get(board, Column.F, Line.Two), 0);
		assertEquals(Board.get(board, Column.G, Line.Two), 0);
		assertEquals(Board.get(board, Column.H, Line.Two), Player.White);
		assertEquals(Board.get(board, Column.A, Line.Three), Player.White);
		assertEquals(Board.get(board, Column.B, Line.Three), 0);
		assertEquals(Board.get(board, Column.C, Line.Three), 0);
		assertEquals(Board.get(board, Column.D, Line.Three), 0);
		assertEquals(Board.get(board, Column.E, Line.Three), 0);
		assertEquals(Board.get(board, Column.F, Line.Three), 0);
		assertEquals(Board.get(board, Column.G, Line.Three), 0);
		assertEquals(Board.get(board, Column.H, Line.Three), Player.White);
		assertEquals(Board.get(board, Column.A, Line.Four), Player.White);
		assertEquals(Board.get(board, Column.B, Line.Four), 0);
		assertEquals(Board.get(board, Column.C, Line.Four), 0);
		assertEquals(Board.get(board, Column.D, Line.Four), 0);
		assertEquals(Board.get(board, Column.E, Line.Four), 0);
		assertEquals(Board.get(board, Column.F, Line.Four), 0);
		assertEquals(Board.get(board, Column.G, Line.Four), 0);
		assertEquals(Board.get(board, Column.H, Line.Four), Player.White);
		assertEquals(Board.get(board, Column.A, Line.Five), Player.White);
		assertEquals(Board.get(board, Column.B, Line.Five), 0);
		assertEquals(Board.get(board, Column.C, Line.Five), 0);
		assertEquals(Board.get(board, Column.D, Line.Five), 0);
		assertEquals(Board.get(board, Column.E, Line.Five), 0);
		assertEquals(Board.get(board, Column.F, Line.Five), 0);
		assertEquals(Board.get(board, Column.G, Line.Five), 0);
		assertEquals(Board.get(board, Column.H, Line.Five), Player.White);
		assertEquals(Board.get(board, Column.A, Line.Six), Player.White);
		assertEquals(Board.get(board, Column.B, Line.Six), 0);
		assertEquals(Board.get(board, Column.C, Line.Six), 0);
		assertEquals(Board.get(board, Column.D, Line.Six), 0);
		assertEquals(Board.get(board, Column.E, Line.Six), 0);
		assertEquals(Board.get(board, Column.F, Line.Six), 0);
		assertEquals(Board.get(board, Column.G, Line.Six), 0);
		assertEquals(Board.get(board, Column.H, Line.Six), Player.White);
		assertEquals(Board.get(board, Column.A, Line.Seven), Player.White);
		assertEquals(Board.get(board, Column.B, Line.Seven), 0);
		assertEquals(Board.get(board, Column.C, Line.Seven), 0);
		assertEquals(Board.get(board, Column.D, Line.Seven), 0);
		assertEquals(Board.get(board, Column.E, Line.Seven), 0);
		assertEquals(Board.get(board, Column.F, Line.Seven), 0);
		assertEquals(Board.get(board, Column.G, Line.Seven), 0);
		assertEquals(Board.get(board, Column.H, Line.Seven), Player.White);
		assertEquals(Board.get(board, Column.A, Line.Eight), 0);
		assertEquals(Board.get(board, Column.B, Line.Eight), Player.Black);
		assertEquals(Board.get(board, Column.C, Line.Eight), Player.Black);
		assertEquals(Board.get(board, Column.D, Line.Eight), Player.Black);
		assertEquals(Board.get(board, Column.E, Line.Eight), Player.Black);
		assertEquals(Board.get(board, Column.F, Line.Eight), Player.Black);
		assertEquals(Board.get(board, Column.G, Line.Eight), Player.Black);
		assertEquals(Board.get(board, Column.H, Line.Eight), 0);
	}

	@Test
	public void testToString() {
		int[] board = Board.makeBoard(new char[] {
			'0', '2', '2', '2', '2', '2', '2', '0',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'0', '2', '2', '2', '2', '2', '2', '0'
		});

		assertEquals(Board.toString(board), ". O O O O O O .\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			". O O O O O O .\n");
	}

	@Test
	public void testConstructor() {
		int[] board = Board.makeBoard(new char[] {
			'0', '2', '2', '2', '2', '2', '2', '0',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'4', '0', '0', '0', '0', '0', '0', '4',
			'0', '2', '2', '2', '2', '2', '2', '0'
		});

		int[] board2 = Board.applyMovement(board,
			Movement.makeMovement(Column.C, Line.Eight, Column.A, Line.Six));

		assertEquals(Board.toString(board2), ". O . O O O O .\n" +
			"X . . . . . . X\n" +
			"O . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			"X . . . . . . X\n" +
			". O O O O O O .\n");
	}
}
