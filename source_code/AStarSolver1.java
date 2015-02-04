/**
 A* algorithm using Heuristic 1
 */


import java.util.*;
public class AStarSolver1 {
	private State stateToSolve;
	private final String goal = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0 ";
	private Map<String, State> previous;
	private Queue<State> states;
	private int totalnodes;
	private final int left = -1;
	private final int right = 1;
	private final int up = -4;
	private final int down = 4;
	
	public AStarSolver1(State s){
		stateToSolve = duplicateState(s);
		stateToSolve.setCost(findMisplaced(s));
		stateToSolve.setH(findMisplaced(s));
		previous = new HashMap<String,State>();
		totalnodes = 0;
		Comparator<State> comparator = new Comparator<State>(){
			public int compare(State s1, State s2){
				int i1 = s1.getCost();
				int i2 = s2.getCost();
				if(i1>i2)
					return 1;
				if(i1<i2)
					return -1;
				else
					return 0;
			}
		};
		states = new PriorityQueue<State>(11,comparator);
		aStarSolve1();
	}
	
	private void aStarSolve1(){
		//perfect input
		if(goalReached(stateToSolve)){
			System.out.println("Goal state initially.");
			return;
		}
		State s = duplicateState(stateToSolve);
		StringState ss = new StringState(s);
//		s.addSteps("Initial State\n");
//		s.addState(ss.getStringState());
//		s.addSteps("\n");
		makeS(ss.getStringState(),s);//add to stack
		
		while(!states.isEmpty()){
			State tempS = states.remove();
//			precost = tempS.getCost();
			if(goalReached(tempS)){
				s = duplicateState(tempS);
				System.out.println("#########\n# Solved #\n#########\nA* Heuristic 1");
				break;
			}
			explore(tempS);
		}
        System.out.println("Board\t\t\t\t\t\tNumber of moves\t\tSolution");

		System.out.println( ss.getStringState() + "\t\t" + s.getNumMoves() + "\t\t\t"+s.getSolution() );

	}
	
	/**
	 * help find the total misplaced piece in the puzzle
	 * @param s
	 * @return
	 */
	private int findMisplaced(State s){
		Vector<Tile> tiles = new Vector<Tile>();
		tiles = s.getTiles();
		int misplace = 0;
		for (int i = 1; i < 16; i++){
			int temp = Integer.parseInt(tiles.get(i-1).getName());
			if(temp != i)
				misplace++;
		}
		return misplace;
	}
	
	
	private void explore(State s){	
		totalnodes++;
		int move = movable(s);	
		moveUp(s,move);
		moveDown(s,move);
		moveLeft(s,move);		
		moveRight(s,move);
		
	}
	private void moveLeft(State s,int move){
		//can't move left
		if(!s.getTiles().get(move).couldMoveLeft())
			return;
		State copyS = duplicateState(s);
		//swap names of the two tiles
		copyS.getTiles().get(move).setName(copyS.getTiles().get(move+left).getName());
		copyS.getTiles().get(move+left).setName("0");
		copyS.addNumMoves(1);
		int gcost = copyS.getNumMoves();
		int hcost = findMisplaced(copyS);
		int fcost = gcost + hcost;

		copyS.setCost(hcost+gcost);
		StringState ss = new StringState(copyS);
//		copyS.addState(ss.getStringState());
//		copyS.addSteps("Left\n\n");//add to solution
		copyS.addSteps("L");
		makeS(ss.getStringState(),copyS);

	}
	
	private void moveRight(State s,int move){
		if(!s.getTiles().get(move).couldMoveRight())
			return;
		State copyS = duplicateState(s);
		//swap names of the two tiles
		copyS.getTiles().get(move).setName(copyS.getTiles().get(move+right).getName());
		copyS.getTiles().get(move+right).setName("0");
		copyS.addNumMoves(1);

		int gcost = copyS.getNumMoves();
		int hcost = findMisplaced(copyS);
		int fcost = gcost + hcost;

		copyS.setCost(fcost);
		copyS.setH(hcost);

		StringState ss = new StringState(copyS);
//		copyS.addState(ss.getStringState());
//		copyS.addSteps("Right\n\n");//add to solution
		copyS.addSteps("R");
		makeS(ss.getStringState(),copyS);

	}
	
	private void moveDown(State s, int move){
		if(!s.getTiles().get(move).couldMoveDown())
			return;
		State copyS = duplicateState(s);
		//swap names of the two tiles
		copyS.getTiles().get(move).setName(copyS.getTiles().get(move+down).getName());
		copyS.getTiles().get(move+down).setName("0");
		copyS.addNumMoves(1);

		int gcost = copyS.getNumMoves();
		int hcost = findMisplaced(copyS);
		int fcost = gcost + hcost;

		copyS.setCost(gcost);
		copyS.setH(hcost);
		StringState ss = new StringState(copyS);
//		copyS.addState(ss.getStringState());
//		copyS.addSteps("Down\n\n");//add to solution
		copyS.addSteps("D");
		makeS(ss.getStringState(),copyS);

	}
	
	private void moveUp(State s, int move){
		if(!s.getTiles().get(move).couldMoveUp())
			return;
		State copyS = duplicateState(s);
		//swap names of the two tiles
		copyS.getTiles().get(move).setName(copyS.getTiles().get(move+up).getName());
		copyS.getTiles().get(move+up).setName("0");
		copyS.addNumMoves(1);

		int gcost = copyS.getNumMoves();
		int hcost = findMisplaced(copyS);
		copyS.setCost(gcost+hcost);
		copyS.setH(hcost);
		StringState ss = new StringState(copyS);
//		copyS.addState(ss.getStringState());
//		copyS.addSteps("Up\n\n");//add to solution
		copyS.addSteps("U");
		makeS(ss.getStringState(),copyS);
	}
	
	
	private void makeS(String next, State s){
		if (!previous.containsKey(next)){
			previous.put(next, s);//make sure no duplicated State gets searched
//			System.out.println("MakeS: "+next);
			states.add(s);
		}
	}
	
	
	//find the index of the movable piece marked 0 in the State
	private int movable(State s){
		for (int i = 0; i < 16;i++){
			if (s.getTiles().get(i).getName().equals("0"))
				return i;
		}
		return -1;//error
	}
			
	/**
	 * duplicate the State
	 * @param s
	 * @return
	 */
	private State duplicateState(State s){
		Vector<Tile> tempTs = new Vector<Tile>();
		for (int i = 0; i < 16; i++){
			String name = s.getTiles().get(i).getName();
//			int in = s.getTiles().get(i).getIndex();
			Tile tempT = new Tile(name, i);
			tempTs.add(tempT);
		}
		State state = new State(tempTs);
		state.setNumMoves(s.getNumMoves());
		state.setSolution(s.getSolution());
		state.setCost(s.getCost());
		state.setH(s.getH());
		return state;
	}
	
	//find the goal state
		private boolean goalReached(State s){
			StringState ss = new StringState(s);
			if (ss.getStringState().equals(goal))
				return true;
			return false;
		}
}
