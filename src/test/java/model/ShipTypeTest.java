package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShipTypeTest {

    @Test
    public void shouldResultSize4ForBattleship(){
        // given
        ShipType shipType = ShipType.BATTLESHIP;
        // when
        Long shipSize = shipType.size();
        // then
        assertEquals(shipSize, (Long)4L);
    }
    @Test
    public void shouldResultSize3ForCruiser(){
        // given
        ShipType shipType = ShipType.CRUISER;
        // when
        Long shipSize = shipType.size();
        // then
        assertEquals(shipSize, (Long)3L);
    }
    @Test
    public void shouldResultSize2ForDestroyer(){
        // given
        ShipType shipType = ShipType.DESTROYER;
        // when
        Long shipSize = shipType.size();
        // then
        assertEquals(shipSize, (Long)2L);
    }
    @Test
    public void shouldResultSize1ForSubmarine(){
        // given
        ShipType shipType = ShipType.SUBMARINE;
        // when
        Long shipSize = shipType.size();
        // then
        assertEquals(shipSize, (Long)1L);
    }
}