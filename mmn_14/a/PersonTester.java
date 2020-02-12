import java.util.*;

// Tester class for Person object
public class PersonTester {

    // empty constructor
    public PersonTester()
    {
        System.out.println("-----------------");
        System.out.println("=== Person PROGRAM: Tester ===");
        System.out.println("-----------------");
        // run the test
        testProgram();
    }

    private void testProgram()
    {
        // initialization the Persons and the Set
        Person p1,p2,p3,p4,p5;
        Set <Person> s = new Set<>();

        p1 = new Person(11111, "aaa", "bbb", new Date(99, 00,15));
        p2 = new Person(22222, "ccc", "ddd", new Date(101, 05,30));
        p3 = new Person(33333, "ccc", "ddc", new Date(110, 10,00));
        p4 = new Person(44444, "aa", "ffa", new Date(110, 10,00));
        p5 = new Person(55555, "aa", "ffb", new Date(95, 02,16));

        // insert Persons to Set
        s.insert(p1);
        s.insert(p2);
        s.insert(p3);
        s.insert(p4);
        s.insert(p5);

        // Iterator for Set
        Iterator iter = s.iterator();

        // print the set to the screen
        while (iter.hasNext())
           System.out.println(iter.next() + " ");

        // get the min in the set and print
        Person min = minInSet(s);
        System.out.println("\nThe minimum is:\n " + min.toString());

    }

    // generic method that return the minimum member of the Set by compareTo
    public static <T extends Comparable<T>> T minInSet(Set<T> s)
    {
        Iterator iter = s.iterator();   // Iterator for the Set
        T curMin = (T)iter.next(); // for now 1st is the minimum
        T check;

        // run on the set and get the minimum
        while (iter.hasNext()) {
            check = (T)iter.next(); // check get the next member

            /*
                compareTo > 0, mean we found new minimum, update curMin -> check
                compareTo < 0, the minimum is still the minimum, not update curMin
                compareTo == 0, them equal curMin == check, not update curMin
             */
            if (curMin.compareTo(check) > 0)
                curMin = check;
        }

        return curMin;
    }
}
