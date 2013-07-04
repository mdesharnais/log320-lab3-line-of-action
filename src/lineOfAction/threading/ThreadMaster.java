package lineOfAction.threading;

public abstract class ThreadMaster {

	private static boolean started = false;

	public static void startThreads() {
		if (started) {
			return;
		}

		started = true;

		Thread t = new Thread(new TaskRunner());
		t.start();
	}

}
