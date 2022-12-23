import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class SchedulerSRTF extends Scheduler {
    Platform srtfPlatform;
    Queue<Process> processes;

    public SchedulerSRTF(Platform platform) {
        srtfPlatform = platform;
        Comparator<Process> ShortestRemainTimeComparator = new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return (p1.getTotalTime() - p1.getElapsedTotal()) - (p2.getTotalTime() - p2.getElapsedTotal());
            }
        };
        processes = new PriorityQueue<>(ShortestRemainTimeComparator);
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
            if (processes.peek() != null) {
                if (process.getRemainingBurst() > processes.peek().getRemainingBurst()) {
                    srtfPlatform.log("CPU " + cpu + " > Preemptively removed: " + process.getName());
                    notifyNewProcess(process);
                    srtfPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
                    return processes.poll();
                }
            }
            if (process.getElapsedBurst() >= process.getBurstTime() || process.getElapsedTotal() >= process.getTotalTime()) {
                process.isBurstComplete();
                srtfPlatform.log("CPU " + cpu + " > Process " + process.getName() + " burst complete");
                contextSwitches++;
                if (process.getElapsedTotal() >= process.getTotalTime()) {
                    process.isExecutionComplete();
                    srtfPlatform.log("CPU " + cpu + " > Process " + process.getName() + " execution complete");
                    if (processes.peek() != null) {
                        srtfPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
                    }
                    return processes.poll();
                }
                Process updatedProcess = new Process(process.getName(), process.getStartTime(), process.getBurstTime(), process.getTotalTime() - process.getElapsedTotal());
                notifyNewProcess(updatedProcess);
                if (processes.peek() != null) {
                    srtfPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
                }
                return processes.poll();
            }
        }
        else if (processes.peek() != null){
            srtfPlatform.log("CPU " + cpu + " > Scheduled " + processes.peek().getName());
            return processes.poll();
        }
        return process;
    }
}