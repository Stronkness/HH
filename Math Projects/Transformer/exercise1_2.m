% Authers: Fredrik Kortetjärvi , André Frisk , Rohullah Khorami , William
% Wahlberg

%declares wLRC as symbolic variables to be used in the symbolic function
%H(w)
syms w0 L1 R1 C1 L2 R2 C2 s t i
syms H(w)

%Task 1d
T=1/50; %Period
w0= 100.*pi; %Frequency

L1=0.18;     %L1 is the coils inductivity 1c
R1=100;      %R1 is resisstors för resisistance from 1c
C1=36.*10.^(-6); %C1 is the capacitors capacitance from 1c

L2=0.106;    %L1 is the coils inductivity 1f
R2=10;       %R1 is resisstors för resisistance from 1f
C2=10.6.*10.^(-6);%C1 is the capacitors capacitance from 1f


% The transfer function is taken from Task 1a.
x(t) = 1/(s*C1); % input
y(t) = s*L1 + R1 + 1/(s*C1); % output
h(t) = x(t)/y(t); % transfer function

v(t) = 0; % Empty the functions
vc(t) = 0;
for i = -20:20 % The K first fourier sum
    f =@(t) 2.*exp(-1i.*i.*w0.*t);  % 2e^jkw_0t
    g =@(t) exp(-1i.*i.*w0.*t);     % e^jkw_0t
    c = (1/T)*(integral(f, -T/4, T/4)+integral(g, -T/2, -T/4)+integral(g, T/4, T/2)); % The ck value
    v(t) = v(t) + c*exp(1i.*i.*w0.*t);   % The fourerin sum
    Ck = laplace(c*exp(1i.*i.*w0.*t),t,s); % Laplace trasforms the input that is v(s)
    next = Ck*h(t); % v(s)*h(s)= vc(s)
    vc(t) = vc(t) +  ilaplace(next,s,t); % Invers laplace transform the vc(s)
end

figure(1)
fplot(vc(t), [2*T 4*T])
hold on
grid on
fig = gca;
fig.Title.String = 'Using 1c values';
fig.XLabel.String = 'Time';
fig.YLabel.String = 'Voltage';
fplot(v(t), [2*T 4*T])
legend('Vc(t)', 'V(t)')

%Task 1e
figure(2)
H(w) = ((sqrt(((w.^2-1/(L*C)).^2+R.^2*w.^2/L.^2))).^-1)/(L*C);
hold on;
grid on;

%swaps the symbolic variabels [L1,C1,R1] with [L,C,R] and plots H(w)
fplot(subs(H,[L,C,R],[L1,C1,R1]),[0,1500]);
%swaps the symbolic variabels [L2,C2,R2] with [L,C,R] and plots H(w)
fplot(subs(H,[L,C,R],[L2,C2,R2]),[0,1500]);

%Task 1f
x(t) = 1/(s*C2); % input
y(t) = s*L2 + R2 + 1/(s*C2); % output
h(t) = x(t)/y(t); % transfer function

v(t) = 0; % Empty the functions
vc(t) = 0;
for i = -5:5 % The K first fourier sum
    f =@(t) 2.*exp(-1i.*i.*w0.*t);
    g =@(t) exp(-1i.*i.*w0.*t);
    c = (1/T)*(integral(f, -T/4, T/4)+integral(g, -T/2, -T/4)+integral(g, T/4, T/2)); % The ck value
    v(t) = v(t) + c*exp(1i.*i.*w0.*t);   % The fourerin sum
    Ck = laplace(c*exp(1i.*i.*w0.*t),t,s); % Laplace trasforms the input that is v(s)
    next = Ck*h(t); % v(s)*h(s)= vc(s)
    vc(t) = vc(t) +  ilaplace(next,s,t); % Invers laplace transform the vc(s)
end

figure(3)
fplot(vc(t), [2*T 4*T])
fig = gca;
fig.Title.String = 'Using 1f values';
fig.XLabel.String = 'Time';
fig.YLabel.String = 'Voltage';
hold on
grid on
fplot(v(t), [2*T 4*T])
legend('Vc(t)', 'V(t)')
