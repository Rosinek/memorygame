package com.example.memorygame.ui.game;

import android.os.Handler;

import com.example.memorygame.model.GameItem;

public class GamePresenter {

    private GameView view;
    private GameModel model;

    public GamePresenter(GameView view, GameModel model) {
        this.view = view;
        this.model = model;
    }

    public void _model_clicked(GameItem item) {
        if (model.isItsSecond()) {
            if (model.getFirstItem().getValue().equals(item.getValue())) {
                _view_addScore(item.getValue());
                model.setItsSecond(false);
            } else {
                model.setItsSecond(false);
                _view_search_failed();
            }
            _view_addMoves();
        } else {
            model.setFirstItem(item);
            model.setItsSecond(true);
        }
    }

    private void _view_addMoves() {
        view.addMoves();
    }

    public void _view_addScore(String value) {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            view.addScore(value);
        }, 1500);

    }

    public void _view_search_failed() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            view.showFrontOnAllItems();
        }, 1500);

    }
}
