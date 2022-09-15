public class WordPuzzle
{
	public static void main(String args[])
	{
		//Source 15x15 puzzle board with random characters
		char[][] sboard = {
				{'R', 'Z', 'H', 'O', 'E', 'A', 'Y', 'Y', 'N', 'S', 'W', 'L', 'Z', 'W', 'L'},
				{'F', 'E', 'K', 'Q', 'R', 'F', 'Q', 'D', 'N', 'N', 'P', 'V', 'Y', 'U', 'Y'},
				{'T', 'G', 'M', 'R', 'C', 'S', 'U', 'O', 'I', 'O', 'F', 'O', 'Z', 'Q', 'Z'},
				{'E', 'C', 'A', 'M', 'G', 'R', 'I', 'U', 'H', 'I', 'Z', 'C', 'O', 'I', 'K'},
				{'I', 'Y', 'W', 'H', 'A', 'T', 'Y', 'V', 'L', 'S', 'Q', 'A', 'Z', 'L', 'H'},
				{'S', 'F', 'H', 'A', 'C', 'R', 'L', 'F', 'A', 'I', 'S', 'C', 'D', 'J', 'I'},
				{'V', 'S', 'T', 'N', 'W', 'K', 'G', 'Z', 'L', 'C', 'N', 'I', 'J', 'S', 'N'},
				{'A', 'R', 'U', 'E', 'S', 'R', 'U', 'O', 'C', 'E', 'N', 'G', 'J', 'O', 'M'},
				{'I', 'F', 'P', 'E', 'K', 'E', 'X', 'H', 'R', 'D', 'E', 'O', 'H', 'F', 'U'},
				{'B', 'T', 'H', 'T', 'P', 'E', 'Q', 'A', 'Y', 'P', 'E', 'L', 'K', 'T', 'T'},
				{'R', 'O', 'N', 'S', 'C', 'P', 'L', 'U', 'S', 'P', 'L', 'U', 'S', 'W', 'G'},
				{'W', 'Y', 'V', 'X', 'Z', 'I', 'P', 'H', 'E', 'Y', 'I', 'E', 'Y', 'A', 'X'},
				{'Z', 'W', 'E', 'U', 'B', 'P', 'B', 'A', 'W', 'N', 'A', 'X', 'P', 'R', 'Z'},
				{'K', 'T', 'H', 'R', 'H', 'D', 'D', 'J', 'Z', 'I', 'C', 'H', 'C', 'E', 'J'},
				{'L', 'Z', 'T', 'H', 'I', 'V', 'C', 'S', 'Z', 'I', 'H', 'E', 'K', 'C', 'N'},
			     };
		//Desired words to be searched
		String[] words={"ARRAYS", "COURSE", "CPLUSPLUS", "DECISIONS", "FUNCTIONS", "LOGIC", "LOOPS", "PROGRAMMER", "SEQUENCE", "SOFTWARE"}; 
		int i, j;

		//A 15x15 buffer to store bit pattern to store character status 1 if found 9 if not found
		int[][] result=new int[15][15];
		
		//Displaying Source board
		System.out.println("\nSource Pattern Board:\n");
		for(i=0;i<15;i++)
		{
			for(j=0;j<15;j++)
			{
				System.out.print((char)sboard[i][j]+" ");
			}
			System.out.println();
		}
		//Caling chkWord() method to check every character of the word for all words in the source array
		for(i=0; i<words.length;i++)
		{
			chkWord(sboard, result,words[i]);
		}
		
		//Showing final patters from source board, based on character status result bit pattern buffer
		System.out.println("\nFinal Pattern:\n");
		for(i=0;i<15;i++)
		{
			for(j=0;j<15;j++)
			{
				if(result[i][j]==1)
				{
					System.out.print(sboard[i][j]+" ");	//The character will be displayed if bit value is 1
				}
				else
				{
					System.out.print("  ");			//Otherwise just a space
				}
			}
			System.out.println();
		}
	}

	//This method is useful to check every word in the character board (may be multiple time)
	public static void chkWord(char[][] sboard, int[][]result, String word) 
	{
		for(int i=0; i<sboard.length; i++)
		{
        		for(int j=0; j<sboard[0].length; j++)
			{
				//Calling find() method to check the availability of the character 
           			find(sboard,result,word,i,j,0);
        		}
    		}
 
    		
	}
	public static boolean find(char[][] board, int[][] result, String word, int i, int j, int k)
	{
    		//Terminates if boundary encountered
    		if(i<0 || j<0 || i>=board.length || j>=board[0].length)
		{
        		return false;
    		}
 		//If the character is matched
    		if(board[i][j] == word.charAt(k))
		{
        		//Looking for the next character recursively
			//Last character is searched
        		if(k==word.length()-1)
			{
				result[i][j]=1;		//Setting 1 if the character is confirmed
				return true;
        		}
			else 
			{
				//Call recursively this method applyig all 8 directions-
				//left to right (i, j+1), right to left (i, j-1), top to bottom (i+1, j), bottom to top (i-1, j), 
				//right diagonal down (i+1, j+1), right diagonal up (i-1, j+1), 
				//left diagonal down (i+1, j-1), left diagonal up (i-1, j-1)

				if(find(board, result, word, i+1, j+1, k+1)||find(board, result, word, i+1, j-1, k+1)||find(board, result, word, i-1, j-1, k+1)||find(board, result, word, i-1, j+1, k+1)||find(board, result, word, i-1, j, k+1)||find(board, result, word, i+1, j, k+1)||find(board, result, word, i, j-1, k+1)||find(board, result, word, i, j+1, k+1))
				{
					result[i][j]=1;	//Setting 1 if the character is confirmed
					return true;
        			}
        			
    			}
 		}
    		return false;
	}
}

