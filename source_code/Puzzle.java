/**
 *implementation of 15 puzzle solver using A* with two different heuristic algorithms
 * @author zeli, zli48, 676755673
 *CS411
 *NOTE: This program assumes the empty square at the topleft corner the goal
 *
 *AS WELL AS
 *	1   2   3   4
 *	5   6   7   8
 *	9   10  11  12
 *	13  14  15  0
 */
import java.util.*;
public class Puzzle {


	public static void main(String args[]){
		String str = "1 2 3 4 5 6 7 8 9 10 11 12 13 0 14 15";//two move to the right
		String str1 = "1 2 3 4 5 6 7 0 9 10 11 8 13 14 15 12";//two moves down
		String str2 = "1 2 3 4 5 6 7 8 9 10 11 12 0 13 14 15";
		String str3 = "1 2 3 0 5 6 7 4 9 10 11 8 13 14 15 12";
		String str4 = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0";//perfect input
		String str5 = "0 1 2 3 5 6 7 4 9 10 11 8 13 14 15 12";//RRRDDD
		String str6 = "1 2 3 4 5 6 7 8 9 0 10 11 13 14 15 12";//RRD
		String str7 = "1 2 3 4 5 6 7 8 9 10 0 11 13 14 15 12";//RD
		String str8 = "5 1 2 3 9 6 7 4 13 10 11 8 14 15 12 0";//LLLUUURRRDDD
		String str9 = "1 2 3 4 5 6 7 8 0 9 10 11 13 14 15 12";//RRRD
        String stra = "1 2 3 4 5 7 8 11 10 6 14 12 9 15 13 0";

		Vector<Tile> tiles = new Vector<Tile>();

		readInTile(stra, tiles);
		State s = new State(tiles);
		AStarSolver1 a1 = new AStarSolver1(s);
		AStarSolver2 a2 = new AStarSolver2(s);

    }
    
	public static void readInTile(String s, Vector<Tile> tiles){
		String delims = "[ ]+";
		String[] tokens = s.split(delims);
		for (int i = 0; i < 16; i++){
			Tile t = new Tile(tokens[i],i);
			tiles.add(t);
		}
	}
}



