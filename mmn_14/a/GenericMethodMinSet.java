import java.util.Iterator;

public class GenericMethodMinSet {
    // return the min member of the set
    public static <T extends Comparable<T>> T minInSet(Set<T> s)
    {
        T min = (T)s.getX(0);
        Iterator iter = s.iterator();
        Person p = new Person();

        // print the set to the screen
        while (iter.hasNext()) {
            iter.next();
            // System.out.println(s.compareTo(s));

        }



        return min;
    }
}

// System.out.println("" + min.toString());