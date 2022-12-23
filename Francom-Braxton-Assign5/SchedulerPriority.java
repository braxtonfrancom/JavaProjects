import java.util.Comparator;
import java.util.PriorityQueue;

public class SchedulerPriority extends Scheduler {
    Platform myPlatform;
    PriorityQueue<Process> processes;


    public SchedulerPriority(Platform platform) {
        myPlatform = platform;
        Comparator<Process> jobLengthCompare = new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p1.getPriority() - p2.getPriority();
            }
        };
        processes = new PriorityQueue<>(jobLengthCompare);
    }


    public int getNumberOfContextSwitches() {
        return contextSwitches;
    }


    @Override
    void notifyNewProcess(Process process) {
        contextSwitches++;
        processes.add(process);
    }

    @Override
    Process update(Process process, int cpu) {
        if (process != null) {
            if (process.getElapsedBurst() >= process.getBurstTime() || process.getElapsedTotal() >= process.getTotalTime()) {
                process.isBurstComplete();
                myPlatform.log("CPU " + cpu + " > Process " + process.getName() + " burst complete");
                contextSwitches++;
                if (process.getElapsedTotal() >= process.getTotalTime()) {
                    process.isExecutionComplete();
                    myPlatform.log("CPU " + cpu + " > Process " + process.getName() + " execution complete");
                    if (processes.peek() != null) {
                        myPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
                    }
                    return processes.poll();
                }
                Process updatedProcess = new Process(process.getName(), process.getStartTime(), process.getBurstTime(), process.getTotalTime()-process.getElapsedTotal(), process.getPriority());
                notifyNewProcess(updatedProcess);
                if (processes.peek() != null) {
                    myPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
                }
                return processes.poll();
            }
        }
        else if (processes.peek() != null) {
            myPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
            return processes.poll();
        }
        return process;
    }
}
