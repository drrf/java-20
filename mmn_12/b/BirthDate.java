// BirthDate class

import java.util.Calendar;
import java.util.Date;

public class BirthDate {
    private int year;
    private int month;
    private int day;
    private final int disDate = -9999;   // number of null date for empty constructor

    // empty constructor
    public BirthDate() {
        this.year = disDate;
        this.month = disDate;
        this.day = disDate;
    }

    // constructor with int
    public BirthDate(int year, int month, int day)
    {
        checkDate(year, month, day);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // constructor with Date object
    public BirthDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int d = cal.get(Calendar.DAY_OF_MONTH);
        checkDate(y, m, d);

        this.year = y;
        this.month = m;
        this.day = d;
    }

    // copy constructor
    public BirthDate(BirthDate other)
    {
        this.year = other.year;
        this.month = other.month;
        this.day = other.day;
    }

    // check if date is validate
    private void checkDate(int year, int month, int day)
    {
        if (year < 1900 || year > 2100)   // validate year
            throw new IllegalArgumentException("Year must be > 1900 and < 2100");

        if (month < 1 || month > 12)   // validate month
            throw new IllegalArgumentException("Month muse be > 0 and < 13");

        if (day < 1 || day > 31)   // validate day
            throw new IllegalArgumentException("Day muse be > 0 and < 31");
    }

    // set year
    private void setYear(int year)
    {
        if (year < 1900 || year > 2100)   // validate year
            throw new IllegalArgumentException("Year must be > 1900 and < 2100");
        checkDate(year, disDate, disDate);
        this.year = year;
    }

    // set month
    private void setMonth(int month)
    {
        if (month < 1 || month > 12)   // validate month
            throw new IllegalArgumentException("Month muse be > 0 and < 13");
        this.month = month;
    }

    // set day
    private void setDay(int day)
    {
        if (day < 1 || day > 31)   // validate day
            throw new IllegalArgumentException("Day muse be > 0 and < 31");
        this.day = day;
    }

    // set date by value
    public BirthDate setBirthDate(int year, int month, int day)
    {
        checkDate(year, month, day);
        BirthDate b = new BirthDate(year,month,day);
        this.year = year;
        this.month = month;
        this.day = day;

        return b;
    }

    // set date by object
    public void setBirthDate(BirthDate other)
    {
        checkDate(other.year, other.month, other.day);
        this.year = other.year;
        this.month = other.month;
        this.day = other.day;
    }

    // return if validate date
    public int emptyDate()
    {
        return getYear();    // if year == disDate this is null date
    }

    // return if month of object is today month
    public boolean checkIfBirthDayThisMonth()
    {
        Calendar c = Calendar.getInstance();
        int currMonth = c.get(Calendar.MONTH) + 1; // fix month start from zero

        if (this.getMonth() == currMonth)
            return true;
        else
            return false;
    }

    // return year
    private int getYear()
    {
        return year;
    }

    // return month
    private int getMonth()
    {
        return month;
    }

    // return day
    private int getDay()
    {
        return day;
    }

    // return String representation of BirthDate object

    public String toString()
    {
        return String.format("%d/%d/%d",
                getYear(), getMonth(), getDay());
    }
}
