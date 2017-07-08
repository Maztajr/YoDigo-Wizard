package yo.digo.yodigo_wizard;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/** Clase que se ocupa para poblar el gridview de la base de datos, con imagenes*/
public class MySimpleCursorAdapter extends SimpleCursorAdapter {
	int resId;
	Context con;
	LayoutInflater inflater;
	ArrayList<Integer> clas = new ArrayList<Integer>();
	int i=0;

    @SuppressWarnings("deprecation")
	public MySimpleCursorAdapter(Context context, int layout, Cursor cur, String[] from, int[] to, ArrayList<Integer> clas) {
        super(context, layout, cur, from, to);
        this.clas=clas;
        //se ocupan estos dos para agarrar los resources de la aplicacion, no se porque xD
        inflater = LayoutInflater.from(context);
        con = context; 
    } 

    /* Con el metodo llega en el text el nombre de la imagen y lo convierte a Id para pasarlo
     * con el setImageResource a imagen en el id del grid_item_image     */
    @Override public void setViewImage(ImageView iv, String text)
    {
		//cambia el color de la imagen de fondo segun sea la clasificacion
		if(clas.get(i) == 1) iv.setBackgroundResource(R.drawable.lbluebg);
		else if(clas.get(i) == 5) iv.setBackgroundResource(R.drawable.greenbg);
		else if(clas.get(i) == 10) iv.setBackgroundResource(R.drawable.yellowbg);
		else if(clas.get(i) == 11) iv.setBackgroundResource(R.drawable.pinkbg);
		else if(clas.get(i) == 12) iv.setBackgroundResource(R.drawable.orangebg);
		else iv.setBackgroundResource(R.drawable.blubg);

	    if(text.indexOf('/')==-1){//si no encuentra una diagonal en el texto, entonces no es una imagen de la SD
	    	resId = con.getResources().getIdentifier(text, "drawable", "yo.digo.yodigo_wizard");
	    	iv.setImageResource(resId);
	    	if(i<clas.size()-1)
	    	i++;
	    } else {//en caso contrario parseamos la ruta y obtenemos imagen de la SD
	    	//Bitmap bm = BitmapFactory.decodeFile(text);
	    	Bitmap bm = CategoryMaker.modificarTamImagen(text,300,300);
	        iv.setImageBitmap(bm);
	        if(i<clas.size()-1)
		    i++;
	    }
    	
    }    
    
    @Override public void setViewText(TextView iv, String text){
    	iv.setTypeface(Typeface.createFromAsset(con.getAssets(), "JandaManatee.ttf"));
    	iv.setShadowLayer((float) 1.5,1,2,Color.rgb(0,0,0));
    	iv.setText(text);
    	
    }
}
