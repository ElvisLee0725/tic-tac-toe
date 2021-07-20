// Start a new game
// Display an empty board
// Announce player1 to play X
// Check player1's move is valid (within the board and not repeating the same move)
// Mark the play to the board
// Display player1's move
// Check if player wins or tie? If so, display winner or tie and game over

// Announce player2 to play O
// Check player2's move is valid (within the board and not repeating the same move)
// Mark the play to the board
// Display player2's move
// Check if player wins or tie? If so, display winner or tie and game over

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
    Player valid input:
    row: 0 - 2
    col: 0 - 2

    Display:
      0     1     2
  0   X  |  X  |  O
  1      |  O  |
  2   X  |  X  |  O

 */
public class Game {
    String player1;
    String player2;
    int playCount;
    Board board;
    boolean gamePlaying;
    Scanner in;

    public Game(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
        startGame();
        this.in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        while(gamePlaying) {
            board.displayBoard();
            if(playCount % 2 == 0) {
                System.out.println(player1 + " to play 'X'");
            }
            else {
                System.out.println(player2 + " to play 'O'");
            }

            int row = -1;
            int col = -1;
            boolean playing = false;
            while(!board.inputIsValid(row, col ,playing)) {
                System.out.println("Please input row number (0 - 2)");
                while(!in.hasNextInt()) {
                    System.out.println("That's not a number");
                    in.next();
                }
                row = in.nextInt();

                System.out.println("Please input column number (0 - 2)");
                while(!in.hasNextInt()) {
                    System.out.println("That's not a number");
                    in.next();
                }
                col = in.nextInt();
                playing = true;
            }

            board.makePlay(row, col, playCount);

            if(board.haveWinner() || playCount == 8) {
                if(!board.haveWinner()) {
                    System.out.println("We have a tie!");
                }
                else {
                    String winner = playCount % 2 == 0 ? player1 : player2;
                    System.out.println(winner + " is the winner! Congratulations!");
                }
                board.displayBoard();
                stopGame();
            }

            playCount++;
        }
    }

    public void startGame() {
        gamePlaying = true;
        playCount = 0;
    }

    public void stopGame() {
        gamePlaying = false;
    }
}
