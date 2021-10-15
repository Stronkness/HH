% Authers: Fredrik Kortetjärvi , André Frisk , Rohullah Khorami , William
% Wahlberg
syms i(t) L R C o tau t vc(t) vc(s) g(jw) v(t) v(s) omega
syms s


%Task 1a
vc(t) = 1/C *int(i(tau),tau,0,t); %fun is the function of v(t) the in signal
v(t) = L*diff(i(t))+R*i(t)+vc(t);
%fun3 = @(x)(1/C)*integral(i(o),0,t) %fun3 is the function of u(t) the out signal 
vc(s)=laplace(vc(t));
v(s) = subs(laplace(v(t)),i(0),0);%laplace transform the in signal to
h(s)= simplify(vc(s)/v(s));
g(jw) = h(1i* omega)
%calculate the transfer function
%This is the Transfer function
%This is the Frequency function
%This is the Amplitude function


%Task 1 b&c
C = 36.*10.^(-6);  
T=1/50; %Period
D=T/2; %PulsLength
N=10; %nr of pulses
w0=2*pi/T; % base frequency

t=linspace(-T*N/2,T*N/2,10001); %makes a list of 10001 points between -T*N/2 and T*N/2
d=[-T*N/2:T:T*N/2]; %Makes a perimiter out of -T*N/2 to T*N/2 for the pulstrainfunction
f = @(t)pulstran(t,d,'rectpuls',D)+1; %creates our pulse signal in function f

plot(t,f(t)); 
title(['Fourier series for v(t)'])
ylim([-1 3])
grid on
hold on

X=0; %X will be the final complex fourier series for the pulse signal
for n=0:5 % n will be the number of cosinus functions in the final x. 
          %bigger n means better aproximation of the pulse signal
    
    % a and b is coefficient that will be calculated using Fn and Gn
    Fn=@(x1)f(x1).*sin(w0*n*x1);
    Gn=@(x1)f(x1).*cos(w0*n*x1);
     a(n+1)=(2/T)*integral(Fn,-T/2,T/2);
     b(n+1)=(2/T)*integral(Gn,-T/2,T/2);
    
    if n==0                  % The first term should be half
        b(1)=0.5*b(1);
    end
    
   X=X+a(n+1)*sin(w0*n*t)+b(n+1)*cos(w0*n*t);
    %X=X+b(n+1)*cos(n*t);
end

plot(t,X)




