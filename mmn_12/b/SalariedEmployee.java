// Fig. 10.5: SalariedEmployee.java
// SalariedEmployee concrete class extends abstract class Employee.

public class SalariedEmployee extends Employee
{
    private double weeklySalary;

    // constructor
    public SalariedEmployee(String firstName, String lastName,
                            String socialSecurityNumber, BirthDate birthDate,
                            double weeklySalary)
    {
        super(firstName, lastName, socialSecurityNumber, birthDate);

        if (weeklySalary < 0.0)
            throw new IllegalArgumentException(
                    "Weekly salary must be >= 0.0");

        this.weeklySalary = weeklySalary;
    }

    // set salary
    public void setWeeklySalary(double weeklySalary)
    {
        if (weeklySalary < 0.0)
            throw new IllegalArgumentException(
                    "Weekly salary must be >= 0.0");

        this.weeklySalary = weeklySalary;
    }

    // return salary
    public double getWeeklySalary()
    {
        return weeklySalary;
    }

    // calculate earnings: override abstract method earnings in Employee

    public double earnings()
    {
        _giftFlag = false;
        double salary = getWeeklySalary() + super.birthDayGift();
        _giftFlag = true;
        return salary;
    }

    // return String representation of SalariedEmployee object

    public String toString()
    {
        return String.format("salaried employee: %s%n%s: $%,.2f",
                super.toString(), "weekly salary", getWeeklySalary());
    }
} // end class SalariedEmployee