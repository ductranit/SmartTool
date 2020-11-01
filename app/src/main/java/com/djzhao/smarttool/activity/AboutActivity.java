package com.djzhao.smarttool.activity;

import android.content.Intent;
import android.net.Uri;
import com.google.android.material.snackbar.Snackbar;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.djzhao.smarttool.R;
import com.djzhao.smarttool.util.Constants;
import com.djzhao.smarttool.activity.base.BaseActivity;

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    private Button backBtn;
    private LinearLayout myWeb;
    private LinearLayout contactMe;
    private LinearLayout mailMe;

    private CardView introAppLayout;
    private CardView devAndSupportLayout;
    private CardView feedbackAndContactLayout;
    private CardView openLicensesLayout;

    @Override
    protected void findViewById() {
        backBtn = $(R.id.about_back_button);
        myWeb = $(R.id.about_my_web);
        contactMe = $(R.id.about_contact_me);
        mailMe = $(R.id.about_mail_me);

        introAppLayout = $(R.id.about_card_introduce_app);
        devAndSupportLayout = $(R.id.about_card_dev_and_support);
        feedbackAndContactLayout = $(R.id.about_card_feedback_and_contact);
        openLicensesLayout = $(R.id.about_card_open_licenses);
    }

    @Override
    protected void initView() {
        backBtn.setOnClickListener(this);
        myWeb.setOnClickListener(this);
        contactMe.setOnClickListener(this);
        mailMe.setOnClickListener(this);

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_down_up_show);
        introAppLayout.setAnimation(animation);
        devAndSupportLayout.setAnimation(animation);
        feedbackAndContactLayout.setAnimation(animation);
        openLicensesLayout.setAnimation(animation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        findViewById();
        initView();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.about_back_button:
                finish();
                break;
            case R.id.about_my_web:
                intent.setData(Uri.parse(Constants.MY_HOMEPAGE_ADDRESS));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;
            case R.id.about_contact_me:
                intent.setData(Uri.parse(Constants.CONTACT_MY_QQ));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;
            case R.id.about_mail_me:
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + getString(R.string.sample_email)));
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.about_toolbox));
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Snackbar.make(mailMe, R.string.no_email_program, Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }
}
