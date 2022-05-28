package com.example.educationapp.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RecommendationItem implements Serializable {

	@SerializedName("difficulty")
	private String difficulty;

	@SerializedName("University")
	private String university;

	@SerializedName("coursename")
	private String coursename;

	@SerializedName("Price")
	private String price;

	@SerializedName("Skills")
	private String skills;

	@SerializedName("rating")
	private String rating;

	@SerializedName("description")
	private String description;

	@SerializedName("index")
	private String index;

	@SerializedName("url")
	private String url;

	public String getDifficulty(){
		return difficulty;
	}

	public String getUniversity(){
		return university;
	}

	public String getCoursename(){
		return coursename;
	}

	public String getPrice(){
		return price;
	}

	public String getSkills(){
		return skills;
	}

	public String getRating(){
		return rating;
	}

	public String getDescription(){
		return description;
	}

	public String getIndex(){
		return index;
	}

	public String getUrl(){
		return url;
	}
}