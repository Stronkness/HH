
%Created by Linnéa Olsson and André Frisk
function [varAlongColumns, varAlongRows, varTotal] = e2_2(A)

if nargin > 1 || nargin < 1
    error('The function only takes one input')
else
    dim = ndims(A);
end

if dim < 2 || dim > 2
    error('The input can only be a matrix with two dimensions')
end
    

    if ~isnumeric(A)
        error('Each element of the vector can only be numeric')
    end

    varAlongColumns = var(A,1,1);
    varAlongRows = var(A,1,2);
    varTotal = mean(mean(A-(mean(mean(A)))).^2);
end





