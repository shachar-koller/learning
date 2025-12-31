import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Calculator gc = new Calculator();
        Scanner scnr = new Scanner(System.in);

        System.out.println("Welcome to the Gematria Calculator!");
        System.out.println("Commands:");
        System.out.println("  - Enter a Hebrew word/phrase to calculate");
        System.out.println("  - Type 'history' to see all previous calculations");
        System.out.println("  - Type 'history [n]' to see last n calculations");
        System.out.println("  - Type 'clear' to clear history");
        System.out.println("  - Type 'exit' to quit");
        System.out.print("> ");

        while(true){
            String input = scnr.nextLine().trim();

            if(input.equals("exit")) break;

            if(input.equals("history")) displayHistory(gc.getPreviousInputs());

            else if(input.startsWith("history ")){
                try{
                    int n = Integer.parseInt(input.split(" ")[1]);
                    displayHistory(gc.getNPreviousInputs(n));
                } catch(NumberFormatException e){
                    System.out.println("Invalid number. Usage: history [n]");
                }
            }

            else if(input.equals("clear")) System.out.println(gc.clearHistory());

            else {
                int inputGematria = gc.calculateHebrewGematria(input);
                System.out.println("The hebrew gematria of the word: " + input + " is " + inputGematria);
            }

            System.out.print("> ");
        }

        System.out.println("Goodbye!");
        scnr.close();
    }

    private static void displayHistory(List<Calculator.PreviousInput> history){
        if(history.isEmpty()){
            System.out.println("No calculation history yet.");
            return;
        }

        System.out.println("\n--- Calculation History ---");
        for(int i = 0; i < history.size(); i++){
            Calculator.PreviousInput pi = history.get(i);
            System.out.println((i + 1) + ". \"" + pi.phrase() + "\" = " + pi.value());
        }
        System.out.println("---------------------------\n");
    }
}