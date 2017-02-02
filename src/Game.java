import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    int numberOfPlayers;
    public ArrayList<Player> listOfPlayer=new ArrayList<>();
    public ArrayList<Card> talia=new ArrayList<Card>();
    Croupier croupier = new Croupier();

    private Scanner input = new Scanner(System.in);

    public Game(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
    }

    void deck(){
        String[] suits = {"C", "D", "H", "S"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        for(String suit : suits){
            for(String rank : ranks) {
                Card variable = new Card(suit, rank);
                talia.add(variable);
            }}
        Collections.shuffle(talia);
    }
    private void addPlayers(){
        int k = 0;
        while(k<numberOfPlayers){
            System.out.println("Jak się nazywasz?");
            listOfPlayer.add(k,new Player(input.nextLine()));
            k++;
        }
    }

    private void getBetPlayers(){
        for (Player player: listOfPlayer){
            player.getBet();
        }
    }

    private void givePlayersCards(){
        for(Player player:listOfPlayer){
            while (player.playerCards.size()<2){
                player.givePlayerCard(talia.remove(0));
            }
        }
    }

    private void printPlayersCards(){
        for (Player player: listOfPlayer){
            player.cardValue();
            System.out.println(player.name + ", portfel: "+player.wallet+" stawka: "+player.bet+" wartość kart: "+player.cardsValue+". "+player.printPlayerCard());
        }
    }

    void auction(Player player){
        System.out.println("Wprowadz s - żeby spasować, h - żeby dobrać kartę lub d - żeby podwoić zakład...");
        player.move=input.next().charAt(0);
        if (player.move.equals('s')){
            player.cardValue();
        }
        else if(player.move.equals('h')){
            player.hit(talia.remove(0));
            player.cardValue();
            if (player.cardsValue>21){
                player.bet=0;
                player.move='s';
                System.out.println(player.name+" PRZEGRYWASZ! Zostało Ci: " + player.wallet);
            }
        }
        else if(player.move.equals('d')){
            if (player.wallet>=player.bet){
                player.doubleBet();
                player.cardValue();
            }
            else{
                System.out.println("Nie masz hajsu żeby podwoić!");
                this.auction(player);
            }
        }
        else{
            System.out.println(player.name+" wydaj polecenie z listy!");
            this.auction(player);
        }
    }


    private void auctionForAll(){
        for(Player player: listOfPlayer){
            while(! player.move.equals('s')){
                this.printPlayersCards();
                System.out.println("Licytuje: "+player.name);
                this.auction(player);
            }
        }
    }

    String winning(Player player){

        if(player.cardsValue==21 && player.playerCards.size()==2){
            if (croupier.croupierCardsValue==21 && croupier.croupierCards.size()==2){
                player.wallet+=player.bet;
                player.bet=0;
                return player.name + " REMISUJE! Twoój portfel: "+player.wallet;
            }
            else{
                player.wallet+= ((player.bet*3)/2);
                player.bet = 0;
                return player.name + " WYGRYWA! BLACKJACK! Twoój portfel: "+player.wallet;
            }
        }
        else if(player.cardsValue==croupier.croupierCardsValue){
            player.wallet+=player.bet;
            player.bet=0;
            return player.name + " REMISUJE! Twoój portfel: "+player.wallet;
        }
        else if(player.cardsValue>croupier.croupierCardsValue){
            player.wallet+=(player.bet*2);
            player.bet=0;
            return player.name + " WYGRYWA! Twoój portfel: "+player.wallet;
        }
        else if(player.cardsValue<22 && croupier.croupierCardsValue>21){
            player.wallet+=(player.bet*2);
            player.bet=0;
            return player.name + " WYGRYWA! Twoój portfel: "+player.wallet;
        }
        //if(player.cardsValue<croupierCardsValue)
        else {
            player.bet=0;
            return player.name + " PRZEGRYWA! Twoój portfel: "+player.wallet;
        }
    }


    private void winningPlayers(){
        for (Player player: listOfPlayer){
            System.out.println(this.winning(player));
        }
    }
    void croupierCardDrawing(){
        while(croupier.croupierCardsValue<17){
            croupier.croupierDrawing(talia.remove(0));
            croupier.croupierCardsValue=0;
            croupier.croupierValue();
        }
    }

    private void clearing(){
        croupier.croupierCardsValue=0;
        croupier.croupierCards.clear();
        talia.clear();
        for (Player player: listOfPlayer){
            player.playerCards.clear();
            player.move=' ';
        }
    }
    private void playing(){
        this.clearing();
        this.getBetPlayers();
        this.deck();
        this.givePlayersCards();
        croupier.croupierDrawing(talia.remove(0));
        System.out.println("Karta KRUPIERA: " + croupier.croupierCards.get(0).card());
        this.auctionForAll();
        this.croupierCardDrawing();
        croupier.croupierPrinting();
        this.winningPlayers();
        this.playing();
    }

    void start(){
        this.addPlayers();
        this.playing();
    }

}
