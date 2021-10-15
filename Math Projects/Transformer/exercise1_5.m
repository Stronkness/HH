% Authers: Fredrik Kortetjärvi , André Frisk , Rohullah Khorami , William
% Wahlberg
syms s t L C R;
%declares s t L C R as symbolic variables to be used in the symbolic function% av ï¿½verfï¿½ringsfunktion och fuktionen LPTrain.
syms F(s,t,L,C,R)
%inverlapce transform
syms f1(t)
syms f2(t)

%Task 1g
L1=0.18;
R1=100;
C1=36*10^-6;
L2=0.106;
R2=10;
C2=10.6*10^-6;

% Inserts the values in to H(jw) from 1a
F(s,t,L,C,R)=(1/(L*C)).*LPTrain./(s.^2+(R/L)*s+1/(L*C));
f1(t)=simplify(ilaplace(F(s,t,L1,C1,R1),s,t));
f2(t)=simplify(ilaplace(F(s,t,L2,C2,R2),s,t));


hold on
grid on

fplot(f1,[0,0.1]);
fplot(f2,[0,0.1]);