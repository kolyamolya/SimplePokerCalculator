package com.poker_calculator.Models;

import com.poker_calculator.Options.Default;

public class Stack
{    
    private Integer stackValue;
    private Double countStack;
    private StackStatus state;

    private Integer chips;

    public Stack(Integer stackValue, Integer chips)
    {
        this.stackValue = stackValue;
        this.chips = chips;
    }

    public Double getCountStack() 
    {
        countStack = (double)(chips - stackValue) / stackValue;
        return countStack;
    }

    public Integer getChips() {
        return chips;
    }

    public StackStatus getState() 
    {
        Integer currentShips = chips - stackValue;

        Integer relativeStackValue = 0;

        if (currentShips == relativeStackValue)
        {
            this.state = StackStatus.Neutral;
        }
        else if(currentShips > relativeStackValue)
        {
            this.state = StackStatus.Positive;
        }
        else
        {
            this.state = StackStatus.Negative;
        }

        return state;
    }
   
    public static Stack createDefault() 
    {
        return new Stack(Default.DEFAULT_CHIP, Default.DEFAULT_CHIP);
    }
}