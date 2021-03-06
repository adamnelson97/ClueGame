package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * <h1>Player</h1>
 * This class stores all the information relevant to the players in the game.
 * @author Adam Nelson, Youjun Lee
 * @version 1.0
 * @since 2017-11-02
 *
 */
public class Player {

	//Variables
	private String playerName;
	private int row;
	private int column;
	private BoardCell location;
	private Color color;
	protected ArrayList<Card> hand; //All the cards dealt to the player
	protected ArrayList<Card> seen;
	private boolean human; //Indicates if the player is the human user.
	private final static int RADIUS = BoardCell.getWidth();
	protected Solution suggestion; //Stores the user's guess.

	//Constructors

	/**
	 * Default constructor.
	 */
	public Player() {
		this.hand = new ArrayList<Card>();
		this.seen = new ArrayList<Card>();

	}
	/**
	 * Parameterized Constructor that creates a new player from a data file, and
	 * initializes an empty hand.
	 * @param playerName The name of the player.
	 * @param color The player's identifying color.
	 * @param row The row of the player's starting location.
	 * @param column The column of the player's starting location.
	 */
	public Player(String playerName, Color color, int row, int column) {
		this.playerName = playerName;
		this.color = color;
		this.row = row;
		this.column = column;
		this.hand = new ArrayList<Card>();
		this.seen = new ArrayList<Card>();
	}

	//Methods

	/**
	 * Reveals a suggested card to the player making the suggestion.
	 * @param suggestion The proposed suggestion, e.g. PERSON did it with the WEAPON in the ROOM.
	 * @return Card A suggested card that the player contains in their hand.
	 */
	public Card disproveSuggestion(Solution suggestion) {
		if (hand.size() == 0) return null; //Returns null for an empty hand.

		//Checks hand for any matches in a random order.
		Random rand = new Random();
		for (int i = 0; i < 100; i++) {
			int n = rand.nextInt(hand.size());
			Card c = hand.get(n);
			if (c.getCardName().equals(suggestion.person)) return c;
			if (c.getCardName().equals(suggestion.weapon)) return c;
			if (c.getCardName().equals(suggestion.room)) return c;
		}
		//If no matches are found, return null.
		return null;
	}

	/**
	 * Adds a card to the player's hand.
	 * @param c The card to be added.
	 */
	public void addCard(Card c) {
		hand.add(c);
		seen.add(c);
		//System.out.println("Adding card " + c.getCardName() + " to hand for " + getPlayerName()); //Debugging statement
	}

	/**
	 * Adds a card to a set of seen cards so the player does not
	 * include it in future suggestions.
	 * @param c The card revealed to the player.
	 */
	public void addSeen(Card c) {
		seen.add(c);
	}


	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(column * BoardCell.getWidth(), row * BoardCell.getHeight(), RADIUS, RADIUS);
		g.setColor(Color.BLACK);
		g.drawOval(column * BoardCell.getWidth(), row * BoardCell.getHeight(), RADIUS, RADIUS);
	}


	//Getters for Testing

	public Color getColor() {
		return color;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> deck) {
		hand = deck;
	}

	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", row=" + row + ", column=" + column + ", color=" + color + "]";
	}
	
	public boolean isHuman() {
		return human;
	}
	
	public void setHuman(boolean human) {
		this.human = human;
	}
	
	public void setLoc(BoardCell newLoc) {
		this.column = newLoc.getColumn();
		this.row =newLoc.getRow();
		this.location = newLoc;
	}
	
	public BoardCell getLocation() {
		return location;
	}
	public Solution getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(Solution suggestion) {
		this.suggestion = suggestion;
	}
	public ArrayList<Card> getSeen() {
		return seen;
	}
}
