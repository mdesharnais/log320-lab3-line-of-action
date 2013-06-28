package lineOfAction.threading;

public abstract class ThreadMaster {

	private static boolean started = false;

	public static void startThreads() {
		if (started) {
			return;
		}

		started = true;

		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("Starting " + cores + " thread(s).");

		startThreads(cores);
	}

	private static void startThreads(int numberOfThreads) {
		for (int i = 0; i < numberOfThreads; ++i) {
			Thread t = new Thread(new TaskRunner());
			t.start();
		}
	}

}
