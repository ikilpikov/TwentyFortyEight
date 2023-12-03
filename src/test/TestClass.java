package test;

import board.Board;
import game.Game;
import game.Game2048;
import board.SquareBoard;
import java.util.ArrayList;
import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        List<Integer> cells = new ArrayList<>();
        cells.add(1);
        cells.add(2);
        cells.add(3);
        cells.add(4);
        cells.add(5);
        cells.add(6);
        cells.add(7);
        cells.add(8);

        Board board = new SquareBoard(3);
        board.fillBoard(cells);
        System.out.println(board.availableSpace().size());
        Game game2048 = new Game2048(board);





    }

}
