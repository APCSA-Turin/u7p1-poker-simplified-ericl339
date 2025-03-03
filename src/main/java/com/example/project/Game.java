package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        if (Utility.getHandRanking(p1Hand) > Utility.getHandRanking(p2Hand)) {
            return "Player 1 wins!";
        }
        else if (Utility.getHandRanking(p1Hand) < Utility.getHandRanking(p2Hand)) {
            return "Player 2 wins!";
        }
        else if (Utility.getHandRanking(p1Hand) == Utility.getHandRanking(p2Hand)) {
            int player1High;
            int player2High;
            if (Utility.getRankValue(p1.getHand().get(1).getRank()) >= Utility.getRankValue(p1.getHand().get(0).getRank())) {
                player1High = Utility.getRankValue(p1.getHand().get(1).getRank());
            }
            else {
                player1High = Utility.getRankValue(p1.getHand().get(0).getRank());
            }
            if (Utility.getRankValue(p2.getHand().get(1).getRank()) >= Utility.getRankValue(p2.getHand().get(0).getRank())) {
                player2High = Utility.getRankValue(p2.getHand().get(1).getRank());
            }
            else {
                player2High = Utility.getRankValue(p2.getHand().get(0).getRank());
            }
            if (player1High > player2High) {
                return "Player 1 wins!";
            }
            else if (player1High < player2High) {
                return "Player 2 wins!";
            }
        }

        return "Tie!";
    }
    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();
        
        player1.addCard(new Card("7", "♠"));
        player1.addCard(new Card("7", "♣"));
        
        player2.addCard(new Card("8", "♠"));
        player2.addCard(new Card("3", "♣"));
        
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("A", "♦"));
        communityCards.add(new Card("A", "♥"));
        communityCards.add(new Card("A", "♠"));
    
        String p1Result = player1.playHand(communityCards);
        String p2Result = player2.playHand(communityCards);

        System.out.println(p1Result);
        System.out.println(p2Result);
        String winner = Game.determineWinner(player1, player2, p1Result, p2Result, communityCards);
        System.out.println(winner);
    }
    public static void play(){ //simulate card playing
    }
        
        

}