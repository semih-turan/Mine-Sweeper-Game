import java.util.Arrays;
import java.util.Scanner;
public class MineSweeper {
   // Declare variables
    int width;
    int height;
    int numberOfMines;
    String[][] hidePlayMap;
    String[][] visiblePlayMap;

    // Constructor method
    MineSweeper(int width, int height){
        this.width = width;
        this.height = height;
        hidePlayMap = new String[width][height];
        visiblePlayMap = new String[width][height];
    }

    // Run game method
    public void run(){
        // Printing play map with bombs
        print2DArray(plantMines(this.hidePlayMap));
        System.out.println("--------------------");
        // Printing play map without bombs
        print2DArray(fill(this.visiblePlayMap));

        // Declare variables
        int openedCells = 0;
        int countRow = -1;
        int countCol = -1;
        boolean gameOver = false;

        while (!gameOver) {
            // Prompting user input for row
            System.out.print("Please select row: ");
            Scanner input = new Scanner(System.in);
            int selectRow = input.nextInt();

            // User input validation
            if(selectRow<0 || selectRow>this.width){
                System.out.println("Please choose correct row within play area!");
                continue;
            }

            // Prompting user input for column
            System.out.print("Please select column: ");
            int selectCol = input.nextInt();

            // User input validation
            if(selectCol < 0 || selectCol>this.height){
                System.out.println("Please choose correct col within play area!");
                continue;
            }

            // Verification to prevent same input
            if(countRow == selectRow && countCol == selectCol){
                System.out.println("Please do not enter same values!");
                continue;
            }

            // Stores each user input for prevent enter same values
            countRow = selectRow;
            countCol = selectCol;

            if(!visiblePlayMap[selectRow][selectCol].equals("-")){
                System.out.println("You enter this value before!");
                continue;
            }

            // Game over condition or printing number of mines around selected point
            if (hidePlayMap[selectRow][selectCol].equals("*")) {
                System.out.println("Boom!!!  You are failed!");
                gameOver = true;
            } else {
                // Sets boundary conditions for playing area
                int conditionOne = Math.max(0, selectRow - 1);
                int conditionTwo = Math.min(this.width - 1, selectRow + 1);
                int conditionThree = Math.max(0, selectCol - 1);
                int conditionFour = Math.min(this.height - 1, selectCol + 1);

                // Calculate the number of mines in the play area
                int mineCount = 0;
                for (int i = conditionOne; i <= conditionTwo; i++) {
                    for (int j = conditionThree; j <= conditionFour; j++) {
                        if (hidePlayMap[i][j].equals("*")) {
                            mineCount++;
                        }
                    }
                }

                // Counts all opened cells
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        if (!visiblePlayMap[i][j].equals("-")) {
                            openedCells++;
                        }
                    }
                }
                // Checks win conditions
                if (openedCells == ((width * height) - this.numberOfMines)-1) { // Minus 1 due to printing message before loop
                    System.out.println("Congratulations! You have won the game!");
                    gameOver = true;
                } else{
                    openedCells = 0;
                }

                // Printing visible map
                visiblePlayMap[selectRow][selectCol] = Integer.toString(mineCount);
                print2DArray(visiblePlayMap);
            }
        }
    }

    // Planting mines method
    private String[][] plantMines(String[][] arr){
        // Type casting
        double width = this.width;
        double height = this.height;

        // Calculating number of mines in the play area
        this.numberOfMines = (int) Math.floor((width * height)/4);

        // Fill mine map with "-"
        fill(arr);

        // Insert "*" bombs randomly
        int bombCount =  numberOfMines;
        while(!(bombCount==0)) {
            int randomRow = (int) (Math.random() * this.width);
            int randomCol = (int) (Math.random() * this.height);
            if (!(arr[randomRow][randomCol].equals("*"))) {
                arr[randomRow][randomCol] = "*";  // The Evaluation Form: Item 8
                bombCount--;
            }
        }
        return arr;
    }

    // Print array method
    static void print2DArray(String[][] arr) {
        for (String[] row : arr) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    // Fill array "-" method
    static String[][] fill(String[][] arr){

        for (String[] strings : arr) {
            Arrays.fill(strings, "-");
        }
        return arr;
    }

}