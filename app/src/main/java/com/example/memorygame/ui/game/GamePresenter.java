package com.example.memorygame.ui.game;

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
        } else {
            model.setFirstItem(item);
            model.setItsSecond(true);
        }
    }

    public void _view_addScore(String value) {
        view.addScore(value);
    }

    public void _view_search_failed() {
        view.showFrontOnAllItems();
    }
}
