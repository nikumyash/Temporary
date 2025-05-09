public class TokenRing {
    static class Process extends Thread {
        private int id;
        private int totalProcesses;
        private static volatile int tokenHolder = 0;

        public Process(int id, int total) {
            this.id = id;
            this.totalProcesses = total;
        }

        public void run() {
            while (true) {
                if (id == tokenHolder) {
                    enterCriticalSection();
                    passToken();
                    break; // stop after one pass, or remove break for infinite ring
                }
            }
        }

        private void enterCriticalSection() {
            System.out.println("Process " + id + " has the token.");
            System.out.println("Process " + id + " is in the critical section.");
            try {
                Thread.sleep(1000); // simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Process " + id + " is leaving the critical section.");
        }

        private void passToken() {
            tokenHolder = (id + 1) % totalProcesses;
            System.out.println("Process " + id + " passed the token to Process " + tokenHolder + ".");
        }
    }

    public static void main(String[] args) {
        int numProcesses = 5;

        Process[] processes = new Process[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            processes[i] = new Process(i, numProcesses);
            processes[i].start();
        }

        for (int i = 0; i < numProcesses; i++) {
            try {
                processes[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Token Ring completed.");
    }
}
