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
        print2DArray(fill(this.visiblePlayMap,"-"));
    }

    // Planting mines method
    private String[][] plantMines(String[][] arr){
        // Type casting
        double width = (double) this.width;
        double height = (double) this.height;

        // Calculating number of mines in the play area
        this.numberOfMines = (int) Math.floor((width * height)/4);

        // Fill mine map with "-"
        fill(arr,"-");

        // Insert "*" bombs randomly
        int bombCount =  numberOfMines;
        while(!(bombCount==0)) {
            int randomRow = (int) (Math.random() * this.width); // The Evolution Form: Item 8
            int randomCol = (int) (Math.random() * this.height); // The Evolution Form: Item 8
            if (!(arr[randomRow][randomCol].equals("*"))) {
                arr[randomRow][randomCol] = "*";
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

    // Fill array method
    static String[][] fill(String[][] arr, String value){

        for(int indexRow = 0; indexRow < arr.length; indexRow++){
            for(int indexCol = 0; indexCol < arr[indexRow].length; indexCol++){
                arr[indexRow][indexCol] = value;
            }
        }
        return arr;
    }

}