
public class Board {

	public char[][] boardContents = {{' ', ' ', ' '}, {' ',
			' ', ' '}, {' ', ' ',' '}};
	public Board()
	{
		
	}
	
	public boolean isValid(int b, int c)
	{
		if((b < 3 && b > -1) && (c < 3 && c > -1) && boardContents[b][c] == ' ')
		{
			return true;
		}
		return false;
	}
	
	public void printBoard()
	{
		System.out.printf("      |      |  %n");
		System.out.printf(" %3C  | %3C  | %3C %n", boardContents[0][0], boardContents[0][1], boardContents[0][2]);
		System.out.printf("_____________________%n%n");
		System.out.printf("      |      |  %n");
		System.out.printf(" %3C  | %3C  | %3C %n", boardContents[1][0], boardContents[1][1], boardContents[1][2]);
		System.out.printf("_____________________%n%n");
		System.out.printf("      |      |  %n");
		System.out.printf(" %3C  | %3C  | %3C %n", boardContents[2][0], boardContents[2][1], boardContents[2][2]);
		System.out.println("\n\n");
	}
	
	public void updateBoard(int n, int m, char c)
	{
		if(boardContents[n][m] == ' ')
		{
			boardContents[n][m] = c;
		}
	}
	
	public char[][] getBoard()
	{
		return boardContents;
	}
	

	
	public char[][] getDiagnals()
	{
		char[][] diagnals = {{boardContents[0][0], boardContents[1][1],
					boardContents[2][2]}, {boardContents[2][0], boardContents[1][1],
						boardContents[0][2]}};
		return diagnals;
	}
	
	
	public char[][] getCols()
	{
		char[][] cols = {{boardContents[0][0], boardContents[1][0],
			boardContents[2][0]}, {boardContents[0][1], boardContents[1][1],
			boardContents[2][1]},{boardContents[0][2], boardContents[1][2],
			boardContents[2][2]}};
		return cols;
	}
	
	
	public boolean checkBoard(char c)
	{
		//check all rows for completion
		if(checkRows(c))
			return true;
		if(checkCols(c))
			return true;
		if(checkDiagnals(c))
			return true;
		return false;
	}
	
	public boolean checkTie()
	{
		boolean tie = true;
		for(char[] charArray : boardContents)
		{
			for(char c : charArray)
			{
				if(c == ' ')
				{
					tie = false;
				}
			}
		}
		return tie;
	}
	
	public boolean checkRows(char c)
	{
		boolean completion = false;
		for(char[] charArray : boardContents)
		{
			if(numberOfInstances(charArray, c) == 3)
			{
				completion = true;
			}
		}
		return completion;
	}
	
	public boolean checkCols(char c)
	{
		boolean completion = false;
		for(char[] charArray : getCols())
		{
			if(numberOfInstances(charArray, c) == 3)
			{
				completion = true;
			}
		}
		return completion;
	}
	
	public boolean checkDiagnals(char c)
	{
		boolean completion = false;
		for(char[] charArray : getDiagnals())
		{
			if(numberOfInstances(charArray, c) == 3)
			{
				completion = true;
			}
		}
		return completion;
	}
	
	public int numberOfInstances(char[] array, char ch)
	{
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
}
