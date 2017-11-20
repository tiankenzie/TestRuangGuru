package com.android.ruangguru.feature.games.view;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ruangguru.R;
import com.android.ruangguru.base.BaseActivity;
import com.android.ruangguru.feature.games.adapter.QuizAdapter;
import com.android.ruangguru.feature.games.contract.GameContract;
import com.android.ruangguru.feature.games.model.GameAnswerChoice;
import com.android.ruangguru.feature.games.model.GameResponse;
import com.android.ruangguru.feature.games.presenter.GamePresenter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class GameActivity extends BaseActivity implements GameContract.View{
    private List<GameResponse.Result> list;
    private int questionNumber = 0;
    private int correctNumber = 0;
    private QuizAdapter adapter;

    @Inject
    GamePresenter gamePresenter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.text_view_correct_number)
    TextView tvCorrect;

    @BindView(R.id.text_view_quiz_number)
    TextView tvNumber;

    @BindView(R.id.tv_difficulty)
    TextView tvDifficulty;

    @BindView(R.id.text_view_quiz_question)
    TextView tvQuestion;

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    @BindView(R.id.button_next)
    Boolean button;

    @Override
    public int getLayoutId() {
        return R.layout.activity_game;
    }

    @Override
    public void initial() {
        setupPresenter();
    }

    @Override
    public void initInjector() {
        DaggerGameComponent component = (DaggerGameComponent) DaggerGameComponent.builder()
                .appComponent(getComponent()).build();
        component.inject(this);
        component.inject(gamePresenter);
    }

    @Override
    public void onClickToolbar() {
        finish();
    }

    public static Intent getCallingIntent(AppCompatActivity appCompatActivity, int category, String title) {
        Intent intent = new Intent(appCompatActivity, GameActivity.class);
        intent.putExtra("category", category);
        intent.putExtra("title", title);
        return intent;
    }

    @Override
    public int getButtonIconToolbar() {
        return R.drawable.close;
    }

    @Override
    public String getToolbarTitle() {
        return getIntent().getStringExtra("title");
    }

    private void setupPresenter() {
        int category = getIntent().getIntExtra("category", 15);

        gamePresenter.setView(this);
        gamePresenter.getGame(20, category, "multiple");
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRenderGame(GameResponse gameResponse) {
        list = gameResponse.getResults();
        if (list.size() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
            return;
        }

        showQuestion(questionNumber);
    }

    private void showQuestion(int questionNumber) {
        hideButton();

        GameResponse.Result result = list.get(questionNumber);

        int number = questionNumber + 1;
        tvNumber.setText(number + "");
        tvQuestion.setText(result.getQuestion());
        tvCorrect.setText(correctNumber + "");
        tvDifficulty.setText(result.getDifficulty().toUpperCase());

        List<GameAnswerChoice> listChoice = new LinkedList<>();
        for (String choice : result.getIncorrectAnswers()) {
            // add wrong answer
            GameAnswerChoice gameAnswerChoice = new GameAnswerChoice(' ', choice, false,
                    QuizAdapter.VIEW_QUESTION, null);
            listChoice.add(gameAnswerChoice);
        }

        // add correct answer
        GameAnswerChoice gameAnswerChoice = new GameAnswerChoice(' ', result.getCorrectAnswer(),
                true, QuizAdapter.VIEW_QUESTION, null);
        listChoice.add(gameAnswerChoice);

        // random choice
        Collections.shuffle(listChoice);

        char option = 'A';
        for (GameAnswerChoice data : listChoice) {
            data.setOption(option);
            option ++;
        }

        renderView(listChoice);
    }

    private void renderView(final List<GameAnswerChoice> listChoice) {
        adapter = new QuizAdapter(this, listChoice);
        adapter.setListener(new QuizAdapter.OnAnswerSelectedListener() {
            @Override
            public void onSelected(List<GameAnswerChoice> listSelected) {
                int lastIndex = list.size() - 1;
                if (questionNumber == lastIndex) {
                    button.setText(getString(R.string.finish_game));
                }
                showButton();
                renderView(correction(listSelected));
            }
        });

        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new GridLayoutManager(this, 2));
        recycleView.setAdapter(adapter);
    }

    private List<GameAnswerChoice> correction(List<GameAnswerChoice> listSelected) {
        for (GameAnswerChoice gameAnswerChoice : listSelected) {
            if (gameAnswerChoice.getSelected() != null && gameAnswerChoice.getSelected()) {
                if (gameAnswerChoice.getAnswer()) {
                    correctNumber++;
                    gameAnswerChoice.setType(QuizAdapter.VIEW_CORRECT);
                } else if (!gameAnswerChoice.getAnswer()) {
                    gameAnswerChoice.setType(QuizAdapter.VIEW_WRONG);
                }
            } else {
                if (gameAnswerChoice.getAnswer()) {
                    gameAnswerChoice.setType(QuizAdapter.VIEW_CORRECT);
                }
            }
        }
        return listSelected;
    }

    @Override
    public void onError(String error) {
        Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showButton() {
        button.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideButton() {
        button.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gamePresenter.setView(null);
        gamePresenter.unsubscribe();
    }

    @OnClick(R.id.button_next)
    public void nextQuestion() {
        questionNumber++;

        if (button.getText().toString().equalsIgnoreCase(getString(R.string.finish_game))) {
            String message = "You have " + correctNumber + " Right Answer from " + questionNumber + " questions";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        showQuestion(questionNumber);
    }
}
