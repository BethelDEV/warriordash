package funs.gamez.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import funs.gamez.SpUtil;
import funs.gamez.R;

public class UserAchieveActivity extends Activity {
    private static final String TAG = "UserAchieveActivity";
    //成就数组
    private static String[] achieve = {"初级奔跑达人\n奔跑米数为500", "中级奔跑达人\n奔跑米数为1000，这才哪到到哪啊！", "高级奔跑达人\n奔跑米数为2000，加油小伙！", "骨灰级奔跑达人\n奔跑米数4000以上，跑那么远你不累呀！", "饭桶者\n吃到食物超过10个以上，吃饱了没，要不再来点！", "敌人杀手\n杀敌数超过5", "敌见愁\n杀敌数超过15，拜托不要让他继续碰到敌人了！", "老怪杀手\n成功击杀老怪一次，哥们才杀死一次，不要太嚣张！", "老怪控\n疯狂杀戮老怪达到3次以上，他肯定与老怪有仇！"};
    private ListView listView;
    private SimpleAdapter adapter;
    private Bitmap completed, uncompleted;
    public static int[] userAchieve;
    private List<Map<String, Object>> data;

    private static final String KEY_ACHIEVE = "UserAchieveActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achieve);
        listView = (ListView) findViewById(R.id.listview);
        completed = BitmapFactory.decodeResource(getResources(), R.drawable.completed);
        uncompleted = BitmapFactory.decodeResource(getResources(), R.drawable.uncompleted);
        listView.setAdapter(new MyAdapter(UserAchieveActivity.this));
    }

    public static int[] getAchive(Context context) {
        int[] userAchieve = new int[achieve.length];
        try {
            SpUtil sp = new SpUtil(context);
            String u = sp.getString(KEY_ACHIEVE, "");
            Log.i(TAG, "getAchive:: "+u);
            String[] temp = u.split(",");
            for (int i = 0; i < temp.length; i++)
                userAchieve[i] = Integer.parseInt(temp[i]);
            return userAchieve;
        } catch (Exception e) {
            e.printStackTrace();
            for (int i = 0; i < achieve.length; i++)
                userAchieve[i] = 0;
            return userAchieve;
        }
    }

    public static void saveAchive(Context context) {
        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i < UserAchieveActivity.userAchieve.length; i++){
            sbr.append(UserAchieveActivity.userAchieve[i]).append(",");
        }
        SpUtil sp = new SpUtil(context);
        sp.putString(KEY_ACHIEVE, sbr.toString());
        Log.i(TAG, "saveAchive:: "+sbr);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public class MyAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<Integer> achieveIdxs;

        public MyAdapter(Context context) {
            this.context = context;
            achieveIdxs = new ArrayList<>();
            initData();
        }

        private void initData() {
            final int size = achieve.length;
            for (int i = 0; i < size; i++) {
                if (userAchieve[i] == 1) achieveIdxs.add(i);
            }
        }

        public int getCount() {
            return achieveIdxs.size(); // achieve.length;
        }

        public Integer getItem(int position) {

            return achieveIdxs.get(position); //position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.listview, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
            TextView textView = (TextView) view.findViewById(R.id.textview);
//            if (userAchieve[position] == 1)
            if (userAchieve[getItem(position)] == 1)
                imageView.setImageBitmap(completed);
            else
                imageView.setImageBitmap(uncompleted);
            textView.setText(achieve[position]);
            return view;
        }

    }

}
