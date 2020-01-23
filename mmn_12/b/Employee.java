// Fig. 10.4: Employee.java
// Employee abstract superclass

public abstract class Employee extends BirthDate
{
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    private BirthDate birthDate;    // maybe this should be final, but for fixing date mistake - it's more reasonable to write it like this
    private final int disDate = -9999;  // number of null date from BirthDate empty constructor
    public final double _birthDaySum = 200; // the sum of birth day gift
    private boolean _birthDayThisMonth = false; // birth day this month
    public boolean _giftFlag = false; // flag for avoid double gift to employee


    // constructor
    public Employee(String firstName, String lastName,
                    String socialSecurityNumber, BirthDate birthDate)
    {
        BirthDate b = new BirthDate(birthDate);
        super.setBirthDate(b);  // update the father! else in father the date is -9999
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;

        if (birthDate.emptyDate() == disDate) // null date from BirthDate empty constructor
            throw new IllegalArgumentException(
                    "Tried assign to employee an empty date - Please set a date before!");

        this.birthDate = b;
    }

    // override method to set birth date of employee
    public BirthDate setBirthDate(int year, int month, int day)
    {
        return this.birthDate = super.setBirthDate(year,month,day);
    }

    // return first name
    public String getFirstName()
    {
        return firstName;
    }

    // return last name
    public String getLastNameName()
    {
        return lastName;
    }

    // return social security number
    public String getSocialSecurityNumber()
    {
        return socialSecurityNumber;
    }

    // return birth day gift sum
    private double getBirthDaySum()
    {
        return _birthDaySum;
    }

    // override method the check if the employee have birthday this month
    public boolean checkIfBirthDayThisMonth()
    {
        _birthDayThisMonth = super.checkIfBirthDayThisMonth();
        return _birthDayThisMonth;
    }

    // return String representation of Employee object

    public String toString()
    {
        if (checkIfBirthDayThisMonth())
            return String.format("%s %s%nsocial security number: %s%nBirth Date: %s%nHappy Birth Month! We added to your salary gift of 200$!!",
                    getFirstName(), getLastNameName(), getSocialSecurityNumber(),
                    birthDate.toString());

        return String.format("%s %s%nsocial security number: %s%nBirth Date: %s",
                getFirstName(), getLastNameName(), getSocialSecurityNumber(),
                birthDate.toString());
    }

    // abstract method must be overridden by concrete subclass
    public abstract double earnings();  // no implementation here

    // gift for month birth day
    public double birthDayGift()
    {
        if (_giftFlag == false) // for get gift only one time
            if (checkIfBirthDayThisMonth())
                    return getBirthDaySum();

        return 0;
    }
} // end abstract class Employee