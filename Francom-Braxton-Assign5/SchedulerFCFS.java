import java.util.LinkedList;
import java.util.Queue;

public class SchedulerFCFS extends Scheduler {
    Platform myPlatform;
    Queue<Process> processes;
    public SchedulerFCFS(Platform platform) {
        myPlatform = platform;
        processes = new LinkedList<>();
    }

    public int getNumberOfContextSwitches() {
        return contextSwitches;
    }

    public void notifyNewProcess(Process process) {
        contextSwitches++;
        processes.add(process);
    }


        @Override
    public Process update(Process process, int cpu) {
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
                Process updatedProcess = new Process(process.getName(), process.getStartTime(), process.getBurstTime(), process.getTotalTime()-process.getElapsedTotal());
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
