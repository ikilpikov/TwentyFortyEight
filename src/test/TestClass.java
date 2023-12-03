package test;

import board.Board;
import game.Game;
import game.Game2048;
import board.SquareBoard;
import game.GameHelper;

import static java.util.Arrays.asList;

public class TestClass {
    public static void main(String[] args) {
        Board board = new SquareBoard(3);

        GameHelper gameHelper = new GameHelper();
        System.out.println(gameHelper.moveAndMergeEqual(asList(4, null, null, 4, 5, 6,  2, 2)));

        Game game2048 = new Game2048(board);

    }

}
