package org.vitaly.homework04.memento;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by vitaly on 2017-03-11.
 */
public class Game {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        Deque<Memento> mementos = new LinkedList<>();

        while (!ticTacToe.isOver()) {
            System.out.println(Arrays.deepToString(ticTacToe.getField().getCells()));

            System.out.println("Input back if you want to take a turn back");
            String action = scanner.nextLine();

            if ("back".equals(action.trim().toLowerCase())) {
                if (!mementos.isEmpty()) {
                    ticTacToe.restoreFromMemento(mementos.pollFirst());
                } else {
                    System.out.println("Game is reset");
                }
            } else {
                Memento memento = takeTurn(ticTacToe, scanner);
                mementos.addFirst(memento);
            }
        }

        Player winner = ticTacToe.getCurrentPlayer() == Player.FIRST ? Player.SECOND : Player.FIRST;
        System.out.println(winner + " player won!");
        System.out.println(Arrays.deepToString(ticTacToe.getField().getCells()));
    }

    private static Memento takeTurn(TicTacToe ticTacToe, Scanner scanner) {
        Memento memento = null;
        do {
            System.out.println(ticTacToe.getCurrentPlayer() + " player turn.");
            try {
                System.out.println("Enter row: ");
                int row = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter column: ");
                int column = Integer.parseInt(scanner.nextLine());
                memento = ticTacToe.turn(row, column);
            } catch (Exception e) {
                System.out.println("Wrong input. Try again.");
            }
        } while (memento == null);
        return memento;
    }
}
