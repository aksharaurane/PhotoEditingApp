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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity1 extends Activity {

	Button btnLoadImage,btnsave,btnback;
	 final int RQS_IMAGE1 = 1;

	 Uri source;
	 Bitmap bitmapMaster;
	 Canvas canvasMaster;
ImageView imageView;
SeekBar Bar1, Bar2, Bar3;
Spinner redSpinner, greenSpinner, blueSpinner;
TextView colorInfo;

String[] optColor  = { "Red", "Green", "Blue"};

   @Override
   public void onCreate(Bundle savedInstanceState) {
	   MainActivity1 a=new MainActivity1();
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity1);

       btnLoadImage = (Button)findViewById(R.id.button1);
       btnsave=(Button)findViewById(R.id.button2);
       btnback=(Button)findViewById(R.id.button3);

      
       imageView = (ImageView)findViewById(R.id.result);
       
       Bar1 = (SeekBar)findViewById(R.id.bar1);
       Bar2 = (SeekBar)findViewById(R.id.bar2);
       Bar3 = (SeekBar)findViewById(R.id.bar3);
       
       redSpinner = (Spinner)findViewById(R.id.ropt);
       greenSpinner = (Spinner)findViewById(R.id.gopt);
       blueSpinner = (Spinner)findViewById(R.id.bopt);
       
       colorInfo = (TextView)findViewById(R.id.colorinfo);
       
       ArrayAdapter<String> redArrayAdapter = new ArrayAdapter<String>(
               this, 
               android.R.layout.simple_spinner_item, 
               optColor);
       redArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
       redSpinner.setAdapter(redArrayAdapter);
       redSpinner.setSelection(0);
       
       ArrayAdapter<String> greenArrayAdapter = new ArrayAdapter<String>(
               this, 
               android.R.layout.simple_spinner_item, 
               optColor);
       greenArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
       greenSpinner.setAdapter(greenArrayAdapter);
       greenSpinner.setSelection(1);
       
       ArrayAdapter<String> blueArrayAdapter = new ArrayAdapter<String>(
               this, 
               android.R.layout.simple_spinner_item, 
               optColor);
       blueArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
       blueSpinner.setAdapter(blueArrayAdapter);
       blueSpinner.setSelection(2);
       
       Bar1.setOnSeekBarChangeListener(colorBarChangeListener);
       Bar2.setOnSeekBarChangeListener(colorBarChangeListener);
       Bar3.setOnSeekBarChangeListener(colorBarChangeListener);
       
       redSpinner.setOnItemSelectedListener(colorSpinnerSelectedListener);
       greenSpinner.setOnItemSelectedListener(colorSpinnerSelectedListener);
       blueSpinner.setOnItemSelectedListener(colorSpinnerSelectedListener);
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
       setColorFilter(imageView);
   }
   
   OnSeekBarChangeListener colorBarChangeListener
   = new OnSeekBarChangeListener(){

 @Override
 public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
  setColorFilter(imageView);
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
 
OnItemSelectedListener colorSpinnerSelectedListener
= new OnItemSelectedListener(){

 @Override
 public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
   long arg3) {
  setColorFilter(imageView);
  
 }

 @Override
 public void onNothingSelected(AdapterView<?> arg0) {
  // TODO Auto-generated method stub
  
 } 
};
   
   private void setColorFilter(ImageView iv){
    
      /*
       * 5x4 matrix for transforming the color+alpha components of a Bitmap. 
       * The matrix is stored in a single array, and its treated as follows: 
       * [  a, b, c, d, e, 
       *   f, g, h, i, j, 
       *   k, l, m, n, o, 
       *   p, q, r, s, t ] 
       * 
       * When applied to a color [r, g, b, a], the resulting color is computed 
       * as (after clamping) 
       * R' = a*R + b*G + c*B + d*A + e; 
       * G' = f*R + g*G + h*B + i*A + j; 
       * B' = k*R + l*G + m*B + n*A + o; 
       * A' = p*R + q*G + r*B + s*A + t;
       */
    
    
    float value1 = ((float)Bar1.getProgress())/255;
    float value2 = ((float)Bar2.getProgress())/255;
    float value3 = ((float)Bar3.getProgress())/255;
    
    int redColorSource = redSpinner.getSelectedItemPosition();
    int greenColorSource = greenSpinner.getSelectedItemPosition();
    int blueColorSource = blueSpinner.getSelectedItemPosition();
    
    float a, b, c, f, g, h, k, l, m;
    a = b = c = f = g = h = k = l = m = 0;
    
    String colorCombination = "";
    
    colorCombination += "RED = ";
    switch(redColorSource){
    case 0: a = value1;
      colorCombination += "red x " + String.valueOf(value1) +"\n";
      break;
    case 1: b = value1;
      colorCombination += "green x " + String.valueOf(value1) +"\n";
   break;
    case 2: c = value1;
      colorCombination += "blue x " + String.valueOf(value1) +"\n";
   break;
    }
    
    colorCombination += "GREEN = ";
    switch(greenColorSource){
    case 0: f = value2;
      colorCombination += "red x " + String.valueOf(value2) +"\n";
      break;
    case 1: g = value2;
      colorCombination += "green x " + String.valueOf(value2) +"\n";
   break;
    case 2: h = value2;
      colorCombination += "blue x " + String.valueOf(value2) +"\n";
   break;
    }
    
    colorCombination += "BLUE = ";
    switch(blueColorSource){
    case 0: k = value3;
      colorCombination += "red x " + String.valueOf(value3) +"\n";
      break;
    case 1: l = value3;
      colorCombination += "green x " + String.valueOf(value3) +"\n";
   break;
    case 2: m = value3;
      colorCombination += "blue x " + String.valueOf(value3) +"\n";
   break;
    }
    
    float[] colorMatrix = { 
      a, b, c, 0, 0, //red
      f, g, h, 0, 0, //green
      k, l, m, 0, 0, //blue
      0, 0, 0, 1, 0  //alpha    
    };
    
    colorInfo.setText(colorCombination);
    
    ColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
    
    iv.setColorFilter(colorFilter);
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
