package test;

import board.Board;
import direction.Direction;
import game.Game;
import game.Game2048;
import board.SquareBoard;

import static java.util.Arrays.asList;

public class TestClass {
    public static void main(String[] args) {
        Board board = new SquareBoard(3);
        board.fillBoard(asList(2, null, 2,
                2, null, null,
                2, 4, 4));
        Game game = new Game2048(board);

        System.out.println(game.getGameBoard());

        game.move(Direction.RIGHT);
        System.out.println(game.getGameBoard());
    }

}
