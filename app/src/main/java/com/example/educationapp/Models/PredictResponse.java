package com.example.educationapp.Models;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PredictResponse implements Serializable {

	@SerializedName("Status")
	private int status;

	@SerializedName("Message")
	private String message;

	@SerializedName("recommendation")
	private List<RecommendationItem> recommendation;

	public int getStatus(){
		return status;
	}

	public String getMessage(){
		return message;
	}

	public List<RecommendationItem> getRecommendation(){
		return recommendation;
	}
}