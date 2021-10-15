% Authers: Fredrik Kortetjärvi , André Frisk , Rohullah Khorami , William
% Wahlberg



%Task 3b


for v = 1:81       % n is an input array with elements
    x(v)=2.*cos(v*pi/10)+sin(v*2*pi/5)-cos(v*9*pi/10);    % the function
   
end
Y = exercise3_2(x,4) ; % use the function in 3a
Y1 = exercise3_2(x,8); 
Y2 = exercise3_2(x,12);
hold on
grid on
plot(Y)
plot(Y1)
plot(Y2)
legend('4','8','12') % Naming our curves.