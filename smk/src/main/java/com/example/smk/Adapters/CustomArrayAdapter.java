package com.example.smk.Adapters;

/**
 * Created by Ihor on 30.07.2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smk.Obj_get.GetClass;
import com.example.smk.Obj_get.Product;
import com.example.smk.R;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<String> {
    private List<Product> list;
    private static GetClass get = new GetClass();
    private Context context;

    public CustomArrayAdapter(Context context) {
        super(context, R.layout.products_list, get.getTitlesOfProducts());
        this.context = context;
        list = get.getProducts();
    }
    static class ViewHolder {
        public ImageView imageView;
        public TextView listId;
        public TextView productName;
        public TextView productText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.products_list, null, true);
            holder = new ViewHolder();

            holder.listId = (TextView) rowView.findViewById(R.id.productListId);
            holder.imageView = (ImageView) rowView.findViewById(R.id.thumbnailImage);
            holder.productName = (TextView) rowView.findViewById(R.id.productListName);
            holder.productText = (TextView) rowView.findViewById(R.id.productListText);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        Product product = list.get(position);


        holder.imageView.setImageBitmap(product.getImg());
        holder.productName.setText(product.getTitle());
        holder.productText.setText(product.getText());

        return rowView;
    }
}
