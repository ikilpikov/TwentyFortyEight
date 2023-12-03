package board;

import key.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.stream.*;

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
                    this.addItem(new Key(i, j), iterator.next());
                } else {
                    this.addItem(new Key(i, j), null);
                }
            }
        }
    }

    @Override
    public List<Key> availableSpace() {
        List<Key> nullKeys = new ArrayList<>();

        for (var i : board.entrySet()) {
            if (i.getValue() == null) {
                nullKeys.add(i.getKey());
            }
        }

        return nullKeys;
    }

    @Override
    public void addItem(Key key, Integer value) {
        board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        for (var key : this.board.entrySet()) {
            if (key.getKey().getI() == i && key.getKey().getJ() == j) {
                return key.getKey();
            }
        }

        return null;
    }

    @Override
    public Integer getValue(Key key) {
        return this.board.get(key);
    }

    @Override
    public List<Key> getColumn(int j) {
        List<Key> column = new ArrayList<>();

        for(var entry : this.board.entrySet()){
            if(entry.getKey().getJ() == j){
                column.add(entry.getKey());
            }
        }

        return column;
    }

    @Override
    public List<Key> getRow(int i) {
        List<Key> row = new ArrayList<>();

        for(var entry : this.board.entrySet()){
            if(entry.getKey().getI() == i){
                row.add(entry.getKey());
            }
        }

        return row;
    }

    @Override
    public boolean hasValue(Integer value) {
        return this.board.containsValue(value);
    }

    @Override
    public List<Integer> getValues(List<Key> keys) {
        List<Integer> values = new ArrayList<>();

        for(var key : keys){
            values.add(this.board.get(key));
        }

        return values;
    }
}
