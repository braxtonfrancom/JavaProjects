public class TaskByDeadline extends Task implements Comparable<Task> {

    //Constructor
    public TaskByDeadline(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    @Override
    public int compareTo(Task task) {
        if (task.deadline == deadline) { return 0; }

        else if (task.deadline < deadline) { return 1; }

        else { return -1; }
    }

}
