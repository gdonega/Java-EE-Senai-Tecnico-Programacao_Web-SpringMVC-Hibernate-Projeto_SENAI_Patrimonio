package informatica.sp.senai.br.senaipatrimonio.activities.patrimonio.recyclerview;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class PatrimonioRvAdapterSet {

    private Context context;
    private PatrimonioRvManager manager;
    private Integer cardXmlId;
    private ViewGroup root;
    private Boolean attachToRoot;

    public PatrimonioRvAdapterSet(PatrimonioRvManager manager) {
        context = null;
        cardXmlId = null;
        this.manager = manager;
    }

    public PatrimonioRvAdapterSet setContext(Context context){
        this.context = context;
        return this;
    }

    public PatrimonioRvAdapterSet setCardXmlId(int id){
        this.cardXmlId = id;
        return this;
    }

    public PatrimonioRvAdapterSet setRoot(ViewGroup root){
        this.root = root;
        return this;
    }

    public PatrimonioRvAdapterSet setAttachToRoot(Boolean attachToRoot){
        this.attachToRoot = attachToRoot;
        return this;
    }


    public PatrimonioRvManager and(){
        return manager;
    }

    /**
     *
     *
     *
     *
     *
     *
     *
     *
     */
    public Context getContext() {
        return context;
    }

    public PatrimonioRvManager getManager() {
        return manager;
    }

    public Integer getCardXmlId() {
        return cardXmlId;
    }

    public ViewGroup getRoot() {
        return root;
    }

    public Boolean getAttachToRoot() {
        return attachToRoot;
    }
}
