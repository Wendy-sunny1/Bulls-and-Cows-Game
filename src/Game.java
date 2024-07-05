public class Game {
    String gameChoice;
    PrintingGameInfo printingGameInfo = new PrintingGameInfo();
    public void start(){
        gameChoice = getValidGameChoice();
        printingGameInfo.gameChoice = gameChoice;

        if (gameChoice.equals("1")){
            BullsAndCows bullsAndCows = new BullsAndCows();
            bullsAndCows.getDifficultyChoice();
        } else {
            Wordle wordle = new Wordle();
            wordle.start();
        }
    }
    public String getValidGameChoice(){
        while (true) {
            System.out.println("Please enter 1 or 2 to choose the game you wanna play :) \n" +
                    "1. Traditional bulls and cows  2. Wordle ");
            gameChoice = Keyboard.readInput();
            if (gameChoice.equals("1") || gameChoice.equals("2")){
                break;
            }
        }
        return gameChoice;
    }

}
