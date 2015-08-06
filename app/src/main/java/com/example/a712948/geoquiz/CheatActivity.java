package com.example.a712948.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Hannah Paulson
 * @since 8/5/15.
 */
public class CheatActivity extends ActionBarActivity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.example.a712948.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.example.a712948.geoquiz.answer_shown";
    public static final String CHEATER_KEY = "is_cheater";
    public static final String TAG = "tag";

    private boolean mAnswerIsTrue;
    private boolean mIsCheater;
    private TextView mAnswerTextView;
    private Button mShowAnswer;


    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        setAnswerShownResult(false);

        if (savedInstanceState != null) {
            mIsCheater = savedInstanceState.getBoolean(CHEATER_KEY, mIsCheater);
            if (mIsCheater) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
            }
        }
        Log.i("BEFORE ON CLICK", String.valueOf(mIsCheater));
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
                mIsCheater = true;
                Log.i("AFTER ON CLICK", String.valueOf(mIsCheater));
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        Log.i("In ON Instance State ", String.valueOf(mIsCheater));
        savedInstanceState.putBoolean(CHEATER_KEY, mIsCheater);
    }
}