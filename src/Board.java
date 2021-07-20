/*
    // 0 is an empty place
    // 1 is a move made by player1
    // 2 is a move made by player2
    [
     [0, 1, 0],
     [1, 2, 1],
     [0, 0, 2]
    ]
    When display, convert 1 to 'X' and 2 to 'O'
 */

public class Board {
    int [][] gameBoard;
    public Board() {
        this.gameBoard = new int[3][3];
    }

    public void displayBoard() {
        System.out.println();
        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[0].length; j++) {
                char toPrint = ' ';
                if(gameBoard[i][j] == 1) {
                    toPrint = 'X';
                }
                else if(gameBoard[i][j] == 2) {
                    toPrint = 'O';
                }
                System.out.print("  " + toPrint + "  ");
                if(j < 2) System.out.print("|");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if input is >= 0 and <= 2
    // Check if the user input position is 0 (Not played before)?
    public boolean inputIsValid(int row, int col, boolean playing) {
        if(row < 0 || row > 2 || col < 0 || col > 2) {
            if(playing) System.out.println("Row or Column input is invalid, try again");
            return false;
        }
        else if(gameBoard[row][col] != 0) {
            System.out.println("This position has been taken");
            return false;
        }
        return true;
    }

    public void makePlay(int row, int col, int playCount) {
        gameBoard[row][col] = playCount % 2 == 0 ? 1 : 2;
    }

    // Winner:
    // Check 3 rows, 3 columns and 2 diagonals and see if there are three 1s or 2s
    public boolean haveWinner() {
        if((gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][1] == gameBoard[0][2]) &&
                (gameBoard[0][2] == 1 || gameBoard[0][2] == 2)) {
            return true;
        }
        else if((gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[1][2]) &&
                (gameBoard[1][2] == 1 || gameBoard[1][2] == 2)) {
            return true;
        }
        else if((gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][1] == gameBoard[2][2]) &&
                (gameBoard[2][2] == 1 || gameBoard[2][2] == 2)) {
            return true;
        }
        else if((gameBoard[0][0] == gameBoard[1][0] && gameBoard[1][0] == gameBoard[2][0]) &&
                (gameBoard[2][0] == 1 || gameBoard[2][0] == 2)) {
            return true;
        }
        else if((gameBoard[0][1] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][1]) &&
                (gameBoard[2][1] == 1 || gameBoard[2][1] == 2)) {
            return true;
        }
        else if((gameBoard[0][2] == gameBoard[1][2] && gameBoard[1][2] == gameBoard[2][2]) &&
                (gameBoard[2][2] == 1 || gameBoard[2][2] == 2)) {
            return true;
        }
        else if((gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]) &&
                (gameBoard[2][2] == 1 || gameBoard[2][2] == 2)) {
            return true;
        }
        else if((gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0]) &&
                (gameBoard[2][0] == 1 || gameBoard[2][0] == 2)) {
            return true;
        }

        return false;
    }
}
