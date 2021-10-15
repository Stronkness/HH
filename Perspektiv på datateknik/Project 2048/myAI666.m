% Created by Linnéa Olsson and André Frisk
function direction = myAI666(A)
B = convertToLogBoard(A);              %convert the board 
d = {'down', 'up', 'right', 'left'};   %the set of actions in the game
e = {'down', 'up', 'right', 'left'};   % the set of actions of the following boards
heuristicValues = zeros(1,4);        % Heuristic values of the first board
heuristicValues2 = zeros(1,4);      % Heuristic values of the following board. NOTE: These are reset each time they enter the second for loop
for i = 1:length(d)                 % The first forloop is for calculate the heuristicvalue for the first board.
  Bnew    = slide(B,d{i});          % Prognostic state from action i
    if  isequal(Bnew ,B);           % if action i does not change the state...
    heuristicValues(i) = -Inf;      %... then put heuristic to be negative(-)infinity
  else
    heuristicValues(i) = ...        %otherwise, evaluate the state
      heuristic(Bnew);
  
for j = 1:length(e)             % The second forloop does the same as for the first board but its for the following one. 
  Cnew    = slide(Bnew,e{j});   % Prognostic state from action j (next step in progress)
    if isequal(Cnew ,Bnew);     % if action j does not change the state...
    heuristicValues2(j) = -Inf; % ... then put heuristic to be -infinity
   else
     heuristicValues2(j) = ...          % otherwise, evaluate the state
       heuristic2(Cnew);
    end
end
    end 
   [valueMax,iMax]= max(heuristicValues);
        [valueMax, jMax] = max(heuristicValues2);                % Picks the highest heruisticvalue of the following boards.             
        heuristicValuesFinish(i) = heuristicValues2(jMax) + heuristicValues(iMax); % Sum up heuristicvalue of the first board and the best of the following boards
end
 
    [valueMax, gMax] = max(heuristicValuesFinish);% find the action of the maximum heuristicValuesFinal and then send the direction
    direction = d{gMax};
end
        
    function u = heuristic(B)    
    u  =   (sum(B(:) == 0)); % the original values being summed
                             
end
function u = heuristic2(B)
    u =   (sum(B(:) == 0));  %the original values being summed
    
    
end
 
function B =convertToLogBoard(B)
   B(isnan(B)) = 1;
   B = log2(B);     % end of the script
end  