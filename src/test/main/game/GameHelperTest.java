package main.game;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class GameHelperTest {
    GameHelper gameHelper;

    @Before
    public void setUp() {
        gameHelper = new GameHelper();
    }

    @Test
    public void moveAndMergeEqual() {
        assertEquals(gameHelper.moveAndMergeEqual(asList(1, 2, null, 3)), asList(1, 2, 3, null));
        assertEquals(gameHelper.moveAndMergeEqual(asList(2, 2, null, 3)), asList(4, 3, null, null));
        assertEquals(gameHelper.moveAndMergeEqual(asList(2, 2, 4, 4)), asList(4, 8, null, null));
        assertEquals(gameHelper.moveAndMergeEqual(asList(2, 2, 2, 3)), asList(4, 2, 3, null));
        assertEquals(gameHelper.moveAndMergeEqual(asList(2, null, null, 2)), asList(4, null, null, null));
        assertEquals(gameHelper.moveAndMergeEqual(asList(null, null, null, null)), asList(null, null, null, null));
        assertEquals(gameHelper.moveAndMergeEqual(asList(null, null, null, 2)), asList(2, null, null, null));
        assertEquals(gameHelper.moveAndMergeEqual(asList(null, null, 2, 2)), asList(4, null, null, null));
    }
}