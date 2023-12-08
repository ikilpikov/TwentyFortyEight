package main.game;

import main.board.Board;
import main.board.SquareBoard;
import main.direction.Direction;
import main.key.Key;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class Game2048Test {

    @Test
    public void gameTest() {
        var game = new Game2048();
        Board<Key, String> b2 = new SquareBoard<>(1);
        b2.fillBoard(asList("hello"));
        if (!"hello".equals(b2.getValue(b2.getKey(0, 0)))) throw new RuntimeException("board not work =(");
        if (!b2.hasValue("hello")) throw new RuntimeException("board not work =(");
        Board<String, Double> b3 = new Board<>(1, 1) {
            @Override
            public void fillBoard(List<Double> list) {

            }

            @Override
            public List<String> availableSpace() {
                return null;
            }

            @Override
            public void addItem(String key, Double value) {

            }

            @Override
            public String getKey(int i, int j) {
                return null;
            }

            @Override
            public Double getValue(String key) {
                return null;
            }

            @Override
            public List<String> getColumn(int j) {
                return null;
            }

            @Override
            public List<String> getRow(int i) {
                return null;
            }

            @Override
            public List<Double> getColumnValues(int j) {
                return null;
            }

            @Override
            public List<Double> getRowValues(int i) {
                return null;
            }

            @Override
            public boolean hasValue(Double value) {
                return false;
            }

            @Override
            public List<Double> getValues(List<String> keys) {
                return null;
            }

            @Override
            public void clearBoard() {

            }
        };
        Board<Key, Integer> b = game.getGameBoard();
        if (!b.availableSpace().isEmpty()) throw new RuntimeException("Game board must be empty before initialize");
        b.fillBoard(asList(2, null, null, 8, 2, 2, 8, 8, 2, null, 2, 2, 4, 2, 4, 2048));
        if (!game.hasWin()) throw new RuntimeException("hasWin not work =(");
        game.move(Direction.LEFT);
        if (b.availableSpace().size() != 5) throw new RuntimeException("move must be add item");
        assertEquals(b.getValues(b.getRow(0)).subList(0, 2), asList(2, 8));
    }
}
