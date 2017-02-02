import java.util.ArrayList;

public class Croupier {
    ArrayList<Card> croupierCards=new ArrayList<>();
    int croupierCardsValue=0;

    void croupierDrawing(Card card){
        croupierCards.add(card);
    }

    void croupierValue(){
        for(Card card: croupierCards){
            croupierCardsValue+=card.value;
        }
      }

    void croupierPrinting(){
        ArrayList<String> lista = new ArrayList<>();
        for (Card card: croupierCards){
            lista.add(card.card());
        }
        System.out.println("Karty krupiera: "+ lista);
    }
}
