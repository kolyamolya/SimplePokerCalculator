package com.poker_calculator.Services;

import java.util.ArrayList;
import com.poker_calculator.Models.Game;
import com.poker_calculator.Models.Player;
import com.poker_calculator.Models.StackStatus;

public class GameCalculator 
{
    public Game game;

    public ArrayList<WinnerPlayer> winners;
    public ArrayList<LoserPlayer> losers;
    public ArrayList<NeutralPlayer> neutral;

    public GameCalculator(Game game)
    {
        this.game = game;
        this.winners = new ArrayList<WinnerPlayer>();
        this.losers = new ArrayList<LoserPlayer>();
        this.neutral = new ArrayList<NeutralPlayer>();
    }

    public void computed() throws Exception
    {
        computedNeutral();
        computedLosers();
        computedWinners();

        for (WinnerPlayer winner : winners) {
            winner.ComputedDebots(losers);
        }
    }

    private void computedWinners()
    {
        for (Player player : game.players) 
        {
            if (player.stack.getState() == StackStatus.Positive)
            {
               winners.add(new WinnerPlayer(player));
            }
        }
    }

    private void computedLosers()
    {
        for (Player player : game.players) 
        {
            if (player.stack.getState() == StackStatus.Negative)
            {
                losers.add(new LoserPlayer(player));
            }    
        }
    }

    private void computedNeutral()
    {
        for (Player player : game.players) 
        {
            if (player.stack.getState() == StackStatus.Neutral)
            {
                neutral.add(new NeutralPlayer(player));
            }    
        }
    }
}
