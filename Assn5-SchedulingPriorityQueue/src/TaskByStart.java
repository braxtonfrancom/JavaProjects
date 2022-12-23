public class TaskByStart extends Task implements Comparable<Task> {

    //Constructor
    public TaskByStart(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    @Override
    public int compareTo(Task task) {
        if (task.start == start) {
            if (task.deadline < deadline) { return 1; }
            else { return -1; }
        }
        else if (task.start < start) { return 1; }

        else { return -1; }
    }

}
