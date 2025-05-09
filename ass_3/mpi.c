#include <mpi.h>
#include <stdio.h>

int main(int argc, char** argv) {
    int rank, size;
    int local_value, total_sum;

    // Initialize MPI environment
    MPI_Init(&argc, &argv);

    // Get rank and size
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    // Each process uses its rank as local value
    local_value = rank;

    printf("Process %d has value %d\n", rank, local_value);

    // Reduce all local values to total_sum at root (rank 0)
    MPI_Reduce(&local_value, &total_sum, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);

    // Root process prints the result
    if (rank == 0) {
        printf("Total sum = %d\n", total_sum);
    }

    // Finalize MPI
    MPI_Finalize();
    return 0;
}
