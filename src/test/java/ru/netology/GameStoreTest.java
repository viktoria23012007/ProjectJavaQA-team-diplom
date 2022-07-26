package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameStoreTest {

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология Баттл Онлайн 2", "Аркады 2");
        boolean containsGame = store.containsGame(game);

        assertTrue(store.containsGame(game1));
    }

    @Test
    public void shouldPlayTime() {

        GameStore store = new GameStore();
        Player player = new Player("Irina");

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология Баттл Онлайн2", "Аркады2");
        store.addPlayTime("Irina", 1);
        store.addPlayTime("Sasha", 1);
        store.addPlayTime("Masha", 1);

        String expected = store.getMostPlayer();
        String actual = "Irina";
        assertEquals(expected, actual);
<<<<<<< HEAD

    }

    @Test
    public void shouldSumTimeIra() {
=======
    }

    @Test //  выдать сообщение при создании игры, что данная игра уже есть в каталоге
    public void shouldMessageIfGameAlreadyExists() {
        Game game1 = store.publishGame("А", "Стратегия");

        assertThrows(AlreadyExistsException.class, () -> {
            store.publishGame("А", "Стратегия");
        });
    }

    @Test // покажи каталог, к которому принадлежит добаленная игра
    public void shouldShowCurrentStore() {
>>>>>>> a1740ba16704e688bada7cba5741d8e0a7a5408d
        GameStore store1 = new GameStore();

        Game game3 = store1.publishGame("NFS", "Races");
        Game game4 = store1.publishGame("FarCry 6", "Shooter");
        store1.addPlayTime("Ira", 3);
        store1.addPlayTime("Masha", 1);
        store1.addPlayTime("Dasha", 5);
        store1.addPlayTime("Ira", 4);
        store1.getMostPlayer();

        String actual = store1.getMostPlayer();
        String expected = "Ira";

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumTimePlayers() {
        GameStore store1 = new GameStore();

        Game game3 = store1.publishGame("NFS", "Races");
        Game game4 = store1.publishGame("Far Cry 6", "Shooter");
        store1.addPlayTime("Roma", 4);
        store1.addPlayTime("Anton", 2);
        store1.addPlayTime("Misha", 6);
        store1.getSumPlayedTime();

        int actual = store1.getSumPlayedTime();
        int expected = 12;

        assertEquals(expected, actual);
    }

    @Test
    public void addDoubleGames() {
        GameStore store = new GameStore();

        Game game1 = store.publishGame("NFS", "Races");
        assertThrows(RuntimeException.class, () -> {
            store.publishGame("NFS", "Races");
        });
    }

<<<<<<< HEAD
    @Test
    public void addNegativePlayTime() {
        GameStore store = new GameStore();
=======
    @Test  // добавить время игрока, если ранее играл в игры каталога
    public void shouldAddTimePlayerIfPlayedSeveralGames() {
        Game game1 = store.publishGame("А", "Стратегия");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Player player1 = new Player("Петя");
        player1.installGame(game1);
        player1.installGame(game2);
        player1.play(game1, 4);
        player1.play(game2, 1);
        store.addPlayTime(player1.getName(), 1);
>>>>>>> a1740ba16704e688bada7cba5741d8e0a7a5408d

        Game game1 = store.publishGame("NFS", "Races");
        Game game2 = store.publishGame("Far Cry", "Shooter");
        Game game3 = store.publishGame("Hitman", "Shooter");

        store.addPlayTime("Petya", 4);
        store.addPlayTime("Anton", 2);
        store.addPlayTime("Kirill", 6);
        store.getMostPlayer();

        assertThrows(RuntimeException.class, () -> {
            store.addPlayTime("Petya", -5);
        });

    }

<<<<<<< HEAD
    // другие ваши тесты
=======
    @Test  // указать отрицательное число в параметре времени
    public void shouldNotAddIfTimeNegative() {
        Player player1 = new Player("Петя");
        store.addPlayTime(player1.getName(), -1);

        Integer expected = 0;
        Integer actual = (store.getPlayedTime()).get(player1.getName());

        assertEquals(expected, actual);
    }
>>>>>>> a1740ba16704e688bada7cba5741d8e0a7a5408d
}