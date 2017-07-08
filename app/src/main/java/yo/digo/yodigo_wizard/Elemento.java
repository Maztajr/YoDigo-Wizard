package yo.digo.yodigo_wizard;

import android.annotation.SuppressLint;

/**
 * Clase Elemento, se utiliza para la ventana de Mensaje y la clase MessageAdapter, para poder manejar mejor
 * los elementos del MessageAdapter.
 * 
 */
public class Elemento implements Comparable<Elemento>{
	String texto, category;
	int image;
	int clasificacion;
	String ruta;
	
	public Elemento(String t, int i, String c, int cl) {
		texto = t;
		image = i;
		category = c;
		clasificacion = cl;
		ruta = null;
	}
	
	public Elemento(String t, String i, String c, int cl) {
		texto = t;
		ruta = i;
		category = c;
		clasificacion = cl;
	}
	
	public void setTexto(String t) {
		texto = t;
	}
	
	public String getRuta(){
		return ruta;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setImage(int i) {
		image = i;
	}
	
	public int getImage() {
		return image;
	}
	
	public void setCategory(String c) {
		category = c;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setClasificacion(int i) {
		clasificacion = i;
	}
	
	public int getClasificacion() {
		return clasificacion;
	}
	
	public String getTextoByClas(int clas) {
		String txt="";
		if(clas==clasificacion)
			txt = texto;
		return txt;
	}

	//Aqui compara con la clasificacion
	@SuppressLint("UseValueOf")
	public int compareTo(Elemento arg0) {
		// TODO Auto-generated method stub
		return new Integer(clasificacion).compareTo(Integer.valueOf(arg0.clasificacion));
	}
}
