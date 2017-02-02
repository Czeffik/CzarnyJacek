import java.util.HashMap;
import java.util.Map;

public class Card {
    String suit;
    String rank;
    int value;
    Map<String, Integer> values = new HashMap<String, Integer>(){{
        put("A",1); put("2",2);put("3",3);put("4",4);put("5",5);put("6",6);put("7",7);
        put("8",8);put("9",9);put("10",10);put("J",10);put("Q",10);put("K",10);
    }};
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = values.get(rank);
    }
    String card(){
        return this.suit+this.rank;
    }
}
