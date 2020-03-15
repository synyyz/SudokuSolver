package sudoSolver;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is the main class to solve the sudoku
 * 
 * @author Yuning Sun
 * @version 1 (30 January 2020)
 */

public class Solver {
	public int [][] gameBoard;
	private int [][] fixed = new int[10][10];
	
	public Solver() {
		gameBoard = new int[10][10];
	}
	public Solver(int [][] a) {
		this.gameBoard = a;
		getFixed();
	}
	private void getFixed(){
		for(int i = 0; i < 9; i++) {
			for(int j =0; j < 9; j++) {
				if(gameBoard[i][j]!=0) {
					fixed[i][j] = 1;
				}else {
					fixed[i][j] = 0;
				}
			}
		}
		
	}
	/**
     * give a possible answer of the current sudoku
     * 
     * @param none
     * @return solved gameBoard 2D array with numbers 1 to 9 that respects all the constrain
     */
	boolean check = false;
	public boolean Solve(int x, int y) {
		int nextX =0;
		int nextY =0;
		
		if(y == 8) { 		// end of a row
			nextX = x+1;
			nextY =0;
		}else {				// next field of the row
		nextX =x;
		nextY =y+1;
		}
		
		if(x == 9) {		// row 9 do not exist we arrived at the end of algorithm
			check=true;
			return true;
		}else {
			
			ArrayList<Integer> possibleNumber = fixed[x][y]==1? new ArrayList<Integer>(Arrays.asList(gameBoard[x][y])):getPotential(x,y);
			if(possibleNumber.size()==0)	// no possible answer return to previous stack
				return false;
			for(int i=0; i<possibleNumber.size(); i++) {
				//System.out.println("checking: i = " + i + " et y = "+ y);
				 gameBoard[x][y]=possibleNumber.get(i);
					if(!Solve(nextX,nextY)) {
						if(fixed[x][y] == 0) {
						gameBoard[x][y]=0;
						}
					}else return true;
				}
			
			if(gameBoard[x][y]==0)		// tried all possible answer and still cant find answer for next field
				return false;
		}
	
		if(check) {return true;}
		else return false;
		
	}
	/**
     * Potential possible number that can be in that field
     * 
     * @param position x, position y of the field
     * @return ArrayList that contains all possible number
     */
	private ArrayList<Integer> getPotential(int x, int y) {
		afficher();
		ArrayList<Integer> possibleNumber = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		int position_X = x-x%3;
		int position_Y = y-y%3;
		
	
		//check if the number is repeated in one case 3x3
		for(int i = 0; i <3; i++) {
			for(int j = 0; j  < 3; j++) {
				if(!(gameBoard[position_X+i][position_Y+j] == 0)) {
					possibleNumber.remove(Integer.valueOf(gameBoard[position_X+i][position_Y+j]));
				}
			}
		}
		
		// check if the number is repeated in one column and one row
		for(int i = 0; i < 9; i++) {
			if(!(gameBoard[i][y] == 0)) {		// in the same column
				if(possibleNumber.contains(gameBoard[i][y])) {
				possibleNumber.remove(Integer.valueOf(gameBoard[i][y]));
				}
			}
			if(!(gameBoard[x][i] == 0)) {		// in the same row
				if(possibleNumber.contains(gameBoard[x][i])) {
				possibleNumber.remove(Integer.valueOf(gameBoard[x][i]));
				}
			}
		}
	
		
		return possibleNumber;
		
	}
	/**
     * Displays the gameBoard
     * 
     * @param
     * @return 
     */
	public void afficher()
	{
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++)
				System.out.print(gameBoard[i][j]+" ");
			System.out.println();
		}
		System.out.println("---------------------------------------");
	}
}
