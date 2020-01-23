// Tester class for Polynom object

import java.util.Scanner;

public class PolynomTester {
    public PolynomTester ()
    {
        System.out.println("-----------------");
        System.out.println("=== POLYNOM PROGRAM ===");
        System.out.println("-----------------");
        // run the test
        testProgram();
        System.out.println("----------------------------------");
        userInput();
    }

    private void testProgram()
    {
        double[] base = {8, -3, -1, 7};
        int[] power = {3, 2, 1, 0};

        // input parameter
        double[] paramBase = {1, 15};
        int[] paramExpo = {2, 0};

        /*
        // input Polynom
        double[] base = {1, -2, -1, 1};
        int[] power = {3, 2, 1, 0};

        // input parameter
        double[] paramBase = {7, 2, 1, 1};
        int[] paramExpo = {3, 2, 1, 0};
        */

        // create the polynom and call to print answer method
        Polynom p = new Polynom(base,power);
        Polynom param = new Polynom(paramBase,paramExpo);
        printAnswer(p,param);
    }

    private void printAnswer(Polynom p, Polynom param)
    {
        // print the input
        System.out.println("\n=== THE INPUT ===");
        System.out.println("Enter Polynom is: " + p.toString());
        System.out.println("Enter parameter is: " + param.toString());

        // print the methods
        System.out.println("\n=== THE METHODS ===");
        System.out.println("Plus method:\n(" + param.toString() + ") + (" + p.toString() + ")\n= " + p.plus(param).toString() + "\n");
        System.out.println("Minus method:\n(" + param.toString() + ") - (" + p.toString() + ")\n= " + p.minus(param).toString() + "\n");
        // System.out.println("Minus method 2:\n(" + p.toString() + ") - (" + param.toString() + ")\n= " + param.minus(p).toString() + "\n");
        System.out.println("Derivative method:\n(" + p.toString() + ")\n= " + p.derivative().toString() + "\n");
        // System.out.println("Derivative method 2:\n(" + p.toString() + ")\n= " + param.derivative().toString() + "\n");
        System.out.println("Equals method:\n(" + param.toString() + ") Equals to (" + p.toString() + ") ?\n" + p.equals(param)+ "\n");
        // System.out.println("Equals method 2:\n(" + p.toString() + ") Equals to (" + p.toString() + ") ?\n" + p.equals(p));
    }

    private void userInput()
    {
        int ans = -99;
        Scanner scan = new Scanner(System.in);  // Create a Scanner object

        // forever while ans not: 1 == YES or 0 == EXIT
        while (ans != 0 && ans != 1) {
            System.out.print("\nWant enter polynom by yourself?\n");
            System.out.print("Enter your answer (1 = YES, 0 = NO): ");
            ans = scan.nextInt();  // Read user input
            if (ans == 0) {
                System.out.println("Goodbye!");
                System.exit(0);
            } else if (ans == 1)
                break;
            else
                System.out.println("Not valid number, try again!");
        }
        // start input polynom method
        inputPolynom();
    }

    // NOT READY YET: input 2 Polynom data from user and run the program
    private void inputPolynom()
    {
        Polynom p;
        Polynom param;
        Scanner scan = new Scanner(System.in);  // Create a Scanner object

        System.out.println("-----------------");
        System.out.println("=== POLYNOM PROGRAM - USER INPUT ===");
        System.out.println("example: 8x^3 - 3x^2 - x + 7");
        System.out.println("-----------------");
        System.out.print("Enter the first polynom: ");
        String polynomIn = scan.nextLine();  // Read user input
        System.out.print("Enter the parameter: ");
        String paramIn = scan.nextLine();  // Read user input
        System.out.println("polynom: " + polynomIn);  // Output user input
        System.out.println("parameter: " + paramIn);  // Output user input

        // create the polynom and call to print answer method
        p = parsingInput(polynomIn);
        param = parsingInput(paramIn);
        printAnswer(p,param);
    }

    // parsing the input the use it as polynom
    private Polynom parsingInput (String input)
    {
        Polynom p;
        // remove white space from the input
        input = input.replaceAll("\\s+","");

        // Create a new string
        String newInput = addToMinus(input);

        // spilt the String to array of Strings by the char '+'
        String[] arrOfStr = newInput.split("\\+", 0);

        return p = new Polynom(arrOfStr);

    }

    // add "+" before each "-" in String for easy spilt
    private String addToMinus(String input)
    {
        // Create a new input string
        String newInput = new String();
        String addThisToMinus = "+";

        for (int ind = 0; ind < input.length(); ind++) {
            newInput += input.charAt(ind);
            if (ind+1 < input.length() && input.charAt(ind+1) == '-') {   // if not overflow and the next char is '-'
                newInput += addThisToMinus; // add this before
            }
        }

        return newInput;
    }
}
