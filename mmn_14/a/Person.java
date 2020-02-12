// Person class

import java.util.Date;
import java.util.Objects;

public class Person implements Comparable<Person> {
    private int id;
    private String lsatName;
    private String firstName;
    private Date birthDay;
    private final int disID = -999; // number of null ID for empty constructor

    // empty constructor
    public Person()
    {
        this.id = disID;
    }

    // constructor with data
    public Person(int id, String lastName, String firstName, Date birthDay)
    {
        setId(id);
        setLsatName(lastName);
        setFirstName(firstName);
        setBirthDay(birthDay);
    }

    // set ID
    private void setId(int id)
    {
        this.id = id;
    }

    // Set Last name
    private void setLsatName(String lsatName)
    {
        this.lsatName = lsatName;
    }

    // set First name
    private void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    // set Date of birth
    private void setBirthDay(Date birthDay)
    {
        this.birthDay = birthDay;
    }

    // return id
    private int getId()
    {
        return this.id;
    }

    // return last name
    private String getLsatName()
    {
        return this.lsatName;
    }

    // return first name
    private String getFirstName()
    {
        return this.firstName;
    }

    // return date of birth
    private Date getBirthDay()
    {
        return this.birthDay;
    }

    /* override compareTo: by Lexicographic the last name
       Person's are equals if their last && first name are equals */
    @Override
    public int compareTo(Person o)
    {
        return this.comparePerson(o);
    }

    // private compareTo help method
    private int comparePerson(Person p)
    {
        int ans;

        ans = this.lsatName.compareTo(p.lsatName);  // check last name
        if ( ans == 0) // if last name equals
            ans = this.firstName.compareTo(p.firstName);    // check first name

        return ans;
    }

    // override equals: Person's are equals if their last && first name are equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // if the same object return true
        if (o == null || getClass() != o.getClass()) return false;  // if null or different class return flase

        return this.compareTo((Person) o) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lsatName, firstName, birthDay, disID);
    }

    // return String representation of Person object
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lsatName='" + lsatName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
