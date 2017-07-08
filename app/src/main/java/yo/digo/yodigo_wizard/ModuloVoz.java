package yo.digo.yodigo_wizard;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

public class ModuloVoz implements OnInitListener 
{
	TextToSpeech _tts;
	boolean _ttsActive;
	String s;
	
	public ModuloVoz(Context _contexto, String s)// esta variable contexto ira en ves del parametro getApplicationContext()
	{
		_tts = new TextToSpeech(_contexto, this); 
		this.s=s;
	}
	
	public void vozAdulto()//metodo para cambiar la voz a voz de Adulto (tono por defecto)
	{
		_tts.setPitch(1);
	}
	
	public void vozNino()//metodo para cambiar la voz a voz de Ni�a (tono por defecto)
	{
		_tts.setPitch(50);
	}
	
	public void detenerMotor()//Metodo usado para detener el motor cuando nos salimos de la aplicacion
	{
		try
        { 
			//Para de hablar si la aplicacion se quita de la pantalla
		    if (_tts != null)
		    { 
	            _tts.stop(); 
	            _ttsActive = false; 
            } 
        }catch(Exception e){} 
	}
	
	public void hablar( String mensaje )//metodo que reproduce un mensaje  en formato auditivo
	{
		_tts.speak(mensaje, TextToSpeech.QUEUE_FLUSH, null);
	}
	
	public void cerrarMotor()// metodo asociado con el onDestroy() de la actividad que use este modulo.
	{
		try
	    { 
	        // Cuando la aplicacion ya no esta en uso, cerramos el motor tts porque consume recursos 
	        if (_tts != null)
	        { 
	            _tts.shutdown(); 
	            _tts = null; 
	        } 
	    } 
	    catch(Exception e){} 
	}

	public void onInit(int arg0)//onInit es un metodo de la interfaz implementada, sirve para inicializar el motor tts
	{ 
	    //Si la inicializacion fue exitosa, activamos una bandera que indica que podemos usar el tts
	    if (arg0 == TextToSpeech.SUCCESS)
	    { 
	        _ttsActive = true; 
	        hablar(s);//Se se�ala cuando el dispositivo se inicializo correctamente
	    } 
	}

}