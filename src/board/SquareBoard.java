package board;

import key.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class SquareBoard<V> extends Board<Key, V> {
    public SquareBoard (int size) {
        super(size, size);
    }

    @Override
    public void fillBoard(List<V> list) {
        var iterator = list.listIterator();

        for (var i = 0; i < getWidth(); i++) {
            for (var j = 0; j < getHeight(); j++) {
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
        var nullValueKeys = new ArrayList<Key>();

        for (var i : board.entrySet()) {
            if (i.getValue() == null) {
                nullValueKeys.add(i.getKey());
            }
        }

        return nullValueKeys;
    }

    @Override
    public void addItem(Key key, V value) {
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
    public V getValue(Key key) {
        return board.get(key);
    }

    @Override
    public List<Key> getColumn(int j) {
        var column = new ArrayList<Key>();

        for (var entry : board.entrySet()) {
            if (entry.getKey().getJ() == j) {
                column.add(entry.getKey());
            }
        }

        return column;
    }

    @Override
    public List<Key> getRow(int i) {
        var row = new ArrayList<Key>();

        for (var entry : board.entrySet()) {
            if (entry.getKey().getI() == i) {
                row.add(entry.getKey());
            }
        }

        return row;
    }

    @Override
    public List<V> getColumnValues(int j) {
        return getValues(getColumn(j));
    }

    @Override
    public List<V> getRowValues(int i) {
        return getValues(getRow(i));
    }

    @Override
    public boolean hasValue(V value) {
        return board.containsValue(value);
    }

    @Override
    public void clearBoard() {
        for(var i : board.entrySet()){
            i.setValue(null);
        }
    }

    @Override
    public List<V> getValues(List<Key> keys) {
        var values = new ArrayList<V>();

        for (var key : keys) {
            values.add(board.get(key));
        }

        return values;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();

        for (var i = 0; i < getWidth(); i++) {
            for (var j = 0; j < getHeight(); j++) {
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
