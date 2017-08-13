package yo.digo.yodigo_wizard;


import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static yo.digo.yodigo_wizard.MainActivity.databaseHelper;


public class CategoryFragment extends Fragment {
    public static String TITLES[];
    public static Typeface font;
    private long categoryId = 0;

    String currentName;
    String currentImg;
    int currentCategory;
    String textElim;
    Context contextFrag;

    private static final String ARG_ID = "CURRENT_ID"; //almacena la categoria actual, segun su posicion en los Tabs
    private static final String ARG_NAME = "CURENT_NAME";
    private static final String ARG_IMG = "CURRENT_IMG";


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null) {
            categoryId = savedInstanceState.getLong("categoryId");
            currentCategory = savedInstanceState.getInt(ARG_ID);
            currentName = savedInstanceState.getString(ARG_NAME);
            currentImg = savedInstanceState.getString(ARG_IMG);
        }


        //return inflater.inflate(R.layout.fragment_category, container, false); //antes
        View rootView = inflater.inflate(R.layout.category_layout, container, false); //nueva linea

        //contextFrag = getContext();
        //contextFrag = getView().getContext();
        contextFrag = rootView.getContext();

         /*SE ACTUALIZA EL GRID*/
        GridView grid = (GridView) rootView.findViewById(R.id.elementGrid);
        setUpGrid(grid, currentCategory); //actualiza el grid actual con la informacion de la categoriaActual, en base al ID recibido

        /*SE ACTUALIZA LA IMAGEN DE LA CATEGORIA*/
        ImageView image = (ImageView) rootView.findViewById(R.id.categoryImage);
        if(currentImg.indexOf('/') != -1) {
            Bitmap bm = modificarTamImagen(currentImg,70,70);
            image.setImageBitmap(bm);
        }else{
            int imgid = contextFrag.getResources().getIdentifier(currentImg, "drawable", "yo.digo.yodigo_wizard");
            image.setImageResource(imgid);
        }
        image.setPadding(8, 5, 5, 6);

        /*SE ACTUALIZA EL NOMBRE DE LA CATEGORIA*/
        TextView name = (TextView) rootView.findViewById(R.id.categoryName);
        //cats = currentName; //PENDIENTE
        name.setText(currentName);
        name.setTypeface(font);
        name.setTextSize(25);
        name.setPadding(8, 5, 5, 6);
        name.setTextColor(Color.rgb(235, 142, 3));
        name.setShadowLayer((float) 1.5,1,2,Color.rgb(0,0,0));
        setUpTextView(name,currentName);


        return  rootView;
    }

    @SuppressWarnings("deprecation")
    public void setUpGrid(GridView grid, int id){
        /**
         * Cambiar para checar la gramatica
         */
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TextView text = (TextView) v.findViewById(R.id.grid_item_label);
                String txt = text.getText().toString();
                System.out.println(txt);
                Cursor cur = databaseHelper.getElementId(txt);
                cur.moveToFirst();
                String imgn = cur.getString(2);
                int clas = cur.getInt(3);

                ((MainActivity)contextFrag).play(txt); //se activa el modulo voz
                if(imgn.indexOf('/')==-1){//si imagen es una ruta de imagen (imagen de  la SD)
                    int resId = contextFrag.getResources().getIdentifier(imgn, "drawable", "yo.digo.yodigo_wizard");
                    ((MainActivity)contextFrag).messageWindow(txt, resId, clas); //se agrega el elemento a la ventana de mensaje
                }else{
                    ((MainActivity)contextFrag).messageWindow(txt, imgn, clas); //se agrega el elemento a la ventana de mensaje, siendo imgn, la ruta de una imagen
                }
                //Despues de agregar el elemento, avanzamos un step en el wizard
                ((MainActivity)contextFrag).incrementarWizardStep();
                //((MainActivity)contextFrag).iniWizard(); //se manda a llamar desde el metodo incrementarWizardStep
            }
        });
        /**
         * Cambiar para pedir contrasena
         */
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View v,int index, long arg3) {
                TextView text = (TextView) v.findViewById(R.id.grid_item_label);
                textElim = text.getText().toString();

                DialogFragment d = new EliminateConfirmation();
                d.show(((FragmentActivity)contextFrag).getSupportFragmentManager() ,"eliminar");
                return false;
            }
        });

        FragmentActivity act = (FragmentActivity) contextFrag;
        Cursor c = databaseHelper.getElementoCategoria(id);
        if(c!=null && c.getCount()>0){
            act.startManagingCursor(c); //evita cerrar el cursor al interectuar con varios fragments
            MySimpleCursorAdapter adapter;
            adapter = fillData(id);
            grid.setAdapter(adapter); //grid.setAdapter(fillData(id)); //tambien se puede...
        }

    }

    @SuppressWarnings("deprecation")
    private MySimpleCursorAdapter fillData(int i) {
        FragmentActivity act = (FragmentActivity) contextFrag;
        Cursor c = databaseHelper.getElementoCategoria(i);
        act.startManagingCursor(c);
        c.moveToFirst();

        ArrayList<Integer> clas = new ArrayList<Integer>();

        do {
            clas.add(c.getInt(3)); //get clasificacion, no necesariamente es el mismo valor que la categoria
        } while(c.moveToNext());

        //Se guardan en el arreglo las columnas de donde se sacaran los datos, imagen,cadena,categoria padre, clasificacion
        String[] from = new String[] {DatabaseHelper.ELEMENTO_2, DatabaseHelper.ELEMENTO_1,DatabaseHelper.ELEMENTO_5 };//imagen, cadena y clasificacion

        //En el 2do parametro se guardan los ids de los views donde iran los datos
        int[] to = new int[] { R.id.grid_item_image, R.id.grid_item_label };

        MySimpleCursorAdapter adapter = new MySimpleCursorAdapter(contextFrag, R.layout.item_grid, c, from, to, clas);

        return adapter;
    }

    public static Bitmap modificarTamImagen(String ruta, float newWidth, float newHeigth){//Modificar tama�o con ruta de imagen (SD)
        Bitmap mBitmap = BitmapFactory.decodeFile(ruta);
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
        return salida;
    }

    public void setUpTextView(TextView text, String catName){
        text.setOnLongClickListener(new View.OnLongClickListener()
        {
            /**
             * Cambiar para pedir contrasena
             */
            @Override
            public boolean onLongClick(View arg0)
            {
                DialogFragment d = new CategoryEliminateConfirmation();
//					DialogFragment d = new EliminateConfirmation();
                d.show(((FragmentActivity)contextFrag).getSupportFragmentManager() ,"eliminar");
                //cats = ((TextView) arg0).getText().toString();
                Toast.makeText(getContext(),"Solucionar cats",Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("categoryId", categoryId);
        savedInstanceState.putInt(ARG_ID,currentCategory);
        savedInstanceState.putString(ARG_NAME,currentName);
        savedInstanceState.putString(ARG_IMG,currentImg);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            /*TextView title = (TextView) view.findViewById(R.id.textPrueba);
            TITLES = getResources().getStringArray(R.array.categories_names);
            title.setText(TITLES[(int)categoryId]+" CuName: "+currentName + ", CuImg: "+currentImg);
            font = Typeface.createFromAsset(view.getContext().getAssets(),"JandaManatee.ttf");
            title.setTypeface(font);
            title.setTextColor(Color.rgb(255, 255, 255));
            title.setShadowLayer((float) 1.5,1,2,Color.rgb(0,0,0));*/
        }
    }

    public void setCategory(long id){ this.categoryId = id; }
    public void setCurrentName(String name){this.currentName = name; }
    public void setCurrentImg(String img){this.currentImg= img; }
    public void setCurrentCategory(int currentCategory){ this.currentCategory = currentCategory; }

}
