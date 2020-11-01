package com.djzhao.smarttool.fragment.tanscoding;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.djzhao.smarttool.R;
import com.djzhao.smarttool.util.ClipboardUtil;

import java.security.MessageDigest;

public class MD5Fragment extends Fragment implements View.OnClickListener {

    private RadioButton encodeRadio;
    private EditText inputText;
    private Button dealInput;
    private TextView resultText;
    private TextView clearBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.transcoding_fragment_md5, container, false);
        encodeRadio = linearLayout.findViewById(R.id.fag_md5_encode);
        inputText = linearLayout.findViewById(R.id.fag_md5_input);
        dealInput = linearLayout.findViewById(R.id.fag_md5_deal);
        resultText = linearLayout.findViewById(R.id.fag_md5_result);
        clearBtn = linearLayout.findViewById(R.id.fag_md5_clear);
        return linearLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inputText.requestFocus();
        dealInput.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        resultText.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fag_md5_deal:
                if (TextUtils.isEmpty(inputText.getText())) {
                    Snackbar.make(getView(), R.string.please_enter_text_first, Snackbar.LENGTH_SHORT).show();
                } else {
                    String text = inputText.getText().toString();
                    resultText.setText(MD5Encode(text));
                    ClipboardUtil.copyToClipboard(resultText.getText().toString());
                    Snackbar.make(getView(), R.string.the_result_has_been_copied_to_the_clipboard, Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.fag_md5_clear:
                inputText.setText("");
                break;
        }
    }

    private String MD5Encode(String plaintext) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = plaintext.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}
