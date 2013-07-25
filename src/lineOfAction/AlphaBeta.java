package lineOfAction;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

//alpha & beta use the following encoding:
//
//64       56       48       40       32       24       16       8        0
//+--------+--------+--------+--------+--------+--------+--------+--------+
//| Alpha or beta value               | srcCol | srcLin | dstCol | dstLin |
//+--------+--------+--------+--------+--------+--------+--------+--------+
//
//Since the alpha/beta value are the most significant bits, we cans still compare them and the position
//will not have any effect on the result.
public class AlphaBeta implements Callable<Long> {
	private long friends;
	private long enemies;
	private int depth;

	public AlphaBeta(long friends, long enemies, int depth) {
		this.friends = friends;
		this.enemies = enemies;
		this.depth = depth;
	}

	private static class FuckingJavaThatDoNotHaveProperSupportOfClosures implements Callable<Long> {
		private long friends;
		private long enemies;
		private long alpha;
		private long beta;
		private int depth;
		private boolean maximize;

		public FuckingJavaThatDoNotHaveProperSupportOfClosures(long friends, long enemies, long alpha,
			long beta, int depth, boolean maximize) {
			this.friends = friends;
			this.enemies = enemies;
			this.alpha = alpha;
			this.beta = beta;
			this.depth = depth;
			this.maximize = maximize;
		}

		@Override
		public Long call() throws Exception {
			return Main.ab(this.friends, this.enemies, this.alpha, this.beta, this.depth, this.maximize);
		}
	}

	@SuppressWarnings("boxing")
	@Override
	public Long call() {
		//System.out.printf("%s\n%s", Main.repeat('-', this.depth), Board.toString(this.friends, this.enemies));

		Queue<Future<Long>> futures = new ArrayDeque<Future<Long>>(4);

		int[] movements = Utils.generateMovements(this.friends, this.enemies);
		int index = 0;
		long alpha = Long.MIN_VALUE;
		long beta = Long.MAX_VALUE;

		int m;
		while ((m = movements[index++]) != 0) {
			int srcOffset = ((m & 0x00FF0000) >> 13) + ((m & 0xFF000000) >> 24);
			int dstOffset = ((m & 0x000000FF) << 3) + ((m & 0x0000FF00) >> 8);

			// Our friends is our child enemies
			long childFriends = this.enemies;
			long childEnemies = this.friends;

			// Clear our source position in our board
			childEnemies &= ~(0x1l << srcOffset);

			// Clear our destination position in enemies board
			childFriends &= ~(0x1l << dstOffset);

			// Set our destination position in our board
			childEnemies |= (0x1l << dstOffset);

			/*
			alpha = Math.max(alpha, Main.ab(childFriends, childEnemies, (alpha & 0xFFFFFFFF00000000l)
				| (m & 0x00000000FFFFFFFFl), (beta & 0xFFFFFFFF00000000l) | (m & 0x00000000FFFFFFFFl),
				this.depth - 1, false));
			/*/
			futures.add(Main.s_executor.submit(new FuckingJavaThatDoNotHaveProperSupportOfClosures(
				childFriends, childEnemies, (alpha & 0xFFFFFFFF00000000l) | (m & 0x00000000FFFFFFFF),
				(beta & 0xFFFFFFFF00000000l) | (m & 0x00000000FFFFFFFF), this.depth - 1, false)));

			if (futures.size() == 4) {
				try {
					boolean haveBreak = false;
					while (!futures.isEmpty()) {
						if (haveBreak) {
							futures.remove().cancel(true);
						} else {
							alpha = Math.max(alpha, futures.remove().get());

							if (beta <= alpha) {
								haveBreak = true;
							}
						}
					}
				} catch (Exception e) {
				}
			}
			//*/
		}
		//*
		try {
			boolean haveBreak = false;
			while (!futures.isEmpty()) {
				if (haveBreak) {
					futures.remove().cancel(true);
				} else {
					alpha = Math.max(alpha, futures.remove().get());

					if (beta <= alpha) {
						haveBreak = true;
					}
				}
			}
		} catch (Exception e) {
		}
		//*/
		return alpha;
	}
}
