package sg.np.edu.OwenHo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogInActivity extends AppCompatActivity {
    public static String correctPassword;
    public static String correctUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        EditText username = findViewById(R.id.usernameBox);
        EditText password = findViewById(R.id.passwordBox);
        Button loginBtn = findViewById(R.id.loginButton);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-week-6-practical-challenge-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User");
        myRef.child("mad").child("password").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Password = "+value);
                correctPassword = value;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        myRef.child("mad").child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Name= "+value);
                correctUsername = value;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((correctPassword.equals(password.getText().toString())) && (correctUsername.equals(username.getText().toString()))){
                    Intent i = new Intent(LogInActivity.this,ListActivity.class);
                    //finish();
                    startActivity(i);
                }
                else{
                    //Log.d(TAG, "Correct name: "+correctUsername+" Correct pass: "+correctPassword);
                    //Log.d(TAG,"Name: "+username.getText().toString()+" Pass: "+password.getText().toString());
                    Toast.makeText(LogInActivity.this,"Incorrect username or password",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}