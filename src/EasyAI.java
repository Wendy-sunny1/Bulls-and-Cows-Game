import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EasyAI {
    String computerCode;
    String userCode;
    int bull = 0;
    int cow = 0;
    int numOfGuess;
    PrintingGameInfo printingGameInfo = new PrintingGameInfo();
    public void start() {

        computerCode = getComputerCode();
        printingGameInfo.computerCode = computerCode;

        userCode = getValidCode("Please enter your secret code: ");
        printingGameInfo.userCode = userCode;
        System.out.println("---");

        while (true) {
            showUserGuessResults();
            if (bull == 4) {
                System.out.println("You win! :)");
                printingGameInfo.resultStatement = "You win! :)";
                break;
            }
            System.out.println();
            showComputerGuessResults();
            if (bull == 4){
                System.out.println("The computer wins! :)");
                printingGameInfo.resultStatement = "The computer wins! :)";
                break;
            }
            System.out.println("---");

            numOfGuess++;

            if (numOfGuess >= 7){
                System.out.println("Only 7 times allowed. The game ended in a draw :(");
                printingGameInfo.resultStatement = "Only 7 times allowed. The game ended in a draw :(";
                break;
            }
        }
        printingGameInfo.numOfGuess = this.numOfGuess - 2;
        printingGameInfo.start();
    }
    public String getComputerCode () {
        List computerCodeList = new ArrayList();
        Random singleRandomNum = new Random();
        while (computerCodeList.size() < 4) {
            int num = singleRandomNum.nextInt(10);
            if (!computerCodeList.contains(num)) {
                computerCodeList.add(num);
            }
        }
        computerCode = "" + computerCodeList.get(0) + computerCodeList.get(1) +
                computerCodeList.get(2) + computerCodeList.get(3);
        return computerCode;
    }

    public String getValidCode (String code){
        String userCode = "";
        while (true) {
            System.out.println(code);
            userCode = Keyboard.readInput();

            if (userCode.length() == 4) {
                List userCodeList = new ArrayList();
                for(int i = 0; i < userCode.length(); i++){
                    userCodeList.add(userCode.charAt(i));
                }
                Long processedList = userCodeList.stream().distinct().count();
                if (userCodeList.size() == processedList.intValue()) {
                    break;
                }
            }
        }
        return userCode;
    }
    public void showUserGuessResults () {
        String userGuess = getValidCode("You guess: ");
        printingGameInfo.userGuessArray.add(userGuess);
        calculateBullAndCowNums(computerCode, userGuess);
        printingGameInfo.userBullsArray.add(bull);
        printingGameInfo.userCowsArray.add(cow);
    }

    public void showComputerGuessResults(){
        String computerGuess = getComputerCode();
        System.out.println("Computer guess: " + computerGuess);
        printingGameInfo.computerGuessArray.add(computerGuess);
        calculateBullAndCowNums(userCode,computerGuess);
        printingGameInfo.computerBullsArray.add(bull);
        printingGameInfo.computerCowsArray.add(cow);
    }

    public void calculateBullAndCowNums (String code, String guess){
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