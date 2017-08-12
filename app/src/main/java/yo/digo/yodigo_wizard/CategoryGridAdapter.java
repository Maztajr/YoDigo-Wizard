package yo.digo.yodigo_wizard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dan on 8/11/2017.
 */

public class CategoryGridAdapter extends BaseAdapter{
    private Context con;
    private LayoutInflater mInflater;
    private List<CategoryItem> mItems;
    //int i = 0;


    public CategoryGridAdapter(Context context, List<CategoryItem> listCategories){
        this.con = context;
        this.mInflater = LayoutInflater.from(context);
        this.setItems(listCategories); //this.mItems = categoryItems;
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty() ? getItems().size() : 0 );
        //return 0;
    }

    @Override
    public CategoryItem getItem(int position) {
        return (getItems() != null && !getItems().isEmpty() ? getItems().get(position) : null );
        //return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
        //return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getmId() : position;
        //return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;

        if ( v == null){
            v = mInflater.inflate(R.layout.item_grid,parent,false);
            holder = new ViewHolder();
            holder.txtCategory = (TextView) v.findViewById(R.id.grid_item_label);
            holder.imgCategory = (ImageView) v.findViewById(R.id.grid_item_image);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }
        //fill items of grid
        CategoryItem currentItem = getItem(position);
        if (currentItem != null){
            //MODIFICAMOS EL ViewImage
            holder.imgCategory.setBackgroundResource(R.drawable.blubg);

            if( currentItem.getIconBm() != null){
                holder.imgCategory.setImageBitmap(currentItem.getIconBm());
            }
            else {
                holder.imgCategory.setImageResource(currentItem.getIcon());
            }
            //MODIFICAMOS EL TextView
            holder.txtCategory.setTypeface(Typeface.createFromAsset(con.getAssets(),"JandaManatee.ttf"));
            holder.txtCategory.setShadowLayer((float) 1.5,1,2, Color.rgb(0,0,0));
            holder.txtCategory.setText(currentItem.getTitle());
        }
        return v;
    }



    public List<CategoryItem> getItems() { return mItems;}
    public void setItems(List<CategoryItem> mItems){ this.mItems = mItems; }

    class ViewHolder{
        TextView txtCategory;
        ImageView imgCategory;
    }
}
