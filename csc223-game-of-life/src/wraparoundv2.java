
/**
 * Write a description of class csharptojavawraparound here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class wraparoundv2
{

    // initialise instance variables
    final static int ROWS = 10, COLS = 10;
    public static void main(String[] args)
    {
        int generationNum = 0;
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
        System.out.println("Original Generation");
        Print(ROWS, COLS, grid);
        
        //need to fix this, find way to incorporate
        System.out.println("generation: " + generationNum);

        //next gen
        nextGeneration(grid, ROWS, COLS);
    }
    private static void Print(int ROWS, int COLS, int[][] grid)
    {
        //for ( int i = 0; i < generationNum; i++ ){
            for (int row = 0; row < ROWS; row++)
            {
                for (int col = 0; col < COLS; col++)
                {
                    if (grid[row][col] == 0)
                        //System.out.print(" 💄 ");
                        System.out.print(" - ");
                    else
                        //System.out.print(" 💋 ");
                        System.out.print(" 0 ");
                }
                System.out.println();
            }
            System.out.println();
        //}
    }

    // Function to print next generation
    private static void nextGeneration(int[][] grid, int ROWS, int COLS)
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
        Print(ROWS, COLS, future);
    }
    
    // Function to check if cell is alive in next generation
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
// /**
// * An example of a method - replace this comment with your own
// *
// * @param  y  a sample parameter for a method
// * @return    the sum of x and y
// */
// public int sampleMethod(int y)
// {
// // put your code here
// return x + y;
// }
// }
// // Game of Life

//using System;

