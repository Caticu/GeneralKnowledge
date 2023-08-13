package com.example.generalknowledge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.generalknowledge.Api.Question;
import com.example.generalknowledge.Api.QuestionAPI;
import com.example.generalknowledge.Api.QuestionBody;
import com.example.generalknowledge.Api.RetrofitClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizActivity extends AppCompatActivity
{
    TextView correctAnswers;
    TextView wrongAnswers;
    TextView question;
    ToggleButton answer1;
    ToggleButton answer2;
    ToggleButton answer3;
    ToggleButton answer4;
    Button nextQuestion;
    Button endGame;

    private List<QuestionBody> questionBodies;
    private int currentQuestionIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        correctAnswers = findViewById(R.id.CorrectAnswersScoreTextView);
        wrongAnswers = findViewById(R.id.WrongAnswersScoreTextView);
        question = findViewById(R.id.QuestionTextView);
        answer1 = findViewById(R.id.Answer1Button);
        answer2 = findViewById(R.id.Answer2Button);
        answer3 = findViewById(R.id.Answer3Button);
        answer4 = findViewById(R.id.Answer4Button);
        nextQuestion = findViewById(R.id.NextQuestionButton);
        endGame = findViewById(R.id.EndQuizButton);

        getQuestionAndAnswer();

        endGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               Intent intent =  new Intent(QuizActivity.this, MainActivity.class);
               startActivity(intent);
               finish();
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }


    public void getQuestionAndAnswer() {
        QuestionAPI questionAPI = RetrofitClient.getInstance().create(QuestionAPI.class);
        Call<List<QuestionBody>> call = questionAPI.getQuestions();
        List<String> answerOptions = new ArrayList<>();
        call.enqueue(new Callback<List<QuestionBody>>() {
            @Override
            public void onResponse(Call<List<QuestionBody>> call, Response<List<QuestionBody>> response) {
                if (response.isSuccessful()) {
                    questionBodies = response.body();
                    if (questionBodies != null && !questionBodies.isEmpty()) {
                        // Display the first question
                        displayQuestion(questionBodies.get(currentQuestionIndex));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<QuestionBody>> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong");
            }
        });
    }


    private void displayQuestion(QuestionBody questionBody) {
        // Populate UI elements with question and answer options
        Object questionText = questionBody.getQuestionToAsk();
        question.setText(questionText.toString());

        List<String> answerOptions = new ArrayList<>();
        answerOptions.add(questionBody.getCorrectAnswer());
        answerOptions.addAll(questionBody.getIncorrectAnswers());
        Collections.shuffle(answerOptions);

        answer1.setText(answerOptions.get(0));
        answer2.setText(answerOptions.get(1));
        answer3.setText(answerOptions.get(2));
        answer4.setText(answerOptions.get(3));
    }

    private void displayNextQuestion() {
        // Move to the next question
        currentQuestionIndex++;

        // Check if there are more questions
        if (currentQuestionIndex < questionBodies.size()) {
            // Display the next question
            displayQuestion(questionBodies.get(currentQuestionIndex));
        } else {
            // Handle end of questions
        }
    }

}

