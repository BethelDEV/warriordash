package funs.gamez.relatedclz;

import funs.gamez.mainview.DashSurfaceView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Loading {
	private Bitmap bmploading_bg;
	private int x,y,textX,textY;
	private Rect rect;
	private String[] load={"Loding . ","Loding. .","Loding. . .","Loding. . . .","Loding. . . . .","Loding. . . . . ."};
	public  Loading(Bitmap bitmap){
		this.bmploading_bg=bitmap;
		x=0;
		y=0;
		textX= DashSurfaceView.screenW/2-60;
		textY= DashSurfaceView.screenH/2-40;
		rect=new Rect(0, 0, DashSurfaceView.screenW, DashSurfaceView.screenH);
	}

	public void draw(Canvas canvas,Paint paint){
		//画背景图Loading
		canvas.drawBitmap(bmploading_bg, null, rect=new Rect(0, 0, DashSurfaceView.screenW, DashSurfaceView.screenH), paint);
	}


}
