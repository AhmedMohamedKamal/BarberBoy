package kamal.agooz.ahmed.barberboy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerification extends AppCompatActivity
{
    Button done_btn;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        done_btn = findViewById(R.id.done_btn);

        auth = FirebaseAuth.getInstance();

        done_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                user = auth.getCurrentUser();

                if (user != null)
                {
                    user.reload();

                    if (user.isEmailVerified())
                    {
                        Intent intent = new Intent(getApplicationContext(), startActivity.class);
                        startActivity(intent);
                    } else
                    {
                        Toast.makeText(getApplicationContext(), "Please Verify Your Email", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}