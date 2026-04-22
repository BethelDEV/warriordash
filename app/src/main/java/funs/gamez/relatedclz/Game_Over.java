package funs.gamez.relatedclz;


import funs.gamez.Utils;
import funs.gamez.pages.UserAchieveActivity;
import funs.gamez.mainview.DashSurfaceView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Game_Over {
	private Context context;
	//初始化背景图片
	private Bitmap bmpbg;
	private Bitmap bmpover_quit_up,bmpover_quit_down,bmpover_try_up,bmpover_try_down,bmpover_submit_up,bmpover_submit_down,bmpover_achi_up,bmpover_achi_down;//图片
	public static  int latestmeter;//米。。跑了多少米
	private boolean isexit,istry,issubmit,isachi;
	private Rect exit_rect,try_rect,sub_rect,achi_rect;
	private Rect meter_rect;
	public Game_Over(Bitmap bmpbg,Bitmap bmpover_quit_up,Bitmap bmpover_quit_down,Bitmap bmpover_try_up,Bitmap bmpover_try_down,Bitmap bmpover_submit_up,Bitmap bmpover_submit_down,Bitmap bmpover_achi_up,Bitmap bmpover_achi_down,Context context){
		this.context=context;
		this.bmpover_achi_down=bmpover_achi_down;
		this.bmpover_achi_up=bmpover_achi_up;
		this.bmpover_quit_down=bmpover_quit_down;
		this.bmpover_quit_up=bmpover_quit_up;
		this.bmpover_submit_down=bmpover_submit_down;
		this.bmpover_submit_up=bmpover_submit_up;
		this.bmpbg=bmpbg;
		this.bmpover_try_down=bmpover_try_down;
		this.bmpover_try_up=bmpover_try_up;
		//初始化 矩形
		exit_rect=new Rect(0, 0, DashSurfaceView.screenW/4, DashSurfaceView.screenW/7-1);
//		exit_rect=new Rect(0, 0, DashSurfaceView.screenW/3, DashSurfaceView.screenW/4-10);
		sub_rect=new Rect(0, DashSurfaceView.screenH*3/4, DashSurfaceView.screenW/3, DashSurfaceView.screenH);
		try_rect=new Rect(DashSurfaceView.screenW/3, DashSurfaceView.screenH*3/4, DashSurfaceView.screenW*2/3, DashSurfaceView.screenH);
		achi_rect=new Rect(DashSurfaceView.screenW*2/3, DashSurfaceView.screenH*3/4, DashSurfaceView.screenW, DashSurfaceView.screenH);

		meter_rect=new Rect(DashSurfaceView.screenW/4, DashSurfaceView.screenH*3/5, DashSurfaceView.screenW*3/4 , DashSurfaceView.screenH*4/5);

	}

	public void draw(Canvas canvas,Paint paint){
		//画背景
		canvas.drawBitmap(bmpbg, null,new Rect(0, 0, DashSurfaceView.screenW, DashSurfaceView.screenH) , paint);
		//画分数

		canvas.save();
		float density = Utils.getScreenDensity(context);
		float textSize = density<1? 22 : 22 * density;
		paint.setTextSize(textSize);
		paint.setStrokeWidth(3);
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		canvas.drawText(" 你总共跑了  "+latestmeter+"  米！ ", DashSurfaceView.screenW/100 *38,  DashSurfaceView.screenH*5/9, paint);
		canvas.restore();

		//画退出键
		if(!isexit){
			canvas.drawBitmap(bmpover_quit_up, null, exit_rect, paint);
		}else{
			canvas.drawBitmap(bmpover_quit_down, null, exit_rect, paint);
		}

		if(!issubmit){
			canvas.drawBitmap(bmpover_submit_up, null, sub_rect, paint);
		}else{
			canvas.drawBitmap(bmpover_submit_down, null, sub_rect, paint);
		}

		if(!istry){
			canvas.drawBitmap(bmpover_try_up, null, try_rect, paint);
		}else{
			canvas.drawBitmap(bmpover_try_down, null, try_rect, paint);

		}

		if(!isachi){
			canvas.drawBitmap(bmpover_achi_up, null, achi_rect, paint);
		}else{
			canvas.drawBitmap(bmpover_achi_down, null, achi_rect, paint);
		}

	}


	public void logic(){

	}

	public void onTouchEvent(MotionEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			if(exit_rect.contains(x, y)){
				isexit=true;
			}else if(sub_rect.contains(x, y)){
				issubmit=true;
				funs.gamez.dialog.MyDialog.saveScoreAndGo2ScorePage(context, latestmeter);
//				new funs.gamez.dialog.MyDialog(context, latestmeter);
			}
			else if(try_rect.contains(x, y)){
				istry=true;
			}else if(achi_rect.contains(x, y)){
				isachi=true;
			}
		}else if(event.getAction()==MotionEvent.ACTION_UP){

			if(exit_rect.contains(x, y)){
				DashSurfaceView.status=Tools.GAME_MENU;
				UserAchieveActivity.saveAchive(context);
			}else if(sub_rect.contains(x, y)){

			}
			else if(try_rect.contains(x, y)){
				DashSurfaceView.status=Tools.GAME_LOADING;
			}else if(achi_rect.contains(x, y)){
				Intent intent=new Intent(context, UserAchieveActivity.class);
				context.startActivity(intent);
			}
			isexit=false;
			issubmit=false;
			istry=false;
			isachi=false;
		}

	}

}
