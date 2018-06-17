package informatica.sp.senai.br.senaipatrimonio.activities.patrimonio.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class PatrimonioAdapter extends RecyclerView.Adapter {
    private PatrimonioRvManager manager;

    public PatrimonioAdapter(PatrimonioRvManager manager) {
        this.manager = manager;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PatrimonioRvAdapterSet adapterSet = manager.getRvAdapterSet();

        Context context = adapterSet.getContext();
        if (context == null)
            context = parent.getContext();

        Integer id = adapterSet.getCardXmlId();

        ViewGroup root = adapterSet.getRoot();
        if (root == null)
            root = parent;

        Boolean attachToRoot = adapterSet.getAttachToRoot();
        if (attachToRoot == null)
            attachToRoot = false;

        View view = LayoutInflater.from(context).inflate(id, root, attachToRoot);

        ViewHxH viewHxH = null;
        Class<? extends ViewHxH> aClass = manager.getViewHolder();
        try {
            aClass = (Class<? extends ViewHxH>) Class.forName(manager.getViewHolder().toString().split(" ")[1]);
            Constructor<?> ctor = aClass.getConstructor(View.class);
            Log.e("ok", "HAHAHHA");
            viewHxH = (ViewHxH) ctor.newInstance(view);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            Log.e("ok", "No such");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Log.e("View Original",String.valueOf(view.getId()));
        Log.e("View pego",String.valueOf(viewHxH.getCardView().getId()));


        return viewHxH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHxH viewHolder = (ViewHxH) holder;
        viewHolder.setOnHolder(manager.getList().get(position));
    }

    @Override
    public int getItemCount() {
        if (manager.getList() != null)
            return manager.getList().size();

        return 0;
    }
}
