package main.board;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Board<K, V>  {
    private final int width;
    private final int height;
    protected Map<K, V> board = new LinkedHashMap<>();

    public Board(int weigh, int height) {
        this.width = weigh;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void fillBoard(List<V> list);

    public abstract List<K> availableSpace();

    public abstract void addItem(K key, V value);

    public abstract K getKey(int i, int j);

    public abstract V getValue(K key);

    public abstract List<K> getColumn(int j);

    public abstract List<K> getRow(int i);

    public abstract List<V> getColumnValues(int j);

    public abstract List<V> getRowValues(int i);

    public abstract boolean hasValue(V value);

    public abstract List<V> getValues(List<K> keys);

    public abstract void clearBoard();

}
