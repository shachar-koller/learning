import java.util.*;

public class Calculator {
    private final Map<Character, Integer> gmMap;
    private Stack<PreviousInput> previousInputs;
    public record PreviousInput(String phrase, int value){}

    public Calculator(){
        this.previousInputs = new Stack<>();
        this.gmMap = new HashMap<>();
        gmMap.put('א', 1);
        gmMap.put('ב', 2);
        gmMap.put('ג', 3);
        gmMap.put('ד', 4);
        gmMap.put('ה', 5);
        gmMap.put('ו', 6);
        gmMap.put('ז', 7);
        gmMap.put('ח', 8);
        gmMap.put('ט', 9);
        gmMap.put('י', 10);
        gmMap.put('כ', 20);
        gmMap.put('ל', 30);
        gmMap.put('מ', 40);
        gmMap.put('נ', 50);
        gmMap.put('ס', 60);
        gmMap.put('ע', 70);
        gmMap.put('פ', 80);
        gmMap.put('צ', 90);
        gmMap.put('ק', 100);
        gmMap.put('ר', 200);
        gmMap.put('ש', 300);
        gmMap.put('ת', 400);
        gmMap.put('ץ', 90);
        gmMap.put('ף', 80);
        gmMap.put('ך', 20);
        gmMap.put('ם', 40);
        gmMap.put('ן', 50);
    }

    public int calculateHebrewGematria(String word){
        if(word == null) return -1;

        int total = 0;
        String cleaned = word.replaceAll("[^א-ת]", "");

        for(char c: cleaned.toCharArray()) total += gmMap.getOrDefault(c, 0);

        PreviousInput pi = new PreviousInput(word, total);
        previousInputs.push(pi);
        return total;
    }

    public List<PreviousInput> getPreviousInputs(){
        List<PreviousInput> PI = new ArrayList<>();
        Stack<PreviousInput> localPI = (Stack<PreviousInput>) previousInputs.clone();

        while(!localPI.isEmpty()){
            PI.add(localPI.pop());
        }

        return PI;
    }

    public List<PreviousInput> getNPreviousInputs(int n){
        List<PreviousInput> PI = new ArrayList<>();
        Stack<PreviousInput> localPI = (Stack<PreviousInput>) previousInputs.clone();

        int count = 0;
        while(!localPI.isEmpty() && count < n){
            PI.add(localPI.pop());
            count++;
        }

        return PI;
    }

    public String clearHistory(){
        previousInputs.clear();
        return "History Cleared";
    }

    public PreviousInput getLastInput(){
        if(previousInputs.isEmpty()) return null;
        else return previousInputs.peek();
    }

}