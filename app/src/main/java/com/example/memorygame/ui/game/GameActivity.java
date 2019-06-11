package com.example.memorygame.ui.game;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.R;
import com.example.memorygame.model.GameItem;
import com.example.memorygame.widgets.GridButton;
import com.example.memorygame.widgets.GridButtonListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static butterknife.ButterKnife.bind;

public class GameActivity extends AppCompatActivity implements GameView, GridButtonListener {

    @BindView(R.id.tv_score)
    TextView tvScore;

    private List<String> listOfItems = new ArrayList<>();

    private GridButton gv0, gv1, gv2, gv3, gv4, gv5, gv6, gv7, gv8, gv9, gv10, gv11;

    private List<GridButton> listOfButtons = new ArrayList<>();

    private GamePresenter presenter;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        bind(this);
        initViews();
        initList();
        randomizeItems();
        presenter = new GamePresenter(this, new GameModel());
    }

    @Override
    public void setScore(int score) {
        tvScore.setText(String.format("Score: %d", score));

    }

    @Override
    public void randomizeItems() {
        Collections.shuffle(listOfItems);
        setItemsToGridButtons();
    }

    @Override
    public void showFrontOnAllItems() {
        for (GridButton item : listOfButtons) {
            if (item.ismIsBackVisible())
                item.showFrontSide();
        }
    }

    @Override
    public void addScore(String value) {
        score++;
        tvScore.setText(String.format("Score: %d", score));

        hideItem(value);
    }

    private void hideItem(String value) {
        for (GridButton item : listOfButtons) {
            if (item.getItemValue().equals(value))
                item.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClickGridButton(GameItem item) {
        presenter._model_clicked(item);
    }


    @OnClick(R.id.btn_reset)
    public void reset() {
        showFrontOnAllItems();
        Handler handler = new Handler();
        handler.postDelayed(this::randomizeItems, 1100);
        score = 0;
        tvScore.setText(String.format("Score: %d", score));
        showAllItems();
    }

    private void showAllItems() {
        for (GridButton item : listOfButtons) {
            item.setVisibility(View.VISIBLE);
        }
    }

    private void initList() {
        listOfItems.addAll(Arrays.asList(getResources().getStringArray(R.array.your_strings)));
        listOfItems.addAll(Arrays.asList(getResources().getStringArray(R.array.your_strings)));

        listOfButtons.add(gv0);
        listOfButtons.add(gv1);
        listOfButtons.add(gv2);
        listOfButtons.add(gv3);
        listOfButtons.add(gv4);
        listOfButtons.add(gv5);
        listOfButtons.add(gv6);
        listOfButtons.add(gv7);
        listOfButtons.add(gv8);
        listOfButtons.add(gv9);
        listOfButtons.add(gv10);
        listOfButtons.add(gv11);
    }

    private void initViews() {
        gv0 = findViewById(R.id.btn0);
        gv1 = findViewById(R.id.btn1);
        gv2 = findViewById(R.id.btn2);

        gv3 = findViewById(R.id.btn3);
        gv4 = findViewById(R.id.btn4);
        gv5 = findViewById(R.id.btn5);

        gv6 = findViewById(R.id.btn6);
        gv7 = findViewById(R.id.btn7);
        gv8 = findViewById(R.id.btn8);

        gv9 = findViewById(R.id.btn9);
        gv10 = findViewById(R.id.btn10);
        gv11 = findViewById(R.id.btn11);
    }

    private void setItemsToGridButtons() {
        int i = 0;
        for (GridButton item : listOfButtons) {
            item.setChangeListener(this);
            item.setItemValue(listOfItems.get(i));
            i++;
        }
    }


}
