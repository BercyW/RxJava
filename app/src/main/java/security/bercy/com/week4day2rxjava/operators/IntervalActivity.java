package security.bercy.com.week4day2rxjava.operators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import security.bercy.com.week4day2rxjava.R;

public class IntervalActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    private void doSomeWork() {
        disposable.add(Observable.interval(0,2,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(Long value) {
                        textView.append("onNext : Value: "+value);
                        textView.append("\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        textView.append("onError "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        textView.append(" onComplete");
                    }
                }));




    }
}
