import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PrintingGameInfo {
    String resultStatement;
    static String gameChoice;
    String computerCode;
    String userCode;
    String userNeed;
    ArrayList userGuessArray = new ArrayList();
    ArrayList computerGuessArray = new ArrayList();
    ArrayList userBullsArray = new ArrayList();
    ArrayList userCowsArray = new ArrayList();
    ArrayList computerBullsArray = new ArrayList();
    ArrayList computerCowsArray = new ArrayList();
    String fileName;
    int numOfGuess;

    public void start(){

        userNeed = askNeedForPrint();

        if (gameChoice.equals("1") && userNeed.equals("yes")){
            System.out.println("Please enter the file name: ");
            fileName = Keyboard.readInput();

            try(PrintWriter pr = new PrintWriter(new FileWriter( "" + fileName + ".txt"))){
                pr.println("Bulls & Cows game result: ");
                pr.println("Your code: " + this.userCode);
                pr.println("Computer's code: " + this.computerCode);
                pr.println("---");
                for (int i = 0; i <= numOfGuess + 1; i++){
                    pr.println("Turn" + (i + 1) + ": ");
                    pr.println("You guessed " + userGuessArray.get(i));
                    pr.println("Computer guessed " + computerGuessArray.get(i));
                    pr.println("Your score is: " + userBullsArray.get(i) + " bulls and " + userCowsArray.get(i) + " cows");
                    if(((Integer)userBullsArray.get(i)) == 4){
                        break;
                    }
                    pr.println("Computer score is: " + computerBullsArray.get(i) + " bulls and " + computerCowsArray.get(i) + " cows");
                    if(((Integer)computerBullsArray.get(i)) == 4){
                        break;
                    }
                    pr.println("---");
                }
                pr.println(resultStatement);
            }
            catch (
                    IOException e){
        }
    } else if(gameChoice.equals("2") && userNeed.equals("yes")){
            System.out.println("Please enter the file name: ");
            fileName = Keyboard.readInput();

            try(PrintWriter pr = new PrintWriter(new FileWriter( "" + fileName + ".txt"))){
                pr.println("Wordle game result: ");
                for (int i = 0; i < numOfGuess + 1; i++){
                    pr.println("Turn" + (i + 1) + ": ");
                    pr.println("You guessed " + userGuessArray.get(i));
                    pr.println("Your score is: " + userBullsArray.get(i) + " bulls and " + userCowsArray.get(i) + " cows");
                    if(((Integer)userBullsArray.get(i)) == 5){
                        break;
                    }
                    pr.println("---");
                }
                pr.println(resultStatement);
            }
            catch (
                    IOException e){
            }
        }
    }
    public String askNeedForPrint(){
        System.out.println("Do you wish to save the results to a text file?");
        return Keyboard.readInput().toLowerCase();
    }
}
