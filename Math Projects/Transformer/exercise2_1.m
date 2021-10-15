% Authers: Fredrik Kortetjärvi , André Frisk , Rohullah Khorami , William
% Wahlberg



%Task 2a &
%Task 2b


% K is a controllable positive amplification, s time, t complex frequense
syms t s K;
% x(t) inputsignal, H(s) transfer funtion, h(t) impulse response
syms f(t) H(s) h(t) response(t) transprK(s) ;
K1=5; %diffrent values on K
K2=40;
K3=85;
% functions defines
% Stepfunktion
f(t)=heaviside(t);
%if the comment takes away you plot the stepfunction
%fplot(f,[-5,5]);
%ylim([-1 2])
grid on
hold on



% this is the tranfer function form exercise 2 a) with K variable
HK(s,K)=K./(K+s.*(s+4));
% this is transfer funtion with k value 5
H5(s)=5/(5+s.*(s+4));
% this is transfer funtion with k value 40
H40(s)=40/(40+s.*(s+4));
% this is transfer funtion with k value 85
H85(s)=85/(85+s.*(s+4));
transprKK(s,K)=HK(s,K)./s;
%pruduct from transfer function for k = 5 och laplace transform from stepfunction that is 1/s.
transprK5(s)=H5(s)./s;
%pruduct from transfer function for k = 40 och laplace transform from stepfunction that is 1/s.
transprK40(s)=H40(s)./s;
%pruduct from transfer function for k = 85 och laplace transform from stepfunction that is 1/s.
transprK85(s)=H85(s)./s;
%to calculate outputsignal for scheme will we take 
%inverse lapace form transprk
%calculate inverse laplace to response
responseK(t)=ilaplace(transprKK(s,K), s,t)
response5(t)=ilaplace(transprK5(s), s,t)
response40(t)=ilaplace(transprK40(s), s,t)
response85(t)=ilaplace(transprK85(s), s,t)
%make it pretty
pretty(responseK)
%plot the values for K values 


fplot(response5,[0,4]);
fplot(response40,[0,4]);
fplot(response85,[0,4]);
legend('K = 5','K = 40','K = 85')