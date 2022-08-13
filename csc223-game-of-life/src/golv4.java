/**

 * Write a description of class.

 *

 * @author (your name)

 * @version (a version number or a date)

 */

import java.util.Scanner;

public class golv4

{

    // initialise instance variables

    final static int ROWS = 10, COLS = 10;

    static int generationNum = 0;

 

    public static void main(String[] args)

    {

        PrintTitle();

 

        System.out.println();

        System.out.println("Welcome to Conway's Game of Life. Choose a pattern to run");

        PrintOptions();

 

        // The grid is outside of the for loop so we keep it

        int[][] grid = null;        

        Scanner keyboard = new Scanner(System.in);

 

        for(int generation = 0; true ; )

        {

            // Pick a pattern to run

            String choice = keyboard.nextLine();

                       

            if (choice.equals("1")){

                grid = CreateBlinker();                

                generation = 0;

                startNewGame(keyboard, grid);

            }

            else if (choice.equals("2")){

                grid = CreateGlider();

                generation = 0;

                startNewGame(keyboard, grid);

            }

            else if (choice.equalsIgnoreCase("E")){

                keyboard.close();

                System.exit(0);

            }

            else if (grid != null && choice.equals("")){

                // Continue with next generation

            }

            else if (grid != null && choice.equalsIgnoreCase("M")){

                PrintOptions();

                // Go back to the top of the loop with continue

                continue;

            }

            else {

                System.out.println("Invalid choice");

                PrintOptions();

                // Go back to the top of the loop with continue

                continue;

            }

                   

            // Calculate next generation

            generation++;

            grid = nextGeneration(grid, ROWS, COLS);        

            Print(ROWS, COLS, grid);            

 

            System.out.println("Generation: " + generation + ". [ENTER] to continue. [M]enu. [E]xit");

        }

    }

 

    private static void startNewGame(Scanner keyboard, int[][] grid) {  

        Print(ROWS, COLS, grid);    

        System.out.println("Generation: 0. ENTER to continue. M for Menu. E to exit");

        keyboard.nextLine();

    }

 

    private static void PrintOptions() {

        System.out.println("1. Blinker");

        System.out.println("2. Glider");

        System.out.println("3. Random");

        System.out.println("4. Custom");

        System.out.println("E. Exit");

    }

 

    // Prints grid but does not update it

    private static void Print(int ROWS, int COLS, int[][] grid)

    {  

        PrintHorizontalBorder();

             

        for (int row = 0; row < ROWS; row++)

        {

            System.out.print("| ");

            for (int col = 0; col < COLS; col++)

            {

                if (grid[row][col] == 0)

                    //System.out.print(" 💄 ");

                    System.out.print(" - ");

                else

                    //System.out.print(" 💋 ");

                    System.out.print(" 0 ");

            }

            System.out.print("|");

            System.out.println();

        }

 

        PrintHorizontalBorder();  

    }

 

    private static void PrintHorizontalBorder() {

        int borderWidth = COLS + 1;

        for (int j = 0; j < borderWidth; j++) {

            System.out.print("---");

        }

        System.out.println();

    }

 

    private static void PrintTitle()

    {        

        System.out.println("\f");

        System.out.println('\u000c');

   

        System.out.println(" __     __    _      ____      ___   ____      _     _   ____  ____ ");

        System.out.println("/ /`_  / /\\  | |\\/| | |_      / / \\ | |_      | |   | | | |_  | |_  ");

        System.out.println("\\_\\_/ /_/--\\ |_|  | |_|__     \\_\\_/ |_|       |_|__ |_| |_|   |_|__ ");

    }

 

    // Function to calculate next generation, does not print

    // Returns the next generation of the grid

    private static int[][] nextGeneration(int[][] grid, int ROWS, int COLS)

    {

        int[][] future = new int[ROWS][COLS];

       

        // Loop through every cell

        for (int row = 0; row < ROWS; row++)

        {

            for (int col = 0; col < COLS; col++)

            {

                int aliveNeighbours = 0;

                // top left

                aliveNeighbours += IsNeighbourAlive(grid, row, col, -1, -1);

                // top middle

                aliveNeighbours += IsNeighbourAlive(grid, row, col, 0, -1);

                // top right

                aliveNeighbours += IsNeighbourAlive(grid, row, col, 1, -1);

                // middle left

                aliveNeighbours += IsNeighbourAlive(grid, row, col, -1, 0);

                // middle right

                aliveNeighbours += IsNeighbourAlive(grid, row, col, 1, 0);

                // bottom left

                aliveNeighbours += IsNeighbourAlive(grid, row, col, -1, 1);

                // bottom middle

                aliveNeighbours += IsNeighbourAlive(grid, row, col, 0, 1);

                // bottom right

                aliveNeighbours += IsNeighbourAlive(grid, row, col, 1, 1);

                // Implementing the Rules of Life

 

                // Cell is lonely and dies

                if ((grid[row][col] == 1) && (aliveNeighbours < 2))

                    future[row][col] = 0;

 

                // Cell dies due to over population

                else if ((grid[row][col] == 1) && (aliveNeighbours > 3))

                    future[row][col] = 0;

 

                // A new cell is born

                else if ((grid[row][col] == 0) && (aliveNeighbours == 3))

                    future[row][col] = 1;

 

                // Remains the same

                else

                    future[row][col] = grid[row][col];

            }

        }

        return future;

    }

   

    // Function to check if cell is alive in next generation, returns 0 for dead, 1 for alive

    private static int IsNeighbourAlive(int[][] grid, int row, int col, int horizontal, int vertical)

    {

        int neighbourCol = col;

        int neighbourRow = row;

       

        // Can we go left?

        if (horizontal == 1)

        {

            if (col == 0)

                neighbourCol = COLS - 1;

            else

                neighbourCol = col - 1;

        }

       

        // Can we go up?

        if (vertical == -1)

        {

            if (row == 0)

                neighbourRow = ROWS - 1;

            else

                neighbourRow = row - 1;

        }

       

        // Can we go right?

        if (horizontal == -1)

        {

            if (col == COLS - 1)

                neighbourCol = 0;

            else

                neighbourCol = col + 1;

        }

       

        // Can we go down?

        if (vertical == 1)

        {

            if (row == ROWS - 1)

                neighbourRow = 0;

            else

                neighbourRow = row + 1;

        }

       

        // Is the neighbour alive?

        return grid[neighbourRow][neighbourCol];

    }

 

    public static int[][] CreateBlinker(){

        int[][] grid =

            {

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }

            };

            return grid;

          }

   

      public static int[][] CreateGlider(){

        int[][] grid =

            {

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }

            };

            return grid;

          }

   

      public static int[][] CreatePulsar(){

        int[][] grid =

            {

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }

            };

            return grid;

      }

}