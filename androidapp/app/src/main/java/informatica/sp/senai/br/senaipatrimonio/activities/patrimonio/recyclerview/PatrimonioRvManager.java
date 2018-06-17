package informatica.sp.senai.br.senaipatrimonio.activities.patrimonio.recyclerview;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;

import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.logic.model.Patrimonio;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class PatrimonioRvManager {

    private LayoutManager layoutManager;
    private Adapter adapter;
    private RecyclerView recyclerView;
    private Class<? extends  ViewHxH> viewHolder;
    private PatrimonioRvAdapterSet rvAdapterSet;
    private List<Patrimonio> list;


    public PatrimonioRvManager() {
        rvAdapterSet = new PatrimonioRvAdapterSet(this);
        adapter = new PatrimonioAdapter(this);
    }

    public void build(){
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    public PatrimonioRvAdapterSet getRvAdapterSet(){
        return rvAdapterSet;
    }

    public PatrimonioRvManager setViewHolderClass(Class<? extends ViewHxH> viewHolder){

        this.viewHolder = viewHolder;
        return this;
    }

    public Class<? extends ViewHxH> getViewHolder() {
        return viewHolder;
    }

    public PatrimonioRvManager setList(List<Patrimonio>list){
        this.list = list;
        return this;
    }

    public List<Patrimonio> getList() {
        return list;
    }

    public PatrimonioRvManager setLayoutManager(LayoutManager layoutManager){
        this.layoutManager = layoutManager;
        return this;
    }

    public PatrimonioRvManager createBaseGridLayoutManager(Context context, int spanCountPortrait, int spanCountLandscape){
        int orientacao = context.getResources().getConfiguration().orientation;
        if (orientacao == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(context, 2);
        } else {
            layoutManager = new GridLayoutManager(context, 2);
        }
        return this;
    }

//    public PatrimonioRvManager setAdapter(Adapter adapter){
//        this.adapter = adapter;
//        return this;
//    }

    public PatrimonioRvManager setRecyclerView(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
        return this;
    }


//    /**
//     * Este método é executado quando o usuário exclui algum item da tela
//     * Ele exclui da base de dados
//     * E atualiza a lista
//     *
//     * @param positionOfTheItemOnList
//     * @param aluno
//     */
//    public void notificarRemocaoDeUmItem(Integer positionOfTheItemOnList, Aluno aluno) {
//        dao.delete(aluno);
//        alunoAdapter.notifyItemRemoved(positionOfTheItemOnList);
//    }
//
    /**
     * Atualiza toda a lista
     */
    public void notificarQualquerMudanca() {
        getAdapter().notifyDataSetChanged();
    }

    public LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public Adapter getAdapter() {
        return adapter;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }


//    public void setRvAdapterSet(PatrimonioRvAdapterSet rvAdapterSet) {
//        this.rvAdapterSet = rvAdapterSet;
//    }


}
