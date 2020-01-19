// CardsGame class represents a game of playing cards.

import javax.swing.*;
import java.awt.*;

public class CardsGame extends JPanel {
    private static final int NUMBER_OF_CARDS = 52;  // constant # of Cards
    private static final int EMPTY = 0;  // constant of empty deck
    private static final int TOP = 0;   // the top of the deck of Cards
    private static final  int outLOOP = -99; // constant # for round() -> switch case
    private static final  int player1 = 1;
    private static final  int player2 = 2;
    private JFrame frame;
    private JPanel p;
    private JLabel headline, center, west, east, endText;

    public void start_game()
    {
        GUI_SETUP();

        // get master deck of cards, shuffles and dealt to players
        DeckOfCards masterDeck = new DeckOfCards(), p1_Deck = new DeckOfCards(EMPTY), p2_Deck = new DeckOfCards(EMPTY);
        masterDeck.shuffle();
        dealt_2_players(masterDeck,p1_Deck,p2_Deck);

        // continue until one of the deck is empty
        for (int i=1; p1_Deck.sizeDeck() > EMPTY && p2_Deck.sizeDeck() > EMPTY; ++i)
        {
            round(p1_Deck, p2_Deck, masterDeck);
        }

        update_GUI_playersSizeDecks(p1_Deck.sizeDeck(), p2_Deck.sizeDeck());
        if (p1_Deck.sizeDeck()>p2_Deck.sizeDeck())
            end_game(player1);
        else
            end_game(player2);

    }

    // dealt the cards to the 2 players
    private void dealt_2_players(DeckOfCards masterDeck, DeckOfCards p1_deck, DeckOfCards p2_deck)
    {
        Card c = masterDeck.getTopCard();
        for (int i = 1; i <= NUMBER_OF_CARDS; i++)
        {
            if (i % 2 != 0)
                masterDeck.dealtCards(p1_deck);
            else
                masterDeck.dealtCards(p2_deck);
        }
    }

    // this is one round in the games
    private void round(DeckOfCards p1_Deck, DeckOfCards p2_Deck, DeckOfCards masterDeck)
    {
        Card c1 = p1_Deck.getTopCard();
        Card c2 = p2_Deck.getTopCard();
        int round = 1;
        int res = c1.compare(c2);   // return 0 if equal, 1 if bigger, -1 if smaller
        int minDeck;

        do {
            update_GUI_playersSizeDecks(p1_Deck.sizeDeck(), p2_Deck.sizeDeck());
            switch(res) {
                case 0:
                    printWAR(0, c1, c2); // print the war info
                    res = war(1, p1_Deck, p2_Deck, masterDeck);
                    break;
                case 1:     // Player_1: win!
                    printRound(player1, c1, c2);
                    p1_Deck.putCardsOnEnd(1, p2_Deck);
                    res = outLOOP;
                    break;
                case -1:    // Player_2: win!
                    printRound(player2, c1, c2);
                    p2_Deck.putCardsOnEnd(1, p1_Deck);
                    res = outLOOP;
                    break;
                default:
                    System.out.println("ERR!\n");
            }
        } while (res != outLOOP);   // not need the loop -> remember to del in the next updates
    }

    // this is when war between to cards
    private int war(int round, DeckOfCards p1_Deck, DeckOfCards p2_Deck, DeckOfCards masterDeck)
    {
        int minDeck;
        Card c1, c2;

        minDeck = p1_Deck.getSizeMinDeck(p2_Deck);  // return 3 or less (if one Deck is smaller)
        masterDeck.putCardsOnMASTER(minDeck, p1_Deck, p2_Deck); // take 3 (or less) cards from p1 and p2, put on masterDeck
        c1 = p1_Deck.getTopCard();
        c2 = p2_Deck.getTopCard();

        if (c1.compare(c2) == 1)    // Player_1: win!
        {
            printWAR(player1, c1, c2);
            p1_Deck.putCardsOnEnd(1, p2_Deck);
            p1_Deck.putCardsOnEnd(masterDeck.sizeDeck(), masterDeck);
        } else if (c1.compare(c2) == -1) {  // Player_2: win!
            printWAR(player2, c1, c2);
            p2_Deck.putCardsOnEnd(1, p1_Deck);
            p2_Deck.putCardsOnEnd(masterDeck.sizeDeck(), masterDeck);
        } else {    // another war round
            war(round+1, p1_Deck, p2_Deck, masterDeck);
        }

        return outLOOP;
    }

    // print win p1 == 1, p2 == 2
    private void printRound(int winner, Card c1, Card c2)
    {
        if (winner == 1) {
            JOptionPane.showMessageDialog(frame, "p1 card: " + c1 +
                    "\np2 card: " + c2 + "\nPlayer_1: win!");
        } else if (winner == 2) {
            JOptionPane.showMessageDialog(frame,"p1 card: " + c1 +
                    "\np2 card: " + c2 + "\nPlayer_2: win!");
        } else {
            System.out.println("ERR from printRound(): " + winner);
        }
    }

    // print win p1 == 1, p2 == 2, war == 0 or else
    private void printWAR(int winner, Card c1, Card c2)
    {
        if (winner == 1) {
            JOptionPane.showMessageDialog(frame,"p1 card: " + c1 +
                    "\np2 card: " + c2 + "\nPlayer_1: win the war!", "WAR!", JOptionPane.ERROR_MESSAGE);
        } else if (winner == 2) {
            JOptionPane.showMessageDialog(frame,"p1 card: " + c1 +
                    "\np2 card: " + c2 + "\nPlayer_2: win the war!", "WAR!", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame,"p1 card: " + c1 +
                    "\np2 card: " + c2 + "\nTHIS IS WAR!!", "WAR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    // print the the winner
    private void end_game(int win)
    {
        center.setText("== THE WINNER ==");
        endText.setText("-> Player #" + win);
    }

    // update GUI of player deck size
    private void update_GUI_playersSizeDecks(int p1, int p2)
    {
        west.setText("  p1: " + p1);
        east.setText("p2: " + p2 + "  ");
    }

    // GUI SETUP FOR THE GAME
    private void GUI_SETUP()
    {
        frame = new JFrame("WAR GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,150);
        p = new JPanel();
        frame.add(p);
        p.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        headline = new JLabel("=== CARDS - WAR GAME ===");
        center = new JLabel("The war game running...");
        west = new JLabel("  p1: 0");
        east = new JLabel("p2: 0  ");
        endText = new JLabel("");
        headline.setHorizontalAlignment(JLabel.CENTER);
        headline.setVerticalAlignment(JLabel.CENTER);
        center.setHorizontalAlignment(JLabel.CENTER);
        center.setVerticalAlignment(JLabel.CENTER);
        endText.setHorizontalAlignment(JLabel.CENTER);
        endText.setVerticalAlignment(JLabel.CENTER);
        p.add(headline, BorderLayout.NORTH);
        p.add(center, BorderLayout.CENTER);
        p.add(west, BorderLayout.WEST);
        p.add(east, BorderLayout.EAST);
        p.add(endText, BorderLayout.SOUTH);
    }

    // implementation of paintComponent
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
}