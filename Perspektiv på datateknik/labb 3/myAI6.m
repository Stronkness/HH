% Created by André Frisk & Linnéa Olsson
function direction = myAI6(A)
B = convertToLogBoard(A);              %convert the board 
d = {'up', 'down', 'right', 'left'};   %the set of actions in the game

heuristicValues = zeros(1,4);
for i = 1:length(d)
  Bnew    = slide(B,d{i});          %Predictive state from action i
  if  isequal(Bnew ,B);             %if action i does not change the state...
    heuristicValues(i) = -Inf;      %... then put heuristic to be -infinity
  else
    heuristicValues(i) = ...        %otherwise, evaluate the state
      heuristic0(Bnew);
  end
end

%find the action of the maximum heuristic
[valueMax, iMax] = max(heuristicValues);  
direction = d{iMax};
end

function a = heuristic0(B)
u  =  sum(B(:) == 0);  %the number of bricks that are zero
y  =  - sum(B(:));
a =   sum(1-0.25)*u+(0.25*y);
end

function B =convertToLogBoard(B)
   B(isnan(B)) = 1;
   B = log2(B);
end