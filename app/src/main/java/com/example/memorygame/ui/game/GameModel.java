package com.example.memorygame.ui.game;

import com.example.memorygame.model.GameItem;

public class GameModel {


    private boolean itsSecond = false;
    private GameItem firstItem;

    public boolean isItsSecond() {
        return itsSecond;
    }

    public void setItsSecond(boolean itsSecond) {
        this.itsSecond = itsSecond;
    }

    public GameItem getFirstItem() {
        return firstItem;
    }

    public void setFirstItem(GameItem firstItem) {
        this.firstItem = firstItem;
    }


}

