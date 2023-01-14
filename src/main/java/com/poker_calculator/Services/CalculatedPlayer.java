package com.poker_calculator.Services;

import com.poker_calculator.Models.Player;

public abstract class CalculatedPlayer implements IPrintInfoСalculation
{
    protected Player player;
    private СalculationStatus status;

    public void setStatus(СalculationStatus status) {
        this.status = status;
    }
    
    public СalculationStatus getStatus() {
        return status;
    }
}