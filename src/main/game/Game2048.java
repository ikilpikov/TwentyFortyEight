package main.game;

import main.board.Board;
import main.board.SquareBoard;
import main.direction.Direction;
import main.key.Key;

import java.util.*;

public class Game2048 implements Game {
    private GameHelper helper;
    Random random;
    public static final int GAME_SIZE = 4;
    private Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);


    public Game2048(Board<Key, Integer> board) {
        this.board = board;
        helper = new GameHelper();
        random = new Random();
    }

    public Game2048() {
        helper = new GameHelper();
        random = new Random();
    }

    @Override
    public void init() {
        board.clearBoard();
        addItem();
        addItem();
    }

    @Override
    public boolean canMove() {
        if (board.availableSpace().isEmpty()) {
            return false;
        }

        return haveEqualNeighbours();
    }

    @Override
    public boolean move(Direction direction) {
        if (!canMove()) {
            return false;
        }

        switch (direction) {
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
            case UP -> moveUp();
            case DOWN -> moveDown();
            default -> {
                return false;
            }
        }
        addItem();

        return true;
    }

    /**
     * В случайное свободное место на доске добавляется плитка
     * со значением 4 (с вероятностью 10%) или
     * соначением 3 (с вероятностью 90%).
     */
    @Override
    public void addItem() {
        var emptyFields = board.availableSpace();
        var randomEmptyField = emptyFields
                .get(random.nextInt(emptyFields.size() - 1));

        var randomTile = random.nextInt(10) + 1;

        if (randomTile <= 9) {
            board.addItem(randomEmptyField, 2);
        } else {
            board.addItem(randomEmptyField, 2);
        }


    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }

    private void moveLeft() {
        var updatedBoardValues = new ArrayList<Integer>();

        for (var i = 0; i < board.getHeight(); i++) {
            List<Integer> mergedRows = helper
                    .moveAndMergeEqual(board.getRowValues(i));
            updatedBoardValues.addAll(mergedRows);
        }

        board.fillBoard(updatedBoardValues);
    }

    private void moveRight() {
        var updatedBoardValues = new ArrayList<Integer>();

        for (var i = 0; i < board.getHeight(); i++) {
            List<Integer> mergedRows = helper
                    .moveAndMergeEqual(board.getRowValues(i));
            mergedRows = reverseNullsOnly(mergedRows);

            updatedBoardValues.addAll(mergedRows);
        }

        board.fillBoard(updatedBoardValues);
    }

    private void moveDown() {
        var updatedBoardValues = new ArrayList<Integer>();

        for (var i = 0; i < board.getWidth(); i++) {
            List<Integer> mergedColumns = helper
                    .moveAndMergeEqual(board.getColumnValues(i));
            mergedColumns = reverseNullsOnly(mergedColumns);

            updatedBoardValues.addAll(mergedColumns);
        }

        var transposedUpdatedValues =
                transportBoardValues(updatedBoardValues);

        board.fillBoard(transposedUpdatedValues);
    }

    private void moveUp() {
        var updatedBoardValues = new ArrayList<Integer>();

        for (var i = 0; i < board.getWidth(); i++) {
            List<Integer> mergedColumns = helper
                    .moveAndMergeEqual(board.getColumnValues(i));

            updatedBoardValues.addAll(mergedColumns);
        }

        var transposedUpdatedValues =
                transportBoardValues(updatedBoardValues);

        board.fillBoard(transposedUpdatedValues);
    }

    private List<Integer> transportBoardValues(List<Integer> list) {
        var result = new ArrayList<Integer>();

        for (var i = 0; i < board.getHeight(); i++) {
            for (var j = 0; j < board.getWidth(); j++) {
                result.add(list
                        .get(j * board.getHeight() + i));
            }
        }

        return result;
    }

    private List<Integer> reverseNullsOnly(List<Integer> list) {
        var reversedList = new ArrayList<Integer>(list);

        Collections.sort(reversedList, (x, y) -> {
            if (x == null && y != null) {
                return -1;
            } else if (x != null && y == null) {
                return 1;
            } else {
                return 0;
            }
        });

        return reversedList;
    }

    private boolean haveEqualNeighbours() {
        for (var i = 1; i < board.getHeight(); i++) {
            for (var j = 1; j < board.getWidth(); j++) {
                if (board.getValue(new Key(i, j)) == board.getValue(new Key(i, j - 1)) ||
                        board.getValue(new Key(i, j)) == board.getValue(new Key(i - 1, j))) {
                    return true;
                }
            }
        }

        return false;
    }

}
