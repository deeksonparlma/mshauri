package com.epicodus.mshauri.service;

import com.epicodus.mshauri.constants.constants;
import com.epicodus.mshauri.model.AwarenessModel;
import com.epicodus.mshauri.constants.constants;
import com.epicodus.mshauri.model.AwarenessModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class mshauriService {
    //user profiles//
    public static void getprofile(Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(constants.PROFILE).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
//    public ArrayList<userDetailsModel> processResults(Response response) {
//        ArrayList<userDetailsModel> user = new ArrayList<>();
//        try {
//            String jsonData = response.body().string();
//            System.out.println(jsonData);
//            JSONArray jsonArray = new JSONArray(jsonData); //access the top node//
//            if(response.isSuccessful()){
//                for(int i =0; i<jsonArray.length();i++){
//                    JSONObject users = jsonArray.getJSONObject(i);
//                    String username = users.getString("username");
//                    String email = users.getString("email");
//                    String phoneNumber = users.getString("tel_no");
//                    String location = users.getString("user_location");
//
//                    userDetailsModel usersInfo = new userDetailsModel(username,phoneNumber,email,location);
//                    user.add(usersInfo);
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    return user;
//    }
    //foundations//
//    public static void getFoundation(Callback callback){
//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(constants.FOUNDATIONS).newBuilder();
//        String url = urlBuilder.build().toString();
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        Call call = client.newCall(request);
//        call.enqueue(callback);
//    }
//
//    public ArrayList<foundationModel> foundations(Response response) {
//        ArrayList<foundationModel> foundationArray = new ArrayList<>();
//        try {
//            String jsonData = response.body().string();
//            System.out.println(jsonData);
//            JSONArray jsonArray = new JSONArray(jsonData); //access the top node//
//            if(response.isSuccessful()){
//                for(int i =0; i<jsonArray.length();i++){
//                    JSONObject users = jsonArray.getJSONObject(i);
//                    String name = users.getString("name");
//                    String contact = users.getString("contact");
//                    String website = users.getString("website_link");
//                    String location = users.getString("location");
//                    String description = users.getString("description");
//
//                    foundationModel usersInfo = new foundationModel(name,location,contact,website,description);
//                    foundationArray.add(usersInfo);
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return foundationArray;
//    }

    //Awareness posts//
    public static void getAwarenessPosts(Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(constants.AWARENESS).newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public ArrayList<AwarenessModel> awarenessPosts(Response response){
        ArrayList<AwarenessModel> AwarenessArray = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            System.out.println(jsonData);
            JSONArray jsonArray = new JSONArray(jsonData); //access the top node//
            if(response.isSuccessful()){
                for(int i =0; i<jsonArray.length();i++){
                    JSONObject posts = jsonArray.getJSONObject(i);
                    String foundationName = posts.getString("foundation");
                    String article = posts.getString("article");
                    String articleTitle = posts.getString("article_title");
                    String date = posts.getString("date");

                    AwarenessModel awarenessPosts = new AwarenessModel(foundationName,articleTitle,article,date);
                    AwarenessArray.add(awarenessPosts);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AwarenessArray;
    }
}



