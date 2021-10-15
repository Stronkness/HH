% Authers: Fredrik Kortetjärvi , André Frisk , Rohullah Khorami , William
% Wahlberg


% This is a low pass filter
%Task 3c
syms n z T x omega H(z);
%assume(omega,'integer')
H4= 0;
H8=0;
H12=0;
for k = 0 : 3
        H4 = H4 + 1./(4*z^k);
end
for k = 0 : 7
        H8 = H8 + 1./(8*z^k);
end
for k = 0 : 11
        H12 = H12 + 1./(12*z^k);
end
Y4(z)=H4;
Y8(z)=H8;
Y12(z)=H12;
Y4D(omega) = subs(Y4, z,exp(1i*omega)); 
Y8D(omega) = subs(Y8, z,exp(1i*omega));
Y12D(omega) = subs(Y12, z,exp(1i*omega));


fplot(abs(Y4D),[0,pi])  
hold on
fplot(abs(Y8D),[0,pi]) 
hold on
fplot(abs(Y12D),[0,pi]) 
grid on  