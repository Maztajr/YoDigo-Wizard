package yo.digo.yodigo_wizard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;

public class EliminateConfirmation extends DialogFragment {
	
	LayoutInflater inflater;
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Desea eliminar el digo?");
			inflater = getActivity().getLayoutInflater();
			builder.setView(inflater.inflate(R.layout.contrasena,null));
			builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   EditText user = (EditText) EliminateConfirmation.this.getDialog().findViewById(R.id.user_name);
                	   EditText pass = (EditText) EliminateConfirmation.this.getDialog().findViewById(R.id.password);
                	   if( user.getText().toString().equals("yodigo") && pass.getText().toString().equals("1234")){
                		   eliminar();
                	   }
                   }
	  		});
			builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //no hace nada
                }
	  		});
			Dialog d = builder.create();
	        return d;
	    }
	
	public void eliminar(){
		((MainActivity) getActivity()).eliminarElemento();
	}
}
