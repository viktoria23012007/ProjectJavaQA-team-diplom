package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class GameStoreTest {

    GameStore store = new GameStore();

    @Test // найти, если в списке одна игра
    public void shouldFindGameIfInStoreOneGame() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");

        assertTrue(store.containsGame(game1));
    }

    @Test // найти первую добавленную, если в списке 2 игры
    public void shouldFindFirstGameIfInStoreTwoGames() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game1));
    }

    @Test // найти послденюю добавленную, если в списке 2 игры
    public void shouldFindLastGameIfInStoreTwoGames() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game2));
    }

    @Test // найти добавленную в середине списка
    public void shouldFindGameInTheMiddleStoreGames() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        ru.netology.Game game3 = store.publishGame("Б", "Квест");

        assertTrue(store.containsGame(game2));
    }

    @Test // искать, если список пустой
    public void shouldNotFindIfInEmptyStore() {
        ru.netology.Game game1 = new ru.netology.Game("А", "Стратегия", this.store);

        boolean expected = false;
        boolean actual = store.containsGame(game1);

        assertEquals(expected, actual);
    }

    @Test // покажи каталог, к которому принадлежит добаленная игра
    public void shouldShowCurrentStore() {
        GameStore store1 = new GameStore();
        ru.netology.Game game1 = store1.publishGame("А", "Стратегия");

        GameStore expected = store1;
        GameStore actual = game1.getStore();

        assertEquals(expected, actual);
    }

    @Test  // найти лучшего если игроков не было
    public void shouldNotFindMostPlayerIfNoPlayers() {
        ru.netology.Game game = store.publishGame("А", "Стратегия");

        String expected = null;
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test  // найти лучшего игрока, если был один игрок
    public void shouldFindMostPlayerIfOnePlayer() {
        ru.netology.Game game = store.publishGame("А", "Стратегия");
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        player1.installGame(game);
        player1.play(game, 2);

        String expected = "Петя";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test  // найти лучшего игрока, если несколько игроков играли в разные игры
    public void shouldFindMostPlayerIfPlayerPlayedDifferentGames() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        ru.netology.Player player2 = new ru.netology.Player("Маша");
        ru.netology.Player player3 = new ru.netology.Player("Коля");
        player1.installGame(game1);
        player2.installGame(game2);
        player3.installGame(game1);
        player1.play(game1, 2);
        player2.play(game2, 5);
        player3.play(game1, 1);

        String expected = "Маша";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test  // найти лучшего игрока, если один из игроков играл 0 часов
    public void shouldFindMostPlayerIfOnePlayedZeroHours() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        ru.netology.Player player2 = new ru.netology.Player("Маша");
        player1.installGame(game1);
        player2.installGame(game1);
        player1.play(game1, 2);
        player2.play(game1, 0);

        String expected = "Петя";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test  // найти лучшего игрока, если лучшее время 1 час
    public void shouldFindMostPlayerBestTimeOneHour() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        ru.netology.Player player2 = new ru.netology.Player("Маша");
        player1.installGame(game1);
        player2.installGame(game1);
        player1.play(game1, 1);
        player2.play(game1, 0);

        String expected = "Петя";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test  // найти последнего лучшего игрока, если 2 игрока играли одинаковое кол-во времени
    public void shouldFindMostPlayerIfTwoHaveSameTime() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        ru.netology.Player player2 = new ru.netology.Player("Маша");
        ru.netology.Player player3 = new ru.netology.Player("Вася");
        player1.installGame(game1);
        player2.installGame(game1);
        player3.installGame(game1);
        player1.play(game1, 2);
        player2.play(game1, 2);
        player3.play(game1, 0);

        String expected = "Маша";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test  // найти лучшего, если один из игроков играл в несколько игр каталога
    public void shouldFindMostPlayerIfPlayedSeveralPlayers() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        ru.netology.Game game3 = store.publishGame("Б", "Квест");
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        ru.netology.Player player2 = new ru.netology.Player("Маша");
        ru.netology.Player player3 = new ru.netology.Player("Коля");
        player1.installGame(game1);
        player2.installGame(game1);
        player3.installGame(game1);
        player3.installGame(game2);
        player3.installGame(game3);
        player1.play(game1, 3);
        player2.play(game1, 4);
        player3.play(game1, 2);
        player3.play(game2, 1);
        player3.play(game3, 2);

        String expected = "Коля";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test  // если не было игроков
    public void shouldNotSetPlayedTimeIfPlayersDidNotPlay() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        int expected = 0;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test  // если 1 игра и 1 игрок
    public void shouldSetPlayedTimeIfOnePlayerOneGame() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        player1.installGame(game1);
        player1.play(game1, 3);

        int expected = 3;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test  // если 1 игра и несколько игроков
    public void shouldSetPlayedTimeIfSeveralPlayerOneGame() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        ru.netology.Player player2 = new ru.netology.Player("Маша");
        ru.netology.Player player3 = new ru.netology.Player("Коля");
        player1.installGame(game1);
        player2.installGame(game1);
        player3.installGame(game1);
        player1.play(game1, 3);
        player2.play(game1, 2);
        player3.play(game1, 1);

        int expected = 6;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test  // если несколько игр и несколько игроков
    public void shouldSetPlayedTimeIfSeveralPlayerSeveralGames() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        ru.netology.Game game3 = store.publishGame("Б", "Квест");
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        ru.netology.Player player2 = new ru.netology.Player("Маша");
        ru.netology.Player player3 = new ru.netology.Player("Коля");
        player1.installGame(game3);
        player2.installGame(game2);
        player3.installGame(game1);
        player1.play(game3, 10);
        player2.play(game2, 1);
        player3.play(game1, 1);

        int expected = 12;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test  // если играют по несколько раз в одну и ту же игру
    public void shouldSetPlayedTimeIfPlayerPlayMultipleTimes() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        ru.netology.Player player2 = new ru.netology.Player("Коля");
        player1.installGame(game1);
        player2.installGame(game2);
        player1.play(game1, 10);
        player2.play(game2, 6);
        player1.play(game1, 4);
        player2.play(game2, 1);

        int expected = 21;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test  // найти время игрока, если добавляем 0
    public void shouldSetTimePlayerIfAddZero() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        player1.installGame(game1);
        player1.play(game1, 4);
        store.addPlayTime(player1.getName(), 0);

        Integer expected = 4;
        Integer actual = (store.getPlayedTime()).get(player1.getName());

        assertEquals(expected, actual);
    }

    @Test  // добавить время игрока, если ранее игрок не играл
    public void shouldAddTimePlayerIfWasNotPlayTime() {
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        store.addPlayTime(player1.getName(), 5);

        Integer expected = 5;
        Integer actual = (store.getPlayedTime()).get(player1.getName());

        assertEquals(expected, actual);
    }

    @Test  // добавить время игрока, если ранее играл в игры каталога
    public void shouldAddTimePlayerIfPlayedSeveralGames() {
        ru.netology.Game game1 = store.publishGame("А", "Стратегия");
        ru.netology.Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        player1.installGame(game1);
        player1.installGame(game2);
        player1.play(game1, 4);
        player1.play(game2, 1);
        store.addPlayTime(player1.getName(), 1);

        Integer expected = 6;
        Integer actual = (store.getPlayedTime()).get(player1.getName());

        assertEquals(expected, actual);
    }

    @Test  // указать отрицательное число в параметре времени
    public void shouldNotAddIfTimeNegative() {
        ru.netology.Player player1 = new ru.netology.Player("Петя");
        store.addPlayTime(player1.getName(), -1);

        Integer expected = null;
        Integer actual = (store.getPlayedTime()).get(player1.getName());

        assertEquals(expected, actual);
    }
}