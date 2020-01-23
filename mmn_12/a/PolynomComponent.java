// smallPolynom class

public class PolynomComponent {
    private int expo; // the n: the exponent (or power) in b^n
    private double base; // the b: the base in b^n

    // constructor by double and int
    public PolynomComponent(double base, int power)
    {
        this.base = base;
        this.expo = power;
    }

    // constructor by string
    public PolynomComponent(String str)
    {
        String[] arrOfStr = new String[1];
        arrOfStr = str.split("\\^", 0);

        // deal with the base
        if(arrOfStr[0].equals("x")) {   // if there only x
            this.base = 1;
        } else if(arrOfStr[0].equals("-x")) {   // if there only -x
            this.base = -1;
        } else if(arrOfStr[0].contains("x")) {   // if there char "x" remove and take the value
            arrOfStr[0] = arrOfStr[0].replaceAll("x","");
            this.base = Double.valueOf(arrOfStr[0]);
        } else      // if there only value in the str take the value
            this.base = Double.valueOf(str);

        // deal with the expo
        if (str.contains("^")) {      // if there exponent add the value
            this.expo = Integer.valueOf(arrOfStr[1]);
        } else if (str.contains("x"))   // if there x the exponent must be 1 - is mean it's "x"
            this.expo = 1;
        else    // there only base not x and not exponent
            this.expo = 0;
    }

    // copy constructor
    public PolynomComponent(PolynomComponent other)
    {
        this.base = other.base;
        this.expo = other.expo;
    }

    // return get base
    public double getBase()
    {
        return base;
    }

    // return get exponent
    public int getExpo()
    {
        return expo;
    }

    // set base
    public void setBase(double base)
    {
        this.base = base;
    }

    // set exponent
    public void setExpo(int expo)
    {
        this.expo = expo;
    }

    // return String representation of Polynom object
    public String toString()
    {
        return String.format("%.2f^%d", getBase(), getExpo());
    }

    // compare two SmallPolynom object return -1 for the bigger exponent!!
    public int compareByExpo(PolynomComponent other)
    {
        int expo1 = this.getExpo();
        int expo2 = other.getExpo();

        if (expo1 > expo2)
            return -1;  // not err here: return -1 mean is the bigger
        else if (expo1 < expo2)
            return 1;
        else
            return 0;
    }

    /* compare two SmallPolynom object
       return 1 for bigger, -1 for smaller, 0 if equal */
    public int compareTo(PolynomComponent other)
    {
        int expo1 = this.getExpo();
        int expo2 = other.getExpo();
        double base1 = this.getBase();
        double base2 = other.getBase();

        // calculate the power of them
        double poly_1 = Math.pow(base1, expo1);
        double poly_2 = Math.pow(base2, expo2);

        if (poly_1 > poly_2)
            return 1;
        else if (poly_1 < poly_2)
            return -1;
        else    // if equal return 0
            return 0;
    }
} // end class smallPolynom