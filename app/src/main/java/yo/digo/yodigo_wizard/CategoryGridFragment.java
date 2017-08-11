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
    //public static Typeface font;
    Context contextFrag;

    public CategoryGridFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.grid_category_layout, container, false);

        //contextFrag = rootView.getContext();

        TextView name = (TextView) rootView.findViewById(R.id.categoriesTxt);
        name.setText("Categorias");
        name.setTypeface(font);
        name.setTextSize(25);
        name.setPadding(8, 5, 5, 6);
        name.setTextColor(Color.rgb(235, 142, 3));
        name.setShadowLayer((float) 1.5,1,2,Color.rgb(0,0,0));
        //setUpTextView(name,currentName);

        return rootView;
    }
}
