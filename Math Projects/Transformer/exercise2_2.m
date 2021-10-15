% Authers: Fredrik Kortetjärvi , André Frisk , Rohullah Khorami , William
% Wahlberg


syms K;
K=32;
xMin = 0; xMax = 2.0; size = 0.005; 
x = xMin:size:xMax ;
y5 = response5(x) ;
y40 = response40(x) ;
y85 = response85(x) ;
hold on
grid on
%plot(X,Y5)
%plot(X,Y40)
%plot(X,Y85)
[val5,idx5] = max(y5);
[val40,idx40] = max(y40); 
[val85,idx85] = max(y85); 
double(val5)
double(x(idx5))
double(max(y40))
double(x(idx40))
double(max(y85))
double(x(idx85))
ax=gca
ax.XTick = 0:0.1:2; % X axis from 0 to 1.2
ax.YTick = 0:0.05:1.5; % Y axis from 0 to 2
for n=0:1:10
    hold on
    resp=1-exp(-2.*x).*(cos(sqrt(K+n-4).*x)+2*sin(sqrt(K+n-4).*x)/sqrt(K+n-4));
       plot(x,resp)
    [valK,idxK] = max(resp);
    K+n
    double(x(idxK))
double(max(resp))
   end