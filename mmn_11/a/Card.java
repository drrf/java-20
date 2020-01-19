// From -> 300P
// Card class represents a playing card.

public class Card
{
    private final int value;    // value of card (1, 2, ... 13)
    private final String face;  // face of card ("Ace", "Deuce", ...)
    private final String suit;  // suit of card ("Hearts", "Diamonds", ...)

    // two-argument constructor initializes card's face and suit
    public Card(int cardValue, String cardFace, String cardSuit)
    {
        this.value = cardValue; // initialize value of card
        this.face = cardFace;   // initialize face of card
        this.suit = cardSuit;   // initialize suit of card
    }

    // return String representation face of card
    public String getFace()
    {
        return face;
    }

    // return String representation Suit of card
    public String getSuit()
    {
        return suit;
    }

    // return value 0 if equal, 1 if bigger, -1 if smaller
    public int compare(Card other) {
        if (this.value == other.value)
            return 0;
        else if(this.value > other.value)
            return 1;
        else
            return -1;
    }

    // return String representation of card
    public String toString()
    {
        return face + " of " + suit;
    }
} // end of card