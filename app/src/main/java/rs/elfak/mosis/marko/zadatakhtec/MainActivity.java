package rs.elfak.mosis.marko.zadatakhtec;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
        new GetRows().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                HashMap<String,String> row= (HashMap<String, String>) adapterView.getItemAtPosition(i);
                String image= row.get(TAG_IMAGE);
                String title=row.get(TAG_TITLE);
                String description=row.get(TAG_DESCRIPTION);

                Intent ca=new Intent(MainActivity.this.getApplicationContext(),RowActivity.class);
                ca.putExtra("image",image);
                ca.putExtra("title",title);
                ca.putExtra("description",description);
                startActivity(ca);

            }
        });


    }

    private class GetRows extends AsyncTask<Void,Void,Void>
    {
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

    private ArrayList<HashMap<String,String>> ParseJSON(String json)
    {
        if(json!=null)
        {
            try
            {
                ArrayList<HashMap<String,String>> rowList=new ArrayList<HashMap<String, String>>();

                JSONArray array= new JSONArray(json);

                for(int i=0;i<array.length();i++)
                {
                    JSONObject object=array.getJSONObject(i);

                    String image=object.getString(TAG_IMAGE);
                    String title=object.getString(TAG_TITLE);
                    String description=object.getString(TAG_DESCRIPTION);

                    final HashMap<String, String> row = new HashMap<String, String>();

                    row.put(TAG_IMAGE,image);
                    row.put(TAG_TITLE,title);
                    row.put(TAG_DESCRIPTION,description);

                    rowList.add(row);
                }
                return rowList;
            }
            catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            Log.e("ServiceHandler", "No data received from HTTP Request");
            return null;
        }

    }
}
