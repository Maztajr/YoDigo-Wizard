package yo.digo.yodigo_wizard;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SelectGrammarActivity extends Activity{
Activity act = this;
	
	DatabaseHelper idh;//referencia a la BD
	Cursor categoriaResult;//resultado de query
	MyListSimpleCursorAdapter adaptador;//adaptador para el listview
	ListView listview;//referencia al listview
	public static final int RESULT_GRAMMAR_TYPE = 4;

	private OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        // Do something in response to the click
	    	Intent intent = getIntent();
	    	Cursor renglonContent = (Cursor) parent.getItemAtPosition(position);
	    	int idTipo = renglonContent.getInt(0);
	    	idh.setSelectedGramar(idTipo);
	    	System.out.println(renglonContent.getInt(0));//columna1 contiene l nombre de la imagen
	    	intent.putExtra("idTipo", idTipo);
			act.setResult(RESULT_GRAMMAR_TYPE, intent);
			idh.close();
			finish();
	    
	    }
	};
	
	
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_category);
		
		//voy preparando la BD
			idh = new DatabaseHelper(this);
			idh.open();
			categoriaResult = idh.getGrammarTypes();
			act.startManagingCursor(categoriaResult);
			 
			//elemento2 es nombre imagen, elemento1 es la cadena de la imagen, 7 es id
			String[] from = new String[] {DatabaseHelper.ELEMENTO_1,DatabaseHelper.ELEMENTO_7 };
			//referencio a cada renglon
			int[] to = new int[] {R.id.category_list_name,0 };
			//formo mi adaptador
			adaptador = new MyListSimpleCursorAdapter(act,R.layout.category_list_item,categoriaResult,from,to);
			listview = (ListView) findViewById(R.id.listaCat);
			listview.setAdapter(adaptador);
			listview.setOnItemClickListener(mMessageClickedHandler); 
	}
	
	public void cancelar(View view) {
		this.setResult(RESULT_CANCELED);
		finish();
	}

}
