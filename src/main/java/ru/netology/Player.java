package ru.netology;

import java.util.HashMap;
import java.util.Map;

public class Player {
    //    public String installGame;
    private String name;

    /**
     * информация о том, в какую игру сколько часов было сыграно
     * ключ - игра
     * значение - суммарное количество часов игры в эту игру
     */
    private Map<Game, Integer> playedTime = new HashMap<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * добавление игры игроку
     * если игра уже была, никаких изменений происходить не должно
     *
     * @return
     */
    public Game installGame(Game game) {
        playedTime.putIfAbsent(game, 0);
        return game;
    }

    /**
     * игрок играет в игру game на протяжении hours часов
     * об этом нужно сообщить объекту-каталогу игр, откуда была установлена игра
     * также надо обновить значения в мапе игрока, добавив проигранное количество часов
     * возвращает суммарное количество часов, проигранное в эту игру.
     * если игра не была установлена, то надо выкидывать RuntimeException
     */
    public int play(Game game, int hours) {
        if (!playedTime.containsKey(game)) {
            throw new GameNotInstalled(
                    "Данная игра не установлена"
            );
        }
        game.getStore().addPlayTime(name, hours);
        int timeGenre = playedTime.getOrDefault(game, hours);
        if (hours < 0) {
            throw new RuntimeException("Quantity hours " + "can't be negative");
        } else if (playedTime.containsKey(game)) {
            playedTime.put(game, timeGenre + hours);
        } else {
            throw new RuntimeException("Element with game: " + game.getTitle() + " not found");
        }
        return playedTime.get(game);
    }

    /**
     * Метод принимает жанр игры (одно из полей объекта игры) и
     * суммирует время, проигранное во все игры этого жанра этим игроком
     */
    public int sumGenre(String genre) {
        int sum = 0;
        for (Game game : playedTime.keySet()) {
            if (game.getGenre().equals(genre)) {
                sum += playedTime.get(game);
            } else {
                sum = sum;
            }
        }
        return sum;
    }

    /**
     * Метод принимает жанр и возвращает игру этого жанра, в которую играли больше всего
     * Если в игры этого жанра не играли, возвращается null
     */
    public String mostPlayerByGenre(String genre) {
        int mostGenre = 0;
        String bestGenre = null;
        for (Game game : playedTime.keySet()) {
            int genreTime = playedTime.get(game);
            if (game.getGenre().equals(genre) && genreTime > mostGenre) {
                mostGenre = genreTime;
                bestGenre = game.getTitle();
            }
        }
        return bestGenre;
    }
}