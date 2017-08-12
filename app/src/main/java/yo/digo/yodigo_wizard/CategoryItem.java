package yo.digo.yodigo_wizard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class CategoryItem {
    private String title;
    private int icon;
    private Bitmap iconBm = null;
    private Context context;

    public CategoryItem(Context context,String title, int icon) {
        this.title = title;
        this.icon = icon;
        this.context = context;
        this.iconBm = null;
    }

    public CategoryItem(Context context,String title, String icon) {
        this.title = title;
        if(icon.indexOf('/') != -1) { //si no encuentra una diagonal en el texto, entonces no es una imagen de la SD
            this.iconBm = modificarTamImagen(icon,70,70);
        }else{
            this.icon = context.getResources().getIdentifier(icon, "drawable", "yo.digo.yodigo_wizard");
        }
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

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getIcon() { return icon; }
    public Bitmap getIconBm(){return iconBm;}
    public void setIcon(int icon) { this.icon = icon; }
}
