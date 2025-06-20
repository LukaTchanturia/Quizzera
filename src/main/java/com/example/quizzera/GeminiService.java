package com.example.quizzera;

import java.io.IOException;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class GeminiService {



    private static final String API_KEY = "AIzaSyAPdbZZD_usy9TaQsnJ16toAn0wVVE2hSI";
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
    private static final String MODEL_NAME = "gemini-2.0-flash";


    public static String getAIQuiz(String prompt) throws IOException {
        OkHttpClient client = new OkHttpClient();


        JSONArray contents = new JSONArray();

        contents.put(new JSONObject()
                .put("role", "user")
                .put("parts", new JSONArray()
                        .put(new JSONObject().put("text", "You are a helpful quiz generator. Generate quizzes in JSON format."))
                )
        );


        contents.put(new JSONObject()
                .put("role", "user")
                .put("parts", new JSONArray()
                        .put(new JSONObject().put("text", prompt))
                )
        );

        JSONObject payload = new JSONObject();
        payload.put("contents", contents);


        JSONObject generationConfig = new JSONObject();
        generationConfig.put("temperature", 0.7);
        generationConfig.put("maxOutputTokens", 2000);

        generationConfig.put("responseMimeType", "application/json");

        payload.put("generationConfig", generationConfig);

        RequestBody body = RequestBody.create(
                payload.toString(),
                MediaType.get("application/json; charset=utf-8")
        );


        String apiUrlWithKey = API_URL + "?key=" + API_KEY;

        Request request = new Request.Builder()
                .url(apiUrlWithKey)
                .header("Content-Type", "application/json")

                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorBody = response.body() != null ? response.body().string() : "No error body";
                throw new IOException("Gemini API error: " + response.code() + " - " + errorBody);
            }

            if (response.body() == null) {
                throw new IOException("Gemini API returned an empty response body.");
            }

            JSONObject responseJson = new JSONObject(response.body().string());


            if (responseJson.has("candidates") && responseJson.getJSONArray("candidates").length() > 0) {
                JSONObject firstCandidate = responseJson.getJSONArray("candidates").getJSONObject(0);
                if (firstCandidate.has("content") && firstCandidate.getJSONObject("content").has("parts") &&
                        firstCandidate.getJSONObject("content").getJSONArray("parts").length() > 0) {
                    return firstCandidate.getJSONObject("content")
                            .getJSONArray("parts")
                            .getJSONObject(0)
                            .getString("text");
                }
            }
            throw new IOException("Gemini API response did not contain expected content structure.");

        } finally {

        }
    }
}