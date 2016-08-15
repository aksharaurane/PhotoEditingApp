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
import android.graphics.Bitmap.Config;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity6 extends Activity {
	 
	 Button btnLoadImage1, btnLoadImage2;
	 TextView textSource1, textSource2;
	 Button btnProcessing;
	 ImageView imageResult;
	 
	 final int RQS_IMAGE1 = 1;
	 final int RQS_IMAGE2 = 2;
	 
	 Uri source1, source2;

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		 MainActivity6 a=new MainActivity6();
	 		super.onCreate(savedInstanceState);
	 		setContentView(R.layout.activity_main_activity6);
	  btnLoadImage1 = (Button)findViewById(R.id.loadimage1);
	  btnLoadImage2 = (Button)findViewById(R.id.loadimage2);
	 
	 
	  btnProcessing = (Button)findViewById(R.id.processing);
	  imageResult = (ImageView)findViewById(R.id.result);
	 
	  btnLoadImage1.setOnClickListener(new OnClickListener(){

	   @Override
	   public void onClick(View arg0) {
	    Intent intent = new Intent(Intent.ACTION_PICK,
	      android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	    startActivityForResult(intent, RQS_IMAGE1);
	   }});
	  
	  btnLoadImage2.setOnClickListener(new OnClickListener(){

	   @Override
	   public void onClick(View arg0) {
	    Intent intent = new Intent(Intent.ACTION_PICK,
	      android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	    startActivityForResult(intent, RQS_IMAGE2);
	   }});
	  
	  btnProcessing.setOnClickListener(new OnClickListener(){

	   @Override
	   public void onClick(View v) {
	    
	    if(source1 != null && source2 != null){
	     Bitmap processedBitmap = ProcessingBitmap();
	     if(processedBitmap != null){
	      imageResult.setImageBitmap(processedBitmap);
	      Toast.makeText(getApplicationContext(), 
	        "Done", 
	        Toast.LENGTH_LONG).show();
	     }else{
	      Toast.makeText(getApplicationContext(), 
	        "Something wrong in processing!", 
	        Toast.LENGTH_LONG).show();
	     }
	    }else{
	     Toast.makeText(getApplicationContext(), 
	       "Select both image!", 
	       Toast.LENGTH_LONG).show();
	    }
	    
	   }});
	 }

	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  if(resultCode == RESULT_OK){
	   switch (requestCode){
	   case RQS_IMAGE1:
	    source1 = data.getData();
	    textSource1.setText(source1.toString());
	    break;
	   case RQS_IMAGE2:
	    source2 = data.getData();
	    textSource2.setText(source2.toString());
	    break;
	   }
	    
	  }
	 }

	 private Bitmap ProcessingBitmap(){
	  Bitmap bm1 = null;
	  Bitmap bm2 =  null;
	  Bitmap newBitmap = null;
	  
	  try {
	   bm1 = BitmapFactory.decodeStream(
	     getContentResolver().openInputStream(source1));
	   bm2 = BitmapFactory.decodeStream(
	     getContentResolver().openInputStream(source2));
	   
	   int w = bm1.getWidth() + bm2.getWidth();
	   int h;
	   if(bm1.getHeight() >= bm2.getHeight()){
	    h = bm1.getHeight();
	   }else{
	    h = bm2.getHeight();
	   }
	   
	   Config config = bm1.getConfig();
	   if(config == null){
	    config = Bitmap.Config.ARGB_8888;
	   }
	   
	   newBitmap = Bitmap.createBitmap(w, h, config);
	   Canvas newCanvas = new Canvas(newBitmap);
	   
	   newCanvas.drawBitmap(bm1, 0, 0, null);
	   newCanvas.drawBitmap(bm2, bm1.getWidth(), 0, null);
	   
	  } catch (FileNotFoundException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  
	  return newBitmap;
	 }
	 

	}
