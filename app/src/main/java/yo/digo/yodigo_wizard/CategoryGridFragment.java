package yo.digo.yodigo_wizard;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static yo.digo.yodigo_wizard.MainActivity.databaseHelper;
import static yo.digo.yodigo_wizard.MainActivity.font;

public class CategoryGridFragment extends Fragment {
    private final static String TAG = "CategoryGridFragment";
    private List<CategoryItem> categoryItems;
    Context contextFrag;
    int wizardStep = -1; //valor default, todas las categorias se muestran

    private static final String ARG_STEP = "CURRENT_STEP";

    public CategoryGridFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null) {
            wizardStep = savedInstanceState.getInt(ARG_STEP);
        }

        View rootView = inflater.inflate(R.layout.grid_category_layout, container, false);

        contextFrag = rootView.getContext();

        //ACTUALIZAMOS EL TextView DEL LAYOUT
        TextView name = (TextView) rootView.findViewById(R.id.categoriesTxt);
        name.setText("Categorias");
        name.setTypeface(font);
        name.setTextSize(25);
        name.setPadding(8, 5, 5, 6);
        name.setTextColor(Color.rgb(235, 142, 3));
        name.setShadowLayer((float) 1.5,1,2,Color.rgb(0,0,0));
        //setUpTextView(name,currentName); //aqui no se modificara el texto


        //ACTUALIZAMOS EL GRID CON LAS CATEGORIAS NECESARIAS, SEGUN EL VALOR DE wizardStep
        GridView grid = (GridView) rootView.findViewById(R.id.grid_category);
        setUpGridCategories(grid,wizardStep);

        return rootView;
    }

    public void setUpGridCategories(GridView grid,int step){
        //Se implementa listener para poder llamar a los elementos de la categoria seleccionada en el grid
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //@Override //probar si no es necesario...
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"Elegido item, pos:"+String.valueOf(position)+", id: "+String.valueOf(id));

                //aqui se cambia el fragmente_container del main por el fragment
                // con los elementos de la categoria seleccionada
                TextView text = (TextView) view.findViewById(R.id.grid_item_label);
                String txt = text.getText().toString();

                ((MainActivity)contextFrag).play(txt); //se activa el modulo voz
                ((MainActivity)contextFrag).showCategoryByName(txt);
            }
        });

        //Se rellena el grid con las categorias correspondientes segun el step actual
        categoryItems = new ArrayList<CategoryItem>();

        Cursor c = databaseHelper.getCategorias();
        c.moveToFirst();
        Log.d(TAG,"Cargando categorias en el grid, step: "+String.valueOf(step));

        while (!c.isAfterLast()){
            CategoryItem ci = new CategoryItem(contextFrag,c.getString(1),c.getString(0));
            //llamar a metodo que filtre segun el step actual...
            //filtrarCategoria(ci,step);
            categoryItems.add(ci);
            c.moveToNext();
        }
        //se implementa el adapter con las categorias necesarias
        CategoryGridAdapter adapter = new CategoryGridAdapter(contextFrag,categoryItems);
        grid.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(ARG_STEP,wizardStep);
    }

    public void setWizardStep(int step){ this.wizardStep = step; }
}
