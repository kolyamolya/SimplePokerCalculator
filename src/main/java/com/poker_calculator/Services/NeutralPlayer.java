package com.poker_calculator.Services;

import com.poker_calculator.Models.Player;

public class NeutralPlayer extends CalculatedPlayer
{
    public NeutralPlayer(Player player)
    {
        this.player = player;
        this.setStatus(СalculationStatus.Completed);;
    }

    @Override
    public void Print() 
    {
        System.out.println("Игрок " + player.name + " закончил игру в 0.");
    }
}