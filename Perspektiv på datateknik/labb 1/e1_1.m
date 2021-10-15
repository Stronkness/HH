% Line in remarks: Linnea Olsson, Andre Frisk, Andreas R Almgren

x = linspace(0, 2, 1000); 						% Create a vector of 1000 evenly spaced points in the interval [0, 2]. 
f1 = sin(10 * x) + x;
f2 = sin(30 * x) + 1;
f3 = x.^2; 
plot(x, f1, 'b', x, f2, 'r', x, f3, 'g') 				% Plot the functions f1, f2 and f3. Specify the colours of respective functions.
ylim([-1 5])								% Specify limits on f-axis.
xticks([0 0.2 0.4 0.6 0.8 1 1.2 1.4 1.6 1.8 2]) 			% Specifies axis tick values.
xlabel('x') 								% Set label below x-axis.
ylabel('f(x)') 
grid on 
ax = gca; 								% Current axes.
ax.GridLineStyle = '--'; 						% Set the style of the gridlines to be dotted.
legend('sin(10x) + x','sin(30x) + 1', 'x^2', 'Location', 'northeast') 	% Make a legend.
print('-dpng', '-r600', 'e1_1') 					% Save plot as a PNG named e1_1. 
