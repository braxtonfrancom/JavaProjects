public class TaskByDuration extends Task implements Comparable<Task> {

    //Constructor
    public TaskByDuration(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
    }

    @Override
    public int compareTo(Task task) {
        if (task.duration == duration) { return 0; }

        else if (task.duration < duration) { return 1; }

        else { return -1; }
    }

}
