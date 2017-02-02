import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Jak dużo graczy gra?");
        Game BJ = new Game(input.nextInt());
        BJ.start();

    }
}
