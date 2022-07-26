/**
 * Write a description of class twodimensionalarray here.
 *
 * @Mila van Stokkum
 * @16.05/2022
 */
import java.util.Scanner;
public class correctxywraparound
{
    // instance variables - replace the example below with your own
    private int x;
    final int ROWS = 10;
    final int COLS = 10;
    int dead = 0;    
    //String aliveFace = "☻";
    int futureBoard[][] = new int[COLS][ROWS];
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
            {0,0,0,0,0,0,0,0,0,0}
        };
    /**
     * Constructor for objects of class twodimensionalarray
     */
    public correctxywraparound()
    {
        // initialise instance variables
        System.out.println('\u000c');
        Scanner keyboard = new Scanner(System.in);
        System.out.println("☻");
        System.out.println("How many iterations do you want?");
        String iterations = keyboard.nextLine();
        int iterationNum = Integer.parseInt(iterations);
        // for(int x=0; x<COLS; x++){
            // for(int y = 0; y<ROWS; y++){                
                // //currentBoard[x][y] = (int)(Math.floor(Math.random()*(1-0+1)+0));
            // }
        // }

        for(int i=0; i<iterationNum; i++){
            printBoard();
            System.out.println();
            for(int y = 0; y<ROWS; y++)
                for(int x=0; x<COLS; x++)
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
        for(int y = 0; y<ROWS; y++){
            for(int x=0; x<COLS; x++){                
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
        deadOrAlive = 0;
        if(x == 0 || y == 0 || x == COLS - 1 || y == ROWS - 1){
            futureBoard[x][y] = ifAliveEdge (x, y);
        }else{
            neighbours = (currentBoard[x][y-1] + currentBoard[x][y+1] + currentBoard[x-1][y] +
                          currentBoard[x+1][y] + currentBoard[x-1][y-1] + currentBoard[x+1][y+1] +
                          currentBoard[x-1][y+1] + currentBoard[x+1][y-1]);
                         
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

    public int ifAliveEdge(int x, int y)
    {
        // put your code here
        //if(currentBoard[x][y] == 0)
        int deadOrAlive;
        int neighbours;
        // Top left ↖
        if (row-1 >=0 && col-1 >= 0) {
            neighbours += currentBoard[row-1][col-1];
        }else{
            neighbours += (currentBoard[ROWS-1][COLS-1] + currentBoard[0][ROWS] + currentBoard[COLS][0]);
        }
        // Top middle ⬆
        if (row-1 >= 0) {
            neighbours += currentBoard[row-1][col];
        }else{
            neighbours += (currentBoard[row-1][COLS-1]);
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
        return deadOrAlive;
    }

}
//}