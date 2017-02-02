import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    String name;
    int wallet;

    ArrayList<Card> playerCards=new ArrayList<>();
    int cardsValue=0;
    Character move;
    int bet=0;

    Scanner input = new Scanner(System.in);

    Player(String name){
        this.name = name;
        System.out.println("Z jakim hajsem siadasz przy stole?");
        this.wallet= input.nextInt();
    }

    void givePlayerCard(Card card){
        playerCards.add(card);
    }

    void cardValue(){
        this.cardsValue=0;
        for(Card card:this.playerCards){
            this.cardsValue+=card.value;
        }
        if (this.cardsValue<=11){
            for(Card card:this.playerCards){
                if(card.rank.equals("A")){
                    this.cardsValue+=10;
                }
            }
        }
    }

    void getBet(){
        System.out.println("Za ile wchodzisz "+this.name+ "?");
        int bet = input.nextInt();
        if(bet>this.wallet){
            System.out.println("Nie masz tyle hajsu w portfleu!");
            this.getBet();
        }
        else{
            this.wallet-=bet;
            this.bet +=bet;
        }
    }

    void hit(Card card){
        this.playerCards.add(card);
    }

    void doubleBet(){
        this.wallet -= this.bet;
        this.bet*=2;
    }


    ArrayList<String> printPlayerCard(){
        ArrayList<String>lista=new ArrayList<>();
        for (Card cards: this.playerCards){
            lista.add(cards.card());
        }
        return lista;
    }


//    void auction(){
//        System.out.println("Wprowadz s - żeby spasować, h - żeby dobrać kartę lub d - żeby podwoić zakład...");
//        this.move=input.next().charAt(0);
//        if (this.move.equals('s')){
//            this.cardValue();
//        }
//        else if(this.move.equals('h')){
//            this.hit();
//            this.cardValue();
//            if (this.cardsValue>21){
//                this.bet=0;
//                this.move='s';
//                System.out.println(this.name+" PRZEGRYWASZ! Zostało Ci: " + this.wallet);
//            }
//        }
//        else if(this.move.equals('d')){
//            if (this.wallet>=this.bet){
//                this.doubleBet();
//                this.cardValue();
//            }
//            else{
//                System.out.println("Nie masz hajsu żeby podwoić!");
//                this.auction();
//            }
//        }
//        else{
//            System.out.println(this.name+" wydaj polecenie z listy!");
//            this.auction();
//        }
//    }


//    String winning(){
//
//        if(this.cardsValue==21 && this.playerCards.size()==2){
//            if (croupierCardsValue==21 && croupierCards.size()==2){
//                this.wallet+=this.bet;
//                this.bet=0;
//                return this.name + " REMISUJE! Twoój portfel: "+this.wallet;
//            }
//            else{
//                this.wallet+= ((this.bet*3)/2);
//                this.bet = 0;
//                return this.name + " WYGRYWA! BLACKJACK! Twoój portfel: "+this.wallet;
//            }
//        }
//        else if(this.cardsValue==croupierCardsValue){
//            this.wallet+=this.bet;
//            this.bet=0;
//            return this.name + " REMISUJE! Twoój portfel: "+this.wallet;
//        }
//        else if(this.cardsValue>croupierCardsValue){
//            this.wallet+=(this.bet*2);
//            this.bet=0;
//            return this.name + " WYGRYWA! Twoój portfel: "+this.wallet;
//        }
//        else if(this.cardsValue<22 && croupierCardsValue>21){
//            this.wallet+=(this.bet*2);
//            this.bet=0;
//            return this.name + " WYGRYWA! Twoój portfel: "+this.wallet;
//        }
//        //if(this.cardsValue<croupierCardsValue)
//        else {
//            this.bet=0;
//            return this.name + " PRZEGRYWA! Twoój portfel: "+this.wallet;
//        }
//    }


}
