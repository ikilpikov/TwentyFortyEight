package board;

import key.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class SquareBoard extends Board {
    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    public void fillBoard(List<Integer> list) {
        ListIterator<Integer> iterator = list.listIterator();

        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (iterator.hasNext()) {
                    addItem(new Key(i, j), iterator.next());
                } else {
                    return;
                }
            }
        }
    }

    @Override
    public List<Key> availableSpace() {
        List<Key> nullValueKeys = new ArrayList<>();

        for (var i : board.entrySet()) {
            if (i.getValue() == null) {
                nullValueKeys.add(i.getKey());
            }
        }

        return nullValueKeys;
    }

    @Override
    public void addItem(Key key, Integer value) {
        board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        for (var key : board.entrySet()) {
            if (key.getKey().getI() == i && key.getKey().getJ() == j) {
                return key.getKey();
            }
        }

        return null;
    }

    @Override
    public Integer getValue(Key key) {
        return board.get(key);
    }

    @Override
    public List<Key> getColumn(int j) {
        List<Key> column = new ArrayList<>();

        for (var entry : board.entrySet()) {
            if (entry.getKey().getJ() == j) {
                column.add(entry.getKey());
            }
        }

        return column;
    }

    @Override
    public List<Key> getRow(int i) {
        List<Key> row = new ArrayList<>();

        for (var entry : board.entrySet()) {
            if (entry.getKey().getI() == i) {
                row.add(entry.getKey());
            }
        }

        return row;
    }

    @Override
    public List<Integer> getColumnValues(int j) {
        return getValues(getColumn(j));
    }

    @Override
    public List<Integer> getRowValues(int i) {
        return getValues(getRow(i));
    }

    @Override
    public boolean hasValue(Integer value) {
        return board.containsValue(value);
    }

    @Override
    public List<Integer> getValues(List<Key> keys) {
        List<Integer> values = new ArrayList<>();

        for (var key : keys) {
            values.add(board.get(key));
        }

        return values;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (getValue(getKey(i, j)) == null) {
                    sb.append("* ");
                } else {
                    sb.append(getValue(getKey(i, j)) + " ");
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
