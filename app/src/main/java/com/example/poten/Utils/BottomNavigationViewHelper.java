package com.example.poten.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.poten.Board.HomeActivity;
import com.example.poten.MainActivity;
import com.example.poten.MyPage.ClubMyPageActivity;
import com.example.poten.MyPageActivity;
import com.example.poten.Notice.NoticeActivity;
import com.example.poten.R;
import com.example.poten.Search.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationView bottomNavigationViewEx) {
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
    }

    public static void enableNavigation(final Context context, final Activity callingActivity, BottomNavigationView view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_feeds:
                        Intent intent1 = new Intent(context, HomeActivity.class);//ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        callingActivity.finish();
                        break;

                    case R.id.ic_notice:
                        Intent intent2 = new Intent(context, NoticeActivity.class);//ACTIVITY_NUM = 1
                        context.startActivity(intent2);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        callingActivity.finish();
                        break;

                    case R.id.ic_search:
                        Intent intent3 = new Intent(context, SearchActivity.class);//ACTIVITY_NUM = 2
                        context.startActivity(intent3);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        callingActivity.finish();
                        break;

                    case R.id.ic_mypage:
                        Intent intent4 = new Intent(context, MyPageActivity.class);//ACTIVITY_NUM = 3
                        context.startActivity(intent4);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        callingActivity.finish();
                        break;
                }


                return false;
            }
        });
    }
}
