/**
 * Write a description of class twodimensionalarray here.
 *
 * @Mila van Stokkum
 * @16.05/2022
 */
import java.util.Scanner;
public class twodimensionalarray
{
    // instance variables - replace the example below with your own
    private int x;
    final int ROWS = 12;
    final int COLS = 10;
    int dead = 0;    
    //String aliveFace = "☻";
    int futureBoard[][] = new int[ROWS][COLS];
    //int futureBoard[][] = new int [ROWS][COLS];   
    int currentBoard[][] = {
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0}
        };
    /**
     * Constructor for objects of class twodimensionalarray
     */
    public twodimensionalarray()
    {
        // initialise instance variables
        System.out.println('\u000c');
        Scanner keyboard = new Scanner(System.in);
        System.out.println("☻");
        System.out.println("How many iterations do you want?");
        String iterations = keyboard.nextLine();
        int iterationNum = Integer.parseInt(iterations);
        for(int x=0; x<ROWS; x++){
            for(int y = 0; y<COLS; y++){                
                //currentBoard[x][y] = (int)(Math.floor(Math.random()*(1-0+1)+0));
            }
        }

        for(int i=0; i<iterationNum; i++){
            printBoard();
            System.out.println();
            for(int x=0; x<ROWS; x++)
                for(int y = 0; y<COLS; y++)
                    currentBoard[x][y] = futureBoard[x][y];

        }
        System.out.println("  ");
        // for(int x1=0; x1<ROWS; x1++){
        // for(int y1 = 0; y1<COLS; y1++){
        // System.out.print(futureBoard[x1][y1] + " ");
        // }
        // System.out.println();
        // }

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void printBoard(){
        for(int x=0; x<ROWS; x++){
            for(int y = 0; y<COLS; y++){                
                System.out.print(currentBoard[x][y] + " ");
                futureBoard[x][y] = ifAlive(x, y);
            }
            System.out.println();
        }
    }

    public int ifAlive(int x, int y)
    {
        // put your code here
        //if(currentBoard[x][y] == 0)
        int deadOrAlive;
        int neighbours;
        if(x == 0 || y == 0 || x == ROWS - 1 || y == COLS - 1){
            deadOrAlive = 3;
        }else{
            neighbours = (currentBoard[x][y-1] + currentBoard[x][y+1] + currentBoard[x-1][y] + currentBoard[x+1][y] + currentBoard[x-1][y-1] + currentBoard[x+1][y+1] + currentBoard[x-1][y+1] + currentBoard[x+1][y-1]);
            if(currentBoard[x][y] == 0){
                if(neighbours == 3){
                    deadOrAlive = 1;
                }else {
                    deadOrAlive = 0;
                }
            }
            else if(currentBoard[x][y] == 1){
                if(neighbours < 2){
                    deadOrAlive = 0;
                }else if(neighbours == 2 || neighbours == 3){
                    deadOrAlive = 1;
                }else if(neighbours > 3){
                    deadOrAlive = 0;
                }else {
                    deadOrAlive = 0;
                }
            } else{
                deadOrAlive = 0;
            }
        }
        return deadOrAlive;
    }
}