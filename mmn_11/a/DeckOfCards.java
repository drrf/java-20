// From -> 300-301P
// DackOfCards class represents a deck of playing cards.
import java.io.*;
import java.util.*;
import java.security.SecureRandom;

public class DeckOfCards
{
    private ArrayList<Card> deck;    // array of Card objects
    private int currentCard;    // index of next Card to be dealt (0-51)
    private static final int NUMBER_OF_CARDS = 52;  // constant # of Cards
    private static final SecureRandom randomNumbers = new SecureRandom();   // random number generator
    private static final int TOP = 0;
    private static final int ONE = 1;

    // constructor fills deck of Cards
    public DeckOfCards()
    {
        String[] faces = { "Ace", "Deuce", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spaces" };

        deck = new ArrayList<Card>(NUMBER_OF_CARDS);
        currentCard = 0;    // first Card dealt will be deck[0]
        // populate deck with Card objects
        for (int count = 0; count < NUMBER_OF_CARDS; count++)
            deck.add(new Card((count % 13)+ONE, faces[count % 13], suits[count / 13]));
    }

    // constructor of empty deck of Cards
    public DeckOfCards(int empty)
    {
        deck = new ArrayList<Card>(NUMBER_OF_CARDS);
        return;
    }

    // print all 52 Cards in the order in which they are dealt
    public void printsTheDeck(DeckOfCards Deck)
    {
        for (int i = 1; i <= Deck.sizeDeck(); i++)
        {
            // deal and display a Card
            System.out.printf("%-19s", Deck.dealCard());

            if (i % 4 == 0) // output a newline after every fourth card
                System.out.println();
        }
        System.out.println("\n");
    }

    // shuffle deck of Cards with one-pass algorithm
    public void shuffle()
    {
        // next call to method dealCard should start at deck[0] again
        currentCard = 0;

        // for each Card, pick another random Card (0-51) and swap them
        for (int first = 0; first < deck.size(); first++)
        {
            // select a random number between 0 and 51
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            // swap current Card with randomly selected Card
            Card temp = deck.get(first);
            Collections.swap(deck, first, second);
            deck.set(second, temp);
        }
    }

    // deal one Card
    public Card dealCard()
    {
        // determine whether Cards remain to be dealt
        if (currentCard < deck.size())
            return deck.get(currentCard++); // return current Card in array
        else
            return null;    // return null to indicate that all Cards were dealt
    }

    // print the top card
    public Card getTopCard()
    {
        return deck.get(TOP);
    }

    // dealt one Card to the players from master Deck
    public void dealtCards(DeckOfCards other_Deck)
    {
        Card c = this.deck.remove(TOP);
        other_Deck.deck.add(c);
    }

    public void putCardsOnEnd(int num, DeckOfCards other_Deck)
    {
        for (int i = 0; i < num; i++) {
            Card c1 = this.deck.remove(TOP);
            Card c2 = other_Deck.deck.remove(TOP);
            this.deck.add(c1);
            this.deck.add(c2);
        }
    }

    public void putCardsOnMASTER(int num, DeckOfCards p1_Deck, DeckOfCards p2_Deck)
    {
        for (int i = 0; i < num; i++) {
            Card c1 = p1_Deck.deck.remove(TOP);
            Card c2 = p2_Deck.deck.remove(TOP);
            this.deck.add(c1);
            this.deck.add(c2);
        }
    }

    // return the size of the deck
    public int sizeDeck()
    {
        return deck.size();
    }

    // return min size of 2 deck (this vs other)
    public int getSizeMinDeck(DeckOfCards other_Deck)
    {
        int min = 3;
        int a = this.deck.size();
        int b = other_Deck.deck.size();

        if (a < 3 || b < 3 )
            min = a < b ? a : b;    // return 2 for minimum - the war round be on min cards
        else if (a == 3 || b == 3)
            return min - 1; // return 2 for minimum - the war round be on 2 cards

        return min;
    }

    public Card getThe_X_Card(int num)
    {
        if (num == 1)   // if only 1 card in the deck it's the top card
            return deck.get(TOP);
        else
            return deck.get(TOP+num);
    }


} // end class DeckOfCards