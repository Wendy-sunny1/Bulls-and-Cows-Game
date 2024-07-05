import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Wordle {
    int bull;
    int cow;
    int numOfGuess = 0;
    ArrayList stringList = new ArrayList();
    String randomWord;
    PrintingGameInfo printingGameInfo = new PrintingGameInfo();
    public void start(){
        randomWord = getRandomWord();

        while (true){
            showGuessResult();
            if (bull == 5) {
                System.out.println("You win! :)");
                printingGameInfo.resultStatement = "You win! :)";
                break;
            }
            numOfGuess++;
            if (numOfGuess >= 6){
                System.out.println("Only 6 times allowed. The game ended :(");
                printingGameInfo.resultStatement = "Only 6 times allowed. The game ended :(";
                break;
            }
            printingGameInfo.numOfGuess = numOfGuess;
        }
        printingGameInfo.start();
    }
    public String getRandomWord(){
        String filePath = "dictionary-v2.txt";
        try (BufferedReader fr = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while ((line = fr.readLine()) != null) {
                if (line.length() == 5){
                    stringList.add(line);
                }
            }
        } catch (IOException e) {
        }
        return (String) (stringList.get((int)(Math.random() * stringList.size())));
    }
    public String getValidWord (){
        boolean b = true;
        String userCode = "";
        while (b) {
            System.out.println("Please enter a five-letter word: ");
            userCode = Keyboard.readInput();
            if (userCode.length() == 5) {
                for (int i = 0; i < 5; i++) {
                    char letter = userCode.charAt(i);
                    if (!Character.isLetter(letter)) {
                        break;
                    }
                    return userCode;
                }
            }
        }
        return userCode;
    }
    public void showGuessResult(){
        String userGuess = getValidWord();
        printingGameInfo.userGuessArray.add(userGuess);
        calculateBullAndCowNums(randomWord,userGuess);
    }
    public void calculateBullAndCowNums (String randomWord, String guess){
        int eachBull = 0;
        int eachCow = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((randomWord.charAt(i) == guess.charAt(j)) && i != j) {
                    eachCow++;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            if (randomWord.charAt(i) == guess.charAt(i)) {
                eachBull++;
            }
        }

        this.bull = eachBull;
        this.cow = eachCow;
        printingGameInfo.userBullsArray.add(eachBull);
        printingGameInfo.userCowsArray.add(eachCow);
        if (bull == 1 && cow == 1) {
            System.out.println("Result: " + bull + " bull and " + cow + " cow");
        } else if (bull == 1 && cow != 1) {
            System.out.println("Result: " + bull + " bull and " + cow + " cows");
        } else if (bull != 1 && cow == 1) {
            System.out.println("Result: " + bull + " bulls and " + cow + " cow");
        } else {
            System.out.println("Result: " + bull + " bulls and " + cow + " cows");
        }
    }
}

