import java.util.Random;
import java.util.Scanner;
public class GuessingGame 
{
    private static final int START_RANGE = 1;
    private static final int END_RANGE = 100;
    private static final int MAX_ATTEMPTS = 10;
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalRounds = 0;
        int totalWins = 0;
        boolean playAgain;
        do 
        {
            int numberToGuess = random.nextInt(END_RANGE - START_RANGE + 1) + START_RANGE;
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrectly = false;
            System.out.println("Guess the number between " + START_RANGE + " and " + END_RANGE + ".");
            while (attemptsLeft > 0) 
            {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                if (guess < numberToGuess) 
                {
                    System.out.println("Too low.");
                } 
                else if (guess > numberToGuess) 
                {
                    System.out.println("Too high.");
                } 
                else 
                {
                    System.out.println("Congratulations! You've guessed the number " + numberToGuess + "!");
                    guessedCorrectly = true;
                    break;
                }
                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }
            if (!guessedCorrectly) 
            {
                System.out.println("Sorry, you've run out of attempts. The number was " + numberToGuess + ".");
            }
            totalRounds++;
            if (guessedCorrectly) 
            {
                totalWins++;
            }
            System.out.println("You have won " + totalWins + " out of " + totalRounds + " rounds.");
            System.out.print("Would you like to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        } 
        while (playAgain);
        scanner.close();
        System.out.println("Thanks for playing! You won " + totalWins + " out of " + totalRounds + " rounds.");
    }
}

