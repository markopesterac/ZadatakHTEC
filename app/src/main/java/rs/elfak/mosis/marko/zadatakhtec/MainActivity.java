package rs.elfak.mosis.marko.zadatakhtec;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Row> rows;
    private RowAdapter rowAdapter;
    List<Row> list;

    ListView listView;


    private static String url="https://raw.githubusercontent.com/danieloskarsson/mobile-coding-exercise/master/items.json";

    // JSON Node names
    private static final String TAG_IMAGE = "image";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.list_view);

        //Callig async task to get JSON
        //new GetRows().execute();

    }

    private class GetRows extends AsyncTask<Void,Void,Void>
    {
        //Hashmap for listView
        ArrayList<HashMap<String,String>> rowList;
        ProgressDialog proDialog;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            proDialog = new ProgressDialog(MainActivity.this);
            proDialog.setMessage("Please wait...");
            proDialog.setCancelable(false);
            proDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            WebRequest webreq = new WebRequest();
            // Making a request to url and getting response
            String jsonStr = webreq.makeWebServiceCall(url);
            Log.d("Response: ", "> " + jsonStr);
            rowList = ParseJSON(jsonStr);
            return null;
        }
        @Override
        protected void onPostExecute(Void requestresult) {
            super.onPostExecute(requestresult);
            // Dismiss the progress dialog
            if (proDialog.isShowing())
                proDialog.dismiss();
            /**
             * Updating received data from JSON into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, rowList,
                    R.layout.list_item, new String[]{TAG_TITLE,TAG_DESCRIPTION },
                    new int[]{R.id.row_title, R.id.row_description});
            listView.setAdapter(adapter);
        }



    }

}
