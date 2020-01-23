// PieceWorker class extends class Employee.

public class PieceWorker extends Employee {
    private double onePiecePrice; // price of one piece
    private int numOfPieceProd; // number of piece the worker produce

    // constructor
    public PieceWorker(String firstName, String lastName,
                              String socialSecurityNumber, BirthDate birthDate,
                              double onePiecePrice, int numOfPieceProd)
    {
        super(firstName, lastName, socialSecurityNumber, birthDate);

        if (onePiecePrice <= 0.0) // validate
            throw new IllegalArgumentException("Piece price must be > 0.0");

        if (numOfPieceProd < 0) // validate
            throw new IllegalArgumentException("number of piece the worker produce must be >= 0.0");

        this.onePiecePrice = onePiecePrice;
        this.numOfPieceProd = numOfPieceProd;
    }

    // set piece price
    public void setOnePiecePrice(double onePiecePrice)
    {
        if (onePiecePrice <= 0.0) // validate
            throw new IllegalArgumentException("Piece price must be > 0.0");

        this.onePiecePrice = onePiecePrice;
    }

    // return piece price
    public double getOnePiecePrice()
    {
        return onePiecePrice;
    }

    // set number of piece the worker produce
    public void setNumOfPieceProd(int numOfPieceProd)
    {
        if (numOfPieceProd < 0) // validate
            throw new IllegalArgumentException("number of piece the worker produce must be >= 0.0");

        this.numOfPieceProd = numOfPieceProd;
    }

    // return number of piece the worker produce
    public double getNumOfPieceProd()
    {
        return numOfPieceProd;
    }

    // calculate earnings; override abstract method earnings in Employee

    public double earnings()
    {
        _giftFlag = false;
        double salary = getNumOfPieceProd() * getOnePiecePrice() + super.birthDayGift();
        _giftFlag = true;
        return salary;
    }

    // return String representation of PieceWorker object

    public String toString()
    {
        return String.format("%s: %s%n%s: $%,.2f; %s: %.2f",
                "commission employee", super.toString(),
                "pay for each piece is", getOnePiecePrice(),
                "produce #item", getNumOfPieceProd());
    }
} // end class PieceWorker
