package rs.elfak.mosis.marko.zadatakhtec;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Marko on 3/29/2018.
 */

public class RowAdapter extends ArrayAdapter<Row> {

    public static final int WRAP_CONTENT_LENGTH = 50;

    public RowAdapter(Context context, List<Row> objects) {
        super(context,0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, null);
        }

        Row r=getItem(position);


        if(r != null) {
            TextView title = (TextView) convertView.findViewById(R.id.row_title);
            TextView description = (TextView) convertView.findViewById(R.id.row_desc);

            title.setText(r.getTitle());

            //correctly show preview of the content (not more than 50 char or more than one line!)
            int toWrap = WRAP_CONTENT_LENGTH;
            int lineBreakIndex = r.getDescription().indexOf('\n');
            //not an elegant series of if statements...needs to be cleaned up!
            if(r.getDescription().length() > WRAP_CONTENT_LENGTH || lineBreakIndex < WRAP_CONTENT_LENGTH) {
                if(lineBreakIndex < WRAP_CONTENT_LENGTH) {
                    toWrap = lineBreakIndex;
                }
                if(toWrap > 0) {
                    description.setText(r.getDescription().substring(0, toWrap) + "...");
                } else {
                    description.setText(r.getDescription());
                }
            } else { //if less than 50 chars...leave it as is :P
                description.setText(r.getDescription());
            }
        }

        return convertView;
    }
}
