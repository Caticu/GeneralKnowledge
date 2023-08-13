package com.example.generalknowledge.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionAPI
{
    @GET("v2/questions")
    Call<List<QuestionBody>> getQuestions();



}
