package lineOfAction.threading;

public class TestTask extends Taskable {

	@Override
	public void doWork() {
		System.out.println("Started " + this);

		try {
			Thread.sleep((int) (Math.random() * 3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Finished " + this);
	}
}
