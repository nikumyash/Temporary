import threading
import time
import random

class Clock(threading.Thread):
    def __init__(self, name, time_offset):
        super().__init__()
        self.name = name
        self.offset = time_offset  # Offset from actual time
        self.adjustment = 0        # Time to be added/subtracted
        self.running = True

    def run(self):
        while self.running:
            current_time = time.time() + self.offset + self.adjustment
            print(f"[{self.name}] Local Time: {time.strftime('%H:%M:%S', time.localtime(current_time))}")
            time.sleep(1)

    def stop(self):
        self.running = False

    def get_time(self):
        return time.time() + self.offset + self.adjustment

    def adjust_time(self, delta):
        self.adjustment += delta


class Coordinator(Clock):
    def __init__(self, name, time_offset, slaves):
        super().__init__(name, time_offset)
        self.slaves = slaves

    def synchronize(self):
        print("\n[Coordinator] Starting synchronization...")
        # Step 1: Poll time differences
        time_differences = []
        self_time = self.get_time()
        print(f"[{self.name}] Time: {self_time:.2f}")
        for slave in self.slaves:
            slave_time = slave.get_time()
            diff = slave_time - self_time
            print(f"[{slave.name}] Time: {slave_time:.2f}, Difference: {diff:.2f}")
            time_differences.append(diff)

        total = sum(time_differences) + 0  # Coordinator diff is 0
        avg = total / (len(self.slaves) + 1)
        print(f"[Coordinator] Average offset: {avg:.2f}")

        # Step 2: Adjust clocks
        self.adjust_time(avg)
        print(f"[{self.name}] Adjusted by {avg:.2f} seconds")

        for i, slave in enumerate(self.slaves):
            adjustment = avg - time_differences[i]
            slave.adjust_time(adjustment)
            print(f"[{slave.name}] Adjusted by {adjustment:.2f} seconds")
        print("[Coordinator] Synchronization complete.\n")


# Simulate
if __name__ == "__main__":
    # Create 3 slave clocks with random offsets (-5 to +5 seconds)
    slaves = [Clock(f"Slave{i+1}", random.randint(-5, 5)) for i in range(3)]

    # Create a coordinator with a slight offset
    coordinator = Coordinator("Coordinator", random.randint(-5, 5), slaves)

    # Start all clocks
    coordinator.start()
    for s in slaves:
        s.start()

    # Wait for a few seconds to let clocks run
    time.sleep(5)

    # Synchronize all clocks
    coordinator.synchronize()

    # Let clocks run after sync
    time.sleep(5)

    # Stop all threads
    coordinator.stop()
    for s in slaves:
        s.stop()

    # Join threads
    coordinator.join()
    for s in slaves:
        s.join()
