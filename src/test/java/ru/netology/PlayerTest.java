package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() { //#2
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Игра 2", "Гонки");
        Game game3 = store.publishGame("Игра 3", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }


    @Test
    public void TestSumGenreTwoGames() { //#10
        GameStore store = new GameStore();
        Game game = store.publishGame("PUBG", "BattleRoyale");
        Game game1 = store.publishGame("Fortnite", "BattleRoyale");
        Game game3 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game3);
        player.play(game, 3);
        player.play(game1, 2);
        player.play(game3, 1);
        player.play(game1, 2);

        int expected = 7;
        int actual = player.sumGenre("BattleRoyale");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowRunTimeException() { //#6
        GameStore store = new GameStore();
        Game game = store.publishGame("PUBG", "BattleRoyale");
        Game game2 = store.publishGame("Fortnite", "BattleRoyale");
        Game game3 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        Player player2 = new Player("Anton");
        player.installGame(game);
        player.installGame(game2);
        player2.installGame(game);
        player2.installGame(game2);
        player.play(game, 1);
        player.play(game, 3);
        player2.play(game, 2);
        player2.play(game2, 4);


        assertThrows(RuntimeException.class, () -> {
            player.play(game3, 1);

        });
    }

    @Test
    public void shouldMostPlayerByGenre() { //#7
        GameStore store = new GameStore();
        Game game = store.publishGame("PUBG", "BattleRoyale");
        Game game1 = store.publishGame("Fortnite", "BattleRoyale");
        Game game3 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game3);
        player.play(game, 3);
        player.play(game1, 2);
        player.play(game3, 1);

        String expected = "PUBG";
        String actual = player.mostPlayerByGenre("BattleRoyale");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreNoInstallGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("PUBG", "BattleRoyale");
        Game game1 = store.publishGame("Fortnite", "BattleRoyale");
        Game game3 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.play(game, 3);
        player.play(game1, 2);


        String expected = null;
        String actual = player.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void addPlayGameNegativeValue() { //#8
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);


        assertThrows(Exception.class, () -> {
            player.play(game, -1);
        });
    }

    @Test
    public void addDoubleGame() { //#9
        GameStore store = new GameStore();
        Game game = store.publishGame("PUBG", "BattleRoyale");
        Game game1 = store.publishGame("Fortnite", "BattleRoyale");
        Game game3 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 1);
        player.installGame(game);
        player.installGame(game3);
        player.installGame(game3);

        int expected = 0;
        int actual = player.sumGenre(game3.getGenre());
        assertEquals(expected, actual);
    }

}