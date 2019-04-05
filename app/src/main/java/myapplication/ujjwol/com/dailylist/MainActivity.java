package myapplication.ujjwol.com.dailylist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.BatchUpdateException;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login;
    private TextView signup;

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }

        setContentView(R.layout.activity_main);

        mDialog = new ProgressDialog(this);

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);

        login = findViewById(R.id.btnLogin);
        signup = findViewById(R.id.signUP);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mEmail=email.getText().toString().trim();
                final String mPass=password.getText().toString().trim();

                if (TextUtils.isEmpty(mEmail)){
                    email.setError("Required Field..");
                    return;
                }
                if (TextUtils.isEmpty(mPass)){
                    password.setError("Required Field..");
                    return;
                }
//                Log.d(mEmail, mPass);

                mDialog.setMessage("Processing..");
                mDialog.show();

                mAuth.signInWithEmailAndPassword(mEmail, mPass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
//                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();

//                                    user.get

                                    Toast.makeText(MainActivity.this, "SUCCESSFUL", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else{

                                }
                            }
                        });


            }
        });




        //code


//        private void LoginUserAccount(String email, String password) {
//
//            if (email.isEmpty()) {
//                Toast.makeText(this, "Empty Email Fields", Toast.LENGTH_SHORT).show();
//            }
//            if (password.isEmpty()) {
//                Toast.makeText(this, "Empty Password Fields", Toast.LENGTH_SHORT).show();
//            } else {
////                loadingDialog.setTitle("Login Account");
////                loadingDialog.setMessage("Hold On !");
////                loadingDialog.show();
//                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//
//                            String online_user_id = myAuth.getCurrentUser().getUid();
//
//                            String device_token = FirebaseInstanceId.getInstance().getToken();
//
//                            userReference.child(online_user_id).child("device_token").setValue(device_token).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
//                                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    startActivity(mainIntent);
//                                    finish();
//                                }
//                            });
//
//
//                        } else {
//                            Toast.makeText(LoginActivity.this, "Login Failed !", Toast.LENGTH_SHORT).show();
//                        }
//                        loadingDialog.dismiss();
//                    }
//                });
//            }



            //code end


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
            }
        });
    }
}
