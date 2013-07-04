package lineOfAction.threading;

public abstract class Taskable implements Runnable {

	public abstract void doWork();

	@Override
	public void run() {
		doWork();
	}

}
