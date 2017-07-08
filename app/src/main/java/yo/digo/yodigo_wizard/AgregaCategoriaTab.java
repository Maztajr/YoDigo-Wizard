package yo.digo.yodigo_wizard;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

//import android.widget.RadioGroup;

/**
 * Clase para agregar categoria/elemento nuevo creado a alguna otra categoria
 
 * 
 */
public class AgregaCategoriaTab extends Activity{
	
	Activity act = this;
	ImageButton audio;
	public static String nombreDeImagen;
	public static final int TIME_ENTRY_REQUEST_CODE = 1;//se manda a la actividad buscar imagen
	public static final String IMAGEN_NAME_KEY = "IMAGEN_NAME_KEY";
	public static final String CATEGORIA_KEY = "CATEGORIA_KEY";
	public static final String CATEGORIA_IMAGEN_NAME_KEY = "CATEGORIA_IMAGEN_NAME_KEY";
	Intent datos;//intent para guardar los datos a pasarle a la actividad dinamic layout
	String imageName;//guardo el nombre d ela imagen a utilizar, globalmente
	int idCategoria = 1;//le puse directamente el id que `Ayuda` tiene en la BD
	static int flag=0;//seleccion de imagen
	int catCount = 0;
	
	RadioGroup radioGroup;
	RadioButton option1, option2, option3;//son las opciones del RadioGroup
	EditText text;// = (EditText) findViewById(R.id.texto);//campo de texto donde se deposita la cadena proveniente dela actividad anterior
	ImageView selectImage; //icono que muestra la seleccion de imagen
	TextView t1;
	String rutaImagen;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		//imageName="hola";
		datos = getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agregar_categoria);
		text = (EditText) findViewById(R.id.texto);
		t1 = (TextView) findViewById(R.id.textView1);
		t1.setTypeface(Typeface.createFromAsset(getAssets(),"JandaManatee.ttf"));
		//dato de la imagen por defecto que se pone
		datos.putExtra(MainActivity.IMAGE_KEY, "hola" );
		
		audio = (ImageButton) findViewById(R.id.imageButton2);
		audio.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	hablar(v,text.getText().toString());
	            }
	        });
		
		

		//en el key se pasa la cadena de la actividad anterior, que es la cadena de los elementos que quieren
		//agregarse
		try { //try catch para obtener la cadena de la actividad anterior
			System.out.println("AQUI ENTRO AL CATCH PARA OBTENER LA CADENA");
			if(getIntent().getExtras().getString(MainActivity.CADENA_KEY) != null){
				text.setText(getIntent().getExtras().getString(MainActivity.CADENA_KEY));
				
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		//option1.requestFocus();
	}
	
	public void cancelar(View view) {
		this.setResult(RESULT_CANCELED);
		finish();
	}
	
	//metodo para redimensionar imagen, para que se vea bien en el radiobutton
	public Drawable modificarTamImagen(int idImagen,float newWidth, float newHeigth){
		Bitmap mBitmap = BitmapFactory.decodeResource(this.getResources(),idImagen);
		//Redimensionamos
	   int width = mBitmap.getWidth();
	   int height = mBitmap.getHeight();
	   float scaleWidth = (newWidth) / width;
	   float scaleHeight = (newHeigth) / height;
	   // crea una matriz para manipulacion
	   Matrix matrix = new Matrix();
	   // cambia de tamaño al bitmap
	   matrix.postScale(scaleWidth, scaleHeight);
	   Bitmap salida = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
	   Drawable d =new BitmapDrawable(this.getResources(),salida);
	   return d;
	}
	
	/**
	 * En este metodo se guardan todos los parametros que eligio en esta actividad y se guardan en lo de put extra
	 * para pasarse al MainActivity y puedan guardarse desde ahi el nuevo elemento.
	 * @param view
	 */
	
	
	//3 metodos para cuando se hace click en un radiobutton, marcar la seleccion con una flechita
	public void clickOpcion1(View view){
		int idCheck =  this.getResources().getIdentifier("check", "drawable", "yo.digo.yodigo_wizard");//tomo el id de la iamgen de la flechita
		option1.setCompoundDrawablesWithIntrinsicBounds(0, 0, idCheck, 0);
		option1.setCompoundDrawablePadding(20);
		option2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		option3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		idCategoria = obtenerIdCategoria((String) option1.getText());
	}
	public void clickOpcion2(View view){
		int idCheck =  this.getResources().getIdentifier("check", "drawable", "yo.digo.yodigo_wizard");//tomo el id de la iamgen de la flechita
		option1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		option2.setCompoundDrawablesWithIntrinsicBounds(0, 0,idCheck, 0);
		option2.setCompoundDrawablePadding(20);
		option3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		idCategoria = obtenerIdCategoria((String) option2.getText());
	}
	public void clickOpcion3(View view){
		int idCheck =  this.getResources().getIdentifier("check", "drawable", "yo.digo.yodigo_wizard");//tomo el id de la iamgen de la flechita
		option1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		option2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		option3.setCompoundDrawablesWithIntrinsicBounds(0, 0, idCheck, 0);
		option3.setCompoundDrawablePadding(20);
		idCategoria = obtenerIdCategoria((String) option3.getText());
	}
	
	//metodo que se lanza cuando seleccionar buscar categoria
	public void buscaImagen(View view){
		Intent intent = new Intent(act,BuscaImagenActivityCat.class);
		//this.startActivityForResult(intent, TIME_ENTRY_REQUEST_CODE);
		this.startActivityForResult(intent,TIME_ENTRY_REQUEST_CODE);
	}
	
	public void buscaCategoria(View view){
		Intent intent = new Intent(act,BuscaCategoriaActivity.class);
		//this.startActivityForResult(intent, TIME_ENTRY_REQUEST_CODE);
		act.startActivityForResult(intent,TIME_ENTRY_REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK){//este result code es para la actividad de escoger imagen
			
			//resultado de la actividad buscaimagen
			String nombreImagen = data.getStringExtra(IMAGEN_NAME_KEY);
			imageName = data.getStringExtra(IMAGEN_NAME_KEY);//gloobal
			nombreDeImagen=imageName;
			datos.putExtra(MainActivity.IMAGE_KEY, nombreImagen );//pongo el dato dle nombre de la imagen para el intent
			selectImage = (ImageView) findViewById(R.id.imageSelect);//hago referencia al iamgeview
			int resId = act.getResources().getIdentifier(nombreImagen, "drawable", "yo.digo.yodigo_wizard");//obtengo el id de la imagen
			selectImage.setImageResource(resId);
			
			flag=1;
			//fin de manejo de resultado de actividad buscaimagen
		}
		if (resultCode == RESULT_FIRST_USER){//este resultcode es para la actividad escoger categoria::: supuestamente ya no entrara aqui porque no hay que elegir categoria
			String nombreCategoria = data.getStringExtra(CATEGORIA_KEY);
			idCategoria = obtenerIdCategoria(nombreCategoria);//quite (String) option2.getText() y puse nombreCategoria,, igual en el renglon anterior

		}
		if (resultCode == 3){//este result code es para la actividad de escoger imagen desde la SD
			System.out.println("oviamente entra al resultcode 3");
			String nombreImagen = data.getStringExtra(IMAGEN_NAME_KEY);
			imageName = data.getStringExtra(IMAGEN_NAME_KEY);//gloobal
			rutaImagen = data.getStringExtra(IMAGEN_NAME_KEY);//gloobal
			nombreDeImagen = imageName;
			selectImage = (ImageView) findViewById(R.id.imageSelect);//hago referencia al iamgeview
			Bitmap bm = CategoryMaker.modificarTamImagen(nombreImagen,300,300);
			selectImage.setImageBitmap(bm);
			System.out.println("Aqui acaba de poner la imagen en el imageview, mientras, imageName es:"  +imageName);
			flag=1;
			
		}
		
		
	}
	public int obtenerIdCategoria(String nombreCategoria){
		DatabaseHelper idh = new DatabaseHelper(this);
		idh.open();
		Cursor id = idh.getCategoryId(nombreCategoria);
		id.moveToFirst();
		int retorno = id.getInt(0);
		idh.close();
		return retorno;
	}
	
	//
	
	@SuppressWarnings("deprecation")
	public void agregarComoCategoria(View view){
		System.out.println("nombre de la imagen: "+imageName);
		System.out.println("nombre de la imagen static: "+nombreDeImagen);
		System.out.println("bandera para categoria es: "+flag);
		if(flag == 1) imageName=nombreDeImagen;
		else imageName = "hola";
		flag=0;
		System.out.println("nombre de la imagen despues de: "+imageName);
		int ban = 0;
		DatabaseHelper idh = new DatabaseHelper(this);
		idh.open();
		Cursor c = idh.getElemento();
        startManagingCursor(c);
        c.moveToFirst();
        do {
        	String cad = c.getString(1);
        	int catOrElement = c.getInt(3);
        	System.out.println("catOrElem " + catOrElement);
        	System.out.println(cad);
        	if(cad.equals(text.getText().toString()))
        		ban=1;
        	if(catOrElement == 1){
        		catCount++;
        	}
        } while (c.moveToNext());
        idh.close();

        if(ban==0) {
			datos.putExtra(MainActivity.CADENA_KEY, text.getText().toString());
			datos.putExtra(MainActivity.IMAGE_KEY, imageName);
			if(rutaImagen != null){
				datos.putExtra(MainActivity.IMAGE_PATH,rutaImagen );
			}			
			if(getParent()==null) {
				if(rutaImagen == null) setResult(RESULT_FIRST_USER,datos);//validacion que se ocupa hacer, sino me manda un result code de cancelacion
				else setResult(3,datos);//2 es codigo para cuando se selecciono una imagen de la SD
			}
			else {
				if(rutaImagen == null)getParent().setResult(RESULT_FIRST_USER,datos);//validacion que se ocupa hacer, sino me manda un result code de cancelacion
				else getParent().setResult(3,datos);//3 es codigo para cuando se selecciono una imagen de la SD, cuando kieres crear una categoria
				
			}
			
			finish();
        } else {
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Categoría repetida")
        	        .setCancelable(false)
        	        .setTitle("ERROR")
        	        .setIcon(R.drawable.error)
        	        
        	        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
        	            public void onClick(DialogInterface dialog, int id) {
        	                 dialog.cancel();
        	            }
        	        });
        	 AlertDialog alert = builder.create();
        	 alert.show();
        	 WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

        	 lp.copyFrom(alert.getWindow().getAttributes());
        	 lp.width = 250;
        	 lp.height = 500;
        	 lp.y=100;
        	 alert.getWindow().setAttributes(lp);
        }
		
	}
	
	
public void hablar(View V, String mensaje){
		
		ModuloVoz moduloVoz = new ModuloVoz(getApplicationContext(),mensaje);	
		moduloVoz.vozAdulto(); //si el primer radiobutton (que representa al adulto) esta activado, se habla con el tono por defecto (1)
	}
	

}
