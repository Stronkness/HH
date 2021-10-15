% Authers: Fredrik Kortetjärvi , André Frisk , Rohullah Khorami , William
% Wahlberg

%Task 3a
function Y =exercise3_f(N,x) % Making a recursive function 
syms n k m; %  create symbolic variables and functions

 % length of array which is x =  2.*cos(n*pi/10)+sin(n*2*pi/5)-cos(n*9*pi/10);
 % making vector which 1x10 matrix and for loop iteration
for n = 1:length(N)
    y = 0;
for K = 0: x-1
    if(n-K)>0
    y = y + N(n-K);
    end
    if(n-K)==0 % There is no points in the array that ar'et positiv integers
        % Ther for we set them to zero.
        y=y;
    end
end
Y(n) = (1./x).*y; % The output.
end
    
