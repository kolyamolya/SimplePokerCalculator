package com.poker_calculator.Models;

public class Player 
{
    public String name;
    public Stack stack;

    public Player(String name, Stack stack) {
        this.name = name;
        this.stack = stack;
    }
}