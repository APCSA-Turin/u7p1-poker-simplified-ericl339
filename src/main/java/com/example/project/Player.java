package com.example.project;
import java.util.ArrayList;


public class Player{
    // creates the instance variables
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    // constructs the player
    public Player(){
        hand = new ArrayList<>();
    }

    // returns the cards in hand or all cards
    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    // add cards
    public void addCard(Card c){
        hand.add(c);
    }

    // determines what the player has in all cards
    public String playHand(ArrayList<Card> communityCards){      
        // sets allCards to the cards in hand
        allCards = new ArrayList<>(hand);
        // adds the community cards to all cards
        for (Card card1 : communityCards) {
            allCards.add(card1);
        }
        // sorts the cards in order
        sortAllCards();
        // sets up the outcome
        String outcome = "Nothing";

        // if this is true, set outcome to high card
        if (highCard()) {
            outcome = "High Card";
        }
        // if this is true, set outcome to one pair
        if (onePair()) {
            outcome = "A Pair";
        }
        // if this is true, set outcome to two pair
        if (twoPair()) {
            outcome = "Two Pair";
        }
        // if this is true, set outcome to three of a kind
        if (threeofAKind()) {
            outcome = "Three of a Kind";
        }
        // if this is true, set outcome to straight
        if (straight()) {
            outcome = "Straight";
        }
        // if this is true, set outcome to flush
        if (flush()) {
            outcome = "Flush";
        }
        // if this is true, set outcome to full house
        if (fullHouse()) {
            outcome = "Full House";
        }
        // if this is true, set outcome to four of a kind
        if (fourOfAKind()) {
            outcome = "Four of a Kind";
        }
        // if this is true, set outcome to straight flush
        if (straightFlush()) {
            outcome = "Straight Flush";
        }
        // if this is true, set outcome to royal flush
        if (royalFlush()) {
            outcome = "Royal Flush";
        }
        // returns the outcome
        return outcome;
    }

    // determines if the hand has a high card
    private boolean highCard() {
        // sets the highest value to the lowest integer
        int highestValue = Integer.MIN_VALUE;
        // iterates through the hand to find the highest in hand
        for (int i = 0; i < hand.size(); i ++) {
            // if the hand has a higher rank than the highest currently, sets it to the highest
            if (Utility.getRankValue(hand.get(i).getRank()) > highestValue) {
                highestValue = Utility.getRankValue(hand.get(i).getRank());
            }
        }
        // finds the highest in all cards and if it is the same as the highest in hand, return true
        if (Utility.getRankValue(allCards.get(allCards.size() - 1).getRank()) == highestValue) {
            return true;
        }
        // else return false
        return false;
    }

    // determines if all cards have one pair
    private boolean onePair() {
        // creates a frequency list for rank
        ArrayList<Integer> rankF = findRankingFrequency();
        // iterates through the frequency list
        for (int i = 0; i < rankF.size(); i ++) {
            // if there are more than one of the same rank then return true
            if (rankF.get(i) > 1) {
                return true;
            }
        }
        // else return false
        return false;
    }

    // determines if all cards have two pairs
    private boolean twoPair() {
        // creates a frequency list for rank
        ArrayList<Integer> rankF = findRankingFrequency();
        // sets the amount repeating not zero to 0
        int amountRepeat = 0;
        // iterates through the frequency list
        for (int i = 0; i < rankF.size(); i ++) {
            // if the index has two cards of the same, add one to amount repeating
            if (rankF.get(i) > 1) {
                amountRepeat ++;
            }
        }
        // if there is at least two pairs, then return true. Else return false
        if (amountRepeat >= 2) {
            return true;
        }
        return false;
    }

    // determines if all cards have three of a kind
    private boolean threeofAKind() {
        // creates a rank frequency list
        ArrayList<Integer> rankF = findRankingFrequency();
        // iterates through the rank frequency
        for (int i = 0; i < rankF.size(); i ++) {
            // if there is 3 at the index, return true
            if (rankF.get(i) > 2) {
                return true;
            }
        }
        // return false
        return false;
    }

    // determines if all cards have a straight
    private boolean straight() {
        // creates a rank frequency list
        ArrayList<Integer> rankF = findRankingFrequency();
        // initiates count to 0
        int count = 0;
        // iterates through rank frequency
        for (int i = 0; i < rankF.size(); i ++) {
            // if there is a card at the index, add one to count
            if (rankF.get(i) == 1) {
                count ++;
            }
            // else resets the counter
            else {
                count = 0;
            }
            // if count is 5, then there is a straight, return true
            if (count == 5) {
                return true;
            }
        }
        // returns false
        return false;
    }

    // determines if all cards have a flush
    private boolean flush() {
        // creates a list of suit frequency
        ArrayList<Integer> rankS = findSuitFrequency();
        // iterates through the suit frequency
        for (int i = 0; i < rankS.size(); i ++) {
            // determines if the int at the index is 5 and if it is, then return true because there can only be a max of 5 suits
            if (rankS.get(i) == 5) {
                return true;
            }
        }
        // return false
        return false;
    }

    // determines if all cards have a full house
    private boolean fullHouse() {
        // this works because all cards is sorted
        // outer if statement passes if the first three are equal in rank
        if (allCards.get(0).getRank().equals(allCards.get(1).getRank()) && allCards.get(0).getRank().equals(allCards.get(2).getRank())) {
            // sees the last two card and determines if they are the same
            if (allCards.get(3).getRank().equals(allCards.get(4).getRank())) {
                return true;
            }
        }
        // same concept but outer if now compares the first two and inner if compares the last three
        if (allCards.get(0).getRank().equals(allCards.get(1).getRank())) {
            if (allCards.get(2).getRank().equals(allCards.get(3).getRank()) && allCards.get(2).getRank().equals(allCards.get(4).getRank())) {
                return true;
            }
        }
        // returns false
        return false;
    }

    // determines if all cards have a four of a kind
    private boolean fourOfAKind() {
        // creates a frequency list for rank
        ArrayList<Integer> rankF = findRankingFrequency();
        // iterates through the rank frequency
        for (int i = 0; i < rankF.size(); i ++) {
            // if the int at the index is at least 4, return true
            if (rankF.get(i) > 3) {
                return true;
            }
        }
        // return false
        return false;
    }

    // determines if all cards have a straight flush
    private boolean straightFlush(){
        // calls flush to determine if all cards have a flush, and if so, goes to inner if
        if (flush()) {
            // if all cards also has a straight, return true
            if (straight()) {
                return true;
            }
        }
        // return false
        return false;
    }

    // determines if all cards have a royal flush
    private boolean royalFlush(){
        // creates a frequecy rank list
        ArrayList<Integer> rankF = findRankingFrequency();
        // calls flush to determine if all cards have a flush and if so, goes to inner if
        if (flush()) {
            // initiates count to 0
            int count = 0;
            // iterates through rank frequency starting at the card 10
            for (int i = 8; i < rankF.size(); i ++) {
                // determines if there is a straight starting from 10
                // adds count if there is a card
                if (rankF.get(i) == 1) {
                    count ++;
                }
                // else resets
                else {
                    count = 0;
                }
                // if count is 5 meaning there is a straight, return true
                if (count == 5) {
                    return true;
                }
            }
        }
        // return false
        return false;
    }
    // sorts card from low to high
    public void sortAllCards(){
        // uses selection sort
        // initiates min index and temp
        int minIndex = 0;
        Card temp;
        // iterates through all cards
        for (int i = 0; i < allCards.size(); i ++) {
            // sets min index to the index it's on
            minIndex = i;
            // nested for loop
            for (int j = i; j < allCards.size(); j ++) {
                // if the index j is less than min index, sets j to min index
                if (Utility.getRankValue(allCards.get(j).getRank()) < Utility.getRankValue(allCards.get(minIndex).getRank())) {
                    minIndex = j;
                }
            }
            // swaps i and min index
            temp = allCards.get(i);
            allCards.set(i, allCards.get(minIndex));
            allCards.set(minIndex, temp);
        }
    } 

    // finds the frequency list
    public ArrayList<Integer> findRankingFrequency(){
        // creates the return array list
        ArrayList<Integer> rankFrequency = new ArrayList<Integer>();
        // iterates through the ranks list
        for (int i = 0; i < ranks.length; i ++) {
            // initiates count to 0
            int count = 0;
            // nested for loop through all cards
            for (int j = 0; j < allCards.size(); j ++) {
                // if the rank in all cards matches the rank is ranks list, count adds one
                if (Utility.getRankValue(allCards.get(j).getRank()) == Utility.getRankValue(ranks[i])) {
                    count ++;
                }
            }
            // adds count to the return list
            rankFrequency.add(count);
        }
        //returns the arraylist
        return rankFrequency; 
    }

    //finds the frequency for suits
    public ArrayList<Integer> findSuitFrequency(){
        // creates a return list
        ArrayList<Integer> suitFrequency = new ArrayList<Integer>();
        // iterates through the suits list
        for (int i = 0; i < suits.length; i ++) {
            // inititates count to 0
            int count = 0;
            // nested for loop that goes through all cards
            for (int j = 0; j < allCards.size(); j ++) {
                // if the suit at j matches the suit at i, count adds 1
                if (allCards.get(j).getSuit().equals(suits[i])) {
                    count ++;
                }
            }
            // adds the count to frequency
            suitFrequency.add(count);
        }
        // returns the frequency
        return suitFrequency; 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
