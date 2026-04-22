package funs.gamez.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import funs.gamez.SpUtil;
import funs.gamez.Utils;
import funs.gamez.dialog.MyDialog;
import funs.gamez.dialog.UserScore;
import funs.gamez.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GameScoreActivity extends Activity {
    private static final String TAG = "GameScoreActivity";
    private ListView show;
    private ArrayList<UserScore> userScores;
    private int mark;


    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gamescorelistview);
        show = (ListView) findViewById(R.id.show_lv);
        userScores = new ArrayList<UserScore>();
        loadScoreData();

        Collections.sort(userScores, new MyComparator());

        myAdapter = new MyAdapter(this, userScores);
        show.setAdapter(myAdapter);
    }

    private void loadScoreData() {
        SpUtil sp = new SpUtil(getApplication());
        String data = sp.getString(MyDialog.KeyScore, "");
        String[] results = data.split("\n");
        for (String line : results) {
            String[] temp = line.split(",");
            String name = temp[0];
            int score = Integer.parseInt(temp[1]);
            long time = Long.parseLong(temp[2]);
            userScores.add(new UserScore(name, score, time));

            Log.i(TAG, "loadScoreData, line: " + line);
        }
        Log.i(TAG, "loadScoreData, size: " + userScores.size());
    }


    public static void startMe(Context context) {
        Intent intent = new Intent(context, GameScoreActivity.class);
        context.startActivity(intent);
    }

}

class MyComparator implements Comparator<UserScore> {

    public int compare(UserScore lhs, UserScore rhs) {

        return (int) (rhs.getTime() - lhs.getTime());
//		return rhs.getScroe() - lhs.getScroe();
    }

}


class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<UserScore> userScores;

    public MyAdapter(Context context, ArrayList<UserScore> userScores) {
        this.context = context;
        this.userScores = userScores;
    }

    public int getCount() {

        return userScores.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return userScores.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.listview_item_gamescore, null);
        TextView name = (TextView) view.findViewById(R.id.name_item_lv_gamescore);
        TextView score = (TextView) view.findViewById(R.id.score_item_lv_gamescore);
        name.setText(Utils.getTimeString(userScores.get(position).getTime()));
//        name.setText("" + userScores.get(position).getTime());
        score.setText(" 成绩： " + userScores.get(position).getScroe());

        return view;
    }

}