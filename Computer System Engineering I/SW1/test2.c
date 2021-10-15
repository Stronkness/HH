#include <stdio.h>
//#include <values.h>
#include <math.h>

double avarage(double dVec[], int nVals);
double minima(double dVec[], int nVals);
double maxima(double dVec[], int nVals);
double variance(double dVec[], int nVals);

struct stats_vars //created the struct with my variables
{
  int many;
  double aver;
  double mini;
  double max;
  double
  var;
};
struct stats_vars calc_stats(double dVec[], int nVals) // my calculation to the struct 
{
  struct stats_vars nextlevel;
  nextlevel.aver = avarage(dVec, nVals);
  nextlevel.mini = minima(dVec, nVals);
  nextlevel.max = maxima(dVec, nVals);
  nextlevel.var = variance(dVec, nVals);
  nextlevel.many = nVals;
  return nextlevel;
}

int main(void) {
  struct stats_vars everything;
  double done = 0;

  double dVec[] = {
    0.9501,
    0.2311,
    0.6068,
    0.4860,
    0.8913,
    0.7621,
    0.4565,
    0.0185,
    0.8214,
    0.4447,
    0.6154,
    0.7919,
    0.9218,
    0.7382,
    0.1763,
    0.4057,
    0.9355,
    0.9169,
    0.4103,
    0.8936,
    0.0579,
    0.3529,
    0.8132,
    0.0099,
    0.1389,
    0.2028,
    0.1987,
    0.6038,
    0.2722,
    0.1988,
    0.0153,
    0.7468,
    0.4451,
    0.9318,
    0.4660,
    0.4186,
    0.8462,
    0.5252,
    0.2026,
    0.6721,
    0.8381,
    0.0196,
    0.6813,
    0.3795,
    0.8318,
    0.5028,
    0.7095,
    0.4289,
    0.3046,
    0.1897,
    0.1934,
    0.6822,
    0.3028,
    0.5417,
    0.1509,
    0.6979,
    0.3784,
    0.8600,
    0.8537,
    0.5936,
    0.4966,
    0.8998,
    0.8216,
    0.6449,
    0.8180,
    0.6602,
    0.3420,
    0.2897,
    0.3412,
    0.5341,
    0.7271,
    0.3093,
    0.8385,
    0.5681,
    0.3704,
    0.7027,
    0.5466,
    0.4449,
    0.6946,
    0.6213,
    0.7948,
    0.9568,
    0.5226,
    0.8801,
    0.1730,
    0.9797,
    0.2714,
    0.2523,
    0.8757,
    0.7373,
    0.1365,
    0.0118,
    0.8939,
    0.1991,
    0.2987,
    0.6614,
    0.2844,
    0.4692,
    0.0648,
    0.9883
  };

  int nVals = (int)(sizeof(dVec) / sizeof(dVec[0]));
  everything = calc_stats(dVec, nVals); // create my struct will all the calculations.
  // done = avarage(dVec, nVals);  
  printf("Average = %f\n", everything.aver); // write out the values
  // done = minima(dVec, nVals);
  printf("Mini = %f\n", everything.mini);
  // done = maxima(dVec, nVals);
  printf("Max = %f\n", everything.max);
  // done = variance(dVec, nVals);
  printf("variance = %f\n", everything.var);
  printf("size of struct %d",sizeof(everything));
  return 0;
}

double avarage(double dVec[], int nVals) // make the avarage values with everything i get in. 
{
  double all = 0;
  for (int i = 0; i < nVals; i++) //count everything so i can divide with the amount of values
  {
    all = all + dVec[i];
  }
  all = all / nVals;
  return all;
}

double minima(double dVec[], int nVals) // find the min value with a for loop
{
  double mini = dVec[0];
  for (int i = 1; i < nVals; i++) // spin the for loop 
  {
    if (mini > dVec[i]) // shift the values if the new value is lower then the value i have saved 
    {
      mini = dVec[i];
    }
  }
  return mini;
}

double maxima(double dVec[], int nVals) //find the max value with a for loop
{
  double max = dVec[0];
  for (int i = 1; i < nVals; i++) //spin the for loop
  {
    if (max < dVec[i]) // shift the values it the new value is higher then the value i have saved 
    {
      max = dVec[i];
    }
  }
  return max;
}

double variance(double dVec[], int nVals) // calculate the variance of the values i throw in
{
  double all = 0;
  double avar = avarage(dVec, nVals);
  for (int i = 0; i < nVals; i++) // spin the for loop to sum all the values -average and power it up with 2 
  {

    all = all + pow((dVec[i] - avar), 2);

  }
  all = all / (double) nVals; // devide the whole value with the amount of the vector/array
  return all;
}