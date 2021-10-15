#include <stdio.h>
#include <math.h>
#define PI 3.14159
int PI_estimator(int nMembers);

int main(void)
{
    double part = 1;
    double number = 1;
    int PI_est = 0;
    
    puts("Hello, World");

    while((part/(PI/4)) > 1.001 || (part/(PI/4)) <0.999)//check if the values is outside the range with +-0.1% 
    {
        number = number + 2;
        part = part - (pow(-1,PI_est)/number);
        PI_est = PI_estimator(PI_est);
        
    }
    part = part * 4;
    printf("pi = %f number member = %d", part,PI_est);
    return 0;
}

int PI_estimator (int nMembers)
{
    nMembers = nMembers + 1;
    return nMembers;
}