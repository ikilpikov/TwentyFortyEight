package game;

import board.Board;
import direction.Direction;
import key.Key;
import java.util.*;

import static java.util.Arrays.asList;

public class Game2048 implements Game {
    private GameHelper helper;
    private Board board;
    private Random random;

    public Game2048(Board board) {
        this.board = board;
        this.helper = new GameHelper();
        this.random = new Random();
    }

    @Override
    public void init() {
        board.fillBoard(asList(2, null, 2,
                2, null, null,
                2, 4, 4));
    }

    @Override
    public boolean canMove() {
        return !this.board.availableSpace().isEmpty();
    }

    @Override
    public void move(Direction direction) {
        switch (direction) {
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
    }

    @Override
    public void addItem() {
        List<Key> emptyFields = this.board.availableSpace();
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
        return this.board.hasValue(2048);
    }

    private void moveLeft() {
        List<Integer> updatedBoardValues = new ArrayList<>();

        for (int i = 0; i < this.board.getHeight(); i++) {
            List<Integer> mergedRows = this.helper
                    .moveAndMergeEqual(this.board.getRowValues(i));
            updatedBoardValues.addAll(mergedRows);
        }

        this.board.fillBoard(updatedBoardValues);
    }

    private void moveRight() {
        List<Integer> updatedBoardValues = new ArrayList<>();

        for (int i = 0; i < this.board.getHeight(); i++) {
            List<Integer> mergedRows = this.helper
                    .moveAndMergeEqual(this.board.getRowValues(i));
            mergedRows = this.reverseNullsOnly(mergedRows);

            updatedBoardValues.addAll(mergedRows);
        }

        this.board.fillBoard(updatedBoardValues);
    }

    private void moveDown() {
        List<Integer> updatedBoardValues = new ArrayList<>();

        for (int i = 0; i < this.board.getWidth(); i++) {
            List<Integer> mergedColumns = this.helper
                    .moveAndMergeEqual(this.board.getColumnValues(i));
            mergedColumns = this.reverseNullsOnly(mergedColumns);

            updatedBoardValues.addAll(mergedColumns);
        }

        List<Integer> transposedUpdatedValues =
                this.transportBoardValues(updatedBoardValues);

        this.board.fillBoard(transposedUpdatedValues);
    }

    private void moveUp() {
        List<Integer> updatedBoardValues = new ArrayList<>();

        for (int i = 0; i < this.board.getWidth(); i++) {
            List<Integer> mergedColumns = this.helper
                    .moveAndMergeEqual(this.board.getColumnValues(i));

            updatedBoardValues.addAll(mergedColumns);
        }

        List<Integer> transposedUpdatedValues =
                this.transportBoardValues(updatedBoardValues);

        this.board.fillBoard(transposedUpdatedValues);
    }

    private List<Integer> transportBoardValues(List<Integer> list) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < this.board.getHeight(); i++) {
            for (int j = 0; j < this.board.getWidth(); j++) {
                result.add(list
                        .get(j * this.board.getHeight() + i));
            }
        }

        return result;
    }

    private List<Integer> reverseNullsOnly(List<Integer> list) {
        List<Integer> reversedList = new ArrayList<>(list);

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
