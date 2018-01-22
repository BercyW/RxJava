package security.bercy.com.week4day2rxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import security.bercy.com.week4day2rxjava.operators.BufferActivity;
import security.bercy.com.week4day2rxjava.operators.IntervalActivity;
import security.bercy.com.week4day2rxjava.operators.MapActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startMapActivity(View view) {
        startActivity(new Intent(MainActivity.this,MapActivity.class));
    }

    public void startIntervalActivity(View view) {
        startActivity(new Intent(MainActivity.this,IntervalActivity.class));
    }

    public void startBufferActivity(View view) {
        startActivity(new Intent(MainActivity.this,BufferActivity.class));
    }

    public void startFilterActivity(View view) {
    }

    public void startMergeActivity(View view) {
    }
}
