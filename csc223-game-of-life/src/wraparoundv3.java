
/**
 * Write a description of class csharptojavawraparound here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class wraparoundv3
{

    // initialise instance variables
    final static int ROWS = 10, COLS = 10;
    static int generationNum = 0;

    public static void main(String[] args)
    {

        // Initializing the grid.
        int[][] grid =
            {
                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 1 }
            };

        //how many iterations
        System.out.println('\u000c');
        Scanner keyboard = new Scanner(System.in);
        System.out.println("How many iterations do you want?");
        String iterations = keyboard.nextLine();
        generationNum = Integer.parseInt(iterations);
        
        // Displaying the grid
        System.out.println("Original Generation " );
        Print(ROWS, COLS, grid);

        for (int i = 1; i <= generationNum; i++ ){

            System.out.println("generation: " + i);
        
            //next gen
            grid = nextGeneration(grid, ROWS, COLS);
            
            Print(ROWS, COLS, grid);
            
        }

        keyboard.close();
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
        for(int j = 0; j < borderWidth; j ++){
            System.out.print("---");
        }      
            System.out.println();
    }


    // Function to calculate next generation, does not print
    // Returns the next generation of the grid, leaving orignal unchanged 
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

}
