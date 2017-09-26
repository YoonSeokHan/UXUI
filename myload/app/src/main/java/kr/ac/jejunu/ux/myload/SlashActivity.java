package kr.ac.jejunu.ux.myload;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class SlashActivity extends Activity {

    /**
     * TimerTask 보다는 Handler 사용이 더 안정적이다.
     */
    private TimerTask mTask;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        setContentView(R.layout.splash);
        mTask = new TimerTask() {
            @Override
            public void run() {
                Log.d("Splash","Splash");
                //MainActivity로 이동
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            }
        };
        mTimer = new Timer();
        mTimer.schedule(mTask, 1000);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTimer != null){
            mTimer.cancel();   //메모리 낭비를 피하기 위해 꼭 필요한 코드
        }
    }
}

