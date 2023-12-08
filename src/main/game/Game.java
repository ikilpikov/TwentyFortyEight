package main.game;

import main.board.Board;
import main.direction.Direction;

public interface Game {
    void init();

    boolean canMove();

    boolean move(Direction direction);

    void addItem();

    Board getGameBoard();

    boolean hasWin();

}
