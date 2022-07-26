/**
 * Write a description of class twodimensionalarray here.
 *
 * @Mila van Stokkum
 * @16.05/2022
 */
import java.util.Scanner;
public class wrapAroundBorder
{
    // instance variables - replace the example below with your own
    final int ROWS = 10;
    final int COLS = 10;
    int dead = 0;    
    //String aliveFace = "☻";
    int futureBoard[][] = new int[ROWS][COLS];
    //int futureBoard[][] = new int [ROWS][COLS];  
    int currentBoard[][] = new int [ROWS][COLS];
    // {
    // {0,0,0,0,0,0,0,0,0,0},
    // {0,0,0,0,0,0,0,0,0,0},
    // {0,0,0,0,1,0,0,0,0,0},
    // {0,0,0,0,1,0,0,0,0,0},
    // {0,0,0,0,1,0,0,0,0,0},
    // {0,0,0,0,0,0,0,0,0,0},
    // {0,0,0,0,0,0,0,0,0,0},
    // {0,0,0,0,0,0,0,0,0,0},
    // {0,0,0,0,0,0,0,0,0,0},
    // {0,0,0,0,0,0,0,0,0,0}
    // };
    /**
     * Constructor for objects of class twodimensionalarray
     */
    public wrapAroundBorder()
    {
        // initialise instance variables
        // currentBoard[2][4] = 1;
        // currentBoard[3][4] = 1;
        // currentBoard[4][4] = 1;
        System.out.println('\u000c');
        Scanner keyboard = new Scanner(System.in);
        System.out.println("☻");
        System.out.println("How many iterations do you want?");
        String iterations = keyboard.nextLine();
        int iterationNum = Integer.parseInt(iterations);
        for(int row=0; row<ROWS; row++){
            for(int col=0; col<COLS; col++){                
                currentBoard[row][col] = (int)(Math.floor(Math.random()*(1-0+1)+0));
            }
        }

        for(int i=0; i<iterationNum; i++){
            printBoard();
            System.out.println();
            for(int row=0; row<ROWS; row++)
                for(int col=0; col<COLS; col++)
                    currentBoard[row][col] = futureBoard[row][col];

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
        for(int row=0; row<ROWS; row++){
            for(int col=0; col<COLS; col++){                
                if(currentBoard[row][col] == 0){
                    System.out.print(" □ ");
                }
                if(currentBoard[row][col] == 1){
                    System.out.print(" ■ ");
                }
                //System.out.print(currentBoard[row][col] + " ");
                futureBoard[row][col] = isAlive(row, col);
            }
            System.out.println();
        }
    }

    int isAlive(int row, int col)
    {
        int neighbours = 0;
        int deadOrAlive = 0;
        // Top left ↖
        if (row-1 >=0 && col-1 >= 0) {
            neighbours += currentBoard[row-1][col-1];
        }else{
            neighbours += (currentBoard[ROWS-1][COLS-1]);
        }
        // Top middle ⬆
        if (row-1 >= 0) {
            neighbours += currentBoard[row-1][col];
        }else{
            neighbours += currentBoard[row-1][COLS-1];
        }
        // Top right ↗
        if (row-1 >= 0 && col+1 < COLS) {
            neighbours += currentBoard[row-1][col+1];
        }else{
            neighbours += currentBoard[0][COLS];
        }
        // Left ⬅
        if (col-1 >= 0) {
            neighbours += currentBoard[row][col-1];
        }else{
            neighbours += currentBoard[ROWS][col];
        }
        // Right ➡
        if (col+1 < COLS) {
            neighbours += currentBoard[row][col+1];
        }else{
            neighbours += currentBoard[row][COLS];
        }
        // Bottom left ↙
        if (row+1 < ROWS && col-1 >= 0) {
            neighbours += currentBoard[row+1][col-1];
        }else{
            neighbours += currentBoard[0][COLS];
        }
        // Bottom middle ⬇
        if (row+1 < ROWS) {
            neighbours += currentBoard[row+1][col];
        }
        // Bottom right ↘
        if (row+1 < ROWS && col+1 < COLS) {
            neighbours += currentBoard[row+1][col+1];
        }
        if(currentBoard[row][col] == 0){
            if(neighbours == 3){
                deadOrAlive = 1;
            }else {
                deadOrAlive = 0;
            }
        }
        else if(currentBoard[row][col] == 1){
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
        return deadOrAlive;
    }

}