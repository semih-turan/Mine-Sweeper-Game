import java.util.Arrays;
import java.util.Scanner;
public class MineSweeper { // The Evaluation Form: Item 5
   // Declare variables
    int width;
    int height;
    int numberOfMines;
    String[][] hidePlayMap;
    String[][] visiblePlayMap;

    // Constructor method
    public MineSweeper(int width, int height){
        this.width = width;
        this.height = height;
        hidePlayMap = new String[width][height];
        visiblePlayMap = new String[width][height];
    }

    // Run game method
    public void run(){ // The Evaluation Form: Item 6
        // Printing play map with bombs
        System.out.println("Welcome to mine sweeper game!");
        System.out.println("-----Mine Locations-----");
        print2DArray(plantMines(this.hidePlayMap));
        System.out.println("------------------------");
        System.out.println("------------------------");

        // Printing play map without bombs
        print2DArray(fill(this.visiblePlayMap));

        // Declare variables
        int openedCells = 0;
        int countRow = -1;
        int countCol = -1;
        boolean gameOver = false;

        while (!gameOver) {
            // Prompting user input for row and checks proper input or not
            Scanner input = new Scanner(System.in);
            System.out.print("Please select row: ");
            while(!input.hasNextInt()){ // The Evaluation Form: Item 9
                System.out.println("Please enter a valid number for row!");
                System.out.print("Please select row: ");
                input.next(); // The Evaluation Form: Item 9
            }
            int selectRow = input.nextInt(); // The Evaluation Form: Item 9

            // User input validation
            if(selectRow<0 || selectRow>this.width){ // The Evaluation Form: Item 10
                System.out.println("Please choose correct row within play area!");
                continue;
            }

            // Prompting user input for column checks proper input or not
            System.out.print("Please select column: ");
            while(!input.hasNextInt()){
                System.out.println("Please enter a valid number for column!");
                System.out.print("Please select column: ");
                input.next();
            }
            int selectCol = input.nextInt();

            // User input validation
            if(selectCol < 0 || selectCol>this.height){ // The Evaluation Form: Item 10
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
            if (hidePlayMap[selectRow][selectCol].equals("*")) { // The Evaluation Form: Item 13
                System.out.println("Boom!!!  You are failed!"); // The Evaluation Form: Item 15
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
                // Checks win conditions (due to printing message before loop you need to add minus 1)
                if (openedCells == ((width * height) - this.numberOfMines)-1) { // The Evaluation Form: Item 14
                    System.out.println("Congratulations! You have won the game!"); // The Evaluation Form: Item 15
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
    public void print2DArray(String[][] arr) {
        for (String[] row : arr) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    // Fill array "-" method
    public String[][] fill(String[][] arr){

        for (String[] strings : arr) {
            Arrays.fill(strings, "-");
        }
        return arr;
    }

}