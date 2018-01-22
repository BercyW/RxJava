package security.bercy.com.week4day2rxjava.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import security.bercy.com.week4day2rxjava.R;

/**
 * Created by Bercy on 1/21/18.
 */

public class BufferActivity extends AppCompatActivity {


    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);

        setContentView(R.layout.activity_operator);

        button = findViewById(R.id.btn_dosomework);
        textView = findViewById(R.id.tv_showResult);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomeWork();
            }
        });

    }

    private void doSomeWork() {

        Observable<List<String>> buffered =
                Observable.just("one","two","three","four","five")
                .buffer(3,1);
                buffered.subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        textView.append(" onNext size : " + strings.size());
                        textView.append("\n");
                        for (String value : strings) {
                            textView.append(" value : " + value);
                            textView.append("\n");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        textView.append(" onComplete");
                    }
                });




    }
}
