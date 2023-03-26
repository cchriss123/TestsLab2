package com.example;
import bowlinggame.Frame;
import bowlinggame.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BowlingGameTest {

    List<Frame> frames;
    Game game = new Game(frames = new ArrayList<>());


    @Test
    void fullGameOffMissedRolls(){
        rollMany(20,0);
        assertEquals(0, game.score());
    }
    @Test
    void fullGameOffOnes(){
        rollMany(20,1);
        assertEquals(20, game.score());

    }
    @Test
    void oneSpareThenFiveThenRestMiss(){
        game.roll(5);
        game.roll(5);
        game.roll(5);
        rollMany(17, 0);
        assertEquals(15, frames.get(0).getTotalScore());
    }

    @Test
    void fullGameWithTwoSparesThenFiveThenRestMiss() {
        game.roll(5);
        game.roll(5);
        game.roll(5);
        game.roll(5);
        game.roll(5);
        rollMany(15, 0);
        assertEquals(35, game.score());
    }

    @Test
    void framesShouldHaveCorrectSizeAfterStrikeAndOneExtraRoll(){
        game.roll(10);
        game.roll(1);
        assertEquals(2,frames.size());

    }

    @Test
    void oneStrikeThenToFivesThenRestMiss(){
        game.roll(10);
        game.roll(5);
        game.roll(5);
        rollMany(16, 0);
        assertEquals(30, game.score());
    }

    @Test
    void twoStrikesThanFiveThanZeroThanFiveRestZero(){
        game.roll(10);
        game.roll(10);
        game.roll(5);
        game.roll(0);
//        framesToString();
        rollMany(13, 0);
        assertEquals(45, game.score());
    }


    @Test
    void testTenStrikes(){
        rollMany(10,10);
        assertEquals(270, game.score());
    }

    @Test
    void PerfectGame(){
        rollMany(12,10);
        framesToString();

        assertEquals(300, game.score());
    }

    @Test
    void
    oneStrikeShouldMarkFrameAsStrike(){
        game.roll(10);
        rollMany(16, 0);
        assertTrue(frames.get(0).isStrike());
    }

    @Test
    void
    threeStrikesThanSpareRestZero(){
        game.roll(10);
        game.roll(10);
        game.roll(10);
        game.roll(5);
        game.roll(5);
        frames.forEach(System.out::println);

        rollMany(12, 0);
        assertEquals(85, game.score());
    }


    private void rollMany(int amountOfRolls, int pins) {
        for (int i = 0; i < amountOfRolls; i++)
            game.roll(pins);
    }

    @Test
    void framesNineStrikesFiveFiveFiveShouldReturn270(){
        rollMany(9,10);
        game.roll(5);
        game.roll(5);
        game.roll(5);


        assertEquals(270,game.score());
    }

    private void framesToString() {
        frames.forEach(System.out::println);

    }

    @ParameterizedTest
    @MethodSource("getGames")
    void shouldGiveCorrectScore(List<Integer> pins, int expectedResult) {
        for (Integer pin : pins) game.roll(pin);
        assertEquals(expectedResult, game.score());
    }

    static Stream<Arguments>getGames(){
        return Stream.of(
                Arguments.of(List.of(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),0),
                Arguments.of(List.of(0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,10,5,5),25),
                Arguments.of(List.of(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,10,10),30));

    }
}
