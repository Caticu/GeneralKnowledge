package com.example.generalknowledge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    Button nextQuestion;
    Button endGame;

    private List<QuestionBody> questionBodies;
    private int currentQuestionIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        question = findViewById(R.id.QuestionTextView);
        answer1 = findViewById(R.id.Answer1Button);
        answer2 = findViewById(R.id.Answer2Button);
        answer3 = findViewById(R.id.Answer3Button);
        answer4 = findViewById(R.id.Answer4Button);
        nextQuestion = findViewById(R.id.NextQuestionButton);
        endGame = findViewById(R.id.EndQuizButton);

        List<Button> listOfButtons = new ArrayList<>();
        listOfButtons.add(answer1);
        listOfButtons.add(answer2);
        listOfButtons.add(answer3);
        listOfButtons.add(answer4);

        getQuestionAndAnswer();


        answer1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String correctAnswer = questionBodies.get(currentQuestionIndex).getCorrectAnswer();
                if (answer1.getText() == correctAnswer)
                {
                    answer1.setBackgroundColor(Color.GREEN);
                }
                else
                {
                    answer1.setBackgroundColor(Color.RED);
                    for (Button button : listOfButtons)
                    {
                        String buttonText = button.getText().toString();
                        if(buttonText.equals(correctAnswer))
                        {
                            button.setBackgroundColor(Color.GREEN);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                                    startActivity(intent);
                                }
                            }, 3000);
                        }
                    }
                }
            }
        });


        answer2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String correctAnswer = questionBodies.get(currentQuestionIndex).getCorrectAnswer();
                if (answer2.getText() == correctAnswer)
                {
                    answer2.setBackgroundColor(Color.GREEN);

                }
                else
                {
                    answer2.setBackgroundColor(Color.RED);
                    for (Button button : listOfButtons)
                    {
                        String buttonText = button.getText().toString();
                        if(buttonText.equals(correctAnswer))
                        {
                            button.setBackgroundColor(Color.GREEN);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                                    startActivity(intent);
                                }
                            }, 3000);
                        }
                    }
                }
            }
        });
        answer3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String correctAnswer = questionBodies.get(currentQuestionIndex).getCorrectAnswer();
                if (answer3.getText() == correctAnswer)
                {
                    answer3.setBackgroundColor(Color.GREEN);

                }
                else
                {
                    answer3.setBackgroundColor(Color.RED);
                    for (Button button : listOfButtons)
                    {
                        String buttonText = button.getText().toString();
                        if(buttonText.equals(correctAnswer))
                        {
                            button.setBackgroundColor(Color.GREEN);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                                    startActivity(intent);
                                }
                            }, 3000);
                        }
                    }
                }
            }
        });
        answer4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String correctAnswer = questionBodies.get(currentQuestionIndex).getCorrectAnswer();
                if (answer4.getText() == correctAnswer)
                {
                    answer4.setBackgroundColor(Color.GREEN);
                    for (Button button : listOfButtons)
                    {
                        String buttonText = button.getText().toString();
                        if(buttonText.equals(correctAnswer))
                        {
                            button.setBackgroundColor(Color.GREEN);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                                    startActivity(intent);
                                }
                            }, 3000);
                        }
                    }

                }
                else
                {
                    answer4.setBackgroundColor(Color.RED);
                }
            }
        });

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
                Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                startActivity(intent);

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
        String questionTextUnedited = questionText.toString();
        String questionEdited = editQuestion(questionTextUnedited);
        question.setText(questionEdited);

        List<String> answerOptions = new ArrayList<>();
        answerOptions.add(questionBody.getCorrectAnswer());
        answerOptions.addAll(questionBody.getIncorrectAnswers());
        Collections.shuffle(answerOptions);

        answer1.setText(answerOptions.get(0));
        answer2.setText(answerOptions.get(1));
        answer3.setText(answerOptions.get(2));
        answer4.setText(answerOptions.get(3));
    }

    public String editQuestion(String text) {
        String cleanedText = text.replace("{text=", "").replace("}", "");
        return cleanedText;
    }


}

