package com.indianhangman;

import java.util.ArrayList;
import java.util.Random;

import com.indianhangman.Models.Question;
import com.indianhangnam.Dbutils.DatabaseAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class GameActivity extends Activity  implements OnClickListener {
	
	private static final String TAG ="GAME";
	private DatabaseAdapter dbAdapter;
    ArrayList<Question> questions ;
    Question currentQuestion;
    
    @Override
	public void onCreate(Bundle savedInstanceState)
	{
		Intent intent = getIntent();
		int subcatId = intent.getIntExtra("SubCategoryId", 2001);
		super.onCreate(savedInstanceState);
        //setContentView(R.layout.category);
        dbAdapter = new DatabaseAdapter(this);
        questions = dbAdapter.getQuestionsBySubcat(subcatId);
        getQuestion();
	}

    
    public Question getQuestion()
    {
    	Random generator = new Random();
    	int rand =generator.nextInt(questions.size());
    	Log.d(TAG, "Question Choosen has the id "+questions.get(rand).getId());
    	currentQuestion =questions.get(rand);
    	return currentQuestion;
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
