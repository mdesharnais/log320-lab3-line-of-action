package lineOfAction.threading;

class TaskRunner implements Runnable {

	private boolean run = true;

	public TaskRunner() {
	}

	public void stop() {
		this.run = false;
	}

	@Override
	public void run() {
		Taskable task;

		while (this.run) {
			task = TaskMaster.getNextTask();

			if (task != null) {
				task.doWork();
				continue;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
