
import java.util.Scanner;
public class Play 
{
	
	public static void win(String s)
	{
		System.out.println(s + " won!");
	}
	
	public static void tie()
	{
		System.out.println("Tie. Everyone loses!");
	}
	
	public static void main(String[] args)
	{
		int playerChoice1;
		int playerChoice2;
		Board board = new Board();
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to the game");
		System.out.println("Would you like to be X or O? (Type X or O)");
		//first character of submitted string response
		char playerChar = in.next().charAt(0);
		char aiChar;
		//set ai character to be used
		if(playerChar == 'O')
		{
			 aiChar = 'X';
		}
		else
			 aiChar = 'O';
		
		//determine who starts first
		boolean playerFirst = false;
		if(Math.random() < 0.5)
		{
			playerFirst = true;
		}
		
		System.out.println("\n\n");
		
		while(true)
		{
			if(playerFirst)
			{
				System.out.println("Please make a move. Type 1-3 then "
						+ "1-3 again to "
						+ "choose which square you want to mark");
				while(true)
				{
				playerChoice1 = in.nextInt() - 1;
				playerChoice2 = in.nextInt() - 1;
				if(board.isValid(playerChoice1, playerChoice2))
				{
					break;
				}
				else
					System.out.println("Invalid choice, please choose again");
				}
				board.updateBoard(playerChoice1, playerChoice2, playerChar);
				if(board.checkBoard(playerChar))
				{
					board.printBoard();
					Play.win("Player");
					break;
				}
				if(board.checkTie())
				{
					board.printBoard();
					tie();
					break;
				}
			//AI's turn	
			System.out.println("The AI will now make its move");
			int[] aiMove = AI.move(board.getBoard(), board.getCols(), board.getDiagnals(),
					playerChar);
			board.updateBoard(aiMove[0], aiMove[1], aiChar);
			board.printBoard();
			if(board.checkBoard(aiChar))
			{
				board.printBoard();
				Play.win("AI");
				break;
			}
			
			if(board.checkTie())
			{
				tie();
				break;
			}
			
			}
			
			else if(!playerFirst)
			{
				//AI's turn	
				System.out.println("The AI will now make its move");
				int[] aiMove = AI.move(board.getBoard(), board.getCols(), board.getDiagnals(),
						playerChar);
				board.updateBoard(aiMove[0], aiMove[1], aiChar);
				board.printBoard();
				if(board.checkBoard(aiChar))
				{
					board.printBoard();
					Play.win("AI");
					break;
				}
				
				if(board.checkTie())
				{
					tie();
					break;
				}
				
				System.out.println("Please make a move. Type 1-3 then "
						+ "1-3 again to "
						+ "choose which square you want to mark");
				while(true)
				{
				playerChoice1 = in.nextInt() - 1;
				playerChoice2 = in.nextInt() - 1;
				if(board.isValid(playerChoice1, playerChoice2))
				{
					break;
				}
				else
					System.out.println("Invalid choice, please choose again");
				}
				board.updateBoard(playerChoice1, playerChoice2, playerChar);
				if(board.checkBoard(playerChar))
				{
					board.printBoard();
					Play.win("Player");
					break;
				}
				
				if(board.checkTie())
				{
					tie();
					break;
				}
				
				
			}
		}
		
		
	}
}
