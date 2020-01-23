// Polynom class

import javax.management.remote.rmi._RMIConnection_Stub;
import java.util.ArrayList;

public class Polynom extends SortByExpo {
    private ArrayList<PolynomComponent> polynomArr;
    private int length;
    private final int errInFunc = -999;   // num of err in whether function
    private final int _PLUS = 1;    // indicate the plus calculate in calcPolynom method
    private final int _MINUS = -1;  // indicate the minus calculate in calcPolynom method

    // constructor with String[]
    public Polynom(String[] input) {
        // create new ArrayList of SmallPolynom and copy data into
        ArrayList<PolynomComponent>pArr = new ArrayList<PolynomComponent>();
        PolynomComponent sp;

        for (String a : input) {
            if(a != null && !a.isEmpty())
                pArr.add(sp = new PolynomComponent(a));
        }

        // sort the ArrayList
        pArr = sortByExpoARR(pArr);
        this.polynomArr = pArr;
    }

    // constructor with double[] and int[]
    public Polynom(double[] base, int[] power) {
        if (!checkArrayLength(base, power)) // check if the arrays have the same length
            throw new IllegalArgumentException(
                    "The length of the arrays are not the same!");
        this.length = base.length;

        // create new ArrayList of SmallPolynom and copy data into
        ArrayList<PolynomComponent>pArr = new ArrayList<PolynomComponent>();
        for (int i = 0; i < base.length; i++){
            pArr.add(copyDataArr(base, power, i));
        }

        // sort the ArrayList
        pArr = sortByExpoARR(pArr);
        this.polynomArr = pArr;
    }

    // copy constructor
    public Polynom(Polynom other)
    {
        // deep copy the original ArrayList to a new one
        ArrayList<PolynomComponent>cpArr = new ArrayList<PolynomComponent>();
        for (int i = 0; i < other.polynomArr.size(); i++){
            PolynomComponent cpSP = new PolynomComponent(other.polynomArr.get(i));
            cpArr.add(cpSP);
        }

        this.polynomArr =  cpArr;
    }

    // check array the same length
    private boolean checkArrayLength(double[] base, int[] power)
    {
        return (base.length == power.length) ? true : false;
    }

    // sort the ArrayList by exponent
    private ArrayList<PolynomComponent> sortByExpoARR(ArrayList<PolynomComponent> pArr)
    {
        return SortByExpoARR(pArr);  // call to method in class SortByExpo
    }

    // copy from array type to SmallPolynom type one each time
    private PolynomComponent copyDataArr(double[] base, int[] power, int i)
    {
        if (base[i] == 0) // check if base is zero
            throw new IllegalArgumentException(
                    "The base can't be == 0!");
        if (power[i] < 0) // check if exponent is negative
            throw new IllegalArgumentException(
                    "The exponent can't be < 0!");
        PolynomComponent sp = new PolynomComponent(base[i],power[i]);
        return sp;
    }

    // calculate the Polynom with parameter
    private Polynom calcPolynom(Polynom parameter, int indicate)
    {
        boolean flag = false;   // flag to break out the loop
        Polynom cp = new Polynom(this); // copy the polynom for working & changing only on the copy

        for (int i = 0; i < parameter.polynomArr.size(); i++) {
            PolynomComponent parmSP = parameter.polynomArr.get(i);
            int paramExpo = parmSP.getExpo();
            double paramBase = parmSP.getBase();

            for (int j = 0; j < cp.polynomArr.size(); j++) {
                flag = false;
                PolynomComponent polySP = cp.polynomArr.get(j);
                int polyExpo = polySP.getExpo();
                double polyBase = polySP.getBase();

                switch(indicate) {
                    case _PLUS:
                        if (paramExpo == polyExpo) {    // if the exponent equal
                            if ((paramBase + polyBase) == 0)    // if also the base equal than remove
                                cp.polynomArr.remove(j);
                            else
                                polySP.setBase(paramBase + polyBase);   // make the addition
                            flag = true;
                        }
                        break;
                    case _MINUS:
                        if (paramExpo == polyExpo) {    // if the exponent equal
                            if ((paramBase - polyBase) == 0)  // if also the base equal than remove
                                cp.polynomArr.remove(j);
                            else
                                polySP.setBase((paramBase) - (polyBase));
                            flag = true;
                        } else if(paramExpo != polyExpo) {    // if the exponent not equal
                            // check the original and set this polynom negative
                            double orgPolyBase = this.polynomArr.get(j).getBase();
                            if (polyBase == orgPolyBase)
                                polySP.setBase((orgPolyBase) * (-1));  // set negative
                        } else
                            ;
                        break;
                    default:
                        throw new IllegalArgumentException(
                                "Unknown err in calcPolynom method!");
                }
                if (flag == true)
                    break;
            }
        }

        return cp;
    }

    // return polynom after addition the parameter
    public Polynom plus (Polynom parameter)
    {
        Polynom polynomPlus = new Polynom(this.calcPolynom(parameter,_PLUS));
        return polynomPlus;
    }

    // return polynom after subtraction the parameter
    public Polynom minus (Polynom parameter)
    {
        Polynom polynonMinus = new Polynom(this.calcPolynom(parameter,_MINUS));
        return polynonMinus;
    }

    // return polynom after derivative
    public Polynom derivative()
    {
        Polynom polyDerivative = new Polynom(this);
        return polyDerivative.calcDerivative();
    }

    // calculate derivative and return the polynom
    private Polynom calcDerivative()
    {
        for (int i = 0; i < this.polynomArr.size(); i++) {
            PolynomComponent polySP = this.polynomArr.get(i);
            int paramExpo = polySP.getExpo();
            double paramBase = polySP.getBase();
            if (paramExpo == 0) // remove PolynomComponent object if the exponent == 0
                this.polynomArr.remove(i);
            else {
                polySP.setBase(paramBase * paramExpo);
                polySP.setExpo(paramExpo - 1);
            }
        }
        return this;
    }

    // print the Polynom object
    private String printPolynom()
    {
        String strOut = new String();

        for (int ind = 0; ind < polynomArr.size(); ind++) {
            PolynomComponent sp = polynomArr.get(ind);
            // System.out.println("\nind " + ind + ", case: " + checkPolynomComponent(sp));
            switch(checkPolynomComponent(sp)) {
                case -4:     // example: -2^0 -> print -2
                    if (ind == 0) // if the first member of the polynom
                        strOut += ("-" + Math.abs(sp.getBase()));
                    else
                        strOut += (" - " + Math.abs(sp.getBase()));
                    break;
                case -3:     // example: -2^0 -> print -2
                    if (ind == 0) // if the first member of the polynom
                        strOut += ("-x");
                    else
                        strOut += (" - x");
                    break;
                case -2:    // example: -2^1 -> print -2x
                    if (ind == 0) // if the first member of the polynom
                        strOut += ("-" + Math.abs(sp.getBase()) + "x");
                    else
                        strOut += (" - " + Math.abs(sp.getBase()) + "x");
                    break;
                case -1:    // example: -3^x -> print -3x^x
                    if (ind == 0) // if the first member of the polynom
                        strOut += ("-" + Math.abs(sp.getBase()) + "x^" + sp.getExpo());
                    else
                        strOut += (" - " + Math.abs(sp.getBase()) + "x^" + sp.getExpo());
                    break;
                case 0:     // example: 2^0 -> print 2
                    if (ind == 0) // if the first member of the polynom
                        strOut += ("" + sp.getBase());
                    else
                        strOut += (" + " + sp.getBase());
                    break;
                case 1:     // example: 1^1 -> print x
                    if (ind == 0) // if the first member of the polynom
                        strOut += ("x");
                    else
                        strOut += (" + x");
                    break;
                case 2:    // example: 1^x -> print x^x
                    if (ind == 0) // if the first member of the polynom
                        strOut += ("x^" + sp.getExpo());
                    else
                        strOut += (" + x^" + sp.getExpo());
                    break;
                case 3:    // example: 2^1 -> print 2x
                    if (ind == 0) // if the first member of the polynom
                        strOut += ("" + sp.getBase() + "x");
                    else
                        strOut += (" + " + sp.getBase() + "x");
                    break;
                case 4:    // example: 2^2 -> print 2x^2
                    if (ind == 0) // if the first member of the polynom
                        strOut += ("" + sp.getBase() + "x^" + sp.getExpo());
                    else
                        strOut += (" + " + sp.getBase() + "x^" + sp.getExpo());
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Unknown err in printPolynom method!");
            }
        }

        // strOut += ("test!!!");
        return strOut;
    }

    // check each case the printPolynom method need to print
    private int checkPolynomComponent(PolynomComponent sp)
    {
        double d = sp.getBase();
        int i = sp.getExpo();

        if (d < 0 && i == 0)    // example: -2^0 -> print -2
            return -4;
        if (d == -1 && i == 1)    // example: -1^1 -> print -x
            return -3;
        if (d < 0 && i == 1)    // example: -2^1 -> print -2x
            return -2;
        if (d < 0)  // example: -3^x -> print -3x^x
            return -1;
        if (d >= 1 && i == 0)    // example: 2^0 -> print 2
            return 0;
        if (d == 1 && i == 1)   // example: 1^1 -> print x
            return 1;
        if (d == 1) // example: 1^x -> print x^x
            return 2;
        if (d > 1 && i == 1)    // example: 2^1 -> print 2x
            return 3;
        if (d > 1 && i > 1)    // example: 2^2 -> print 2x^2
            return 4;

        return errInFunc;
    }

    // override equals method
    public boolean equals(Object obj)
    {
        // If the object is compared with itself then return true
        if (obj == this)
            return true;

        /* Check if obj is an instance of Polynom or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Polynom))
            return false;

        // call to Polynom equals help method
        return this.equalsHelp((Polynom) obj);
    }

    // return true if to two polynom have the same member
    private boolean equalsHelp(Polynom other)
    {
        // if they don't have the same length return false
        if (this.polynomArr.size() != other.polynomArr.size()) {
            return false;
        }

        // for loop for check member of polynom
        for (int i = 0; i < polynomArr.size(); i++) {
            PolynomComponent sp1 = this.polynomArr.get(i);
            PolynomComponent sp2 = other.polynomArr.get(i);
            if (sp1.compareTo(sp2) != 0)    // 0 mean equal
                return false;
        }

        return true;
    }

    // return String representation of Polynom object
    public String toString()
    {
        return String.format("" + printPolynom());
    }
}
