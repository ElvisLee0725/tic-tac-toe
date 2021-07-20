import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        System.out.println("Please input player 1's name");
        String name1 = in.next();
        System.out.println(String.format("Hello %s!", name1));

        System.out.println("Please input player 2's name");
        String name2 = in.next();
        System.out.println(String.format("Hello %s!", name2));

        Game myGame = new Game(name1, name2);
    }
}
