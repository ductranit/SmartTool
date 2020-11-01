package com.djzhao.smarttool.activity.github;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.djzhao.smarttool.R;
import com.djzhao.smarttool.activity.base.BaseActivity;
import com.djzhao.smarttool.util.ClipboardUtil;

public class GithubAddressMainActivity extends BaseActivity implements View.OnClickListener {

    private Button backBtn, parseBtn, clearInput;
    private TextView title, result;
    private FloatingActionButton copyResultBtn;
    private EditText inputTxt;

    @Override
    protected void findViewById() {
        backBtn = $(R.id.title_layout_back_button);
        clearInput = $(R.id.github_address_clear_input_btn);
        parseBtn = $(R.id.github_address_parse_btn);
        title = $(R.id.title_layout_title_text);
        result = $(R.id.github_address_result_txt);
        copyResultBtn = $(R.id.github_address_copy_result_btn);
        inputTxt = $(R.id.github_address_input_txt);
    }

    @Override
    protected void initView() {
        title.setText(R.string.github_file_address_resolution);
        backBtn.setOnClickListener(this);
        parseBtn.setOnClickListener(this);
        copyResultBtn.setOnClickListener(this);
        clearInput.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.github_address_activity_main);
        findViewById();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.github_address_parse_btn:
                parseUrl();
                break;
            case R.id.github_address_copy_result_btn:
                copyResult();
                break;
            case R.id.title_layout_back_button:
                finish();
                break;
            case R.id.github_address_clear_input_btn:
                inputTxt.setText("");
                break;
        }
    }

    private void copyResult() {
        String result = this.result.getText().toString().trim();
        if (!TextUtils.isEmpty(result)) {
            ClipboardUtil.copyToClipboard(result);
            Snackbar.make(inputTxt, R.string.the_result_has_been_copied_to_the_clipboard, Snackbar.LENGTH_LONG).show();
        }
    }

    private void parseUrl() {
        // https://github.com/                  djzhao627/fitness_Android/blob/master/APP/Fitness/build.gradle
        // https://raw.githubusercontent.com/   djzhao627/fitness_Android/master/APP/Fitness/app/build.gradle
        String url = inputTxt.getText().toString().trim();
        if (TextUtils.isEmpty(url)) {
            Snackbar.make(inputTxt, R.string.please_enter_address_first, Snackbar.LENGTH_LONG).show();
            return;
        }

        if (!url.startsWith("https://github.com") && !url.startsWith("http://github.com")) {
            Snackbar.make(inputTxt, R.string.incorrect_file_address, Snackbar.LENGTH_LONG).show();
            return;
        }
        url = url.replace("github.com", "raw.githubusercontent.com");
        url = url.replace("blob/", "");
        result.setText(url);
    }
}
