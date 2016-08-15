package com.example.start;

import java.io.FileNotFoundException;

import com.example.start.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity2 extends Activity {
	ImageView imageView;
	 SeekBar brightnessBar;
	 TextView brightnessInfo;
	 Button btnLoadImage,btnsave,btnback;
	 final int RQS_IMAGE1 = 1;

	 Uri source;
	 Bitmap bitmapMaster;
	 Canvas canvasMaster;

	 
	 PorterDuff.Mode[] optMode = PorterDuff.Mode.values();
	 
	 String[] optModeName;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	 MainActivity2 a=new MainActivity2();
	 		super.onCreate(savedInstanceState);
	 		setContentView(R.layout.activity_main_activity2);
	        btnLoadImage = (Button)findViewById(R.id.button1);
	        btnsave=(Button)findViewById(R.id.button2);
	        btnback=(Button)findViewById(R.id.button3);
	        imageView = (ImageView)findViewById(R.id.result);
	        brightnessBar = (SeekBar)findViewById(R.id.bar_brightness);
	        brightnessInfo = (TextView)findViewById(R.id.brightness_info);

	        brightnessBar.setOnSeekBarChangeListener(brightnessBarChangeListener);
	        btnLoadImage.setOnClickListener(new OnClickListener(){

	        	   @Override
	        	   public void onClick(View arg0) {
	        	    Intent intent = new Intent(Intent.ACTION_PICK,
	        	      android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	        	    startActivityForResult(intent, RQS_IMAGE1);
	        	   }});
	        btnback.setOnClickListener(new OnClickListener(){

	     	   @Override
	     	   public void onClick(View arg0) {
	     		   Intent i=new Intent(arg0.getContext(),MainActivity.class);
	       			//Toast.makeText(getApplicationContext(), "Login Successful!!", Toast.LENGTH_LONG);
	       			startActivity(i);
	     	   }});
	        setBlackAndWhite(imageView);
	        
	        
	    }
	    
	    OnSeekBarChangeListener brightnessBarChangeListener
	    = new OnSeekBarChangeListener(){

	  @Override
	  public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
	   setBlackAndWhite(imageView);
	  }

	  @Override
	  public void onStartTrackingTouch(SeekBar seekBar) {
	   // TODO Auto-generated method stub
	   
	  }

	  @Override
	  public void onStopTrackingTouch(SeekBar seekBar) {
	   // TODO Auto-generated method stub
	   
	  } 
	    };

	    
	    private void setBlackAndWhite(ImageView iv){

	     float brightness = (float)(brightnessBar.getProgress() - 255);
	     
	     float[] colorMatrix = { 
	             0.33f, 0.33f, 0.33f, 0, brightness, //red
	             0.33f, 0.33f, 0.33f, 0, brightness, //green
	             0.33f, 0.33f, 0.33f, 0, brightness, //blue
	             0, 0, 0, 1, 0    //alpha    
	           };
	     
	     ColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
	        iv.setColorFilter(colorFilter);
	        
	        brightnessInfo.setText(String.valueOf(brightness));
	    }
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	  super.onActivityResult(requestCode, resultCode, data);
	    	  if(resultCode == RESULT_OK){
	    	   switch (requestCode){
	    	   case RQS_IMAGE1:
	    	    source = data.getData();
	    	   
	    	    
	    	    try {
	    	     bitmapMaster = BitmapFactory.decodeStream(
	    	       getContentResolver().openInputStream(source));
	    	     imageView.setImageBitmap(bitmapMaster);
	    	    } catch (FileNotFoundException e) {
	    	     // TODO Auto-generated catch block
	    	     e.printStackTrace();
	    	    }
	    	    
	    	    break;
	    	   }
	    	  }
	    	 }

	}