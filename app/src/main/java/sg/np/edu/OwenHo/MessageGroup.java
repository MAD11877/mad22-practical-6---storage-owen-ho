package sg.np.edu.OwenHo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);



        Button group1 = findViewById(R.id.group1);
        Button group2 = findViewById(R.id.group2);

        group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Embed fragment in framelayout
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, new Group1Fragment());
                ft.commit();
            }
        });

        group2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Embed fragment in framelayout
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, new MessageFragment());
                ft.commit();
            }
        });
    }
}