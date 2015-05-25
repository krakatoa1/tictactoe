import java.util.ArrayList;
import java.util.Random;
public class AI {

	public AI()
	{
		
	}
	
	/**
	 * 
	 * @param rows, equiv. to board.getBoard(), array of char arrays
	 * @param cols, board.getCols()
	 * @param diagnals, board.getDiagnals()
	 * @param c, the enemy character value to test appropriate locations
	 * @return array of ints, 2-value row/col representation
	 */
	public static int[] move(char[][] rows, char[][] cols, 
				char[][] diagnals, char c)
	{
		boolean moveMade = false;
		//stores values AI is to pick to relay to board to update table
		int[] arrayChoice = new int[2];
		
		/*
		 * This portion of code tests to see if the player is able
		 * to win on their next turn, with 2 marks on a certain
		 * column, row or diagonal, and forces the AI's move to counter
		 * this
		 */
		
		//check rows for 2 occupied by enemy character, marks within row
		for(int k = 0; k < 3; k++)
		{
			if(numberOfInstances(rows[k], c) == 2)
			{
				for(int j = 0; j < 3; j++)
				{
					if(rows[k][j] == ' ')
					{
						arrayChoice[0] = k; arrayChoice[1] = j;
						moveMade = true;
					}
				}
			}
		}
		
		//check columns for 2 occupied by enemy character, marks there
		for(int k = 0; k < 3; k++)
		{
			if(numberOfInstances(cols[k], c) == 2)
			{
				System.out.println("hit");
				for(int j = 0; j < 3; j++)
				{
					if(cols[k][j] == ' ')
					{
						System.out.println("Hit");
						arrayChoice[0] = j; arrayChoice[1] = k;
						moveMade = true;
					}
				}
			}
		}
		
		//check diagonals for 2 occupied by enemy character, marks there
		//for first diagonal
		if(numberOfInstances(diagnals[0], c) == 2)
		{
			for(int k = 0; k < 3; k++)
			{
				if(diagnals[0][k] == ' ')
				{
					arrayChoice[0] = k; arrayChoice[1] = k;
					moveMade = true;
				}
			}
			
		}
		
		//for second diagonal
		if(numberOfInstances(diagnals[1], c) == 2)
		{
			for(int k = 0; k < 3; k++)
			{
				if(diagnals[1][k] == ' ')
				{
					arrayChoice[0] = 2-k; arrayChoice[1] = k;
					moveMade = true;
				}
			}
		}
	
			
	/* This portion of the code focuses on special situations where
	 * the AI should make its mark at a certain location for
	 * the best strategy
	 */
	
	if(!(moveMade))
	{
		int[] specialSelection = specialChoice(rows, c);
		//specialChoice(char[][], char) returns -1 for both values
		//if there is no special move to be made
		if(specialSelection[0] != -1)
		{
			arrayChoice = specialSelection;
			moveMade = true;
		}
	}

	/*This portion chooses a random open space for the AI to mark
	 * if it is not at an immediate risk of losing and
	 * there are no special moves to be made
	 */
	if(!(moveMade))
	{
	ArrayList<Integer> placeList = new ArrayList<Integer>();
	
	for(int k = 0; k < 3; k++)
	{
		for(int j = 0; j < 3; j++)
		{
			if(rows[k][j] == ' ')
			{
				placeList.add(k); 
				placeList.add(j); 
			}
		}
	}
	
	Random rn = new Random();
	int selection = 2*rn.nextInt((placeList.size()/2));
	arrayChoice[0] = placeList.get(selection);
	arrayChoice[1] = placeList.get(selection+1);
	}
	
	
	//return AI choice
	return arrayChoice;	
	}
	
	public static int numberOfInstances(char[] array, char ch)
	{
		//assumes length of 3 for all arrays
		int numInstances = 0;
		for(char c : array)
		{
			// if the array element (c) is equal to the element being
			// looked for (ch)
			if(c == ch)
			{
				numInstances++;
			}
		}
		return numInstances;
		
	}
	/**
	 * 
	 * @param board, standard row array of chars
	 * @param c, player character
	 * @return an array containing the appropriate choice if there is
	 * one, otherwise an array containing [-1,-1]
	 */
	public static int[] specialChoice(char[][] board, char c)
	{
		/*Special move 1: first move by AI (if AI is first)
		 *will always be in the corner (apparently good strategy
		 *according to the howto wiki on tic-tac-toe) 
		 */
		boolean boardIsClear = true;
		int row = -1;
		int col = -1;
		int[] selection = new int[2];
		for(char[] charArray : board)
		{
			for(char ch : charArray)
			{
				if(c == ' ')
					boardIsClear = false;
			}
		}
		
		if(boardIsClear)
		{
			if(Math.random() < 0.5)
				row = 0;
			else
				row = 2;
			if(Math.random() < 0.5)
				col = 0;
			else
				col = 2;
				
		}
		
		/*Special move 2: if AI is second and opponent put their
		 * mark in a corner, the AI will put its in the center
		 */
		int enemyMarks = 0;
		//ensure player only has 1 mark
		for(char[] charArray : board)
		{
			for(char ch : charArray)
			{
				if(ch == c)
					enemyMarks++;
			}
		}
		
		//ensure player has mark placed in corner, make selection if so
		if((board[0][0] == c || board[0][2] == c || board[2][0] == c
				|| board[2][2] == c) && enemyMarks == 1 )
		{
			row = 1; col = 1;
		}
			
		
		selection[0] = row;
		selection[1] = col;
		return selection;
	}
	
	
	
}
