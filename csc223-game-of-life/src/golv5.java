/**
 * A console base Game Of Life program
 *
 * @author (Mila)
 * @version (14.08/2022)
 */

import java.util.Scanner;

public class golv5 {

    // initialise instance variables
    final static int ROWS = 20;
    final static int COLS = 20;
    static int generationNum = 0;
    

    //Main, runs whole time so people can enter stuff
    public static void main(String[] args)
    {
        //Print title and menu 
        printTitle();
        printOptions();
        
        //The grid is outside of the for loop so we keep it
        int[][] grid = new int[ROWS][COLS];
        Scanner keyboard = new Scanner(System.in);
        int skip = 0;

        //While we are in the game
        for (int generation = 0; true;){
            if (skip == 0){

            // Pick a pattern to run
            String choice = keyboard.nextLine();

            //What they can enter from the menu
            if (choice.equals("1")) {
                grid = CreateBlinker();
                generation = 0;
                printGame(generation, grid);
                continue;
            }
            else if (choice.equals("2")) {
                grid = CreateGlider();
                generation = 0;
                printGame(generation, grid);
                continue;
            }
            else if (choice.equals("3")) {
                grid = CreateToad();
                generation = 0;
                printGame(generation, grid);
                continue;
            }
            else if (choice.equalsIgnoreCase("E")) {
                keyboard.close();
                System.exit(0);
                return;
            }
            else if (choice.equalsIgnoreCase("R")) {
                randomGame(keyboard, grid);
                printGame(generation, grid);
                continue; 
            }
            else if (choice.equalsIgnoreCase("C")) {
                customGame(keyboard, grid);
                printGame(generation, grid);
                continue;
            }
            else if(choice.toUpperCase().startsWith("S")){
                skip = skipGen(keyboard, grid);
                printGame(generation, grid);
                continue;
            }
            //If they just press enter (nothing in text box)
            else if (grid != null && choice.equals("")) {
                // Continue with next generation
            }
            else if (grid != null && choice.equalsIgnoreCase("M")) {
                printOptions();
                // Go back to the top of the loop with continue
                continue;
            }
            else {
                System.out.println("Invalid choice");
                printOptions();
                // Go back to the top of the loop with continue, tell them it's invalid
                continue;
            }
        }
        else{
            skip--;
        }

            // Calculate next generation
            generation++;
            grid = nextGeneration(grid, ROWS, COLS);
            printGame(generation, grid);
        }
    }

    // Print the grid, generation number and info for menu etc. 
    private static void printGame(int generation, int[][] grid) {
        //System.out.println('\u000c');
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        printTitle();
        print(ROWS, COLS, grid);
        System.out.println("Generation: " + generation + ". [ENTER] to continue. [M]enu. [E]xit");
    }

    //Print menu
    private static void printOptions() {
        System.out.println("Welcome to Conway's Game of Life. Choose a pattern to run");
        System.out.println("1. Blinker");
        System.out.println("2. Glider");
        System.out.println("3. Toad");
        System.out.println("R. Random");
        System.out.println("C. Custom");
        System.out.println("E. Exit");
        System.out.println("S. Skip");
    }

    // Prints grid but does not update it
    private static void print(int ROWS, int COLS, int[][] grid)
    {
        //Printing borders and the cells
        printHorizontalBorder();
        for (int row = 0; row < ROWS; row++)
        {
            System.out.print("| ");
            for (int col = 0; col < COLS; col++) {
                if (grid[row][col] == 0)
                    System.out.print("   ");
                else
                    System.out.print(" ø ");
            }

            System.out.print("|");
            System.out.println();
        }

        printHorizontalBorder();
    }

    private static void printHorizontalBorder() {
        int borderWidth = COLS + 1;
        for (int j = 0; j < borderWidth; j++) {
            System.out.print("---");
        }
        System.out.println();
    }

    //Ascii title printed at the start
    //Have to use 2 backslashes for each 1 
    private static void printTitle() {
        System.out.println("\f");
        System.out.println('\u000c');
        System.out.println(" __     __    _      ____      ___   ____      _     _   ____  ____ ");
        System.out.println("/ /`_  / /\\  | |\\/| | |_      / / \\ | |_      | |   | | | |_  | |_  ");
        System.out.println("\\_\\_/ /_/--\\ |_|  | |_|__     \\_\\_/ |_|       |_|__ |_| |_|   |_|__ ");
        System.out.println();
    }

    // Function to calculate next generation, does not print
    // Returns the next generation of the grid
    private static int[][] nextGeneration(int[][] grid, int ROWS, int COLS) {
        
        int[][] future = new int[ROWS][COLS];

        // Loop through every cell
        for (int row = 0; row < ROWS; row++){
            for (int col = 0; col < COLS; col++) {
                int aliveNeighbours = 0;

                // top left
                aliveNeighbours += isNeighbourAlive(grid, row, col, -1, -1);

                // top middle
                aliveNeighbours += isNeighbourAlive(grid, row, col, 0, -1);

                // top right
                aliveNeighbours += isNeighbourAlive(grid, row, col, 1, -1);

                // middle left
                aliveNeighbours += isNeighbourAlive(grid, row, col, -1, 0);

                // middle right
                aliveNeighbours += isNeighbourAlive(grid, row, col, 1, 0);

                // bottom left
                aliveNeighbours += isNeighbourAlive(grid, row, col, -1, 1);

                // bottom middle
                aliveNeighbours += isNeighbourAlive(grid, row, col, 0, 1);

                // bottom right
                aliveNeighbours += isNeighbourAlive(grid, row, col, 1, 1);

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

    // Function to check if cell is alive in next generation, returns 0 for dead, 1
    // for alive
    private static int isNeighbourAlive(int[][] grid, int row, int col, int horizontal, int vertical)
    {
        int neighbourCol = col;
        int neighbourRow = row;

        // Can we go left?
        if (horizontal == 1){
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

    //Oscillators
    //Blinker 
    public static int[][] CreateBlinker() {
        int[][] grid =
                {
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  }
                };
        return grid;
    }

    //Glider
    public static int[][] CreateGlider() {
        int[][] grid =
                {
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  }
                };
        return grid;
    }

    //Toad
    public static int[][] CreateToad() {
        int[][] grid =
                {
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  }
                };
        return grid;
    }

    //Allow editing of a cell - custom game
    public static void customGame(Scanner keyboard, int[][] grid){
        while(true){
            System.out.println("Make custom pattern. Type {ROW 0-19} {COL 0-19} {0 or 1} eg. 5 4 1 [ENTER]. [R]un");
            String choice = keyboard.nextLine();
            if (choice.equalsIgnoreCase("R")){
                return;
            }

            //Split string by the space character
            String[] options = choice.split(" ");

            //If they've entered the wrong number of characters 
            if(options.length != 3){
                System.out.println("invalid input");
                continue; 
            }

            //Looked up how to do this; it takes the options string and puts the 1st value = row, 2nd = col, 3rd = state
            try{
                int row = Integer.parseInt(options[0]);
                int col = Integer.parseInt(options[1]);
                int state = Integer.parseInt(options[2]);

                grid[row][col] = state;
                print(ROWS, COLS, grid);
            }

            //So the programme doesn't break if you enter a non-number
            catch(NumberFormatException e){
                System.out.println("invalid input");
            }
        }
    }

    //Generate a random board
    public static void randomGame(Scanner keyboard, int[][] grid){
        for(int i = 0; i < ROWS; i ++){
            for(int j = 0; j < COLS; j ++){
                grid[i][j] = (int)(Math.round(Math.random()));
            }
        }
    }

    //Allow user to skip an entered number of generations
    public static int skipGen(Scanner keyboard, int[][] grid){
        System.out.println("How many generations do you want to skip?");
        String choice = keyboard.nextLine();
        if(choice.equals("")){
            int skip = 0;    
        }
        int skip = Integer.parseInt(choice);
        return skip;
    }
}