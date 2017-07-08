package yo.digo.yodigo_wizard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper {
	
	public static final int DATABASE_VERSION = 16;
	public static final String DATABASE_NAME="yodigo.db";  
	public static final String ELEMENTO_1="cadena"; 
	public static final String ELEMENTO_2="imagen";
	public static final String ELEMENTO_3="categoria_padre"; 
	public static final String ELEMENTO_4="categoria";
	public static final String ELEMENTO_5="clasificacion"; 
	public static final String ELEMENTO_6="nivel";
	public static final String ELEMENTO_7="_id";
	public static final String ELEMENTO_8="imagen_nombre";
	public static final String ELEMENTO_9="ruta";
	
	public static final int PRONOMBRE_PERSONAL = 1;
	public static final int ARTICULO = 2;
	public static final int ADJETIVO_POSESIV0 = 3;
	public static final int PRONOMBRE_REFLEXIVO = 4;
	public static final int VERBO_CONJUGADO = 5;
	public static final int VERBO_INFINITIVO = 6;
	public static final int PREPOSICION = 7;
	public static final int ADVERBIO = 8;
	public static final int SUSTANTIVO = 9;
	public static final int ADJETIVO_CALIFICATIVO = 10;
	public static final int EXPRESION = 11;
	
	private SQLiteDatabase database;
	private OpenHelper openHelper;
	
	DatabaseHelper(Context context){
		openHelper = new OpenHelper(context);

		database = openHelper.getWritableDatabase();
		database = openHelper.getReadableDatabase();	 
	} 
	
	public class OpenHelper extends SQLiteOpenHelper{	
		
		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
					
		@Override
		public void onCreate(SQLiteDatabase database) {
			
			database.execSQL("create table imagen " + 
					"(_id integer primary key autoincrement, imagen_nombre text, ruta text)");		
			database.execSQL("create table elemento " + 
					"(_id integer primary key autoincrement, cadena text, imagen text, categoria integer, nivel integer, " +
					"clasificacion integer, FOREIGN KEY(categoria) REFERENCES categoria(_id), FOREIGN KEY(clasificacion) REFERENCES tipo(_id))");
			database.execSQL("create table categoria " + 
					"(_id integer primary key autoincrement, cadena text, imagen text )");
			database.execSQL("create table frase " + 
			"(_id integer primary key autoincrement, hora_fecha text, id_elemento integer, frase integer, " +
			"FOREIGN KEY(id_elemento) REFERENCES elemento(_id))");
			database.execSQL("create table tipo " + 
					"(_id integer primary key autoincrement, cadena text)");
			database.execSQL("create table gramatica " + 
					"(_id integer primary key autoincrement, cadena text)");
			database.execSQL("create table gramatica_seleccionada " + 
					"(_id integer primary key autoincrement, tipo integer)");
			
			
			database.execSQL("INSERT INTO gramatica_seleccionada (tipo) VALUES ('0')");
			
			/*SECCION TIPO DE PALABRAS*/
			database.execSQL("INSERT INTO tipo (cadena) VALUES ('Pronombre personal')");
			database.execSQL("INSERT INTO tipo (cadena) VALUES ('Artículo')");
			database.execSQL("INSERT INTO tipo (cadena) VALUES ('Adjetivo posesivo')");
			database.execSQL("INSERT INTO tipo (cadena) VALUES ('Pronombre reflexivo')");
			database.execSQL("INSERT INTO tipo (cadena) VALUES ('Verbo conjugado')");
			database.execSQL("INSERT INTO tipo (cadena) VALUES ('Verbo infinitivo')");
			database.execSQL("INSERT INTO tipo (cadena) VALUES ('Preposición')");
			database.execSQL("INSERT INTO tipo (cadena) VALUES ('Adverbio')");
			database.execSQL("INSERT INTO tipo (cadena) VALUES ('Sustantivo')");
			database.execSQL("INSERT INTO tipo (cadena) VALUES ('Adjetivo calificativo')");
			database.execSQL("INSERT INTO tipo (cadena) VALUES ('Expresión')");
			
			/*SECCION TIPO DE PALABRAS*/
			database.execSQL("INSERT INTO gramatica (cadena) VALUES ('Sin Gramatica')");
			database.execSQL("INSERT INTO gramatica (cadena) VALUES ('Gramatica 1')");
			database.execSQL("INSERT INTO gramatica (cadena) VALUES ('Gramatica 2')");
			database.execSQL("INSERT INTO gramatica (cadena) VALUES ('Gramatica 3')");
			database.execSQL("INSERT INTO gramatica (cadena) VALUES ('Gramatica 4')");
			database.execSQL("INSERT INTO gramatica (cadena) VALUES ('Gramatica 5')");
			database.execSQL("INSERT INTO gramatica (cadena) VALUES ('Gramatica 6')");
			database.execSQL("INSERT INTO gramatica (cadena) VALUES ('Gramatica 7')");
			database.execSQL("INSERT INTO gramatica (cadena) VALUES ('Gramatica 8')");
					
			/*SECCION DE IMAGENES*/
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('feel','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('el','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('tu','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('ir','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('can_i_have','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('do_not_want','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('i_dont_know','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('i_hear','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('yo_quiero','nulo')");
			//Basico
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('mellamo1','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('micumpleanos1','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('midireccion1','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('mitelefono1','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('yosoy1','nulo')");
			//categorias
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('comybeb','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('lugares','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('ropa','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('sentimientos','nulo')");
			//categorias-comidas y bebidas
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('agua','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('cafe','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('carne','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('chocolate','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('comida','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('cupcake','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('donas','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('dulces','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('hamburguesa','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('hotdog','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('huevo','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('jugonaranja','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('leche','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('pancakes','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('papas','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('papasfritas','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('pastel','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('pizza','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('pollo','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('refresco','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('sand','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('sushi','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('taco','nulo')");
			//categorias-lugares
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('casa','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('cine','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('escuela','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('hospital','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('iglesia','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('parque','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('restaurante','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('tienda','nulo')");
			//categorias-ropa
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('abrigo','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('blusa','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('bufanda','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('calcetines','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('camisa','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('cinturon','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('corbata','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('falda','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('gorra','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('gorro','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('pantalon','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('pijama','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('playera','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('ropainterior','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('sandalias','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('shor','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('sweater','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('zapatillas','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('zapatos','nulo')");
			//categorias-Sentimientos
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('aburrido','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('asustado','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('caluroso','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('cansado','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('confundido','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('enfermo','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('enojado','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('feliz','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('frio','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('hambriento','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('herido','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('sediento','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('sorprendido1','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('triste','nulo')");
			//yo quiero
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('algodivertido','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('comida2','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('escucharmusica','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('jugar','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('unabebida','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('usarcomputadora','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('vertelevision','nulo')");
			//yo necesito
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('comida3','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('dormir','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('unabebida1','nulo')");
			//comentarios
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('bien','nulo')");
			
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('genial','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('mas','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('megusta','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('no','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('nomegusta','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('oops','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('si','nulo')");
			//ayuda
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('bano','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('me_duele','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('necesito_ayuda','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('tarea','nulo')");
			//Hola adios
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('adios','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('buenasnoches','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('buenosdias','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('comoestas','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('hastaluego','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('hola','nulo')");
			//preguntas
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('como','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('cuando','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('cuantoes','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('donde','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('porque','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('que','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('queeseso','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('quehoraes','nulo')");
			database.execSQL("INSERT INTO imagen (imagen_nombre, ruta) VALUES ('quien','nulo')");

			/*SECCION DE ELEMENTOS*/
 
			/*Categorías principales  189 -356*/
			database.execSQL("INSERT INTO categoria (cadena, imagen) VALUES ('Deseos','yo_quiero')"); //0
			database.execSQL("INSERT INTO categoria (cadena, imagen) VALUES ('Acciones','feel')");//1
			database.execSQL("INSERT INTO categoria (cadena, imagen) VALUES ('Pronombres','quien')");//2
			database.execSQL("INSERT INTO categoria (cadena, imagen) VALUES ('Comidas y Bebidas','comybeb')");//3
			database.execSQL("INSERT INTO categoria (cadena, imagen) VALUES ('Lugares','lugares')"); //4
			database.execSQL("INSERT INTO categoria (cadena, imagen) VALUES ('Ropa','ropa')"); //5
			database.execSQL("INSERT INTO categoria (cadena, imagen) VALUES ('Sentimientos','sentimientos')"); //6
			database.execSQL("INSERT INTO categoria (cadena, imagen) VALUES ('Frases','i_dont_know')"); //7
			database.execSQL("INSERT INTO categoria (cadena, imagen) VALUES ('Expresiones','sorprendido1')");//8 
			database.execSQL("INSERT INTO categoria (cadena, imagen) VALUES ('Saludos','hola')"); //9
			database.execSQL("INSERT INTO categoria (cadena, imagen) VALUES ('Preguntas','i_dont_know')");//10
						
			//1
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('soy','yosoy1',0,"+VERBO_CONJUGADO+")"); 
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('estoy','donde',0,"+VERBO_CONJUGADO+")"); 
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('quiero','megusta',0,"+VERBO_CONJUGADO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('necesito','megusta',0,"+VERBO_CONJUGADO+")");

			//2
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('ir','ir',1,"+VERBO_INFINITIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('beber','unabebida',1,"+VERBO_INFINITIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('comer','comida2',1,"+VERBO_INFINITIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('jugar','jugar',1,"+VERBO_INFINITIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('dormir','dormir',1,"+VERBO_INFINITIVO+")");
			
			//3
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Yo','yosoy1',2,"+PRONOMBRE_PERSONAL+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Tu','tu',2,"+PRONOMBRE_PERSONAL+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('El','el',2,"+PRONOMBRE_PERSONAL+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Ella','el',2,"+PRONOMBRE_PERSONAL+")");
			
			//4
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('agua','agua',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('café','cafe',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('carne','carne',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('chocolate','chocolate',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('comida','comida',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('pastelito','cupcake',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('donas','donas',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('dulces','dulces',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('hamburguesa','hamburguesa',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('hotdog','hotdog',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('huevo','huevo',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('jugo de naranja','jugonaranja',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('leche','leche',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('panqués','pancakes',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('papas','papas',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('papas fritas','papasfritas',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('pastel','pastel',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('pizza','pizza',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('pollo','pollo',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('refresco','refresco',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('emparedado','sand',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('sushi','sushi',3,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('tacos','taco',3,"+SUSTANTIVO+")");


			//5
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('casa','casa',4,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('cine','cine',4,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('escuela','escuela',4,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('hospital','hospital',4,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('iglesia','iglesia',4,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('parque','parque',4,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('restaurante','restaurante',4,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('tienda','tienda',4,"+SUSTANTIVO+")");



			//6
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('abrigo','abrigo',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('blusa','blusa',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('bufanda','bufanda',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('calcetines','calcetines',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('camisa','camisa',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('cinturon','cinturon',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('corbata','corbata',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('falda','falda',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('gorra','gorra',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('gorro','gorro',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('pantalon','pantalon',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('pijama','pijama',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('playera','playera',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('ropa interior','ropainterior',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('sandalias','sandalias',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('short','shor',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('suéter','sweater',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('zapatillas','zapatillas',5,"+SUSTANTIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('zapatos','zapatos',5,"+SUSTANTIVO+")");


			//7
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('aburrido','aburrido',6,"+ADJETIVO_CALIFICATIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('asustado','asustado',6,"+ADJETIVO_CALIFICATIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('caluroso','caluroso',6,"+ADJETIVO_CALIFICATIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('cansado','cansado',6,"+ADJETIVO_CALIFICATIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('confundido','confundido',6,"+ADJETIVO_CALIFICATIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('enfermo','enfermo',6,"+ADJETIVO_CALIFICATIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('enojado','enojado',6,"+ADJETIVO_CALIFICATIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('feliz','feliz',6,"+ADJETIVO_CALIFICATIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('frio','frio',6,"+ADJETIVO_CALIFICATIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('hambriento','hambriento',6,"+ADJETIVO_CALIFICATIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('herido','herido',6,"+ADJETIVO_CALIFICATIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('sorprendido','sorprendido1',6,"+ADJETIVO_CALIFICATIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('sediento','sediento',6,"+ADJETIVO_CALIFICATIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('triste','triste',6,"+ADJETIVO_CALIFICATIVO+")");

			//8
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('hacer algo divertido','algodivertido',7,"+VERBO_INFINITIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('escuchar música','escucharmusica',7,"+VERBO_INFINITIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('usar computadora','usarcomputadora',7,"+VERBO_INFINITIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('ver televisión','vertelevision',7,"+VERBO_INFINITIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('ir al baño','bano',7,"+VERBO_INFINITIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('ayúdame con la tarea','tarea',7,"+VERBO_CONJUGADO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('necesito ayuda','necesito_ayuda',7,"+VERBO_CONJUGADO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('estoy cansado','cansado',7,"+VERBO_CONJUGADO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('estoy enfermo','enfermo',7,"+VERBO_CONJUGADO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('me duele algo','me_duele',7,"+PRONOMBRE_REFLEXIVO+")");

			//9
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Bien','bien',8,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Me gusta','megusta',8,"+PRONOMBRE_REFLEXIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('No me gusta','nomegusta',8,4"+PRONOMBRE_REFLEXIVO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Esta Bien','estabien',8,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Genial','genial',8,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Más','mas',8,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('No','no',8,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Uups','oops',8,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Si','si',8,"+ADVERBIO+")");


			//10
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Hola','hola',9,"+EXPRESION+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Adiós','adios',9,"+EXPRESION+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Buenos días','buenosdias',9,"+EXPRESION+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Buenas noches','buenasnoches',9,"+EXPRESION+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('¿Cómo estas?','comoestas',9,"+EXPRESION+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('Hasta luego','hastaluego',9,"+EXPRESION+")");

			//11
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('¿Cómo?','como',10,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('¿Cuándo?','cuando',10,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('¿Cuánto es?','cuantoes',10,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('¿Dónde?','donde',10,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('¿Porqué?','porque',10,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('¿Qué?','que',10,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('¿Qué es eso?','queeseso',10,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('¿Quién?','quien',10,"+ADVERBIO+")");
			database.execSQL("INSERT INTO elemento (cadena, imagen, categoria, clasificacion) VALUES ('¿Qué hora es?','quehoraes',10,"+ADVERBIO+")");


		}
			
		@Override
		public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
			database.execSQL("DROP TABLE IF EXISTS imagen");
			database.execSQL("DROP TABLE IF EXISTS frase");
			database.execSQL("DROP TABLE IF EXISTS categoria");
			database.execSQL("DROP TABLE IF EXISTS elemento");
			database.execSQL("DROP TABLE IF EXISTS tipo");
			database.execSQL("DROP TABLE IF EXISTS gramatica");
			database.execSQL("DROP TABLE IF EXISTS gramatica_seleccionada");
			onCreate(database);
		}
	}
	
	public void saveElemento1(String cadena, String imagen, int categoria_padre,  int clasificacion,int nivel) {//sincategoria
		ContentValues contentValues = new ContentValues();
		contentValues.put("cadena",cadena);
		contentValues.put("imagen",imagen);
		contentValues.put("nivel",nivel);
		contentValues.put("clasificacion",clasificacion); //david volvio a comentar
	
		database.insert("elemento",null,contentValues);
	}
	
	public DatabaseHelper open() throws SQLException {
        openHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        openHelper.close();
    }

    public void saveImage(String imagen_nombre) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("imagen_nombre",imagen_nombre);
		contentValues.put("ruta","nulo");
		database.insert("imagen",null,contentValues);
		
	}
    
    public void saveImageSD(String imagen_nombre, String ruta) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("imagen_nombre",imagen_nombre);
		contentValues.put("ruta",ruta);
		database.insert("imagen",null,contentValues);
		
	}
	
	public void saveElemento(String cadena, String imagen, int categoria, int clasificacion) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("cadena",cadena);
		contentValues.put("imagen",imagen);
		contentValues.put("categoria",categoria);
		contentValues.put("clasificacion",clasificacion); //David lo comento
	
		database.insert("elemento",null,contentValues);
	}
	
	public void saveCategoria(String cadena, String imagen) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("cadena",cadena);
		contentValues.put("imagen",imagen);
	
		database.insert("categoria",null,contentValues);
	}
	
	public void deleteElemento(String cadena){
		String [] whereArgs = {cadena};
		database.delete("elemento", "cadena = ?", whereArgs);
	}
	
	public void deleteCategoria(String cadena){
		String [] whereArgs = {cadena};
		database.delete("categoria", "cadena = ?", whereArgs);
		
	}
	
	public int deleteAll(){
		 return database.delete("imagen", null, null);
	}
	
	public Cursor getImage() {
		return database.query(
			"imagen",
			new String[]{
				"_id",
				"imagen_nombre"
			},
			null,
			null,
			null,
				null,
				"_id" + " asc"
				);			
	}
	
	public Cursor getElemento() {
		return database.query(
			"elemento",
			new String[]{
				"_id",
				"cadena",
				"imagen",
				"clasificacion"
			},
			null,
			null,
			null,
				null,
				"_id" + " asc"
				);			
	}
	
	public Cursor getElementoCategoria(int cat) {
		return database.query(
			"elemento",
			new String[]{
				"_id",
				"cadena",
				"imagen",
				"clasificacion"
			},
			"categoria=" + cat,
			null,
			null,
				null,
				"_id" + " asc"
				);			
	}
	
	public Cursor getCategoryId(String txt) {
		return database.rawQuery("select _id from categoria where cadena ='" + txt + "'",null);
	}
	
	public Cursor getElementId(String txt) {
		return database.query(
			"elemento",
			new String[]{
				"_id",
				"cadena",
				"imagen",
				"clasificacion"
			},
			"cadena='" + txt+ "'",
			null,
			null,
				null,
				"_id" + " asc"
				);			
	}
	
	public Cursor getImageId(int pos) {
		return database.query(
				"elemento",
				new String[] {
						"_id",
						"cadena",
						"imagen"
				},
				"_id=" + pos,
				null,
				null,
				null,
				null
				);
				
	}
	
	public Cursor getSingleImageName(int id) {//query me devuelve el nombre de una imagen en especifico, segun su id
		return database.rawQuery("select imagen_nombre from imagen where id =" + id,null);
				
	}
	
	public Cursor 	getCategorias() {//query me devuelve el nombre e imagen de todas las categorias
		return database.rawQuery("select imagen,cadena,_id from categoria" ,null);
				
	}
	
	Cursor getCategorias1() {
		return database.rawQuery("select imagen,cadena,_id from categoria" ,null);
	}
	
	public Cursor getCategoriesAndImages(){//query que me devuelve las todas las categorias con su nombre d eimagen correspondiente
		return database.rawQuery("select imagen,cadena from categoria", null);
	}
	
	public Cursor getWordTypes(){
		return database.rawQuery("select _id, cadena from tipo", null);
	}
	
	public Cursor getGrammarTypes(){
		return database.rawQuery("select _id, cadena from gramatica", null);
	}
	
	public Cursor getSelectedGrammar(){
		return database.rawQuery("select tipo from gramatica_seleccionada", null);
	}
	
	public void setSelectedGramar(int tipo){
		String filter = "_id= 1";

		ContentValues contentValues = new ContentValues();
		
		contentValues.put("tipo", tipo);
		
		database.update("gramatica_seleccionada", contentValues, filter, null);
	}
	
	public Cursor getWordTypeId(String type){
		return database.rawQuery("select _id from tipo where cadena ='" + type + "'", null);
	}
}

