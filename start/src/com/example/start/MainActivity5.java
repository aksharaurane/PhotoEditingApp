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
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity5 extends Activity {
	Button btnLoadImage1;
	 TextView textSource1;
	 Button btnProcessing;
	 ImageView imageResult;
	 
	 final int RQS_IMAGE1 = 1;
	 
	 Uri source1;
	 
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		 MainActivity5 a=new MainActivity5();
			
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_activity5);
	  btnLoadImage1 = (Button)findViewById(R.id.loadimage1);
	  textSource1 = (TextView)findViewById(R.id.sourceuri1);
	  btnProcessing = (Button)findViewById(R.id.processing);
	  imageResult = (ImageView)findViewById(R.id.result);
	  
	  btnLoadImage1.setOnClickListener(new OnClickListener(){

	   @Override
	   public void onClick(View arg0) {
	    Intent intent = new Intent(Intent.ACTION_PICK,
	      android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	    startActivityForResult(intent, RQS_IMAGE1);
	   }});
	  
	  btnProcessing.setOnClickListener(new OnClickListener(){

	   @Override
	   public void onClick(View v) {
	    
	    if(source1 != null){
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
	       "Select image!", 
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
	   }
	  }
	 }
	 
	 private Bitmap ProcessingBitmap(){
	  Bitmap bm1 = null;
	  Bitmap newBitmap = null;
	  Bitmap newShadowBitmap = null;
	  
	  try {
	   bm1 = BitmapFactory.decodeStream(
	     getContentResolver().openInputStream(source1));
	   
	   int w = bm1.getWidth();
	   int h = bm1.getHeight();

	   Config config = bm1.getConfig();
	   if(config == null){
	    config = Bitmap.Config.ARGB_8888;
	   }
	   
	   newBitmap = Bitmap.createBitmap(w, h, config);
	   Canvas newCanvas = new Canvas(newBitmap);
	   newCanvas.drawColor(Color.BLACK);

	   Paint paint = new Paint();
	   paint.setColor(Color.WHITE);
	   Rect frame = new Rect(
	     (int)(w*0.05), 
	     (int)(w*0.05), 
	     (int)(w*0.95), 
	     (int)(h*0.95));
	   RectF frameF = new RectF(frame);
	   newCanvas.drawRect(frameF, paint);
	   paint.setXfermode(new PorterDuffXfermode(Mode.DARKEN));
	   newCanvas.drawBitmap(bm1, 0, 0, paint);
	   
	   /*
	    * Create shadow like outer frame
	    */
	   
	   //create BLACK bitmap with same size of the image
	   Bitmap bitmapFullGray = Bitmap.createBitmap(w, h, config);
	   Canvas canvasFullGray = new Canvas(bitmapFullGray);
	   canvasFullGray.drawColor(0xFF808080);
	   
	   //create bigger bitmap with shadow frame
	   int shadowThick = 100;
	   int shadowRadius = 50;
	   newShadowBitmap = Bitmap.createBitmap(w+shadowThick+shadowRadius, 
	     h+shadowThick+shadowRadius, config);
	   Canvas newShadowCanvas = new Canvas(newShadowBitmap);
	   newShadowCanvas.drawColor(Color.WHITE);
	   
	   //generate shadow
	   Paint paintShadow = new Paint();
	   paintShadow.setShadowLayer(shadowRadius, shadowThick, shadowThick, 0xFF000000);
	   newShadowCanvas.drawBitmap(bitmapFullGray, 0, 0, paintShadow);
	   
	   //Place the image
	   paintShadow.clearShadowLayer();
	   newShadowCanvas.drawBitmap(newBitmap, 0, 0, paintShadow);
	   
	  } catch (FileNotFoundException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  
	  return newShadowBitmap;
	 }

	}

