package main.game;

import java.util.ArrayList;
import java.util.List;

public class GameHelper {
    public List<Integer> moveAndMergeEqual(List<Integer> list) {
        List<Integer> result = getNoNullsList(list);
        mergeEquals(result);

        while (result.size() != list.size()) {
            result.add(null);
        }

        return result;
    }

    private List<Integer> getNoNullsList(List<Integer> list) {
        List<Integer> result = new ArrayList<>();

        for (var i : list) {
            if (i != null) {
                result.add(i);
            }
        }

        return result;
    }

    private void mergeEquals(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) == list.get(i + 1)) {
                list.set(i, list.get(i) * 2);
                list.remove(i + 1);
            }
        }
    }
}
