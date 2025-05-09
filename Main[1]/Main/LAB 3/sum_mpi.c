#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {
    int rank, size;
    int N = 12; // Total number of elements
    int data[12] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    MPI_Init(&argc, &argv);                 // Initialize the MPI environment
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);   // Get the rank of the process
    MPI_Comm_size(MPI_COMM_WORLD, &size);   // Get the number of processes

    int local_n = N / size;                 // Divide N elements among processes
    int* local_data = (int*)malloc(local_n * sizeof(int));

    // Scatter the data array to all processes
    MPI_Scatter(data, local_n, MPI_INT, local_data, local_n, MPI_INT, 0, MPI_COMM_WORLD);

    // Each process calculates its local sum
    int local_sum = 0;
    for (int i = 0; i < local_n; i++) {
        local_sum += local_data[i];
    }

    printf("Processor %d: Local sum = %d\n", rank, local_sum);

    // Reduce all local sums to a global sum in process 0
    int global_sum;
    MPI_Reduce(&local_sum, &global_sum, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);

    // Only root process prints the final sum
    if (rank == 0) {
        printf("Total Sum = %d\n", global_sum);
    }

    free(local_data);
    MPI_Finalize();  // Finalize the MPI environment
    return 0;
}
