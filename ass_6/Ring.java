import java.util.*;

public class RingAlgorithm {
    static class Process {
        int id;
        boolean isAlive;

        public Process(int id) {
            this.id = id;
            this.isAlive = true;
        }

        void crash() {
            isAlive = false;
        }

        void activate() {
            isAlive = true;
        }
    }

    static class RingElection {
        List<Process> processes;

        public RingElection(int n) {
            processes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                processes.add(new Process(i + 1));
            }
        }

        void crashProcess(int id) {
            processes.get(id - 1).crash();
        }

        void startElection(int initiatorId) {
            System.out.println("Process " + initiatorId + " starts election.");
            Set<Integer> candidates = new HashSet<>();
            int n = processes.size();
            int current = initiatorId - 1;

            do {
                if (processes.get(current).isAlive) {
                    candidates.add(processes.get(current).id);
                }
                current = (current + 1) % n;
            } while (current != (initiatorId - 1));

            int newCoordinator = Collections.max(candidates);
            System.out.println("Process " + newCoordinator + " becomes the coordinator.");
        }
    }

    public static void main(String[] args) {
        RingElection ring = new RingElection(5); // IDs 1 to 5
        ring.crashProcess(5); // Coordinator crashed
        ring.startElection(2); // Process 2 initiates
    }
}
