package rs.elfak.mosis.marko.zadatakhtec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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



    }
}
