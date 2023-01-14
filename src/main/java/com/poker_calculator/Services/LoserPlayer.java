package com.poker_calculator.Services;

import java.util.HashMap;
import java.util.Map;

import com.poker_calculator.Models.Player;

public class LoserPlayer extends CalculatedPlayer
{
    private Double countStack;
    private Map<WinnerPlayer, Integer> winnerPlayers;

    public LoserPlayer(Player player)
    {
        this.player = player;
        this.setStatus(СalculationStatus.NotCompleted);
        this.countStack = Math.abs(player.stack.getCountStack());
        this.winnerPlayers = new HashMap<WinnerPlayer, Integer>();
    }
    
    public Double getCountStack() {
        return countStack;
    }

    @Override
    public void Print() {
        // TODO Auto-generated method stub
    }

    public Boolean canGive()
    {
        return getStatus() != СalculationStatus.Completed;
    }

    public void giveStack(WinnerPlayer winnerPlayer)
    {
        Integer countStackTransfer = 1;
        
        countStack = countStack - countStackTransfer;
        
        if (countStack == 0)
        {
            setStatus(СalculationStatus.Completed);
        }
        else
        {
            setStatus(СalculationStatus.PartiallyCompleted);
        }

        if (winnerPlayers.containsKey(winnerPlayer))
        {
            Integer currentTransfer = winnerPlayers.get(winnerPlayer);
            winnerPlayers.put(winnerPlayer, currentTransfer + countStackTransfer);
        }
        else
        {
            winnerPlayers.put(winnerPlayer, countStackTransfer);
        }     
    }
}