% Authers: Fredrik Kortetjärvi , André Frisk , Rohullah Khorami , William
% Wahlberg
syms t s
% s is the variable for laplcetransform.
% lpTrain is the laplace transform of pulse train. ptrain is declared in 
% file3
LPTrain=laplace(PTrain,t,s);
pretty(LPTrain)