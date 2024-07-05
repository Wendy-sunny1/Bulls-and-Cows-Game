import java.util.ArrayList;
import java.util.List;

public class MediumAI extends EasyAI {

    @Override
    public void showComputerGuessResults(){
        String computerGuess = getDistinctGuess();
        System.out.println("Computer guess: " + computerGuess);
        calculateBullAndCowNums(userCode,computerGuess);

        printingGameInfo.computerBullsArray.add(bull);
        printingGameInfo.computerCowsArray.add(cow);
    }

    public String getDistinctGuess(){
        String distinctGuess;
        List distinctGuessList = new ArrayList();

        while (true){
            distinctGuess = getComputerCode();
            if (!distinctGuessList.contains(distinctGuess)){
                distinctGuessList.add(distinctGuess);
                break;
            }
        }
        printingGameInfo.computerGuessArray.add(distinctGuess);
        return distinctGuess;
    }
}
