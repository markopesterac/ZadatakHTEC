package rs.elfak.mosis.marko.zadatakhtec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Row> rows;
    private RowAdapter rowAdapter;
    ListView listview;
    List<Row> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview=(ListView)findViewById(R.id.list_view);
        list= new ArrayList<>();
        rowAdapter = new RowAdapter(this, list);



    }
}
