import java.util.Scanner;
public class GradeCalculator
{   
    public static int getNumberOfSubjects(Scanner scanner) 
    {
        System.out.print("Enter the number of subjects: ");
        return scanner.nextInt();
    }
    public static int[] getMarks(int numberOfSubjects, Scanner scanner) 
    {
        int[] marks = new int[numberOfSubjects];
        for (int i = 0; i < numberOfSubjects; i++) 
        {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }
        return marks;
    }
    public static int calculateTotalMarks(int[] marks) 
    {
        int total = 0;
        for (int mark : marks) 
        {
            total += mark;
        }
        return total;
    }
    public static double calculateAveragePercentage(int totalMarks, int numberOfSubjects)
    {
        return (double) totalMarks / numberOfSubjects;
    }
    public static String determineGrade(double averagePercentage) 
    {
        if (averagePercentage >= 90) 
        {
            return "A+";
        } else if (averagePercentage >= 80) 
        {
            return "A";
        } else if (averagePercentage >= 70) 
        {
            return "B+";
        } else if (averagePercentage >= 60) 
        {
            return "B";
        } else if (averagePercentage >= 50) 
        {
            return "C+";
        } else if (averagePercentage >= 40) 
        {
            return "C";
        } else {
            return "F";
        }
    }
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        int numberOfSubjects = getNumberOfSubjects(scanner);
        int[] marks = getMarks(numberOfSubjects, scanner);
        int totalMarks = calculateTotalMarks(marks);
        double averagePercentage = calculateAveragePercentage(totalMarks, numberOfSubjects);
        String grade = determineGrade(averagePercentage);
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage);
        System.out.println("Grade: " + grade);
        scanner.close();
    }
}
