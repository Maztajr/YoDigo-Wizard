package yo.digo.yodigo_wizard;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TextView;


/*
 * Esta actividad contiende las pestanas de agregar elemento y categoria
 */
@SuppressWarnings("deprecation")
public class AgregaActivity extends TabActivity{
	public static final int TIME_ENTRY_REQUEST_CODE = 1;
	public static final String CADENA_KEY = "CADENA_KEY";
	public static final String IMAGE_KEY = "IMAGE_KEY";
	public static final String CATEGORYFA_KEY = "CATEGORYFA_KEY";
	public static final String CATEGORY_KEY = "CATEGORY_KEY";
	public static final String CLAS_KEY = "CLAS_KEY";
	public static final String CAT_ID_KEY = "CAT_ID_KEY";
	public static final String IMAGEN_NAME_KEY = "IMAGEN_NAME_KEY";
	public static final String CATEGORIA_KEY = "CATEGORIA_KEY";
	public static final String CATEGORIA_IMAGEN_NAME_KEY = "CATEGORIA_IMAGEN_NAME_KEY";
	
	/*
	 * Se configura el contenido de cada pestana ademas de la forma en que se
	 * ven los nombres de cada pestana y en caso de versiones pasadas al 4.2 se agrega 
	 * una imagen representativa de cada pestana
	 */
	public void onCreate(Bundle savedInstanceState) {
		String mensaje = getIntent().getExtras().getString(MainActivity.CADENA_KEY);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adding_tabs);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		Resources res = getResources();
	
		intent = new Intent().setClass(this,AgregaElementoTab.class);
		intent.putExtra(CADENA_KEY, mensaje);//paso la cadena dle mensaje por el intent
		spec = tabHost.newTabSpec("Tab1").setIndicator("Elemento",res.getDrawable(R.drawable.digo)).setContent(intent);//elemento
		tabHost.addTab(spec);
		
		intent = new Intent().setClass(this,AgregaCategoriaTab.class);
		intent.putExtra(CADENA_KEY, mensaje);
		spec = tabHost.newTabSpec("Tab2").setIndicator("Categoria",res.getDrawable(R.drawable.categoria)).setContent(intent);//categoria
		
		tabHost.addTab(spec);
		
		TextView t1 = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
		TextView t2 = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
		Typeface font = Typeface.createFromAsset(getAssets(), "JandaManatee.ttf");
		
		t1.setTypeface(font);
		t2.setTypeface(font);
		t1.setTextColor(Color.WHITE);
		t2.setTextColor(Color.WHITE);
		
		
		
		
	}

}
