package com.example.smk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smk.Obj_get.GetClass;
import com.example.smk.Obj_get.Coments;
import com.example.smk.R;

import java.util.List;

/**
 * Created by Ihor on 06.08.2016.
 */
public class ComentsArrayAdapter extends ArrayAdapter<String> {
    private List<Coments> listComents;
    private static GetClass get = new GetClass();
    private Context context;

    public ComentsArrayAdapter(Context context) {
        super(context, R.layout.coments_list, get.getTextesOfComents());
        this.context = context;
        listComents = get.getComents();
    }

    static class ViewHolder {
        public TextView listId;
        public TextView comentstRate;
        public TextView comentsText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.coments_list, null, true);
            holder = new ViewHolder();

            holder.listId = (TextView) rowView.findViewById(R.id.comentsListId);
            holder.comentstRate = (TextView) rowView.findViewById(R.id.comentsListRate);
            holder.comentsText = (TextView) rowView.findViewById(R.id.comentsListText);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        Coments coments = listComents.get(position);

        holder.comentstRate.setText(coments.getRate());
        holder.comentsText.setText(coments.getText());

        return rowView;
    }
}
