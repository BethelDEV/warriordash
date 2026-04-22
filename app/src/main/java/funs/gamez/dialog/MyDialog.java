package funs.gamez.dialog;

import funs.gamez.SpUtil;
import funs.gamez.pages.GameScoreActivity;
import funs.gamez.R;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MyDialog {
    private final int DIALOG = 0x001;
    private EditText input;
    private Button sure, cancel;
    private Dialog dialog;

    private int score;
    private Context context;


    public MyDialog(Context context, int score) {
        this.context = context;
        this.score = score;

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.paihangdialog);
        dialog.setTitle("请输入昵称");
        dialog.show();

        input = (EditText) dialog.findViewById(R.id.input_dialog_et);
        sure = (Button) dialog.findViewById(R.id.sure_dialog_bt);
        cancel = (Button) dialog.findViewById(R.id.cancel_dialog_bt);

        sure.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                System.out.println(input.getText());
                String name = input.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    input.setHint("您输入的名字不合法！");
                } else {
                    SpUtil sp = new SpUtil(context);
                    sp.putString(KeyName, name);
                    saveScore2sp(context, score);

                    dialog.dismiss();
					GameScoreActivity.startMe(context);
                }

            }
        });

        cancel.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                dialog.dismiss();

            }
        });
    }

    private final static String KeyName = "player_name";

    public static void saveScoreAndGo2ScorePage(Context context, int score) {
        SpUtil sp = new SpUtil(context);
        String name = sp.getString(KeyName, "");
        if (TextUtils.isEmpty(name)) {
            new MyDialog(context, score);
        } else {
            saveScore2sp(context, score);
			GameScoreActivity.startMe(context);
        }
    }

    public final static String KeyScore = "game_score";

    private static void saveScore2sp(Context context, int score) {
        SpUtil sp = new SpUtil(context);
        String name = sp.getString(KeyName, "");
        String data = new UserScore(name, score, System.currentTimeMillis()).toString();
        sp.appendString(KeyScore, data);
//        sp.putString(KeyScore, data);
    }
}
