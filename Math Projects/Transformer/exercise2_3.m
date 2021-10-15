% Authers: Fredrik Kortetjärvi , André Frisk , Rohullah Khorami , William
% Wahlberg


syms s t;
%Step function multiplied by t
g(t)=heaviside(t).*t;
%StepFuction multiplied by cos t 
c(t)=heaviside(t).*cos(t);
hold on
grid on
fplot(g,[0,1.6])
fplot(c,[0,1.6])
% LaplaceTrasform for g and c functions are equal to lg and lc 
Lg=laplace(g(t),t,s)
Lc=laplace(c(t),t,s)
%G(t) calculates output singals as inverse_Laplace_Transform of product
% transfer function for k = 40 ; multiplied by lg and as well as for C(t)
G(t)=ilaplace((H40(s).*Lg),s,t)
C(t)=ilaplace((H40(s).*Lc),s,t)
% Commando that draw ticks from 0 to 2 with steps 0.1  and y is from 0 to
% 1.6 with steps on x and y axis;
ax=gca;
ax.XTick = 0:0.1:2;
ax.YTick = 0:0.1:1.6;
%Drawing outPutSignals for G in interval 0  to 1.6 & as well as for C 
fplot(G,[0,1.6])
fplot(C,[0,1.6])
%G - g = Gming is the difference between inputs and out outputSignals.
Gming(t)=abs(G(t)-g(t));
C - c
Cminc(t)=abs(C(t)-c(t));

fplot(Gming,[0,1.6])
fplot(Cminc,[0,1.6])
legend('InPutSignal g(t)','InPutSignal c(t)','OutPutSignal G(t)','OutPutSignal C(t)','Difference between G & g ','Difference between C & c')