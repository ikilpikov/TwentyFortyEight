package main.board;

import main.key.Key;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class SquareBoardTest {

    Board<Key, Integer> board;

    @Before
    public void setUp() {
        board = new SquareBoard(2);
        board.fillBoard(asList(1, 2, 3, null));
    }

    @Test
    public void fillBoard() {

    }

    @Test
    public void availableSpace() {
        var expectedAvailableSpace = List.of(new Key(1, 1));
        var actualAvailableSpace = board.availableSpace();

        assertEquals(expectedAvailableSpace, actualAvailableSpace);
    }

    @Test
    public void addItem() {
        board.addItem(new Key(1, 1), 4);
        var actualItem = board.getValue(new Key(1, 1));
        Integer expectedItem = 4;

        assertEquals(expectedItem, actualItem);
    }

    @Test
    public void getKey() {
        var expectedKey = new Key(0, 0);
        var actualKey = board.getKey(0, 0);

        assertEquals(expectedKey, actualKey);
    }

    @Test
    public void getValue() {
        Integer expectedKey = 1;
        var actualKey = board.getValue(new Key(0, 0));

        assertEquals(expectedKey, actualKey);
    }

    @Test
    public void getColumn() {
        var expectedColumn = List.of(new Key(0, 0), new Key(1, 0));
        var actualColumn = board.getColumn(0);

        assertEquals(expectedColumn, actualColumn);
    }

    @Test
    public void getRow() {
        var expectedRow = List.of(new Key(0, 0), new Key(0, 1));
        var actualRow = board.getRow(0);

        assertEquals(expectedRow, actualRow);
    }

    @Test
    public void getColumnValues() {
        var expectedColumn = List.of(1, 3);
        var actualColumn = board.getColumnValues(0);

        assertEquals(expectedColumn, actualColumn);
    }

    @Test
    public void getRowValues() {
        var expectedRow = List.of(1, 2);
        var actualRow = board.getRowValues(0);

        assertEquals(expectedRow, actualRow);
    }

    @Test
    public void hasValue() {
        assertTrue(board.hasValue(1));
    }

    @Test
    public void clearBoard() {
        board.clearBoard();
        var expectedAvailableSpace = List.of(new Key(0, 0),
                new Key(0, 1),
                new Key(1, 0),
                new Key(1, 1));

        var actualAvailableSpace = board.availableSpace();
        assertEquals(expectedAvailableSpace, actualAvailableSpace);
    }

    @Test
    public void getValues() {
        var actualValues = board
                .getValues(List.of(new Key(0, 0), new Key(0, 1)));
        var expectedValues = List.of(1, 2);

        assertEquals(expectedValues, actualValues);

    }
}