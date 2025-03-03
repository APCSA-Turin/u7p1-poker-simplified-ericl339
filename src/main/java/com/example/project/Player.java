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
        }]
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

    // determines if hand has one pair
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

    private boolean twoPair() {
        ArrayList<Integer> rankF = findRankingFrequency();
        int amountRepeat = 0;
        for (int i = 0; i < rankF.size(); i ++) {
            if (rankF.get(i) > 1) {
                amountRepeat ++;
            }
        }
        if (amountRepeat >= 2) {
            return true;
        }
        return false;
    }

    private boolean threeofAKind() {
        ArrayList<Integer> rankF = findRankingFrequency();
        for (int i = 0; i < rankF.size(); i ++) {
            if (rankF.get(i) > 2) {
                return true;
            }
        }
        return false;
    }

    private boolean straight() {
        ArrayList<Integer> rankF = findRankingFrequency();
        int count = 0;
        for (int i = 0; i < rankF.size(); i ++) {
            if (rankF.get(i) == 1) {
                count ++;
            }
            else {
                count = 0;
            }
            if (count == 5) {
                return true;
            }
        }
        return false;
    }

    private boolean flush() {
        ArrayList<Integer> rankS = findSuitFrequency();
        for (int i = 0; i < rankS.size(); i ++) {
            if (rankS.get(i) == 5) {
                return true;
            }
        }
        return false;
    }

    private boolean fullHouse() {
        if (allCards.get(0).getRank().equals(allCards.get(1).getRank()) && allCards.get(0).getRank().equals(allCards.get(2).getRank())) {
            if (allCards.get(3).getRank().equals(allCards.get(4).getRank())) {
                return true;
            }
        }
        if (allCards.get(0).getRank().equals(allCards.get(1).getRank())) {
            if (allCards.get(2).getRank().equals(allCards.get(3).getRank()) && allCards.get(2).getRank().equals(allCards.get(4).getRank())) {
                return true;
            }
        }
        return false;
    }

    private boolean fourOfAKind() {
        ArrayList<Integer> rankF = findRankingFrequency();
        for (int i = 0; i < rankF.size(); i ++) {
            if (rankF.get(i) > 3) {
                return true;
            }
        }
        return false;
    }

    private boolean straightFlush(){
        ArrayList<Integer> rankF = findRankingFrequency();
        ArrayList<Integer> suitF = findSuitFrequency();
        if (flush()) {
            if (straight()) {
                return true;
            }
        }
        return false;
    }

    private boolean royalFlush(){
        ArrayList<Integer> rankF = findRankingFrequency();
        if (flush()) {
            int count = 0;
            for (int i = 8; i < rankF.size(); i ++) {
                if (rankF.get(i) == 1) {
                    count ++;
                }
                else {
                    count = 0;
                }
                if (count == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    public void sortAllCards(){
        int minIndex = 0;
        Card temp;
        for (int i = 0; i < allCards.size(); i ++) {
            minIndex = i;
            for (int j = i; j < allCards.size(); j ++) {
                if (Utility.getRankValue(allCards.get(j).getRank()) < Utility.getRankValue(allCards.get(minIndex).getRank())) {
                    minIndex = j;
                }
            }
            temp = allCards.get(i);
            allCards.set(i, allCards.get(minIndex));
            allCards.set(minIndex, temp);
        }
    } 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> rankFrequency = new ArrayList<Integer>();
        for (int i = 0; i < ranks.length; i ++) {
            int count = 0;
            for (int j = 0; j < allCards.size(); j ++) {
                if (Utility.getRankValue(allCards.get(j).getRank()) == Utility.getRankValue(ranks[i])) {
                    count ++;
                }
            }
            rankFrequency.add(count);
        }
        return rankFrequency; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> suitFrequency = new ArrayList<Integer>();
        for (int i = 0; i < suits.length; i ++) {
            int count = 0;
            for (int j = 0; j < allCards.size(); j ++) {
                if (allCards.get(j).getSuit().equals(suits[i])) {
                    count ++;
                }
            }
            suitFrequency.add(count);
        }
        return suitFrequency; 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
