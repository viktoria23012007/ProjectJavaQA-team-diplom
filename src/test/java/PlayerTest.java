package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }


    @Test
    public void shouldExpIfGameNotInstall() {

        GameStore store = new GameStore();
        Game game3 = store.publishGame("Симс", "Бродилка");

        Player player = new Player("Petya");

        assertThrows(RuntimeException.class, () -> {
            player.play(game3, 1);
        });
    }

    @Test
    public void shouldMostPlayerByGenreGamePlayed() {

        GameStore store = new GameStore();
        Game game1 = store.publishGame("Ёлки", "Аркада");
        Game game2 = store.publishGame("Палки", "Аркада");
        Game game3 = store.publishGame("Пышки", "Бродилка");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 50);
        player.play(game2, 12);
        player.play(game3, 1);


        Game expected = game1;
        Game actual = player.mostPlayerByGenre("Аркада");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreIfGameNotPlayed() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Танки", "Стрелялка");
        Game game2 = store.publishGame("Симс", "Бродилка");
        Game game3 = store.publishGame("Пышки", "Ещё");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game2, 12);
        player.play(game3, 8);


        Game expected = null;
        Game actual = player.mostPlayerByGenre("Стрелялка");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfGameReplay() {

        GameStore store = new GameStore();

        Game game1 = store.publishGame("Танки", "Стрелялка");
        Game game2 = store.publishGame("Симс", "Бродилка");
        Game game3 = store.publishGame("Симс2", "Бродилка");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 5);
        player.play(game1, 6);


        int expected = 11;
        int actual = player.sumGenre("Стрелялка");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGames() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Танки", "Стрелялка");
        Game game2 = store.publishGame("Симс", "Бродилка");
        Game game3 = store.publishGame("Симс2", "Бродилка");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 5);
        player.play(game2, 3);
        player.play(game3, 20);

        int expected = 23;
        int actual = player.sumGenre("Бродилка");

        assertEquals(expected, actual);
    }
}