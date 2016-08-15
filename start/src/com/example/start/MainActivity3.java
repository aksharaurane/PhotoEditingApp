package com.example.start;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;

import com.example.start.R;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity3 extends Activity {
	
	Button btnLoadImage,btnsave,btnback;
	 final int RQS_IMAGE1 = 1;

	 Uri source;
	 Bitmap originalBitmap,image;
	 Canvas canvasMaster;

ImageView iv_ttx;
EditText et_sample;

Paint paint;
@Override
protected void onCreate(Bundle savedInstanceState) {
	MainActivity3 a=new MainActivity3();
	
	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main_activity3);
btnLoadImage = (Button)findViewById(R.id.button1);
btnsave=(Button)findViewById(R.id.btn_save_image);
btnback=(Button)findViewById(R.id.btn_clr_all);

//image view
iv_ttx = (ImageView) findViewById(R.id.iv_ttx);
//to get screen width and hight
DisplayMetrics displaymetrics = new DisplayMetrics();
getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//dimentions x,y of device to create a scaled bitmap having similar dimentions to screen size
int height1 = displaymetrics.heightPixels;
int width1 = displaymetrics.widthPixels;
//paint object to define paint properties
paint = new Paint();
   paint.setStyle(Paint.Style.FILL);
   paint.setColor(Color.BLUE);
   paint.setTextSize(25);
//loading bitmap from drawable
 originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.images);
//scaling of bitmap
originalBitmap =Bitmap.createScaledBitmap(originalBitmap, width1, height1, false);
//creating anoter copy of bitmap to be used for editing
image = originalBitmap.copy(Bitmap.Config.RGB_565, true);

et_sample =(EditText) findViewById(R.id.et_txt);

Button btn_save_img = (Button) findViewById(R.id.btn_save_image);
Button btn_clr_all = (Button) findViewById(R.id.btn_clr_all);
btn_clr_all.setOnClickListener(new OnClickListener() {

@Override
public void onClick(View v) {
//TODO Auto-generated method stub
//loading original bitmap again (undoing all editing)
image = originalBitmap.copy(Bitmap.Config.RGB_565, true);
  iv_ttx.setImageBitmap(image);
}
});
btnback.setOnClickListener(new OnClickListener(){

	   @Override
	   public void onClick(View arg0) {
		   Intent i=new Intent(arg0.getContext(),MainActivity.class);
			//Toast.makeText(getApplicationContext(), "Login Successful!!", Toast.LENGTH_LONG);
			startActivity(i);
	   }});
btnLoadImage.setOnClickListener(new OnClickListener(){

	   @Override
	   public void onClick(View arg0) {
	    Intent intent = new Intent(Intent.ACTION_PICK,
	      android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	    startActivityForResult(intent, RQS_IMAGE1);
	   }});
btn_save_img.setOnClickListener(new OnClickListener() {

@Override
public void onClick(View arg0) {
//TODO Auto-generated method stub
//funtion save image is called with bitmap image as parameter
saveImage(image);

}
});



iv_ttx.setOnTouchListener(new OnTouchListener() {

@Override
public boolean onTouch(View arg0, MotionEvent arg1) {
//TODO Auto-generated method stub
String user_text=et_sample.getText().toString();
//gettin x,y cordinates on screen touch
float scr_x=arg1.getRawX();
float scr_y=arg1.getRawY();
//funtion called to perform drawing
createImage(scr_x,scr_y,user_text);
return true;
}
});

}

void saveImage(Bitmap img) {
String RootDir = Environment.getExternalStorageDirectory()
               + File.separator + "txt_imgs";
  File myDir=new File(RootDir);
  myDir.mkdirs();
  Random generator = new Random();
  int n = 10000;
  n = generator.nextInt(n);
  String fname = "Image-"+ n +".jpg";
  File file = new File (myDir, fname);
  if (file.exists ()) file.delete (); 
  try {
      FileOutputStream out = new FileOutputStream(file);

      img.compress(Bitmap.CompressFormat.JPEG, 90, out);
      out.flush();
      out.close();
  } catch (Exception e) {
     e.printStackTrace();
  }
  
  Toast.makeText(MainActivity3.this, "Image saved to 'txt_imgs' folder",Toast.LENGTH_LONG).show();
}



public Bitmap createImage(float scr_x,float scr_y,String user_text){
//canvas object with bitmap image as constructor
       Canvas canvas = new Canvas(image);
       int viewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
//removing title bar hight 
       scr_y=scr_y- viewTop;
//fuction to draw text on image. you can try more drawing funtions like oval,point,rect,etc...
       canvas.drawText(""+user_text, scr_x, scr_y, paint);
       iv_ttx.setImageBitmap(image);
       return image;
   }

protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  if(resultCode == RESULT_OK){
	   switch (requestCode){
	   case RQS_IMAGE1:
	    source = data.getData();
	   
	    
	    try {
	    originalBitmap = BitmapFactory.decodeStream(
	       getContentResolver().openInputStream(source));
	    iv_ttx.setImageBitmap(originalBitmap);
	    } catch (FileNotFoundException e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	    }
	    
	    break;
	   }
	  }
	 }
}