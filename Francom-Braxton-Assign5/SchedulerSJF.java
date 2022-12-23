import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class SchedulerSJF extends Scheduler {
    Platform sjfPlatform;
    Queue<Process> processes;

    public SchedulerSJF(Platform platform) {
        sjfPlatform = platform;
        Comparator<Process> jobLengthCompare = new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p1.getTotalTime() - p2.getTotalTime();
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
            if (process.getElapsedBurst() >= process.getBurstTime()  || process.getElapsedTotal() >= process.getTotalTime()) {
                process.isBurstComplete();
                sjfPlatform.log("CPU " + cpu + " > Process " + process.getName() + " burst complete");
                contextSwitches++;
                if (process.getElapsedTotal() >= process.getTotalTime()) {
                    process.isExecutionComplete();
                    sjfPlatform.log("CPU " + cpu + " > Process " + process.getName() + " execution complete");
                    if (processes.peek() != null) {
                        sjfPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
                    }
                    return processes.poll();
                }
                Process updatedProcess = new Process(process.getName(), process.getBurstTime(), process.getBurstTime(), process.getTotalTime() - process.getBurstTime());
                notifyNewProcess(updatedProcess);
                if (processes.peek() != null) {
                    sjfPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
                }
                return processes.poll();
            }
        }
        else if (processes.peek() != null) {
            sjfPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
            return processes.poll();
        }
        return process;
    }
}
