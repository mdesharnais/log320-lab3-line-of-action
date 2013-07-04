package lineOfAction.threading;

import java.util.ArrayList;

public abstract class TaskMaster {

	private static ArrayList<WorkBlock> tasks = new ArrayList<WorkBlock>(50);

	public static void addTask(WorkBlock task) {
		tasks.add(task);
	}

	static WorkBlock getNextTask() {
		if (tasks.size() > 0) {
			return tasks.remove(0);
		}

		return null;
	}
}
