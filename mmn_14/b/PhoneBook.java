// PhoneBook class

import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
    private TreeMap<String,String> p_book;

    // empty constructor
    public PhoneBook()
    {
        p_book = new TreeMap();
    }

    // constructor with map
    public PhoneBook(TreeMap map)
    {
        p_book = new TreeMap(); // initialize the Phone Book
        p_book = map;
        PhoneBookGUI thePhoneBookGUI = new PhoneBookGUI(getPhoneBook());
    }

    // get the phone book
    public TreeMap getPhoneBook()
    {
        return this.p_book;
    }

    // return String representation of PhoneBook object
    @Override
    public String toString() {
        return "PhoneBook{" +
                "p_book=" + p_book +
                '}';
    }
}
