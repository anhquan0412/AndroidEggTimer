package com.anhquan.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView text;
    ImageView image;
    Button b;
    CountDownTimer countdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar)findViewById(R.id.scrubber);
        text = (TextView)findViewById(R.id.time);
        image = (ImageView)findViewById(R.id.imageView);
        b = (Button)findViewById(R.id.button);
        seekBar.setMax(120000);
        seekBar.setProgress(7000);

        setTime(7000);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                {
                    image.setImageResource(R.drawable.egg1);
                    setTime((long)progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


    }

    public void start(View view)
    {

        if((b.getText()).equals("GO"))
        {
            image.setImageResource(R.drawable.egg1);
            b.setText(String.format("STOP"));
            seekBar.setVisibility(View.INVISIBLE);
            countdown = new CountDownTimer(seekBar.getProgress(),1000) {
                public void onTick(long msUntilDone)
                {

                    setTime(msUntilDone);

                }
                public void onFinish()
                {
                    text.setText(String.format("TIME'S UP"));
                    image.setImageResource(R.drawable.egg2);


                    b.setText(String.format("GO"));
                    seekBar.setVisibility(View.VISIBLE);
                }
            };
            countdown.start();
        }
        else //equal STOP
        {
            countdown.cancel();
            b.setText(String.format("GO"));
            seekBar.setVisibility(View.VISIBLE);
        }



    }
    private void setTime(long progress)
    {
        long minute = TimeUnit.MILLISECONDS.toMinutes( progress);
        long second = TimeUnit.MILLISECONDS.toSeconds(progress) - minute*60;
        text.setText(String.format("%02d:%02d", minute,second));
    }


}
