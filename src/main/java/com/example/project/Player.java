package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){      
        allCards = new ArrayList<>(hand);
        for (Card card1 : communityCards) {
            allCards.add(card1);
        }
        sortAllCards();
        String outcome = "Nothing";

        ArrayList<Integer> rankF = findRankingFrequency();
        ArrayList<Integer> suitF = findSuitFrequency();

        if (highCard()) {
            outcome = "High Card";
        }
        if (onePair()) {
            outcome = "A Pair";
        }
        if (twoPair()) {
            outcome = "Two Pair";
        }
        if (threeofAKind()) {
            outcome = "Three of a Kind";
        }
        return outcome;
    }

    private boolean highCard() {
        int highestValue = Integer.MIN_VALUE;
        for (int i = 0; i < hand.size(); i ++) {
            if (Utility.getRankValue(hand.get(i).getRank()) > highestValue) {
                highestValue = Utility.getRankValue(hand.get(i).getRank());
            }
        }
        if (Utility.getRankValue(allCards.get(allCards.size() - 1).getRank()) == highestValue) {
            return true;
        }
        return false;
    }

    private boolean onePair() {
        ArrayList<Integer> rankF = findRankingFrequency();
        for (int i = 0; i < rankF.size(); i ++) {
            if (rankF.get(i) > 1) {
                return true;
            }
        }
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
