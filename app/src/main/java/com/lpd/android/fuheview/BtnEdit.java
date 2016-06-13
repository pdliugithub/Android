package com.lpd.android.fuheview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lpd.android.R;

/**
 * Created by Administrator on 2016/6/1.
 */
public class BtnEdit extends LinearLayout{

    private TextView mInput;

    public BtnEdit(Context context) {
        this(context, null);
    }

    public BtnEdit(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate( R.layout.custom_btn_edit,this);

        mInput = (TextView) view.findViewById(R.id.id_input);

        findViewById(R.id.id_delete).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mInput.setText("");
            }
        });
    }

    public BtnEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }
}
