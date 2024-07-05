import java.util.ArrayList;

public class BullsAndCows {
    String difficultyChoice;

    public void getDifficultyChoice(){
        difficultyChoice = getValidDifficultyChoice();
        if (difficultyChoice.equals("1")){
            EasyAI chooseEasyAI = new EasyAI();
            chooseEasyAI.start();

        } else if (difficultyChoice.equals("2")){
            MediumAI chooseMediumAI = new MediumAI();
            chooseMediumAI.start();
        } else {
            HardAI chooseHardAI = new HardAI();
            chooseHardAI.start();
        }
    }
    public String getValidDifficultyChoice(){
        while (true) {
            System.out.println("Please enter 1, 2 or 3 to select the difficulty level :) \n" +
                    "1. Easy  2. Medium  3. Hard ");
            difficultyChoice = Keyboard.readInput();
            if (difficultyChoice.equals("1") || difficultyChoice.equals("2") || difficultyChoice.equals("3")){
                break;
            }
        }
        return difficultyChoice;
    }

}
