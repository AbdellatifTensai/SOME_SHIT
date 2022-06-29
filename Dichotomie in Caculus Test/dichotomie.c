#include <stdio.h>

double f(double x){
    return x*x*x*x*x + 4*x*x*x - 2;
}
int main(){
    double amplitude = (double)1/0xFFFFFF;
    double intervalle[2] = {0.0,1.0};
    printf("amplitude: %g\n",amplitude);
    dichotomie(intervalle,amplitude);
    return 0;
}

void dichotomie(double inv[2], double amp){
    double centre = (inv[0] + inv[1])/2;
    double new_inv[2];
    if((inv[1] - inv[0]) > amp){

        if(f(centre)*f(inv[0]) <0){
            new_inv[0] = inv[0];
            new_inv[1] = centre;
        }
        if(f(centre)*f(inv[1]) <0){
            new_inv[0] = centre;
            new_inv[1] = inv[1];
        }
        //printf("centre: %f\nf(centre): %f\nf(inv[0]): %f\nf(inv[1]): %f\nnew_inv[0]: %f\nnew_inv[1]: %f\n",centre,f(centre),f(inv[0]),f(inv[1]), new_inv[0], new_inv[1]);
        printf("inv 1: %g , inv 2: %g\n",new_inv[0],new_inv[1]);
        dichotomie(new_inv,amp);
    }
}