package com.example.spacebottle;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView bottle;
    private int last_dir;
    private boolean spinnin;
    private Random random = new Random();
    private Bug[] bugs = new Bug[]{
            new Bug("Клавиатура SwiftKey перекрывает поле ввода в чате"),
            new Bug("Клавиатура Samsung перекрывает поле ввода в чате"),
            new Bug("Краш приложения, хз что произошло)"),
            new Bug("Не начисляются монетки после туториала"),
            new Bug("Однополые матчи"),
            new Bug("Не загружается фотка на аватарку"),
            new Bug("Подарки не убираются с аватарки"),
            new Bug("Сделать модерацию чтобы не загружали пенисы")
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottle = findViewById(R.id.bottle);
    }

    public void spinBottle(final View view) {
        if (!spinnin) {
            int new_dir = random.nextInt(2160);
            float pointWidth = bottle.getWidth() / 2;
            float pointHeigth = bottle.getHeight() / 2;
            Animation rotation = new RotateAnimation(last_dir, new_dir, pointWidth, pointHeigth);
            rotation.setDuration(2700);
            rotation.setFillAfter(true);
            rotation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinnin = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinnin = false;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            popupBugForDev(view);
                        }
                    }, 1000);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            last_dir = new_dir;
            bottle.startAnimation(rotation);
        }
    }
    private void popupBugForDev(View view){
        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(R.layout.popup_bug, null);
        int random_index = random.nextInt(bugs.length);
        TextView bug_message = popupView.findViewById(R.id.bug_for_dev) ;
        bug_message.setText(bugs[random_index].getBug());
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
}

