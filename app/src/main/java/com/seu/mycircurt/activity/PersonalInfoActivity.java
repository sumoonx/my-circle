/**
 * description
 *  PersonalInfoActivity
 * @author jereemy xu
 * @time 2015/12/23
 */
package com.seu.mycircurt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.seu.mycircurt.R;

public class PersonalInfoActivity extends Activity {
    private static final String TAG = "PersonalInfoActivity";

    private EditText idEdit;
    private EditText signatureEditText;
    private EditText phoneNumberEdit;
    private EditText emailEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);

        idEdit = (EditText) findViewById(R.id.per_edit_id);
        signatureEditText = (EditText) findViewById(R.id.per_edit_sig);
        phoneNumberEdit = (EditText) findViewById(R.id.per_edit_num);
        emailEdit = (EditText) findViewById(R.id.per_edit_email);
    }

}