import java.util.Scanner;
/**
 * @author Semih Turan
 * @since march 2024
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Declare variables
        Scanner input = new Scanner(System.in);
        int width, height;

        // Prompting play area width and height
        do{
            System.out.print("Please enter play area width (min. 2): ");
            // User input validation
            while(!input.hasNextInt()){
                System.out.println("Please enter a valid number for width!");
                System.out.print("Please enter play area width (min. 2): ");
                input.next();
            }
            width = input.nextInt(); // The Evaluation Form: Item 7
            if(width < 2){
                System.out.println("Width must be at least 2!");
            }
        }while(width<2);
        do{
            System.out.print("Please enter play area height (min. 2): ");
            // User input validation
            while(!input.hasNextInt()){
                System.out.println("Please enter a valid number for height!");
                System.out.print("Please enter play area height (min. 2): ");
                input.next();
            }
            height = input.nextInt(); // The Evaluation Form: Item 7
            if(height < 2){
                System.out.println("Height must be at least 2!");
            }
        }while(height < 2);

        // Run game
        MineSweeper mine = new MineSweeper(width, height);
        mine.run();
    }
}