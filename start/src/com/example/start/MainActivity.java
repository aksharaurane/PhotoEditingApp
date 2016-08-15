package com.example.start;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.io.FileNotFoundException;

import com.example.start.R;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ImageView imageView;
	final int RQS_IMAGE1 = 1;
	Button btncolor,btnblackwhite,btntext,btnframe,btnexit;
	 Uri source;
	 Bitmap bitmapMaster;
	 Canvas canvasMaster;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnblackwhite=(Button)findViewById(R.id.button3);
		btntext =(Button)findViewById(R.id.button5);
		 btncolor=(Button)findViewById(R.id.button2);
		 btnframe=(Button)findViewById(R.id.button4);
		 
		 btnexit=(Button)findViewById(R.id.button7);
	        imageView = (ImageView)findViewById(R.id.result);
	       
	        
	        btncolor.setOnClickListener(new OnClickListener(){

	        	   @Override
	        	   public void onClick(View arg0) {
	        		   
	        		   
	        		   Intent i=new Intent(arg0.getContext(),MainActivity1.class);
	       			//Toast.makeText(getApplicationContext(), "Login Successful!!", Toast.LENGTH_LONG);
	       			startActivity(i);

	        	    
	        	   }});
	        
	        btnblackwhite.setOnClickListener(new OnClickListener(){

	        	   @Override
	        	   public void onClick(View arg0) {
	        		   
	        		   
	        		   Intent i=new Intent(arg0.getContext(),MainActivity2.class);
	       			//Toast.makeText(getApplicationContext(), "Login Successful!!", Toast.LENGTH_LONG);
	       			startActivity(i);

	        	    
	        	   }});
	        
	        btnframe.setOnClickListener(new OnClickListener(){

	        	   @Override
	        	   public void onClick(View arg0) {
	        		   
	        		   
	        		   Intent i=new Intent(arg0.getContext(),MainActivity4.class);
	       			//Toast.makeText(getApplicationContext(), "Login Successful!!", Toast.LENGTH_LONG);
	       			startActivity(i);

	        	    
	        	   }});
	        
	        btnexit.setOnClickListener(new OnClickListener(){

	        	   @Override
	        	   public void onClick(View arg0) {
	        		   
	        		 

	        	    
	        	   }});
   
	        btntext.setOnClickListener(new OnClickListener(){

	        	   @Override
	        	   public void onClick(View arg0) {
	        		   
	        		   
	        		   Intent i=new Intent(arg0.getContext(),MainActivity3.class);
	       			//Toast.makeText(getApplicationContext(), "Login Successful!!", Toast.LENGTH_LONG);
	       			startActivity(i);

	        	    
	        	   }});
   
      
      
	}
	
	
	

	
}
