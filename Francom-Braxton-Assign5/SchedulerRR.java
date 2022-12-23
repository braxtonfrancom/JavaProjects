import java.util.LinkedList;
import java.util.Queue;

public class SchedulerRR extends Scheduler {
    Platform rrPlatform;
    int quantum;
    Queue<Process> processes;

    public SchedulerRR(Platform platform, int quantum) {
        rrPlatform = platform;
        this.quantum = quantum;
        this.processes = new LinkedList<>();
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
            if (process.getElapsedBurst() >= quantum && process.getElapsedBurst() % quantum == 0 && process.getElapsedTotal() != process.getTotalTime()) {
                rrPlatform.log("CPU " + cpu + " > Time quantum complete for process " + process.getName());
                contextSwitches++;
                Process updatedProcess = new Process(process.getName(), process.getStartTime(), process.getBurstTime(), process.getTotalTime() - process.getElapsedTotal());
                notifyNewProcess(updatedProcess);
                rrPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
                return processes.poll();
                }
                if (process.getElapsedBurst() >= process.getBurstTime() || process.getElapsedTotal() >= process.getTotalTime()) {
                    process.isBurstComplete();
                    rrPlatform.log("CPU " + cpu + " > Process " + process.getName() + " burst complete");
                    contextSwitches++;
                    if (process.getElapsedTotal() >= process.getTotalTime()) {
                        process.isExecutionComplete();
                        rrPlatform.log("CPU " + cpu + " > Process " + process.getName() + " execution complete");
                        if (processes.peek() != null) {
                            rrPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
                        }
                        return processes.poll();
                    }
                    Process updatedProcess = new Process(process.getName(), process.getStartTime(), process.getBurstTime(), process.getTotalTime() - process.getElapsedTotal());
                    notifyNewProcess(updatedProcess);
                    if (processes.peek() != null) {
                        rrPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
                    }
                    return processes.poll();
                }
            }
            else if (processes.peek() != null) {
                rrPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
                return processes.poll();
            }
        return process;
    }
}