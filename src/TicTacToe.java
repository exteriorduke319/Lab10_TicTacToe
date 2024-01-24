import java.util.Scanner;

public class TicTacToe {
    public static Scanner lionstothebowl = new Scanner(System.in);
    public static String[][] grid = new String[3][3];
    public static int moveChecker = 0;
    public static boolean gameRun = true;
    public static boolean validCheck = false;
    public static boolean winCheck = false;

    public static void main(String[] args) {
        clearBoard();

        do {
            while(!validCheck) {
                askMove("X ");
            }

            moveChecker++;
            validCheck = false;

            if(isWin("X ")) {
                System.out.println("Player 1 wins!");
            }


            if (!isWin("X ") && moveChecker < 9) {
                while(!validCheck) {
                    askMove("O ");
                }

                moveChecker++;
                validCheck = false;

                if(isWin("O ")) {
                    System.out.println("Player 2 wins!");
                }
            }


            if (isWin("X ") || isWin("O ")) {
                gameReset();
                if (!gameRun) {
                    System.out.println("Thanks for playing!");
                    break;
                }
            }

            if (isTie()) {
                gameReset();
                if (!gameRun) {
                    System.out.println("Thanks for playing!");
                    break;
                }
            }


        } while (gameRun);


    }

    private static void clearBoard() {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c] = "- ";
            }
        }
    }

    private static void displayBoard() {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col) {
        if (grid[row][col].equals("X ") || grid[row][col].equals("O ")) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isWin(String player) {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player)) {
            winCheck = true;
            return true;
        } else {
            return false;
        }
    }

    private static boolean isColWin(String player) {
        for (int i = 0; i <= 2; i++) {
            if (grid[0][i].equals(player) && grid[1][i].equals(player) && grid[2][i].equals(player)) {

                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i <= 2; i++) {
            if (grid[i][0].equals(player) && grid[i][1].equals(player) && grid[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        if (grid[0][0].equals(player) && grid[1][1].equals(player) && grid[2][2].equals(player) || grid[0][2].equals(player) && grid[1][1].equals(player) && grid[2][0].equals(player)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isTie() {
        if (!winCheck && moveChecker == 9) {
            System.out.println("It's a tie!");
            System.out.println();
            return true;
        } else {
            return false;
        }
    }

    private static void askMove(String player) {
        int moveRow;
        int moveCol;

        if (player.equals("X ")) {
            moveRow = InputHelper.getRangedInt(lionstothebowl, "Player 1, enter your move row [1 - 3]: ", 1, 3);
            moveCol = InputHelper.getRangedInt(lionstothebowl, "Player 1, enter your move column [1 - 3]: ", 1, 3);
        } else {
            moveRow = InputHelper.getRangedInt(lionstothebowl, "Player 2, enter your move row [1 - 3]: ", 1, 3);
            moveCol = InputHelper.getRangedInt(lionstothebowl, "Player 2, enter your move column [1 - 3]: ", 1, 3);
        }

        if (isValidMove(moveRow - 1, moveCol - 1)) {
            grid[moveRow - 1][moveCol - 1] = player;
            System.out.println();
            displayBoard();
            System.out.println();
            validCheck = true;
        } else {
            System.out.println("Invalid move! Try again.");
            System.out.println();
        }
    }

    private static void gameReset() {
        boolean yNCheck = InputHelper.getYNConfirm(lionstothebowl, "Would you like to play again? [Y or N]");
        if (yNCheck) {
            clearBoard();
            moveChecker = 0;
        } else {
            gameRun = false;
        }
    }
}