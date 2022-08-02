/**
 * Write a description of class twodimensionalarray here.
 *
 * @Mila van Stokkum
 * @16.05/2022
 */
import java.util.Scanner;
public class borderTestAllAlive
{
    // instance variables - replace the example below with your own
    final int ROWS = 16;
    final int COLS = 16;
    int dead = 0;    
    int futureBoard[][] = new int[ROWS][COLS];
    int currentBoard[][] = new int [ROWS][COLS];
    int whatToRunInt = 0;
    /**
     * Constructor for objects of class twodimensionalarray
     */
    public borderTestAllAlive()
    {
        // initialise instance variables
        //createBlinker();
        //createPulsar();
        System.out.println('\u000c');
        Scanner keyboard = new Scanner(System.in);
        System.out.println("☻");
        System.out.println("type 1 for blinker, 2 for pulsar or 3 for regular GOL");
        String whatToRun = keyboard.nextLine();
        whatToRunInt = Integer.parseInt(whatToRun);
        if(whatToRunInt == 1){
            createBlinker();
        }
        if(whatToRunInt == 2){
            createPulsar();
        }
        if(whatToRunInt == 3){
            for(int row=0; row<ROWS; row++){
            for(int col=0; col<COLS; col++){                
                currentBoard[row][col] = (int)(Math.floor(Math.random()*(1-0+1)+0));
            }
        }
            printBoard();
        }
        System.out.println("how many iterations do you want?");
        String iterations = keyboard.nextLine();
        int iterationNum = Integer.parseInt(iterations);

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
   
    public void createBlinker(){
        currentBoard[2][4] = 1;
        currentBoard[3][4] = 1;
        currentBoard[4][4] = 1;
    }
   
    public void createPulsar(){
        //top left
        currentBoard[1][5] = 1;
        currentBoard[2][5] = 1;
        currentBoard[3][5] = 1;
        currentBoard[3][6] = 1;
        currentBoard[5][1] = 1;
        currentBoard[5][2] = 1;
        currentBoard[5][3] = 1;
        currentBoard[6][3] = 1;
        currentBoard[6][5] = 1;
        currentBoard[5][6] = 1;
        currentBoard[5][7] = 1;
        currentBoard[6][7] = 1;
        currentBoard[7][5] = 1;
        currentBoard[7][6] = 1;
        //top right
        currentBoard[1][11] = 1;
        currentBoard[2][11] = 1;
        currentBoard[3][11] = 1;
        currentBoard[3][10] = 1;
        currentBoard[5][9] = 1;
        currentBoard[5][10] = 1;
        currentBoard[6][9] = 1;
        currentBoard[6][11] = 1;
        currentBoard[7][10] = 1;
        currentBoard[7][11] = 1;
        currentBoard[5][13] = 1;
        currentBoard[5][14] = 1;
        currentBoard[5][15] = 1;
        currentBoard[6][13] = 1;
        //lower left
        currentBoard[11][1] = 1;
        currentBoard[11][2] = 1;
        currentBoard[11][3] = 1;
        currentBoard[10][3] = 1;
        currentBoard[9][5] = 1;
        currentBoard[9][6] = 1;
        currentBoard[10][5] = 1;
        currentBoard[10][7] = 1;
        currentBoard[11][7] = 1;
        currentBoard[11][6] = 1;
        currentBoard[13][5] = 1;
        currentBoard[13][6] = 1;
        currentBoard[14][5] = 1;
        currentBoard[15][5] = 1;
        //lower right
        currentBoard[9][10] = 1;
        currentBoard[9][11] = 1;
        currentBoard[10][9] = 1;
        currentBoard[10][11] = 1;
        currentBoard[11][9] = 1;
        currentBoard[11][10] = 1;
        currentBoard[10][13] = 1;
        currentBoard[11][13] = 1;
        currentBoard[11][14] = 1;
        currentBoard[11][15] = 1;
        currentBoard[13][10] = 1;
        currentBoard[13][11] = 1;
        currentBoard[14][11] = 1;
        currentBoard[15][11] = 1;
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
                        //System.out.print(" □ ");
                        System.out.print(" - ");
                    }
                    if(currentBoard[row][col] == 1){
                        System.out.print(" ■ ");
                    }
                //System.out.print(currentBoard[row][col] + " ");
                futureBoard[row][col] = isAlive(row, col);
            }
            System.out.println();
        }
        //System.out.println('\u000c'); //clears everything, too fast
    }

    int isAlive(int row, int col)
    {
        int neighbours = 0;
        int deadOrAlive = 0;
        // Top left ↖
        if (row-1 >=0 && col-1 >= 0) {
            neighbours += currentBoard[row-1][col-1];
        }else{
            neighbours += 1;
        }
        // Top middle ⬆
        if (row-1 >= 0) {
            neighbours += currentBoard[row-1][col];
        }else{
            neighbours += 1;
        }
        // Top right ↗
        if (row-1 >= 0 && col+1 < COLS) {
            neighbours += currentBoard[row-1][col+1];
        }else{
            neighbours += 1;
        }
        // Left ⬅
        if (col-1 >= 0) {
            neighbours += currentBoard[row][col-1];
        }else{
            neighbours += 1;
        }
        // Right ➡
        if (col+1 < COLS) {
            neighbours += currentBoard[row][col+1];
        }else{
            neighbours += 1;
        }
        // Bottom left ↙
        if (row+1 < ROWS && col-1 >= 0) {
            neighbours += currentBoard[row+1][col-1];
        }else{
            neighbours += 1;
        }
        // Bottom middle ⬇
        if (row+1 < ROWS) {
            neighbours += currentBoard[row+1][col];
        }else{
            neighbours += 1;
        }
        // Bottom right ↘
        if (row+1 < ROWS && col+1 < COLS) {
            neighbours += currentBoard[row+1][col+1];
        }else{
            neighbours += 1;
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