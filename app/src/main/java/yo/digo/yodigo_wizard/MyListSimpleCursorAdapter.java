package yo.digo.yodigo_wizard;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/** Clase que se ocupa para poblar el gridview de la base de datos, con imagenes*/
public class MyListSimpleCursorAdapter extends SimpleCursorAdapter {
	int resId;
	Context con;
	LayoutInflater inflater;


    @SuppressWarnings("deprecation")
	public MyListSimpleCursorAdapter(Context context, int layout, Cursor cur,
                                     String[] from, int[] to) {
        super(context, layout, cur, from, to);
        
        //se ocupan estos dos para agarrar los resources de la aplicacion, no se porque xD
        inflater = LayoutInflater.from(context);
        con = context; 
        
    }

    /* Con el metodo llega en el text el nombre de la imagen y lo convierte a Id para pasarlo
     * con el setImageResource a imagen en el id del grid_item_image     */
    @Override public void setViewImage(ImageView iv, String text)
    {    	
    	if(text.indexOf('/') == -1){
    		resId = con.getResources().getIdentifier(text, "drawable", "yo.digo.yodigo_wizard");
    		iv.setImageResource(resId);
    	}else{
    		Bitmap bm = BitmapFactory.decodeFile(text);
	        iv.setImageBitmap(bm);
    	}

    }
    
    public int getImage(int pos) {
    	return 0;
    }
    
    @Override public void setViewText(TextView iv, String text){
    	iv.setTypeface(Typeface.createFromAsset(con.getAssets(), "JandaManatee.ttf"));
    	iv.setTextColor(Color.WHITE);
    	iv.setShadowLayer((float) 1.5,1,2,Color.rgb(0,0,0));
    	iv.setText(text);
    	
    }

}
