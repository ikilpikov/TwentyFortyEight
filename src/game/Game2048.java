package game;

import board.Board;
import board.SquareBoard;
import direction.Direction;
import key.Key;

import java.util.*;

import static java.util.Arrays.asList;

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
        for(var i = 0; i < board.getHeight() * board.getWidth(); i++){
            addItem();
        }
    }

    @Override
    public boolean canMove() {
        return !board.availableSpace().isEmpty();
    }

    @Override
    public boolean move(Direction direction) {
        if(!canMove()){
            return false;
        }

        switch (direction) {
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }

        return true;
    }

    @Override
    public void addItem() {
        var emptyFields = board.availableSpace();
        Key randomEmptyField = emptyFields
                .get(random.nextInt(emptyFields.size() - 1));

        board.addItem(randomEmptyField, 2);
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

}
