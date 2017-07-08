package yo.digo.yodigo_wizard;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class CategoryMaker {
	
	ArrayList <RelativeLayout> categories = new ArrayList<RelativeLayout>();
	ArrayList <TextView> categoryNames = new ArrayList<TextView>();
	ArrayList <ImageView> categoryImages = new ArrayList<ImageView>();
	LinearLayout categoryLayout;
	Context context;
	DatabaseHelper idh;
	Typeface font;
	String textElim;
	String cats;
	
	public CategoryMaker(LinearLayout layout, Context c, DatabaseHelper dh){
		categoryLayout = layout; //es el linearLayout que esta dentro del horizontalScrollView en el activity_main.xml
		context = c;
		idh = dh;
		font = Typeface.createFromAsset(context.getAssets(), "JandaManatee.ttf");
	}
	

	public void generator(String catName, String img, int id){
		
		LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout category = (RelativeLayout) vi.inflate(R.layout.category_layout, null);
		category.setPadding(5, 5, 5, 5);
		categoryLayout.addView(category); //agrega un relativeLayout para la categoriaActual, en el LinearLayout categories_layout
		
		GridView grid = (GridView) category.findViewById(R.id.elementGrid);
    	setUpGrid(grid, id); //actualiza el grid actual con la informacion de la categoriaActual, en base al ID recibido
    	
    	ImageView image = (ImageView) category.findViewById(R.id.categoryImage);
		if(img.indexOf('/') != -1) {
    		Bitmap bm = modificarTamImagen(img,70,70);
    		image.setImageBitmap(bm);
        }else{
        	int imgid = context.getResources().getIdentifier(img, "drawable", "yo.digo.yodigo_wizard");
        	image.setImageResource(imgid);
        }
		image.setPadding(8, 5, 5, 6);
		
		TextView name = (TextView) category.findViewById(R.id.categoryName);
		cats = catName;
		name.setText(catName);
		name.setTypeface(font);
		name.setTextSize(25);
		name.setPadding(8, 5, 5, 6);
		name.setTextColor(Color.rgb(235, 142, 3));
		name.setShadowLayer((float) 1.5,1,2,Color.rgb(0,0,0));
		setUpTextView(name,catName);
		
	}
	
	public void setUpTextView(TextView text, String catName){
		text.setOnLongClickListener(new OnLongClickListener()
		 {  
			/** 
             * Cambiar para pedir contrasena
            */
		        @Override
		        public boolean onLongClick(View arg0)
		        {
		        	DialogFragment d = new CategoryEliminateConfirmation();
//					DialogFragment d = new EliminateConfirmation();
            		d.show(((FragmentActivity)context).getSupportFragmentManager() ,"eliminar");
            		cats = ((TextView) arg0).getText().toString();
		            return false;
		        }
		 });
	}
	

	@SuppressWarnings("deprecation")
	public void setUpGrid(GridView grid, int id){
		/** 
         * Cambiar para checar la gramatica
        */
		grid.setOnItemClickListener(new OnItemClickListener() {
          	public void onItemClick(AdapterView<?> parent, View v, int position, long id) { 
          		TextView text = (TextView) v.findViewById(R.id.grid_item_label);
          		String txt = text.getText().toString();
          		System.out.println(txt);
          		Cursor cur = idh.getElementId(txt);
	        		cur.moveToFirst();
	        		String imgn = cur.getString(2);
	        		int clas = cur.getInt(3);
	        		
          		((MainActivity)context).play(txt); //se activa el modulo voz
          		if(imgn.indexOf('/')==-1){//si imagen es una ruta de imagen (imagen de  la SD)
          			int resId = context.getResources().getIdentifier(imgn, "drawable", "yo.digo.yodigo_wizard");
          			((MainActivity)context).messageWindow(txt, resId, clas); //se agrega el elemento a la ventana de mensaje
          		}else{ 
          			((MainActivity)context).messageWindow(txt, imgn, clas); //se agrega el elemento a la ventana de mensaje, siendo imgn, la ruta de una imagen
          		}
          	}
          });
		/** 
         * Cambiar para pedir contrasena
        */
          grid.setOnItemLongClickListener(new OnItemLongClickListener() {
            	public boolean onItemLongClick(AdapterView<?> arg0, View v,int index, long arg3) { 
            		TextView text = (TextView) v.findViewById(R.id.grid_item_label);
            		textElim = text.getText().toString();
  	        		
            		DialogFragment d = new EliminateConfirmation();
            		d.show(((FragmentActivity)context).getSupportFragmentManager() ,"eliminar");
					return false;
            	}
            });
		
		FragmentActivity act = (FragmentActivity) context;
		Cursor c = idh.getElementoCategoria(id);
		if(c!=null && c.getCount()>0){
		    act.startManagingCursor(c); 
			MySimpleCursorAdapter adapter;
			adapter = fillData(id);
			grid.setAdapter(adapter); //grid.setAdapter(fillData(id)); //tambien se puede...
		}

	}
	
	
	//modifica el tamano de la imagen que viene de la tarjeta SD
	public static Bitmap modificarTamImagen(String ruta, float newWidth, float newHeigth){//Modificar tama�o con ruta de imagen (SD)
		Bitmap mBitmap = BitmapFactory.decodeFile(ruta);
		//Redimensionamos
	   int width = mBitmap.getWidth();
	   int height = mBitmap.getHeight();
	   float scaleWidth = (newWidth) / width;
	   float scaleHeight = (newHeigth) / height;
	   // crea una matriz para manipulacion
	   Matrix matrix = new Matrix();
	   // cambia de tamaño al bitmap
	   matrix.postScale(scaleWidth, scaleHeight);
	   // crea el nuevo bitmap
	    Bitmap salida = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
	   return salida;
	}
	
	@SuppressWarnings("deprecation")
	private MySimpleCursorAdapter fillData(int i) {
		FragmentActivity act = (FragmentActivity) context;
        Cursor c = idh.getElementoCategoria(i);
        act.startManagingCursor(c);
        c.moveToFirst();
        
        ArrayList<Integer> clas = new ArrayList<Integer>();
        
        do {
        	clas.add(c.getInt(3));
        } while(c.moveToNext());
        
       //Se guardan en el arreglo las columnas de donde se sacaran los datos, imagen,cadena,categoria padre, clasificacion
       String[] from = new String[] {DatabaseHelper.ELEMENTO_2, DatabaseHelper.ELEMENTO_1,DatabaseHelper.ELEMENTO_5 };
       
       //En el 2do parametro se guardan los ids de los views donde iran los datos
        int[] to = new int[] { R.id.grid_item_image, R.id.grid_item_label };
        
        MySimpleCursorAdapter adapter = new MySimpleCursorAdapter(context, R.layout.item_grid, c, from, to, clas);
        
        return adapter;      
    }
	
	public String getElementToEliminate(){
		return textElim;
	}
	
	public String getCategoryToEliminate(){
		return cats;
	}
	
}
