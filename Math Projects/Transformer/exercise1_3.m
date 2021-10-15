% Authers: Fredrik Kortetjärvi , André Frisk , Rohullah Khorami , William
% Wahlberg
syms t; %declare symbol
T=1/50; %period
w0=2*pi/T;

% the expression calculeats the five first terms of the pulse train
% (only the cosinus part is nesecary)
PTrain=0.5*b(1)+b(1).*cos(w0*t)+b(2).*cos(2*w0*t)+b(3).*cos(3*w0*t)+b(4).*cos(4*w0*t)+b(5).*cos(5*w0*t)
