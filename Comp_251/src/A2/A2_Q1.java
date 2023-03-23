package A2;

import java.util.*;
import java.io.*;

public class A2_Q1{
	
	public static int game_recursion(int[][] board) {
		
		return recursion(board, 0, 0, true);

	}
	
	
	// Recursion Function
	private static int recursion(int[][] board, int myScore, int childScore, boolean myTurn) {
		
		// Flag for whether the board has any more moves
		boolean recursionHappened = false;
		
		// Create a var that is either the min or max of Int, based on whether 
		// we want the biggest difference of the negative or positive based on player
		int biggestDiff;
		
		if (myTurn) {
			biggestDiff = Integer.MIN_VALUE;
		} else {
			biggestDiff = Integer.MAX_VALUE;
		}
		
		// Cycle through board
		for (int i = 0; i < 5; i++) {
			
			for (int z = 0; z < 5; z++) {
				
				if (board[i][z] == 0) {
					
					// Vertical Up
					if (i > 1 && board[i-1][z] > 0 && board[i-2][z] > 0) {
						
						recursionHappened = true;
							
						// Find score from the move
						int val = board[i-2][z] * board[i-1][z];
						
						// Save values so we can fill back in when back-tracking
						int var1 = board[i-2][z];
						int var2 = board[i-1][z];
						
						board[i][z] = board[i-2][z];
						board[i-1][z] = 0;
						board[i-2][z] = 0;
						
						// Based on whose turn, call recursion with new score
						if (myTurn) {
							int result = recursion(board, myScore + val, childScore, !myTurn);
							biggestDiff = Math.max(result, biggestDiff);
						} else {
							int result = recursion(board, myScore, childScore + val, !myTurn);
							biggestDiff = Math.min(result, biggestDiff);
						}
						
						// Backtrack board
						board[i-1][z] = var2;
						board[i-2][z] = var1;
						board[i][z] = 0;
						
					}
					
					// Vertical Down
					if (i < 3 && board[i+1][z] > 0 && board[i+2][z] > 0 ) {
						
						recursionHappened = true;
						
						// Find score from the move
						int val = board[i+2][z] * board[i+1][z];
						
						// Save values so we can fill back in when back-tracking
						int var1 = board[i+2][z];
						int var2 = board[i+1][z];
						board[i][z] = board[i+2][z];
						board[i+1][z] = 0;
						board[i+2][z] = 0;
						
						// Based on whose turn, call recursion with new score
						if (myTurn) {
							int result = recursion(board, myScore + val, childScore, !myTurn);
							biggestDiff = Math.max(result, biggestDiff);
						} else {
							int result = recursion(board, myScore, childScore + val, !myTurn);
							biggestDiff = Math.min(result, biggestDiff);
						}
						
						// Backtrack board
						board[i+1][z] = var2;
						board[i+2][z] = var1;
						board[i][z] = 0;
						
					}
					
					// Horizontal Left
					if (z > 1 && board[i][z-1] > 0 && board[i][z-2] > 0) {
						
						recursionHappened = true;
						
						// Find score from the move
						int val = board[i][z-2] * board[i][z-1];
						
						// Save values so we can fill back in when back-tracking
						int var1 = board[i][z-2];
						int var2 = board[i][z-1];
						board[i][z] = board[i][z-2];
						board[i][z-1] = 0;
						board[i][z-2] = 0;
						
						// Based on whose turn, call recursion with new score
						if (myTurn) {
							int result = recursion(board, myScore + val, childScore, !myTurn);
							biggestDiff = Math.max(result, biggestDiff);
						} else {
							int result = recursion(board, myScore, childScore + val, !myTurn);
							biggestDiff = Math.min(result, biggestDiff);
						}
						
						// Backtrack board
						board[i][z-1] = var2;
						board[i][z-2] = var1;
						board[i][z] = 0;
						
					}
					
					// Horizontal Right
					if (z < 3 && board[i][z+1] > 0 && board[i][z+2] > 0) {
						
						recursionHappened = true;
						
						// Find score from the move
						int val = board[i][z+2] * board[i][z+1];
						
						// Save values so we can fill back in when back-tracking
						int var1 = board[i][z+2];
						int var2 = board[i][z+1];
						board[i][z] = board[i][z+2];
						board[i][z+1] = 0;
						board[i][z+2] = 0;
						
						// Based on whose turn, call recursion with new score
						if (myTurn) {
							int result = recursion(board, myScore + val, childScore, !myTurn);
							biggestDiff = Math.max(result, biggestDiff);
						} else {
							int result = recursion(board, myScore, childScore + val, !myTurn);
							biggestDiff = Math.min(result, biggestDiff);
						}
						
						// Backtrack board
						board[i][z+1] = var2;
						board[i][z+2] = var1;
						board[i][z] = 0;
						
					}
					
					// Diagonal Down
					if (i < 3 && z < 3 && board[i+1][z+1] > 0 && board[i+2][z+2] > 0) {
						recursionHappened = true;
						
						// Find score from the move
						int val = board[i+2][z+2] * board[i+1][z+1];

						// Save values so we can fill back in when back-tracking
						int var1 = board[i+2][z+2];
						int var2 = board[i+1][z+1];
						board[i][z] = board[i+2][z+2];
						board[i+1][z+1] = 0;
						board[i+2][z+2] = 0;
						
						// Based on whose turn, call recursion with new score
						if (myTurn) {
							int result = recursion(board, myScore + val, childScore, !myTurn);
							biggestDiff = Math.max(result, biggestDiff);
						} else {
							int result = recursion(board, myScore, childScore + val, !myTurn);
							biggestDiff = Math.min(result, biggestDiff);
						}
						
						// Backtrack board
						board[i+1][z+1] = var2;
						board[i+2][z+2] = var1;
						board[i][z] = 0;
						
					}
					
					// Diagonal Up
					if (i > 1 && z > 1 && board[i-1][z-1] > 0 && board[i-2][z-2] > 0) {
						
						recursionHappened = true;
						
						// Find score from the move
						int val = board[i-2][z-2] * board[i-1][z-1];
						
						// Save values so we can fill back in when back-tracking
						int var1 = board[i-2][z-2];
						int var2 = board[i-1][z-1];
						board[i][z] = board[i-2][z-2];
						board[i-1][z-1] = 0;
						board[i-2][z-2] = 0;
						
						// Based on whose turn, call recursion with new score
						if (myTurn) {
							int result = recursion(board, myScore + val, childScore, !myTurn);
							biggestDiff = Math.max(result, biggestDiff);
						} else {
							int result = recursion(board, myScore, childScore + val, !myTurn);
							biggestDiff = Math.min(result, biggestDiff);
						}
						
						// Backtrack board
						board[i-1][z-1] = var2;
						board[i-2][z-2] = var1;
						board[i][z] = 0;
						
					}
					
				}
				
			}
			
		}
		

		// If no move as played, return the score difference
		// Else, return the biggestDifference from the recursion calls
		if (!recursionHappened) {
			return myScore - childScore;
		} else {
			return biggestDiff;
		}
		
	}
	
	private static void printBoard(int[][] board) {
		
		System.out.println();
		
		for (int a[] : board) {
			for (int b: a) {
				System.out.print(b + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		
	}
	
	public static void main (String[] args) {
		
		int[][] board = 
			{
					{3, -1, -1, -1, -1},
					{1, 6, -1, -1, -1},
					{1, 7, 8, -1, -1},
					{5, 0, 3, 4, -1},
					{9, 3, 2, 1, 9},
			};
		
		System.out.println(game_recursion(board));
	}
		

}

