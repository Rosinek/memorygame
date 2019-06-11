package com.example.memorygame.ui.game;

interface GameView {

    void setScore(int score);

    void randomizeItems();

    void showFrontOnAllItems();

    void addScore(String value);
}
