package com.poker_calculator.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.poker_calculator.Models.Player;

public class WinnerPlayer extends CalculatedPlayer 
{
    private Double countStack;    
    private Map<LoserPlayer, Integer> losersPlayers;

    public WinnerPlayer(Player player)
    {
        this.player = player;
        this.setStatus(СalculationStatus.NotCompleted);
        this.countStack = player.stack.getCountStack();
        this.losersPlayers = new HashMap<LoserPlayer, Integer>();
    }

    public Double getCountStack() {
        return countStack;
    }

    @Override
    public void Print() {
        StringBuilder sb = new StringBuilder();
        sb.append("Игрок " + player.name + " выиграл " + player.stack.getCountStack() + " стеков. Отдают : ");

        for (Map.Entry<LoserPlayer, Integer> loserPlayer : losersPlayers.entrySet()) {
            LoserPlayer key = loserPlayer.getKey();
            Integer value = loserPlayer.getValue();
            sb.append(key.player.name + " в размере " + value + ",");
        }

        System.out.println(sb.toString());
    }

    public void ComputedDebots(ArrayList<LoserPlayer> loserPlayers) throws Exception
    {
        if(loserPlayers.size() == 0)
            throw new Exception("Ошибка ввода данных. Игра имеет победителя " + this.player.name + ", но не имеет проигравших.");

       Optional<LoserPlayer> loserPlayerOptions = loserPlayers.stream()
                                                .filter(x -> x.getStatus() != СalculationStatus.NotCompleted)
                                                .filter(x -> x.getCountStack() == getCountStack())
                                                .findFirst();
       
        if (loserPlayerOptions.isPresent())
        {
            LoserPlayer loserPlayer = loserPlayerOptions.get();

            while(loserPlayer.canGive() && canTake())
            {
                loserPlayer.giveStack(this);
                takeStack(loserPlayer);
            }

            return;
        }

        for (LoserPlayer loserPlayer : loserPlayers) {
            
            if (loserPlayer.getStatus() == СalculationStatus.Completed){
                continue;
            }

            while(loserPlayer.canGive() && canTake()) 
            {
                loserPlayer.giveStack(this);
                takeStack(loserPlayer);
            }

            if(!canTake())
            {
                return;
            }
        }
    }

    public Boolean canTake()
    {
        return getStatus() != СalculationStatus.Completed;
    }

    public void takeStack(LoserPlayer loserPlayer)
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

        if (losersPlayers.containsKey(loserPlayer))
        {
            Integer currentTransfer = losersPlayers.get(loserPlayer);
            losersPlayers.put(loserPlayer, currentTransfer + countStackTransfer);
        }
        else
        {
            losersPlayers.put(loserPlayer, countStackTransfer);
        }  
    }

}