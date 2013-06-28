package lineOfAction.threading;

import java.util.ArrayList;

public abstract class TaskMaster {

	private static ArrayList<Taskable> tasks = new ArrayList<Taskable>(50);

	public static void addTask(Taskable task) {
		tasks.add(task);
	}

	static Taskable getNextTask() {
		if (tasks.size() > 0) {
			return tasks.remove(0);
		}

		return null;
	}
}
