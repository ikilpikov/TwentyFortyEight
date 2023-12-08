package test;

import board.Board;
import direction.Direction;
import game.Game;
import game.Game2048;
import board.SquareBoard;
import key.Key;

import static java.util.Arrays.asList;

public class TestClass {
    public static void main(String[] args) {
        Board<Key, Integer> board = new SquareBoard<>(4);
        board.fillBoard(asList(2, null, null,8, 2,2,8,8, 2,null,2,2, 4,2,4,2048));
        Game game = new Game2048(board);

        System.out.println(game.getGameBoard());

        game.move(Direction.LEFT);
        System.out.println(game.getGameBoard());
    }

}
