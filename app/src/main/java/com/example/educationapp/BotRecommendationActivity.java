package com.example.educationapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.educationapp.Adapters.MessageRVAdapter;
import com.example.educationapp.Adapters.MyRecommendedCoursesAdapter;
import com.example.educationapp.Models.PredictResponse;
import com.example.educationapp.utils.MessageModal;
import com.example.educationapp.apis.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BotRecommendationActivity extends AppCompatActivity {

    //creating variables for our widgets in xml file.

    private RecyclerView chatsRV;
    private ImageButton sendMsgIB;
    private EditText userMsgEdt;
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    RecyclerView recyclerView;


    //creating a variable for our volley request queue.

    private RequestQueue mRequestQueue;
    ArrayList<PredictResponse> list;

    //creating a variable for array list and adapter class.

    private ArrayList<MessageModal> messageModalArrayList;
    private MessageRVAdapter messageRVAdapter;
    int flagCount;
    ArrayList<String> stringArrayList;
    PredictResponse predictResponse;
    FirebaseDatabase database;
    DatabaseReference myRef;
    MyRecommendedCoursesAdapter recommendedCoursesAdapter;
    ImageView movingImage;
    FirebaseAuth auth;
    FirebaseUser currentUser;
    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_recommendation);

        //on below line we are initializing all our views.

        flagCount = -1;
        stringArrayList = new ArrayList<>();
        auth=FirebaseAuth.getInstance();

        SharedPreferences prefs = this.getSharedPreferences(
                "com.education.app", Context.MODE_PRIVATE);

        email = prefs.getString("userEmail", "");
        database = FirebaseDatabase.getInstance();
         currentUser = auth.getCurrentUser();

        //arraylist for recommendation recycler view that add and delete recommendation
        list=new ArrayList<>();

        chatsRV = findViewById(R.id.idRVChats);
        sendMsgIB = findViewById(R.id.idIBSend);
        userMsgEdt = findViewById(R.id.idEdtMessage);
        movingImage = findViewById(R.id.imageView2);

        //below line is to initialize our request queue.
        mRequestQueue = Volley.newRequestQueue(BotRecommendationActivity.this);
        mRequestQueue.getCache().clear();

        //set the recommendation recycler view i-e
        recyclerView =  findViewById(R.id.assistent_rcommended_courses_recyclerview);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager1);

        //creating a new array list
        messageModalArrayList = new ArrayList<>();
        messageModalArrayList.add(new MessageModal("Hi!", BOT_KEY));
        messageModalArrayList.add(new MessageModal("Which skills you want to learn?", BOT_KEY));

        //adding on click listener for send message button.

        sendMsgIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //checking if the message entered by user is empty or not.
                if (userMsgEdt.getText().toString().isEmpty()) {
                    //if the edit text is empty display a toast message.
                    Toast.makeText(BotRecommendationActivity.this, "Please enter your message..", Toast.LENGTH_SHORT).show();
                    return;
                }

                //calling a method to send message to our bot to get response.
                sendMessage(userMsgEdt.getText().toString());

                //below line we are setting text in our edit text as empty
                userMsgEdt.setText("");

            }
        });

        //on below line we are initialiing our adapter class and passing our array lit to it.
        messageRVAdapter = new MessageRVAdapter(messageModalArrayList, this);

        //below line we are creating a variable for our linear layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BotRecommendationActivity.this, RecyclerView.VERTICAL, false);

        //below line is to set layout manager to our recycler view.
        chatsRV.setLayoutManager(linearLayoutManager);

        //below line we are setting adapter to our recycler view.
        chatsRV.setAdapter(messageRVAdapter);



    }


    @SuppressLint("NotifyDataSetChanged")
    private void sendMessage(String userMsg) {
        //below line is to pass message to our array list which is entered by the user.
        messageModalArrayList.add(new MessageModal(userMsg, USER_KEY));
        messageRVAdapter.notifyDataSetChanged();

        flagCount++;
        if(flagCount == 0)
        {
            stringArrayList.add(userMsg);
            messageModalArrayList.add(new MessageModal("Any preference for Course Difficulty? i.e Difficult, Beginner or Average", BOT_KEY));
        }
        else if(flagCount == 1)
        {
            stringArrayList.add(userMsg);
            messageModalArrayList.add(new MessageModal("Course Rating among Previous Learners? Range 0 to 5 only", BOT_KEY));
        }
        else if(flagCount == 2)
        {
            stringArrayList.add(userMsg);
            messageModalArrayList.add(new MessageModal(" Course Project Network like from Google , IBM or anyone?", BOT_KEY));
        }
        else if(flagCount == 3)
        {
            stringArrayList.add(userMsg);
            messageModalArrayList.add(new MessageModal("Your Expectation from this course?", BOT_KEY));
        }
        else
        {


            stringArrayList.add(userMsg);

           // Toast.makeText(BotRecommendationActivity.this, ""+stringArrayList.add(userMsg), Toast.LENGTH_SHORT).show();

            messageModalArrayList.add(new MessageModal("Thank you for your Response", BOT_KEY));
            callPredictAPI();


        }
    }

    // call Api To Access Data From It
    private void callPredictAPI() {
        try {
            APIInterface apiCalls = APIClient.getClient().create(APIInterface.class);
            HashMap<String, Object> params = new HashMap<>();
            params.put("skills", stringArrayList.get(0));
            params.put("difficulty", stringArrayList.get(1));
            params.put("rating", Integer.parseInt(stringArrayList.get(2)));
            params.put("network", stringArrayList.get(3));
            params.put("expectation", stringArrayList.get(4));

            String jsonObject = new JSONObject((Map) params).toString();
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject);

            Call<PredictResponse> call = apiCalls.predictServer(body);
            call.enqueue(new Callback<PredictResponse>() {
                @Override
                public void onResponse(Call<PredictResponse> call, Response<PredictResponse> response) {
                    Log.d("ABCD", "onResponse: " + response);
                    predictResponse = response.body();
                    // Write a message to the database
                    myRef = database.getReference(email).child("recommendation").push();
                    myRef.setValue(predictResponse);

                    //bottom chat adapter that show the Api result>>>>>>>>>>>>
                    movingImage.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);

                    recommendedCoursesAdapter = new MyRecommendedCoursesAdapter(getApplicationContext(),predictResponse,5);
                    recyclerView.setAdapter(recommendedCoursesAdapter); // set the Adapter to RecyclerView

                }

                @Override
                public void onFailure(Call<PredictResponse> call, Throwable t) {

                    Log.d("TAG", "onFailure:  fail"+t.getMessage());
                }
            });
        }catch (Exception e)
        {
            Log.d("TAG", "callPredictAPI: e.get"+ e.getMessage());
        }
    }

}