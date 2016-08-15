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

public class MainActivity4 extends Activity {
	 Button btnLoadImage1,btnsave,btnback;
	 TextView textSource1;
	 Button btnProcessing;
	 ImageView imageResult;
	 
	 final int RQS_IMAGE1 = 1;
	 
	 Uri source1;
	 
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		 MainActivity4 a=new MainActivity4();
			
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main_activity4);
			 btnsave=(Button)findViewById(R.id.button1);
		       btnback=(Button)findViewById(R.id.button2);
	  btnLoadImage1 = (Button)findViewById(R.id.loadimage1);
	  textSource1 = (TextView)findViewById(R.id.sourceuri1);
	  btnProcessing = (Button)findViewById(R.id.processing);
	  imageResult = (ImageView)findViewById(R.id.result);

      btnback.setOnClickListener(new OnClickListener(){

   	   @Override
   	   public void onClick(View arg0) {
   		   Intent i=new Intent(arg0.getContext(),MainActivity.class);
     			//Toast.makeText(getApplicationContext(), "Login Successful!!", Toast.LENGTH_LONG);
     			startActivity(i);
   	   }});
	  
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
	   newCanvas.drawColor(Color.WHITE);

	   Paint paint = new Paint();
	   paint.setColor(Color.BLACK);
	   Rect frame = new Rect(
	     (int)(w*0.05), 
	     (int)(w*0.05), 
	     (int)(w*0.95), 
	     (int)(h*0.95));
	   RectF frameF = new RectF(frame);
	   newCanvas.drawRoundRect(frameF, (float)(w*0.05), (float)(h*0.05), paint);
	   
	   paint.setXfermode(new PorterDuffXfermode(Mode.SCREEN));
	   newCanvas.drawBitmap(bm1, 0, 0, paint);
	   
	  } catch (FileNotFoundException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  
	  return newBitmap;
	 }

	}
