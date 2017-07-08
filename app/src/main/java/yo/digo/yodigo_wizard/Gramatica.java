package yo.digo.yodigo_wizard;

import android.content.Context;

import java.util.ArrayList;

public class Gramatica {

	public ArrayList<Integer> tiposPermitidos;
	public ArrayList<Elemento> mensaje;
	public Context c;
	
	public Gramatica(ArrayList<Elemento> m, Context con){
		c = con;
		mensaje = m;
		if(!mensaje.isEmpty()){
			mensaje.clear();
		}
		tiposPermitidos = new ArrayList<Integer>();
		tiposPermitidos.add(DatabaseHelper.ADJETIVO_CALIFICATIVO);
		tiposPermitidos.add(DatabaseHelper.ADJETIVO_POSESIV0);
		tiposPermitidos.add(DatabaseHelper.ADVERBIO);
		tiposPermitidos.add(DatabaseHelper.ARTICULO);
		tiposPermitidos.add(DatabaseHelper.EXPRESION);
		tiposPermitidos.add(DatabaseHelper.PREPOSICION);
		tiposPermitidos.add(DatabaseHelper.PRONOMBRE_PERSONAL);
		tiposPermitidos.add(DatabaseHelper.PRONOMBRE_REFLEXIVO);
		tiposPermitidos.add(DatabaseHelper.SUSTANTIVO);
		tiposPermitidos.add(DatabaseHelper.VERBO_CONJUGADO);
		tiposPermitidos.add(DatabaseHelper.VERBO_INFINITIVO);			
	}
	
	public void validarElemento(Elemento e){
		if( tiposPermitidos.contains(e.getClasificacion()) ){
			String cl;
			int ban=0;
			cl = e.getTexto();
			for(int i=0;i<mensaje.size();i++) {
				if(cl == mensaje.get(i).getTexto()){
					ban=1;
				}
			}
			if(ban==0){
				mensaje.add(e);
			}
		}
	}
	
	public void eliminar(){
		if(!mensaje.isEmpty()){
			mensaje.remove(mensaje.size()-1);
		}
	}
	
	public void eliminarTodo(){
		if(!mensaje.isEmpty()){
			mensaje.clear();
		}
	}
	
}
