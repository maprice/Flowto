package com.example.mprice.mpflowto;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mprice on 2/7/16.
 */
public class PhotoUtils {

    public static void highlightText(TextView textView, Context context) {
        int dark = context.getResources().getColor(R.color.colorPrimaryDark);
        int accents = context.getResources().getColor(R.color.colorAccentDark);
        SpannableString hashText = new SpannableString(textView.getText().toString());
        Matcher matcher = Pattern.compile("#([A-Za-z0-9_-]+)").matcher(hashText);
        while (matcher.find()) {
            hashText.setSpan(new ForegroundColorSpan(dark), matcher.start(), matcher.end(), 0);
            textView.setText(hashText);
        }
        matcher = Pattern.compile("@([A-Za-z0-9_-]+)").matcher(hashText);
        while (matcher.find()) {
            hashText.setSpan(new ForegroundColorSpan(accents), matcher.start(), matcher.end(), 0);
            textView.setText(hashText);
        }
    }
}
