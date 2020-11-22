import java.util.*;

public class MultipleQueuesScheduler{
    Vector queues;
    sProcess currentProcess = null;

    public MultipleQueuesScheduler(Vector processes){
        queues = new Vector();
        splitProcessesToQueues(processes);
    }

    public void splitProcessesToQueues(Vector processVector){
        Collections.sort(processVector, new Comparator<sProcess>() {
                    @Override
                    public int compare(sProcess p1, sProcess p2) {
                        return p1.compareTo(p2);
                    }
                }
        );
        int numberOfProcesses = processVector.size();
        int i = 0;
        while (i < numberOfProcesses){
            Deque<sProcess> queue = new LinkedList<>();
            sProcess process = (sProcess)processVector.get(i);
            int priority = process.priority;
            queue.addLast((sProcess)processVector.get(i));
            while(true){
                i++;
                if(i < numberOfProcesses){
                    sProcess currentProcess = (sProcess)processVector.get(i);
                    int currentPriority = currentProcess.priority;
                    if(currentPriority == priority){
                        queue.addLast(currentProcess);
                    }else{
                        break;
                    }
                }
                else {
                    break;
                }
            }
            queues.add(queue);
        }
    }

    public sProcess getNextProcess(){
        int numberOfQueues = queues.size();
        for(int i = 0; i < numberOfQueues;i++){
            Deque currentProcessGroup = (Deque) queues.get(i);
            while(currentProcessGroup.peekFirst()!=null){//if Queue isn`t empty
                sProcess currentProcess = (sProcess)currentProcessGroup.peek();
                if(currentProcess.cpudone != currentProcess.cputime){
                    currentProcessGroup.removeFirst();
                    currentProcessGroup.addLast(currentProcess);//set currentProcess to the end of queue
                    this.currentProcess = currentProcess;
                    return currentProcess;
                }else{
                    currentProcessGroup.removeFirst();//removing finished process from queue
                }
            }
        }
        return null;
    }
    private int removeCurrentProcessFromHisQueue(){
        if(currentProcess == null)
            return -1;
        int numberOfQueues = queues.size();
        for(int i = 0; i < numberOfQueues;i++){
            Deque currentProcessGroup = (Deque) queues.get(i);
            sProcess process = (sProcess)currentProcessGroup.peekLast();
            if(currentProcess == process) {
                currentProcessGroup.removeLast();
                return i;
            }
        }
        return -1;
    }
} 