import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class HardAI extends EasyAI{
    String computerGuess;
    int computerBull;
    int computerCow;
    List lastArray;

    @Override
    public void start() {
        lastArray = generateInitialArray();
        computerCode = getComputerCode();
        printingGameInfo.computerCode = computerCode;

        userCode = getValidCode("Please enter your secret code: ");
        printingGameInfo.userCode = userCode;

        System.out.println("---");
        showUserGuessResults();
        if (bull == 4) {
            System.out.println("You win! :)");
            printingGameInfo.resultStatement = "You win! :)";
        }else {
            System.out.println();
            showComputerGuessResults();//assign the value of the first random guess to variable computerGuess

            computerBull = bull;
            computerCow = cow;

            if (bull == 4) {
                System.out.println("The computer wins! :)");
                printingGameInfo.resultStatement = "The computer wins! :)";
                System.out.println("---");
            } else {

                while (true) {
                    System.out.println("---");
                    showUserGuessResults();
                    if (bull == 4) {
                        System.out.println("You win! :)");
                        printingGameInfo.resultStatement = "You win! :)";
                        break;
                    }
                    System.out.println();

                    List eachPossibleArray = generatePossibleArray(computerBull, computerCow, computerGuess, lastArray);
                    computerGuess = getRandomElement(eachPossibleArray);
                    printingGameInfo.computerGuessArray.add(computerGuess);
                    System.out.println("Computer guess: " + computerGuess);
                    super.calculateBullAndCowNums(userCode, computerGuess);

                    computerBull = bull;
                    computerCow = cow;
                    printingGameInfo.computerBullsArray.add(computerBull);
                    printingGameInfo.computerCowsArray.add(computerCow);
                    lastArray = eachPossibleArray;
                    if (computerBull == 4) {
                        System.out.println("The computer wins! :)");
                        printingGameInfo.resultStatement = "The computer wins! :)";
                        break;
                    }

                    numOfGuess++;
                    printingGameInfo.numOfGuess = numOfGuess;
                    if (numOfGuess >= 6) {
                        System.out.println("Only 7 times allowed. The game ended in a draw :(");
                        printingGameInfo.resultStatement = "Only 7 times allowed. The game ended in a draw :(";
                        break;
                    }

                }
            }

        }
        printingGameInfo.start();
    }
    public String getRandomElement(List array){

        return (String)(array.get((int)(Math.random() * array.size())));
    }
    public List generateInitialArray(){
        List initialList = new ArrayList();
        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        if (j != i && j != k && i != k && i != l && j != l && k != l) {
                            initialList.add("" + i + j + k + l);
                        }
                    }
                }
            }
        }
        return initialList;
    }
    public List generatePossibleArray(int bullNumber, int cowNumber, String guess, List lastArrayList){

        List newPossibleList = new ArrayList();
        for (int i = 0; i < lastArrayList.size(); i++) {
            calculateBullAndCowNums((String)(lastArrayList.get(i)), guess);
            int tryBull = bull;
            int tryCow = cow;
            if (bullNumber == tryBull && cowNumber == tryCow){
                newPossibleList.add(lastArrayList.get(i));
            }
        }
        return newPossibleList;
    }

    @Override
    public void calculateBullAndCowNums (String code, String guess) {
        int eachBull = 0;
        int eachCow = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((code.charAt(i) == guess.charAt(j)) && i != j) {
                    eachCow++;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            if (code.charAt(i) == guess.charAt(i)) {
                eachBull++;
            }
        }
        this.bull = eachBull;
        this.cow = eachCow;
    }
    @Override
    public void showUserGuessResults () {
        String userGuess = getValidCode("You guess: ");
        printingGameInfo.userGuessArray.add(userGuess);
        super.calculateBullAndCowNums(computerCode, userGuess);
        printingGameInfo.userBullsArray.add(bull);
        printingGameInfo.userCowsArray.add(cow);
    }
    @Override
    public void showComputerGuessResults(){
        String computerGuess = getComputerCode();
        printingGameInfo.computerGuessArray.add(computerGuess);
        System.out.println("Computer guess: " + computerGuess);
        super.calculateBullAndCowNums(userCode,computerGuess);
        this.computerGuess = computerGuess;
        printingGameInfo.computerBullsArray.add(bull);
        printingGameInfo.computerCowsArray.add(cow);
    }
}
