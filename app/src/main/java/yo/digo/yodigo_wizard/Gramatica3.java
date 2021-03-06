package yo.digo.yodigo_wizard;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

public class Gramatica3 extends Gramatica {
	
	ArrayList<Integer> added;

	public Gramatica3(ArrayList<Elemento> m, Context con){
		super(m,con);
		added = new ArrayList<Integer>();
		mensaje.add(new Elemento(" ",c.getResources().getIdentifier("line", "drawable", "yo.digo.yodigo_wizard"),"category",-1));
		mensaje.add(new Elemento(" ",c.getResources().getIdentifier("line", "drawable", "yo.digo.yodigo_wizard"),"category",-1));
		mensaje.add(new Elemento(" ",c.getResources().getIdentifier("line", "drawable", "yo.digo.yodigo_wizard"),"category",-1));
		mensaje.add(new Elemento(" ",c.getResources().getIdentifier("line", "drawable", "yo.digo.yodigo_wizard"),"category",-1));
		mensaje.add(new Elemento(" ",c.getResources().getIdentifier("line", "drawable", "yo.digo.yodigo_wizard"),"category",-1));
		mensaje.add(new Elemento(" ",c.getResources().getIdentifier("line", "drawable", "yo.digo.yodigo_wizard"),"category",-1));
		tiposPermitidos.clear();
		tiposPermitidos.add(DatabaseHelper.ADJETIVO_CALIFICATIVO);
		tiposPermitidos.add(DatabaseHelper.PRONOMBRE_PERSONAL);
		tiposPermitidos.add(DatabaseHelper.VERBO_CONJUGADO);
		tiposPermitidos.add(DatabaseHelper.SUSTANTIVO);
		tiposPermitidos.add(DatabaseHelper.ADVERBIO);
		tiposPermitidos.add(DatabaseHelper.PREPOSICION);
		tiposPermitidos.add(DatabaseHelper.ADJETIVO_POSESIV0);
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
				if(e.getClasificacion() == DatabaseHelper.PRONOMBRE_PERSONAL){
					mensaje.set(0, e);
					checkAddedElements(0);
				} else if (e.getClasificacion() == DatabaseHelper.VERBO_CONJUGADO){
					mensaje.set(1, e);
					checkAddedElements(1);
				} else if (e.getClasificacion() == DatabaseHelper.ADVERBIO ){
					mensaje.set(2, e);
					checkAddedElements(2);
				} else if ( e.getClasificacion() == DatabaseHelper.PREPOSICION ) {
					mensaje.set(3, e);
					checkAddedElements(3);
				} else if (e.getClasificacion() == DatabaseHelper.ADJETIVO_POSESIV0) {
					mensaje.set(4, e);
					checkAddedElements(4);
				}else{
					mensaje.set(5, e);
					checkAddedElements(5);
				}
			}
		}
	}
	
	public void checkAddedElements(int index){
		if(!added.contains(index)){
			added.add(index);
			Collections.sort(added);
		}
	}
	
	public void eliminar(){
		if(!mensaje.isEmpty() && !added.isEmpty()){
			mensaje.set(added.get(added.size()-1),new Elemento(" ",c.getResources().getIdentifier("line", "drawable", "yo.digo.yodigo_wizard"),"category",-1));
			added.remove(added.get(added.size()-1));
		}
	}
	
	public void eliminarTodo(){
		super.eliminarTodo();
		mensaje.add(new Elemento(" ",c.getResources().getIdentifier("line", "drawable", "yo.digo.yodigo_wizard"),"category",-1));
		mensaje.add(new Elemento(" ",c.getResources().getIdentifier("line", "drawable", "yo.digo.yodigo_wizard"),"category",-1));
		mensaje.add(new Elemento(" ",c.getResources().getIdentifier("line", "drawable", "yo.digo.yodigo_wizard"),"category",-1));
		mensaje.add(new Elemento(" ",c.getResources().getIdentifier("line", "drawable", "yo.digo.yodigo_wizard"),"category",-1));
		mensaje.add(new Elemento(" ",c.getResources().getIdentifier("line", "drawable", "yo.digo.yodigo_wizard"),"category",-1));
		mensaje.add(new Elemento(" ",c.getResources().getIdentifier("line", "drawable", "yo.digo.yodigo_wizard"),"category",-1));
	}
	
}
	
