package com.android.ruangguru.feature.games.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.ruangguru.R;
import com.android.ruangguru.feature.games.model.GameAnswerChoice;

import java.util.List;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class QuizAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_QUESTION = 0;
    public static final int VIEW_CORRECT = 1;
    public static final int VIEW_WRONG = 2;

    private Context context;
    private List<GameAnswerChoice> list;
    private OnAnswerSelectedListener listener;

    public QuizAdapter(Context context, List<GameAnswerChoice> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;

        if (viewType == VIEW_WRONG) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_wrong_question, parent, false);
            holder = new WrongViewHolder(view);

        } else if (viewType == VIEW_CORRECT) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_correct_question, parent, false);
            holder = new CorrectViewHolder(view);

        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_question, parent, false);
            holder = new QuestionViewHolder(view);

        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final GameAnswerChoice data = list.get(position);

        String option = data.getOption() + "";
        String question = data.getQuestion();
        Spanned spanned;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            spanned = Html.fromHtml(data.getQuestion(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            spanned = Html.fromHtml(data.getQuestion());
        }

        if (holder instanceof CorrectViewHolder) {
            ((CorrectViewHolder) holder).tvOption.setText(option);
            ((CorrectViewHolder) holder).tvAnswerChoice.setText(spanned);

            if (data.getSelected() != null && !data.getSelected()) {
                ((CorrectViewHolder) holder).btnLayout.setEnabled(false);
                ((CorrectViewHolder) holder).btnLayout.setClickable(false);
            }

        } else if (holder instanceof WrongViewHolder) {
            ((WrongViewHolder) holder).tvOption.setText(option);
            ((WrongViewHolder) holder).tvAnswerChoice.setText(spanned);

            if (data.getSelected() != null && !data.getSelected()) {
                ((WrongViewHolder) holder).btnLayout.setEnabled(false);
                ((WrongViewHolder) holder).btnLayout.setClickable(false);
            }

        } else {
            ((QuestionViewHolder) holder).tvOption.setText(option);
            ((QuestionViewHolder) holder).tvChoice.setText(spanned);
            ((QuestionViewHolder) holder).tvChoiceRemarks.setText(spanned);

            if (data.getSelected() != null && !data.getSelected()) {
                ((QuestionViewHolder) holder).btnLayout.setEnabled(false);
                ((QuestionViewHolder) holder).btnLayout.setClickable(false);
            }

            ((QuestionViewHolder) holder).btnLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (GameAnswerChoice gac : list) {
                        if (gac == data) {
                            gac.setSelected(true);
                        } else {
                            gac.setSelected(false);
                        }
                    }

                    listener.onSelected(list);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {

        public TextView tvOption;
        public TextView tvAnswerChoice;
        public TextView tvChoice;
        public TextView tvChoiceRemarks;
        public RelativeLayout btnLayout;

        public QuestionViewHolder(View view) {
            super(view);

            tvOption = view.findViewById(R.id.tv_option);
            tvAnswerChoice = view.findViewById(R.id.tv_choice_answer);
            tvChoice = view.findViewById(R.id.tv_choice);
            tvChoiceRemarks = view.findViewById(R.id.tv_choice_remarks);
            btnLayout = view.findViewById(R.id.btn_layout);
        }
    }

    public static class CorrectViewHolder extends RecyclerView.ViewHolder {

        public TextView tvOption;
        public TextView tvAnswerChoice;
        public TextView tvChoiceRemarks;
        public RelativeLayout btnLayout;

        public CorrectViewHolder(View view) {
            super(view);

            tvOption = view.findViewById(R.id.tv_option);
            tvAnswerChoice = view.findViewById(R.id.tv_choice_answer);
            tvChoiceRemarks = view.findViewById(R.id.tv_choice_remarks);
            btnLayout = view.findViewById(R.id.btn_layout);
        }
    }

    public static class WrongViewHolder extends RecyclerView.ViewHolder {

        public TextView tvOption;
        public TextView tvAnswerChoice;
        public TextView tvChoiceRemarks;
        public RelativeLayout btnLayout;

        public WrongViewHolder(View view) {
            super(view);

            tvOption = view.findViewById(R.id.tv_option);
            tvAnswerChoice = view.findViewById(R.id.tv_choice_answer);
            tvChoiceRemarks = view.findViewById(R.id.tv_choice_remarks);
            btnLayout = view.findViewById(R.id.btn_layout);
        }
    }

    public void setListener(OnAnswerSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnAnswerSelectedListener {

        void onSelected(List<GameAnswerChoice> listSelected);

    }
}
