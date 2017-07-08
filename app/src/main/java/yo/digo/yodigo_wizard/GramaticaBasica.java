package yo.digo.yodigo_wizard;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;


public class GramaticaBasica extends Gramatica {
	
	public GramaticaBasica(ArrayList<Elemento> m, Context con){
		super(m, con);
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
			Collections.sort(mensaje);
		}
	}

	
}
