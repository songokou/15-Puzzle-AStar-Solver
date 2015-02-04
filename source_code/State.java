/**
 * BFS implementation of 15 puzzle solver
 * @author zeli
 *
 */
import java.util.*;
public class State {
	
	private int gcost;
	private Vector<Tile> infoTable;//keeps track of the Tile info
	private String solution;
	private String state;
	private int totalCost;
	private int hcost;
	//constructor
	public State( Vector<Tile> p){
		
		infoTable = p;
		gcost = 0;
		hcost = 0;
		totalCost = 0;
		solution = "";
		state = "";
	}
	//setters
	
	public void setTiles(Vector<Tile> p){
		infoTable = p;
	}
	public void addNumMoves(int i){
		gcost += i;
	}
	public void setNumMoves(int i){
		gcost = i;
	}
	public void addSteps(String s){
		solution += s;
	}
	public void setSolution(String s){
		solution = s;
	}
	public void setH(int i){
		hcost = i;
	}
	public void addState(String s){
		String delims = ",";
		String[] tokens = s.split(delims);
		for (int i = 0; i < 16; i++){
			state+=tokens[i]+"\t";
			if ((i+1)%4==0){
				state+="\n";
			}
		}
		solution+=state;
	}
	public void setCost(int i){
		totalCost = i;
	}
	
	//getters
	
	public Vector<Tile> getTiles(){
		return infoTable;
	}
	public int getNumMoves(){
		return gcost;
	}
	
	public String getSolution(){
		return solution;
	}
	public int getCost(){
		return totalCost;
	}
	
	public int getH(){
		return hcost;
	}
}
