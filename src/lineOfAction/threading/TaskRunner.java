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
		WorkBlock tasks;

		while (this.run) {
			tasks = TaskMaster.getNextTask();

			if (tasks != null) {

				for (Thread task : tasks.getTasks()) {
					task.start();
				}

				System.out.println("Waiting for threads to finish.");

				for (Thread task : tasks.getTasks()) {
					try {
						task.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("Threads finshed.");

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
