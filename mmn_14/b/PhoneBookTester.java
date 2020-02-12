import java.util.Map;
import java.util.TreeMap;

// Tester class for Person object
public class PhoneBookTester {
    // empty constructor
    public PhoneBookTester()
    {
        /*
        System.out.println("-----------------");
        System.out.println("=== Phone Book PROGRAM: Tester ===");
        System.out.println("-----------------");*/
        // run the test
        testProgram();
    }

    private void testProgram()
    {
        // add data
        TreeMap<String,String> map=new TreeMap<>();
        map.put("John Smith", "1-800-050-166");
        map.put("David Jones", "050-7079630");
        map.put("Michael Lee", "*166");
        map.put("Chris Gonzalez", "+972509999166");

        // create phone book
        PhoneBook pb = new PhoneBook(map);

        // for testing
        /*System.out.println(map.toString());
        for(Map.Entry m:map.entrySet()){
            System.out.println(m.getKey()+"\t"+m.getValue());
        }*/
    }
}
