#include<stdio.h>
#include<mpi.h>


int main(int argc,char**argv){  
    MPI_Init(&argc,&argv);

    int rank,size;
    MPI_Comm_rank(MPI_COMM_WORLD,&rank);
    MPI_Comm_size(MPI_COMM_WORLD,&size);

    if(rank == 0){
        char msg[100] = "this is important msg";
        MPI_send(msg,(sizeof(msg)/sizeof(char)) +1,1,0,MPI_COMM_WORLD);
    }
    if(rank ==1){
        char msg[100];
        MPI_receive(&msg)
    }


    MPI_Finalize();

}
