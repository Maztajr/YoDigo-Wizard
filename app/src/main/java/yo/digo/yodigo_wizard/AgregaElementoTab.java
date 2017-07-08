package yo.digo.yodigo_wizard;


import android.app.Activity;
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
public class AgregaElementoTab extends Activity{
	
	Activity act = this;
	ImageButton audio;
	
	public static int idDeCategoria;
	static int flagCat=0;//bandera para categoria
	
	public static String nombreDeImagen;
	static int flag=0;//bandera para imagen
	
	public static final int TIME_ENTRY_REQUEST_CODE = 1;//se manda a la actividad buscar imagen
	public static final String IMAGEN_NAME_KEY = "IMAGEN_NAME_KEY";
	
	public static final String CATEGORIA_KEY = "CATEGORIA_KEY";
	public static final String CATEGORIA_IMAGEN_NAME_KEY = "CATEGORIA_IMAGEN_NAME_KEY";
	Intent datos;//intent para guardar los datos a pasarle a la actividad dinamic layout
	String imageName;//guardo el nombre d ela imagen a utilizar, globalmente
	String rutaImagen = null;
	int idCategoria = 1;//le puse directamente el id que `Deseos` tiene en la BD
	int idTipoPalabra = 1;//default de pronombre personal
	
	RadioGroup radioGroup;
	RadioButton option1, option2, option3;//son las opciones del RadioGroup
	EditText text;// = (EditText) findViewById(R.id.texto);//campo de texto donde se deposita la cadena proveniente dela actividad anterior
	TextView t1, t2, t3;
	ImageView selectImage; //icono que muestra la seleccion de imagen
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		
		
		imageName="hola";
		datos = getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agrega_elemento);
		text = (EditText) findViewById(R.id.texto);
		t1 = (TextView) findViewById(R.id.textView1);
		t1.setTypeface(Typeface.createFromAsset(getAssets(),"JandaManatee.ttf"));
		t2 = (TextView) findViewById(R.id.textView3);
		t2.setTypeface(Typeface.createFromAsset(getAssets(),"JandaManatee.ttf"));
		t3 = (TextView) findViewById(R.id.tipoPalabraTxt);
		t3.setTypeface(Typeface.createFromAsset(getAssets(),"JandaManatee.ttf"));
		//dato de la imagen por defecto que se pone
		datos.putExtra(MainActivity.IMAGE_KEY, "hola" );
		
		audio = (ImageButton) findViewById(R.id.imageButton2);
		audio.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	hablar(v,text.getText().toString());
	            }
	        });
	
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
	   // crea el nuevo bitmap
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
		//datos.putExtra(MainActivity.CATEGORY_KEY, obtenerIdCategoria((String) option1.getText()) );//hacer metodo que obtenga id de categoria
	}
	public void clickOpcion2(View view){
		int idCheck =  this.getResources().getIdentifier("check", "drawable", "yo.digo.yodigo_wizard");//tomo el id de la iamgen de la flechita
		option1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		option2.setCompoundDrawablesWithIntrinsicBounds(0, 0,idCheck, 0);
		option2.setCompoundDrawablePadding(20);
		option3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		idCategoria = obtenerIdCategoria((String) option2.getText());
		//datos.putExtra(MainActivity.CATEGORY_KEY, obtenerIdCategoria((String) option2.getText()) );//hacer metodo que obtenga id de categoria
	}
	public void clickOpcion3(View view){
		int idCheck =  this.getResources().getIdentifier("check", "drawable", "yo.digo.yodigo_wizard");//tomo el id de la iamgen de la flechita
		option1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		option2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		option3.setCompoundDrawablesWithIntrinsicBounds(0, 0, idCheck, 0);
		option3.setCompoundDrawablePadding(20);
		idCategoria = obtenerIdCategoria((String) option3.getText());
		//datos.putExtra(MainActivity.CATEGORY_KEY, obtenerIdCategoria((String) option3.getText()) );//hacer metodo que obtenga id de categoria
	}
	
	//metodo que se lanza cuando seleccionar buscar categoria
	public void buscaImagen(View view){
		Intent intent = new Intent(act,BuscaImagenActivityElem.class);
		this.startActivityForResult(intent,TIME_ENTRY_REQUEST_CODE);
	}
	
	public void buscaCategoria(View view){
		Intent intent = new Intent(act,BuscaCategoriaActivity.class);
		act.startActivityForResult(intent,TIME_ENTRY_REQUEST_CODE);
	}
	
	public void buscaTipoPalabra(View view){
		Intent intent = new Intent(act,BuscaTipoDePalabraActivity.class);
		act.startActivityForResult(intent,TIME_ENTRY_REQUEST_CODE);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			ImageView botonCategoria = (ImageView) findViewById(R.id.imageButton1);//instancia de Imagebutton para ponerle la imagen de la cate seleccionada
			TextView etiquetaNombreCategoria = (TextView) findViewById(R.id.textView3);//instancia de texxtview para ponerle el nombre de la categoria seleccionada
		if (resultCode == RESULT_OK){//este result code es para la actividad de escoger imagen
			
			//resultado de la actividad buscaimagen
			String nombreImagen = data.getStringExtra(IMAGEN_NAME_KEY);
			imageName = data.getStringExtra(IMAGEN_NAME_KEY);//gloobal
			nombreDeImagen=imageName;
			datos.putExtra(MainActivity.IMAGE_KEY, nombreImagen );//pongo el dato dle nombre de la imagen para el intent
			selectImage = (ImageView) findViewById(R.id.imageSelect);//hago referencia al iamgeview
			int resId = act.getResources().getIdentifier(nombreImagen, "drawable", "yo.digo.yodigo_wizard");//obtengo el id de la imagen
			selectImage.setImageResource(resId);
			//fin de manejo de resultado de actividad buscaimagen
			flag=1;
		}
		if (resultCode == RESULT_FIRST_USER){//este resultcode es para la actividad escoger categoria
			String nombreCategoria = data.getStringExtra(CATEGORIA_KEY);
			etiquetaNombreCategoria.setText(nombreCategoria);//pongo el nombre de la cate seleccionada
			//configuro el radiobutton segun seleccion de categoria
			String nombreImagenCategoria = data.getStringExtra(CATEGORIA_IMAGEN_NAME_KEY);
			if(nombreImagenCategoria.indexOf('/')==-1){
				int idImagenCategoria = this.getResources().getIdentifier(nombreImagenCategoria, "drawable", "yo.digo.yodigo_wizard");// del nombre de la imagen obtengo su id
				Drawable icono = modificarTamImagen(idImagenCategoria,70,70);//poner un imageview con este idImagenCategoria?
				botonCategoria.setBackgroundDrawable(icono);// pongo la imagen de la cate seleccionada
			}else{
				Bitmap bm = BitmapFactory.decodeFile(nombreImagenCategoria);
		        botonCategoria.setImageBitmap(bm);
			}
			idCategoria = obtenerIdCategoria(nombreCategoria);//quite (String) option2.getText() y puse nombreCategoria,, igual en el renglon anterior
			System.out.println("estoy en el result de escoger categoria y el id es" + idCategoria);
			idDeCategoria = idCategoria;
			System.out.println("en el resultado de categoria antes del if, el id de categoria es: "+idCategoria);
			flagCat=1;
			
			//clickOpcion2(option2);
		}
		if (resultCode == 2){//este result code es para la actividad de escoger imagen desde la SD
			
			String nombreImagen = data.getStringExtra(IMAGEN_NAME_KEY);
			System.out.println("entro a result 2 y el nombre de imagen es ");
			imageName = data.getStringExtra(IMAGEN_NAME_KEY);//gloobal
			nombreDeImagen = imageName;
			System.out.println("entro a result 2 y el nombre de imagen es "+imageName);
			rutaImagen = data.getStringExtra(IMAGEN_NAME_KEY);//gloobal
			datos.putExtra(MainActivity.IMAGE_KEY, nombreImagen );//pongo el dato dle nombre de la imagen para el intent
			datos.putExtra(MainActivity.IMAGE_PATH, rutaImagen );
			selectImage = (ImageView) findViewById(R.id.imageSelect);//hago referencia al iamgeview
			Bitmap bm = CategoryMaker.modificarTamImagen(nombreImagen,300,300);
			selectImage.setImageBitmap(bm);
			flag =1;
			
		}
		if(resultCode == 3){//eligio el tipo de palabra
			t3.setText(data.getStringExtra("cadena"));
			idTipoPalabra = data.getIntExtra("idTipo",-1);
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
	
	
	public void agregarComoElemento(View view){
		if(flag == 1) {
			imageName=nombreDeImagen; 
			System.out.println("voy al main y el nombre d eimagen es "+imageName);
			flag = 0;
		} else {
			imageName = "hola";
			flag=0;
		}
		if(flagCat==1) {
			idCategoria = idDeCategoria; 
			System.out.println("voy al main y el id de categoria es "+idCategoria);
		} else {
			idCategoria = 0;
			flagCat=0;
		}
		datos.putExtra(MainActivity.CATEGORYFA_KEY, 0 );
		datos.putExtra(MainActivity.CADENA_KEY, text.getText().toString());
		datos.putExtra(MainActivity.CLAS_KEY, idTipoPalabra);
		datos.putExtra(MainActivity.CATEGORY_KEY, idCategoria-1);
		System.out.println("elemento categoria  es:"+idCategoria);
		datos.putExtra(MainActivity.IMAGE_KEY, imageName);
		System.out.println("elemento nombre de imagen es:"+imageName);
		if(rutaImagen != null) datos.putExtra(MainActivity.IMAGE_PATH,rutaImagen );
		
		

		//setResult(RESULT_OK, datos); 
		System.out.println("SEGUN AKI LE MANDO UN RESULT_OK");
		if(getParent()==null) {
			if(rutaImagen == null) setResult(RESULT_OK,datos);//validacion que se ocupa hacer, sino me manda un result code de cancelacion
			else setResult(2,datos);//2 es codigo para cuando se selecciono una imagen de la SD
		}
		else {
			if(rutaImagen == null)getParent().setResult(RESULT_OK,datos);//validacion que se ocupa hacer, sino me manda un result code de cancelacion
			else getParent().setResult(2,datos);//2 es codigo para cuando se selecciono una imagen de la SD, cuando kieres crear un elemento
				
			}
			finish();
	}
	
	public void hablar(View V, String mensaje){
		
		ModuloVoz moduloVoz = new ModuloVoz(getApplicationContext(),mensaje);	
		moduloVoz.vozAdulto(); //si el primer radiobutton (que representa al adulto) esta activado, se habla con el tono por defecto (1)
	}
	

}
