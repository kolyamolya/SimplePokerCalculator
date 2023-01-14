package com.poker_calculator.Models;

import java.util.ArrayList;

public class Game 
{
    public Integer stackChips;
    public ArrayList<Player> players;

    public Game(Integer stackChips)
    {
        this.stackChips = stackChips;
    }

    public void addPlayer(Player player)
    {
        if (players == null)
        {
            players = new ArrayList<Player>();
        }

        players.add(player);
    }

    public Integer calculatedAllChipsInGame()
    {
        Integer chips = 0;
        
        for (Player player : players) 
        {
            if (player.stack.getState() != StackStatus.Negative)
            {
                chips = chips + player.stack.getChips();    
            }
            else
            {
                chips = chips - player.stack.getChips();
            }
        }

        return chips;
    }

    public Double calculatedAllStackInGame()
    {
        return (double)calculatedAllChipsInGame() / stackChips;
    }

    public void gameInfo()
    {
        System.out.println("В игре приняло " + players.size() + " игрока/игроков.");
        System.out.println("Было разыгранно " + calculatedAllChipsInGame() + " фишек или " + calculatedAllStackInGame() + " стеков.");
    }
}