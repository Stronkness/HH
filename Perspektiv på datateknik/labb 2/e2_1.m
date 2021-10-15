
% File created by André Frisk & Linnéa Olsson

function [A, P] = e2_1(a,b,c)			 

if nargin == 2								
    c = sqrt(a^2+b^2);												
elseif nargin > 3 || nargin < 2				  
    error('need 2 or 3 inputs to work');	
end											

P = (a+b+c);
s = P/2;
A = sqrt(s*(s-a)*(s-b)*(s-c));
