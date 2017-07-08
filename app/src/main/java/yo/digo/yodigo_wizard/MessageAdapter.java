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

import java.util.ArrayList;

/**
 * Adapter para el GridView de la ventana de Mensaje, se va guardando los elementos que va seleccionando de todas las
 * categorias en un arreglo de la clase elemento, con este Adaptador se puede manipular, para agregar, remover o cualquier
 * otra cosa. Extiende BaseAdapter que es como el Adaptador base que contiene todos los metodos y funciones
 * que se ocupan para manipular lo del gridview, este MessageAdapter es personalizado para nuestras necesidades.
 *
 */
public class MessageAdapter extends BaseAdapter {
	private Context mContext;
	private static ArrayList<Elemento> elemento = new ArrayList<Elemento>();
	public static Gramatica gramatica;

	public MessageAdapter(Context c) {
		mContext = c;	
		gramatica = new Gramatica2(elemento,mContext);
	}
	
	public int getCount() {
		return elemento.size();
	}
	
	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}
	
	public void setTipoGramatica(int tipo){
		switch(tipo){
			case 1: 
				gramatica = new Gramatica(elemento,mContext);
				break;
			case 2: 
				gramatica = new GramaticaBasica(elemento,mContext);
				break;
			case 3: 
				gramatica = new Gramatica1(elemento,mContext);
				break;
			case 4:
				gramatica = new Gramatica2(elemento,mContext);
				break;
			case 5: 
				gramatica = new Gramatica3(elemento,mContext);
				break;
			case 6: 
				gramatica = new Gramatica4(elemento,mContext);
				break;
			case 7: 
				gramatica = new Gramatica5(elemento,mContext);
				break;
			case 8: 
				gramatica = new Gramatica6(elemento,mContext);
				break;
			case 9: 
				gramatica = new Gramatica7(elemento,mContext);
				break;
		}
	}

	/**
	 * Metodo que permite definir la forma en que se desplegara cada elemento en la ventana de mensaje
	 */
	@SuppressWarnings("deprecation")
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ImageView imageView;
		View gridView;
		
		if(convertView == null) {
			gridView = new View(mContext);
			gridView = inflater.inflate(R.layout.item_message,null);
			TextView textView = (TextView) gridView.findViewById(R.id.elementLabel);
			textView.setText(elemento.get(position).getTexto());
			textView.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "JandaManatee.ttf"));
			textView.setShadowLayer((float) 1.5,1,2,Color.rgb(0,0,0));
			textView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.select));
			textView.setTextColor(Color.WHITE);
			imageView = (ImageView) gridView.findViewById(R.id.elementImage);
			String ruta = elemento.get(position).getRuta();
			System.out.println("ruta contiene esto : "+ruta);
			if(ruta == null || ruta.indexOf('/')==-1){
				System.out.println("si hay id y es: "+ elemento.get(position).getImage());
				imageView.setImageResource(elemento.get(position).getImage());//iamgen de la BD
			}
			else{
				Bitmap bm = CategoryMaker.modificarTamImagen(ruta,300,300);
		        imageView.setImageBitmap(bm);
				
			}
		}
        else {
			gridView = convertView;
		}	
		return gridView;
	}
	
	public static void addElement(Elemento m){
		gramatica.validarElemento(m);
	}	
		
	public static void removeElement() {
		gramatica.eliminar();
	}
	
	public static void emptyGrid() {
		gramatica.eliminarTodo();
	}
	
	/**
	 * Metodo para conseguir el texto de cada elemento en la ventana del mensaje separandolo con espacios.
	 */
	public static String getText() {
		String msg="";
		for(int i=0;i<elemento.size();i++) {
			msg += elemento.get(i).getTexto();
			if(i!=elemento.size()-1){
				msg += " ";
			}
		}
		
		return msg;
	}
	
}
