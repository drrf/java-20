// Tester class for Set object
import java.util.Random;
import java.util.Scanner;

public class SetTester {
    private Scanner scan = new Scanner(System.in);  // Create a Scanner object
    private Random rand = new Random();

    // empty constructor
    public SetTester ()
    {
        System.out.println("-----------------");
        System.out.println("=== SET PROGRAM: Tester ===");
        System.out.println("-----------------");
        // run the test
        testProgram();
    }

    // user input for int number only!
    private int userInput()
    {
        while (!scan.hasNextInt()) scan.next(); // while not get int from input
        int num = scan.nextInt();

        return num;
    }

    private void testProgram()
    {
        int num = 10;
        int rand_int1, rand_int2, rand_int3;
        int num1, num2, num3;

        // initialization the Sets
        Set <Integer> set1 = new Set();
        Set <Integer> set2 = new Set();
        Set <Integer> set3 = new Set();
        Set <Integer> set4 = new Set();

        // add random number
        while (num--!=0)
        {
            set1.insert(rand_int1 = rand.nextInt(100));
            set2.insert(rand_int2 = rand.nextInt(100));
            set3.insert(rand_int3 = rand.nextInt(100));
        }

        // all the test output to screen
        System.out.println("Set1: " + set1.toString());
        System.out.println("Set2: " + set2.toString());
        System.out.println("Set3: " + set3.toString());

        set1.union(set2);
        System.out.println("\nSet2 (after union with Set1): " + set2.toString());

        set2.intersect(set3);
        System.out.println("Set3 (after intersect with Set2): " + set3.toString());

        System.out.println("----------------------------------");
        System.out.print("Please enter number 1: ");
        num1 = userInput();
        System.out.print("Please enter number 2: ");
        num2 = userInput();

        System.out.println("You enter: " + num1 + " " + num2);

        set4.insert(num1);
        set4.insert(num2);

        System.out.println("\nSet4 is sub-set of Set1: " + set1.isSubset(set4));
        System.out.println("Set4 is sub-set of Set2: " + set2.isSubset(set4));
        System.out.println("Set4 is sub-set of Set3: " + set3.isSubset(set4));
        System.out.println("Set4 is sub-set of Set4: " + set4.isSubset(set4));

        System.out.print("\nPlease enter number 3: ");
        num3 = userInput();

        System.out.println(num3 + " is Member of Set1: " + set1.isMember(num3));

        set2.insert(num3);
        System.out.println("Set2 (after insert " + num3 + "): " + set2.toString());

        set3.delete(num3);
        System.out.println("Set3 (after delete " + num3 + "): " + set3.toString());
    }
}
