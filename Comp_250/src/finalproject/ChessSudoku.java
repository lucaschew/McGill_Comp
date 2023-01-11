package finalproject;

import java.util.*;
import java.io.*;


public class ChessSudoku
{
	/* SIZE is the size parameter of the Sudoku puzzle, and N is the square of the size.  For 
	 * a standard Sudoku puzzle, SIZE is 3 and N is 9. 
	 */
	public int SIZE, N;

	/* The grid contains all the numbers in the Sudoku puzzle.  Numbers which have
	 * not yet been revealed are stored as 0. 
	 */
	public int grid[][];

	/* Booleans indicating whether of not one or more of the chess rules should be 
	 * applied to this Sudoku. 
	 */
	public boolean knightRule;	//L shape
	public boolean kingRule;	//Radius of 1 around 
	public boolean queenRule;	//If value == 9: diagonal, up, down, king rule; must not be 9
	
	// Field that stores the same Sudoku puzzle solved in all possible ways
	public HashSet<ChessSudoku> solutions = new HashSet<ChessSudoku>();


	/* The solve() method should remove all the unknown characters ('x') in the grid
	 * and replace them with the numbers in the correct range that satisfy the constraints
	 * of the Sudoku puzzle. If true is provided as input, the method should find finds ALL 
	 * possible solutions and store them in the field named solutions. */
	public void solve(boolean allSolutions) {
		
		boolean failed = false;
		boolean modify = false;
		
		loops:
		//number location	[x][y]
		for (int x = 0; x < N; x++) {
			
			for (int y = 0; y < N; y++) {
				
				//System.out.println(x + " " + y);
				
				if (grid[x][y] == 0) {
					
					modify = true;
					
					for (int i = 1; i <= N; i++) {
						
						if (check(x, y, i)) {
							
							grid[x][y] = i;
							
							solve(allSolutions);
							
							if (!allSolutions && solutions.size() > 0)
								break loops;
							
							grid[x][y] = 0;
						}

						
					}
					
					if (grid[x][y] == 0) {
						failed = true;
						break loops;
					}
					
				}
				
			}
		}
		
		//Multiple Solutions
		if (!failed && !modify) {
			
				boolean dupe = false;
				
				for (ChessSudoku cs: solutions) {
					
					if (Arrays.toString(cs.grid).equals(Arrays.toString(this.grid))) {
						dupe = true;
						break;
					}
				}
				
				if (!dupe) {
					ChessSudoku temp = new ChessSudoku(SIZE);
					temp.grid = new int[N][N];
					
					for (int i = 0; i < N; i++) {
						
						temp.grid[i] = Arrays.copyOf(this.grid[i], N);
						
					}
					
					solutions.add(temp);
				}
			}
		
		
	}
	
	private boolean check(int x, int y, int value) {
		
		boolean currentCheck = checkGrid(x,y,value);
		if (knightRule)
			currentCheck = currentCheck && knightRule(x,y,value);
		if (kingRule)
			currentCheck = currentCheck && kingRule(x,y,value);
		if (queenRule && value == N)
			currentCheck = currentCheck && queenRule(x,y);
		
		return currentCheck;
	}
	
	//Function to check basic rules of sudoku
	//[x, y] is the grid location
	private boolean checkGrid(int x, int y, int value) {
		
		//Check the internal grid
		for (int i = ((int) x/SIZE)*SIZE; i < (((int) x/SIZE)+1)*SIZE; i++) {
			
			for (int p = ((int) y/SIZE)*SIZE; p < (((int) y/SIZE)+1)*SIZE; p++) {
				
//				if (x == 5 && y == 0)
//					System.out.println(i + " " + p);
				
				if (grid[i][p] == value)
					return false;
				
			}
			
		}
		
		//Check column and row
		for (int i = 0; i < N; i++) {
			
			if (grid[x][i] == value)
				return false;
			if (grid[i][y] == value)
				return false;
			
		}
		
		return true;
	}
	
	private boolean knightRule (int x,int y,int value) {
		
		//Top
		if (x-2 >= 0 && y-1 >= 0)
			if (grid[x-2][y-1] == value)
				return false;
		
		if (x-2 >= 0 && y+1 < N)
			if (grid[x-2][y+1] == value)
				return false;
		
		//Bottom
		if (x+2 < N && y-1 >= 0)
			if (grid[x+2][y-1] == value)
				return false;
		
		if (x+2 < N && y+1 < N)
			if (grid[x+2][y+1] == value)
				return false;
		
		//Left
		if (y-2 >= 0 && x+1 < N)
			if (grid[x+1][y-2] == value)
				return false;
		
		if (y-2 >= 0 && x-1 >= 0)
			if (grid[x-1][y-2] == value)
				return false;
		
		//Right
		if (y+2 < N && x+1 < N)
			if (grid[x+1][y+2] == value)
				return false;
		
		if (y+2 < N && x-1 >= 0)
			if (grid[x-1][y+2] == value)
				return false;
		
		return true;
	}
	
	private boolean kingRule (int x,int y,int value) {
		
		//Faster to brute force, than algorithm to save time
		
		//Top Left
		if (x-1 >= 0 && y-1 >= 0)
			if (grid[x-1][y-1] == value)
				return false;
		
		//Top Right
		if (x-1 >= 0 && y+1 < N)
			if (grid[x-1][y+1] == value)
				return false;
		
		//Bottom Left
		if (x+1 < N && y-1 >= 0)
			if (grid[x+1][y-1] == value)
				return false;
		
		//Bottom Right
		if (x+1 < N && y+1 < N)
			if (grid[x+1][y+1] == value)
				return false;
		
		return true;
	}
	
	private boolean queenRule (int x,int y) {
		
		if (!knightRule(x,y,N))
			return false;
		if (!kingRule(x,y,N))
			return false;
		if (!checkGrid(x,y,N)) 
			return false;
		
		//Only need to check diagonal now
		for (int i = 1; i <= N; i++) {
			
			if (x+i < N && y+i < N) {
				if (grid[x+i][y+i] == N)
					return false;
			}
			
			if (x-i >= 0 && y-i >= 0) {
				if (grid[x-i][y-i] == N)
					return false;
			}
			
		}
		
		return true;
	}

	

	/*****************************************************************************/
	/* NOTE: YOU SHOULD NOT HAVE TO MODIFY ANY OF THE METHODS BELOW THIS LINE. */
	/*****************************************************************************/

	/* Default constructor.  This will initialize all positions to the default 0
	 * value.  Use the read() function to load the Sudoku puzzle from a file or
	 * the standard input. */
	public ChessSudoku( int size ) {
		SIZE = size;
		N = size*size;

		grid = new int[N][N];
		for( int i = 0; i < N; i++ ) 
			for( int j = 0; j < N; j++ ) 
				grid[i][j] = 0;
	}


	/* readInteger is a helper function for the reading of the input file.  It reads
	 * words until it finds one that represents an integer. For convenience, it will also
	 * recognize the string "x" as equivalent to "0". */
	static int readInteger( InputStream in ) throws Exception {
		int result = 0;
		boolean success = false;

		while( !success ) {
			String word = readWord( in );

			try {
				result = Integer.parseInt( word );
				success = true;
			} catch( Exception e ) {
				// Convert 'x' words into 0's
				if( word.compareTo("x") == 0 ) {
					result = 0;
					success = true;
				}
				// Ignore all other words that are not integers
			}
		}

		return result;
	}


	/* readWord is a helper function that reads a word separated by white space. */
	static String readWord( InputStream in ) throws Exception {
		StringBuffer result = new StringBuffer();
		int currentChar = in.read();
		String whiteSpace = " \t\r\n";
		// Ignore any leading white space
		while( whiteSpace.indexOf(currentChar) > -1 ) {
			currentChar = in.read();
		}

		// Read all characters until you reach white space
		while( whiteSpace.indexOf(currentChar) == -1 ) {
			result.append( (char) currentChar );
			currentChar = in.read();
		}
		return result.toString();
	}


	/* This function reads a Sudoku puzzle from the input stream in.  The Sudoku
	 * grid is filled in one row at at time, from left to right.  All non-valid
	 * characters are ignored by this function and may be used in the Sudoku file
	 * to increase its legibility. */
	public void read( InputStream in ) throws Exception {
		for( int i = 0; i < N; i++ ) {
			for( int j = 0; j < N; j++ ) {
				grid[i][j] = readInteger( in );
			}
		}
	}


	/* Helper function for the printing of Sudoku puzzle.  This function will print
	 * out text, preceded by enough ' ' characters to make sure that the printint out
	 * takes at least width characters.  */
	void printFixedWidth( String text, int width ) {
		for( int i = 0; i < width - text.length(); i++ )
			System.out.print( " " );
		System.out.print( text );
	}


	/* The print() function outputs the Sudoku grid to the standard output, using
	 * a bit of extra formatting to make the result clearly readable. */
	public void print() {
		// Compute the number of digits necessary to print out each number in the Sudoku puzzle
		int digits = (int) Math.floor(Math.log(N) / Math.log(10)) + 1;

		// Create a dashed line to separate the boxes 
		int lineLength = (digits + 1) * N + 2 * SIZE - 3;
		StringBuffer line = new StringBuffer();
		for( int lineInit = 0; lineInit < lineLength; lineInit++ )
			line.append('-');

		// Go through the grid, printing out its values separated by spaces
		for( int i = 0; i < N; i++ ) {
			for( int j = 0; j < N; j++ ) {
				printFixedWidth( String.valueOf( grid[i][j] ), digits );
				// Print the vertical lines between boxes 
				if( (j < N-1) && ((j+1) % SIZE == 0) )
					System.out.print( " |" );
				System.out.print( " " );
			}
			System.out.println();

			// Print the horizontal line between boxes
			if( (i < N-1) && ((i+1) % SIZE == 0) )
				System.out.println( line.toString() );
		}
	}


	/* The main function reads in a Sudoku puzzle from the standard input, 
	 * unless a file name is provided as a run-time argument, in which case the
	 * Sudoku puzzle is loaded from that file.  It then solves the puzzle, and
	 * outputs the completed puzzle to the standard output. */
	public static void main( String args[] ) throws Exception {
		InputStream in = new FileInputStream("knightOneSolution3x3.txt");

		// The first number in all Sudoku files must represent the size of the puzzle.  See
		// the example files for the file format.
		int puzzleSize = readInteger( in );
		if( puzzleSize > 100 || puzzleSize < 1 ) {
			System.out.println("Error: The Sudoku puzzle size must be between 1 and 100.");
			System.exit(-1);
		}

		ChessSudoku s = new ChessSudoku( puzzleSize );
		
		// You can modify these to add rules to your sudoku
		s.knightRule = false;
		s.kingRule = false;
		s.queenRule = false;
		
		// read the rest of the Sudoku puzzle
		s.read( in );

		System.out.println("Before the solve:");
		s.print();
		System.out.println();

		// Solve the puzzle by finding one solution.
		s.solve(true);

		// Print out the (hopefully completed!) puzzle
		System.out.println("After the solve:");
		s.print();

		for (ChessSudoku c: s.solutions) {
			c.print();
			System.out.println();
		}
	}
}

