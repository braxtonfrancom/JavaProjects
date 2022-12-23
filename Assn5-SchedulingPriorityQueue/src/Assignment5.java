import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment5 {
    public static void main(String[] args) {
        simpleQueueTest();
        //scheduleTasks("taskset1.txt");
        //scheduleTasks("taskset2.txt");
        //scheduleTasks("taskset3.txt");
        scheduleTasks("taskset4.txt");
        //scheduleTasks("taskset5.txt");
    }

    public static void scheduleTasks(String taskFile) {
        // TODO: Uncomment code here as you complete the tasks and scheduling algorithm
        ArrayList<Task> tasksByDeadline = new ArrayList<>();
        ArrayList<Task> tasksByStart = new ArrayList<>();
        ArrayList<Task> tasksByDuration = new ArrayList<>();

        readTasks(taskFile, tasksByDeadline, tasksByStart, tasksByDuration);

        schedule("Deadline Priority : "+ taskFile, tasksByDeadline);
        schedule("Startime Priority : " + taskFile, tasksByStart);
        schedule("Duration priority : " + taskFile, tasksByDuration);
    }

    public static void simpleQueueTest() {
        // TODO: Uncomment code here for a simple test of your priority queue code
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(9);
        queue.enqueue(7);
        queue.enqueue(5);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(10);
        System.out.println();

        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }

    /**Reads the task data from a file, and creates the three different sets of tasks for each*/
    public static void readTasks(String filename,
                                 ArrayList<Task> tasksByDeadline,
                                 ArrayList<Task> tasksByStart,
                                 ArrayList<Task> tasksByDuration) {

        File file = new File(filename);
        int iDValue = 1;

        try (Scanner input = new Scanner(file)) {

            while (input.hasNextLine()) {
                String[] numberArray;
                String number = input.nextLine();
                numberArray = number.split("\\s+");
                int startValue = Integer.parseInt(numberArray[0]);
                int deadlineValue = Integer.parseInt(numberArray[1]);
                int durationValue = Integer.parseInt(numberArray[2]);
                tasksByDeadline.add(new TaskByDeadline(iDValue, startValue, deadlineValue, durationValue));
                tasksByStart.add(new TaskByStart(iDValue, startValue, deadlineValue, durationValue));
                tasksByDuration.add(new TaskByDuration(iDValue, startValue, deadlineValue, durationValue));
                iDValue++;
            }
        } catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the file:  " + ex);
        }
    }

    /**Given a set of tasks, schedules them and reports the scheduling results*/
    public static void schedule(String label, ArrayList<Task> tasks) {
        int time = 1;
        int lateCount = 0;
        int lateAmount = 0;
        PriorityQueue<Task> queue = new PriorityQueue<>();
        System.out.println(label);

        while (!(queue.isEmpty() && tasks.isEmpty())) {
            for (int i = tasks.size() - 1; i >= 0; i--) {
                if (tasks.get(i).start == time) {
                    queue.enqueue(tasks.get(i));
                    tasks.remove(i);
                }
            }
            if (queue.isEmpty()) {
                System.out.printf("\t    Time  %2d: ---\n", time);
            }
            else {
                Task highestPriority = queue.dequeue();
                highestPriority.duration--;
                if (highestPriority.duration == 0) {
                    if (highestPriority.deadline < time) {
                        System.out.printf("\t    Time  %2d: %s ** Late %s\n", time, highestPriority, time - highestPriority.deadline);
                        lateCount++;
                        lateAmount = lateAmount + (time - highestPriority.deadline);
                    }
                    else {
                        System.out.printf("\t    Time  %2d: %s **\n", time, highestPriority);
                    }
                } else {
                    System.out.printf("\t    Time  %2d: %s\n", time, highestPriority);
                    queue.enqueue(highestPriority);
                }
            }
            time++;
        }
        System.out.println("Tasks late " + lateCount + " Total Late " + lateAmount);
    }

}