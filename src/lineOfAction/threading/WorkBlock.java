package lineOfAction.threading;

import java.util.ArrayList;

public class WorkBlock {

	private ArrayList<Thread> tasks;

	public WorkBlock() {
		this.tasks = new ArrayList<Thread>(10);
	}

	public void addTask(final Taskable task) {
		this.tasks.add(new Thread(task));
	}

	public Iterable<Thread> getTasks() {
		return this.tasks;
	}

}
