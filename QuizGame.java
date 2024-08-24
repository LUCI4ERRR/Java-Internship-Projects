import java.util.*;
import java.util.concurrent.*;
public class QuizGame 
{
    static class Question 
    {
        String questionText;
        String[] options;
        String correctAnswer;
        Question(String questionText, String[] options, String correctAnswer) 
        {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }
    public static void displayQuestion(Question question) 
    {
        System.out.println(question.questionText);
        for (int i = 0; i < question.options.length; i++) 
        {
            System.out.println((char) ('A' + i) + ": " + question.options[i]);
        }
    }
    public static String getAnswer(ExecutorService executor, Scanner scanner, long timeLimit) throws Exception 
    {
        Future<String> future = executor.submit(scanner::nextLine);
        try 
        {
            return future.get(timeLimit, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            return null;  // Time's up
        }
    }
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Rome", "Berlin"}, "Paris"));
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, "4"));
        questions.add(new Question("What is the color of the sky?", new String[]{"Green", "Blue", "Red", "Yellow"}, "Blue"));
        int score = 0;
        int questionNumber = 1;
        for (Question question : questions) 
        {
            System.out.println("Question " + questionNumber + ":");
            displayQuestion(question);
            long timeLimit = 10;  // Time limit in seconds
            System.out.println("You have " + timeLimit + " seconds to answer.");
            try 
            {
                String answer = getAnswer(executor, scanner, timeLimit);
                if (answer == null) 
                {
                    System.out.println("Time's up! The correct answer was: " + question.correctAnswer);
                } else if (answer.equalsIgnoreCase(question.correctAnswer)) 
                {
                    System.out.println("Correct!");
                    score++;
                } else 
                {
                    System.out.println("Incorrect. The correct answer was: " + question.correctAnswer);
                }
            } 
            catch (Exception e) 
            {
                System.out.println("An error occurred: " + e.getMessage());
            }
            questionNumber++;
            System.out.println();
        }
        executor.shutdown();
        System.out.println("Quiz finished! Your final score is: " + score + "/" + questions.size());
        scanner.close();
    }
}
