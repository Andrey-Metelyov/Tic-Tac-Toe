package tictactoe;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static char[][] grid = new char[3][3];

    public static void main(String[] args) {
//        System.out.println("Enter cells:");
//        String cells = scanner.nextLine();
//
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = '_';
            }
        }

        showGrid(grid);

        char current = 'X';
        String state = "";

        while (true) {
            getMove(current, grid);
            if (current == 'X') {
                current = 'O';
            } else {
                current = 'X';
            }
            showGrid(grid);
            state = getState(grid);
            if (state.equals("X wins") || state.equals("O wins") || state.equals("Draw")) {
                break;
            }
        }
        System.out.println(state);
    }

    private static void getMove(char value, char[][] grid) {
        boolean goodMove = false;
        int x = 0;
        int y = 0;
        while (!goodMove) {
            System.out.print("Enter the coordinates: ");
            String[] line = scanner.nextLine().split(" ");
            if (line.length != 2 || line[0].length() > 1 || line[1].length() > 1) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (Character.isDigit(line[0].charAt(0)) && Character.isDigit(line[1].charAt(0))) {
                x = Integer.parseInt(line[0]);
                y = Integer.parseInt(line[1]);
                if (x > 0 && x < 4 && y > 0 && y < 4) {
                    if (grid[grid.length - y][x - 1] != '_') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        grid[grid.length - y][x - 1] = value;
                        goodMove = true;
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            }
        }
    }

    private static String getState(char[][] grid) {
        int x = count('X', grid);
        int o = count('O', grid);
        int empty = count('_', grid);

        boolean xInARow = hasInARow('X', grid);
        boolean oInARow = hasInARow('O', grid);

        if (Math.abs(x - o) > 1 || (xInARow && oInARow)) {
            return "Impossible";
        }
        if (xInARow ) {
            return "X wins";
        }
        if (oInARow) {
            return "O wins";
        }
        if (empty > 0) {
            return "Game not finished";
        }
        return "Draw";
    }

    private static boolean hasInARow(char value, char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == value) {
                if (checkInARow(grid, value, i)) {
                    return true;
                }
            }
        }
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] == value) {
                if (checkInAColumn(grid, value, i)) {
                    return true;
                }
            }
        }
        return checkDiagonals(grid, value);
    }

    private static boolean checkDiagonals(char[][] grid, char value) {
        boolean result = true;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][i] != value) {
                result = false;
            }
        }
        if (!result) {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][grid.length - i - 1] != value) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkInAColumn(char[][] grid, char value, int column) {
        for (int i = 1; i < grid.length; i++) {
            if (grid[i][column] != value) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkInARow(char[][] grid, char value, int row) {
        for (int i = 1; i < grid[row].length; i++) {
            if (grid[row][i] != value) {
                return false;
            }
        }
        return true;
    }


    private static int count(char value, char[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == value) {
                    result++;
                }
            }
        }
        return result;
    }

    private static void showGrid(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] == '_' ? ' ' : grid[i][j]);
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
