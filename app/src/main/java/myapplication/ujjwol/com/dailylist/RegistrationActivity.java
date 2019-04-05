package myapplication.ujjwol.com.dailylist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText email, password;
    private TextView signin;
    private Button registration;

    //Object of Firebase auth
    private FirebaseAuth mAuth;

    private ProgressDialog mDilog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        mDilog = new ProgressDialog(this);

        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);

        registration = findViewById(R.id.btnRegister);

        signin = findViewById(R.id.signIN);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mPassword = email.getText().toString().trim();

                if (TextUtils.isEmpty(mEmail)){
                    email.setError("Required Field");
                    return;
                }
                if (TextUtils.isEmpty(mPassword)){
                    password.setError("Required Field");
                    return;
                }

                mDilog.setMessage("PROCESSING>>>");
                mDilog.show();

                mAuth.createUserWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            Toast.makeText(getApplicationContext(), "SUCCESSFUL", Toast.LENGTH_LONG).show();
                            mDilog.dismiss();
                        }else{
                            Toast.makeText(getApplicationContext(), "FAILED", Toast.LENGTH_LONG).show();
                            mDilog.dismiss();
                        }
                    }
                });            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
