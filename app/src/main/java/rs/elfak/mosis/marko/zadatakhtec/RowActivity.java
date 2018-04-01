package rs.elfak.mosis.marko.zadatakhtec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RowActivity extends AppCompatActivity {

    String image;
    String title;
    String description;

    ImageView imageV;
    TextView titleV;
    TextView descriptionV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row);

        imageV=(ImageView)findViewById(R.id.image);
        titleV=(TextView)findViewById(R.id.title);
        descriptionV=(TextView)findViewById(R.id.description);

        Intent i=getIntent();
        image=i.getStringExtra("image");
        title=i.getStringExtra("title");
        description=i.getStringExtra("description");

        titleV.setText(title);
        descriptionV.setText(description);

        Glide.with(this)
                .load(image)
                .into(imageV);

    }
}
