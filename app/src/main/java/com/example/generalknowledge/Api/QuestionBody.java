package com.example.generalknowledge.Api;
import java.util.List;

public  class QuestionBody
{
    public String category;

    public String id;
    public List<String> tags;
    public String difficulty;
    public List<String> regions;
    public boolean isNiche;

    public Object question;

    public String correctAnswer;
    public List<String> incorrectAnswers;

    public String type;

    public String getType()
    {
        return  type;
    }

    public String getCorrectAnswer()
    {
        return correctAnswer;
    }

    public List<String> getIncorrectAnswers()
    {
        return incorrectAnswers;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<String> getRegions() {
        return regions;
    }

    public boolean isNiche() {
        return isNiche;
    }

    public Object getQuestionToAsk() {
        return question;
    }
}

