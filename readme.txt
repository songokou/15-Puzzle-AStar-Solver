//Ze Li, 676755673, zli48
//hw 3, A* Algorithm with two different heuristic approaches, using Java

To compile this program, you could either put them in an IDE and compile it or using command line tools, type “javac *.java” followed by “java Puzzle”.

The input format would be “1 2 3 4 5 6 7 8 9 10 0 11 13 14 15 12”
The output format would be the moves to reach the goal state, “RD”

#########
# Solved #
#########
A*1
Board						Number of moves		Solution
0 1 2 3 5 6 7 4 9 10 11 8 13 14 15 12 		6			RRRDDD
#########
# Solved #
#########
A*2
Board						Number of moves		Solution
0 1 2 3 5 6 7 4 9 10 11 8 13 14 15 12 		6			RRRDDD



In the Puzzle class, you could change the puzzle board in main and enable/disable AStarSolver1, AStarSolver2 to see the result for different heuristics.