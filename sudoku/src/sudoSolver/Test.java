package sudoSolver;

import javax.swing.*;

/**
 * This is the main class to test sudoku solver with an example grid
 * 
 * @author Yuning Sun
 * @version 1 (30 January 2020)
 */

public class Test {

	public static void main(String[] args) {
		int [][] test= {
				{0,0,0,1,0,5,0,0,0},
				{1,4,0,0,0,0,6,7,0},
				{0,8,0,0,0,2,4,0,0},
				{0,6,3,0,7,0,0,1,0},
				{9,0,0,0,0,0,0,0,3},
				{0,1,0,0,9,0,5,2,0},
				{0,0,7,2,0,0,0,8,0},
				{0,2,6,0,0,0,0,3,5},
				{0,0,0,4,0,9,0,0,0}};
		
		Solver sudoku = new Solver(test);
		if(sudoku.Solve(0, 0))
			sudoku.afficher();

	}

}
