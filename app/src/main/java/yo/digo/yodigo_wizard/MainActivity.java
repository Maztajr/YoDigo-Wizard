package yo.digo.yodigo_wizard;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener,OnLongClickListener {
    private final static String TAG = "MainActivity";
    public static Typeface font;
    private Button btnPlay,btnGramma,btnHelp,btnDelete,btnAdd,btnWizard;
    GridView messageGrid;

    public static DatabaseHelper databaseHelper;
    MessageAdapter messageAdapter;
    DialogFragment newFragment;

    int currentWizardStep = 0;

    int accessType = 0;

    public static final int TIME_ENTRY_REQUEST_CODE = 1;
    public static final String CADENA_KEY = "CADENA_KEY";
    public static final String IMAGE_KEY = "IMAGE_KEY";
    public static final String IMAGE_PATH = "IMAGE_PATH";
    public static final String CATEGORYFA_KEY = "CATEGORYFA_KEY";
    public static final String CATEGORY_KEY = "CATEGORY_KEY";
    public static final String CLAS_KEY = "CLAS_KEY";
    public static final String CAT_ID_KEY = "CAT_ID_KEY";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se crea el tipo de fuente que se utilizara
        font = Typeface.createFromAsset(getAssets(),"JandaManatee.ttf");
        //La aplica al titulo
        TextView title = (TextView) findViewById(R.id.title);
        title.setTypeface(font);

        title.setTextColor(Color.rgb(255, 255, 255));
        title.setShadowLayer((float) 1.5,1,2,Color.rgb(0,0,0));

        iniButtons();
        initAdapter();
    }

    //METODOS INICIALIZADORES
    private void iniButtons(){
        //escuchar botones
        btnGramma = (Button)findViewById(R.id.grammar_button);
        btnPlay = (Button)findViewById(R.id.play_button);
        btnAdd= (Button)findViewById(R.id.add_button);
        btnDelete= (Button)findViewById(R.id.delete_button);
        btnHelp= (Button)findViewById(R.id.help_button);
        btnWizard = (Button)findViewById(R.id.btn_wizard);

        this.btnGramma.setOnClickListener(this);
        this.btnPlay.setOnClickListener(this);
        this.btnAdd.setOnClickListener(this);
        this.btnDelete.setOnClickListener(this);
        this.btnHelp.setOnClickListener(this);
        this.btnWizard.setOnClickListener(this);
    }

    private void initAdapter(){
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.open();

        messageAdapter = new MessageAdapter(this);
        Cursor g = databaseHelper.getSelectedGrammar();
        //startManagingCursor(g);
        g.moveToFirst();

        int tipo = g.getInt(0);

        messageAdapter.setTipoGramatica(tipo);
        messageGrid = (GridView) findViewById(R.id.messageGrid);
        messageGrid.setAdapter(messageAdapter);
    }

    /*Metodos de la clase MainActivity*/
    public void deleteCategory(){
        //databaseHelper.deleteCategoria(maker.getCategoryToEliminate());//get nameCategoryCurrent
        Toast.makeText(getApplicationContext(),"ARREGLAR: DELETE CATEGORY",Toast.LENGTH_LONG).show();
        refresh();
    }

    public void refresh(){
        /*categoryLayout.removeAllViews();
        if(categories != 0){
            categories -= catMaked;
        }*/
    }

    public void iniWizard(){
        //Desplegamos en el fragment_container el grid con todas las categorias disponibles
        //Estas categorias disponibles cambiaran segun en la parte del wizard en la que nos encontremos
        //Log.d(TAG,"Iniciando Wizard");
        CategoryGridFragment catGridFrag = new CategoryGridFragment();
        catGridFrag.setWizardStep(currentWizardStep); //valor default

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.fragment_container, catGridFrag);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void showCategoryByName(String nameCategory){
        CategoryFragment details = new CategoryFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        int id = getIdCategoryByName(nameCategory) - 1;

        Log.d(TAG,"Categoria: "+nameCategory+", ID: "+String.valueOf(id));

        Cursor datos = getDataCategory(id);  //obtenemos todos los elemtos de la categoria eledida por el ID

        details.setCategory(id);
        details.setCurrentImg(datos.getString(0)); //image
        details.setCurrentName(datos.getString(1)); //cadena
        details.setCurrentCategory(datos.getInt(2) - 1); //_id



        ft.replace(R.id.fragment_container, details);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public int getIdCategoryByName(String nameCategory){
        int id = 0;
        Cursor c = databaseHelper.getCategoryId(nameCategory);
        c.moveToFirst();
        id = c.getInt(0); //se obtiene el 1er paremtro, es decir el _id
        return id;
    }

    public Cursor getDataCategory(int position){
        Cursor c = databaseHelper.getCategorias(); //este metodo regresa IMAGEN,CADENA, ID from CATEGORIES
        c.moveToPosition(position);
        return  c;
    }

    public void incrementarWizardStep(){
        /*currentWizardStep++;
        if(currentWizardStep > 3)
            currentWizardStep = 0;*/
        currentWizardStep = (currentWizardStep + 1) % 4;
        Log.d(TAG,"Current Wizard Step: "+String.valueOf(currentWizardStep));
    }

    public void play(String s){
        ModuloVoz moduloVoz = new ModuloVoz(getApplicationContext(),s);
        moduloVoz.vozAdulto(); //si el primer radiobutton (que representa al adulto) esta activado, se habla con el tono por defecto (1)
    }

    public void messageWindow(String s, int img, int clas) {
        MessageAdapter.addElement(new Elemento(s, img, "category", clas));
        messageGrid.setAdapter(messageAdapter);
        messageGrid.requestLayout();
    }

    public void messageWindow(String s, String img, int clas) {//sobre carga para el caso en que sea una imagen de la SD
        MessageAdapter.addElement(new Elemento(s, img, "category", clas));
        messageGrid.setAdapter(messageAdapter);
        messageGrid.requestLayout();
    }

    public void eliminarElemento() {
        //databaseHelper.deleteElemento(maker.getElementToEliminate());
        Toast.makeText(getApplicationContext(),"Arreglar eliminar elemento",Toast.LENGTH_LONG).show();
        refresh();
    }

    public boolean agregar() {
        Intent intent = new Intent(this, AgregaActivity.class);

        String mensaje = MessageAdapter.getText();
        intent.putExtra(CADENA_KEY, mensaje);//paso la cadena dle mensaje por el intent

        //Intent intent = new Intent(this,AgregaActivity.class);
        startActivityForResult(intent, TIME_ENTRY_REQUEST_CODE);
        return true;
    }

    public void seleccionarGramatica(){
        Intent intent = new Intent(this,SelectGrammarActivity.class);
        startActivityForResult(intent,5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.grammar_button:
                accessType = 1;
                newFragment = new GetAccessDialog();
                newFragment.show(getSupportFragmentManager() ,"getAccess");
                break;
            case R.id.play_button:
                String mensaje = MessageAdapter.getText();
                play(mensaje);
                Log.d(TAG,"Reproduciendo: "+mensaje);
                break;
            case R.id.add_button:
                //si ni, por default accessType sera 0, cuando ocurra este evento
                //accessType = 0;
                newFragment = new GetAccessDialog();
                newFragment.show(getSupportFragmentManager() ,"getAccess");
                break;
            case R.id.help_button:
                newFragment = new DialogoAyuda();
                newFragment.show(getSupportFragmentManager() ,"ayuda");
                break;
            case R.id.delete_button:
                MessageAdapter.removeElement();
                messageGrid.setAdapter(messageAdapter);
                messageGrid.requestLayout();
                break;
            case R.id.btn_wizard:
                btnWizard.setEnabled(false);
                iniWizard();
                break;
            default: break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.delete_button:
                MessageAdapter adaptador = (MessageAdapter) messageGrid.getAdapter();
                MessageAdapter.emptyGrid();
                messageGrid.setAdapter(adaptador);
                messageGrid.requestLayout();
                break;

            default: break;
        }
        return  false;
    }
}
