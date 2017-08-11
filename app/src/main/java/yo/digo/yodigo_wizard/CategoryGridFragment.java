package yo.digo.yodigo_wizard;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static yo.digo.yodigo_wizard.MainActivity.font;

public class CategoryGridFragment extends Fragment {
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
        //setUpTextView(name,currentName);


        //ACTUALIZAMOS EL GRID CON LAS CATEGORIAS NECESARIAS, SEGUN EL VALOR DE wizardStep


        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(ARG_STEP,wizardStep);
    }

    public void setWizardStep(int step){ this.wizardStep = step; }
}
