package security.bercy.com.week4day2rxjava.operators;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import security.bercy.com.week4day2rxjava.R;
import security.bercy.com.week4day2rxjava.model.ApiUser;
import security.bercy.com.week4day2rxjava.model.User;

/**
 * Created by Bercy on 1/21/18.
 */

public class MapActivity extends AppCompatActivity {


    private static final String TAG = "MapActivity" ;
    private Button button;
    private TextView textView;

    @Override
    protected void onResume() {
        super.onResume();
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

        Observable.create(new ObservableOnSubscribe<List<ApiUser>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ApiUser>> e) throws Exception {
                if(!e.isDisposed()) {
                    e.onNext(getApiUserList());
                    e.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<ApiUser>, List<User>>() {
                    @Override
                    public List<User> apply(List<ApiUser> apiUsers) throws Exception {
                        return convertApiUserListToUserList(apiUsers);
                    }
                }).subscribe(new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: "+d.isDisposed());
            }

            @Override
            public void onNext(List<User> users) {
                textView.append("on Next");
                textView.append("\n");
                for (User user : users) {
                    textView.append("First Name: "+user.firstname);
                    textView.append("\n");
                }
                Log.d(TAG, "onNext: "+users.size());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });






    }

    public static List<User> convertApiUserListToUserList(List<ApiUser> apiUserList) {

        List<User> userList = new ArrayList<>();

        for (ApiUser apiUser : apiUserList) {
            User user = new User();
            user.firstname = apiUser.firstname;
            user.lastname = apiUser.lastname;
            userList.add(user);
        }

        return userList;
    }
    public static List<ApiUser> getApiUserList() {

        List<ApiUser> apiUserList = new ArrayList<>();

        ApiUser apiUserOne = new ApiUser();
        apiUserOne.firstname = "Amit";
        apiUserOne.lastname = "Shekhar";
        apiUserList.add(apiUserOne);

        ApiUser apiUserTwo = new ApiUser();
        apiUserTwo.firstname = "Manish";
        apiUserTwo.lastname = "Kumar";
        apiUserList.add(apiUserTwo);

        ApiUser apiUserThree = new ApiUser();
        apiUserThree.firstname = "Sumit";
        apiUserThree.lastname = "Kumar";
        apiUserList.add(apiUserThree);

        return apiUserList;
    }
}
