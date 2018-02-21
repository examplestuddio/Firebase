package com.example.scarlet.firebase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "mylog";
    TextView nameUser;
TextView emailUser;
ImageView photoUser;
TextView idUser;
Button firstBtn;
Button secondBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        nameUser = findViewById(R.id.nameUser);
        emailUser = findViewById(R.id.emailUser);
        photoUser = findViewById(R.id.photoUser);
        idUser = findViewById(R.id.idUser);
        firstBtn = findViewById(R.id.firstBtn);
        secondBtn = findViewById(R.id.secondBtn);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("a1");

        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // id провайдера (например, google.com)
                String providerId = profile.getProviderId();

                // UID, специфичный для провайдера
                String uid = profile.getUid();

                // Имя, email адрес, и Url на фото профиля
                String name = profile.getDisplayName();
                String email = profile.getEmail();
                Uri photoUrl = profile.getPhotoUrl();
                nameUser.setText(name);
                emailUser.setText(email);

                idUser.setText(uid);
            }
            Uri xx = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
//            Bitmap bitmap = BitmapFactory.decodeStream(user.getPhotoUrl());
            photoUser.setImageURI(xx);
        }

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
            //    User user1 = dataSnapshot.getValue(User.class);

//                if(value.equals("2")){
//                    firstBtn.setEnabled(false);
//                }

                Log.d(TAG, "Value is: " + value);
                Toast.makeText(HomeActivity.this,"Value is: " + value,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(HomeActivity.this,"Failed to read value."+ error.toException(),
                        Toast.LENGTH_SHORT).show();
            }
        });


        }

    public void onClickFirst(View view) {
        Intent intent = new Intent(HomeActivity.this,FirstActivity.class);
        startActivity(intent);
    }

    public void onClickSecond(View view) {
        Intent intent = new Intent(HomeActivity.this, TwoActivity.class);
        startActivity(intent);
    }
}

