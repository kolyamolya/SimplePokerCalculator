package com.poker_calculator;

import com.poker_calculator.Models.Game;
import com.poker_calculator.Models.Player;
import com.poker_calculator.Models.Stack;
import com.poker_calculator.Options.Default;
import com.poker_calculator.Services.GameCalculator;
import com.poker_calculator.Services.NeutralPlayer;
import com.poker_calculator.Services.WinnerPlayer;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Game game = new Game(Default.DEFAULT_CHIP);

        Player kolya = new Player("Коля", new Stack(Default.DEFAULT_CHIP, 600));
        Player sergey = new Player("Сергей", new Stack(Default.DEFAULT_CHIP, 300));
        Player izmailov = new Player("Измайлов", new Stack(Default.DEFAULT_CHIP, 300));
        Player roman = new Player("Роман", new Stack(Default.DEFAULT_CHIP, 300));

        game.addPlayer(kolya);
        game.addPlayer(sergey);
        game.addPlayer(izmailov);
        game.addPlayer(roman);
        
        game.gameInfo();

        GameCalculator gameCalculator = new GameCalculator(game);
        gameCalculator.computed();

        for (WinnerPlayer winnerPlayer : gameCalculator.winners) {
            winnerPlayer.Print();
        }

        for (NeutralPlayer neutralPlayer : gameCalculator.neutral) {
            neutralPlayer.Print();
        }
    }
}
