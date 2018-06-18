package informatica.sp.senai.br.senaipatrimonio.logic.dao;

import android.util.Log;

import org.adataq.jserializer.json.JfoObject;
import org.adataq.jserializer.plugins.retrofit.models.ObjectWithFilter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.logic.model.Ambiente;
import informatica.sp.senai.br.senaipatrimonio.logic.model.ItemPatrimonio;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Movimentacao;
import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.RetrofitConfig;
import informatica.sp.senai.br.senaipatrimonio.util.jserializer.FilerType;
import informatica.sp.senai.br.senaipatrimonio.util.jserializer.JfoUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class ItemPatrimonioDAO {
    //Para executar o metodo "trigger" da classe que utiliza essa DAO
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    //Identificador do metodo - getMovDoItem - para o metodo "trigger"
    public static String GET_ITEMPATRIMONIO_BY_ID = "getItemById";

    /**
     * Recupera um itemPatrimonio do BackEnd
     *
     * @param argsOk
     * @param argsFailure
     * @param results
     * @param methods
     */
    public void getItemById(Long id, List<Object> argsOk, List<Object> argsFailure, List<Object> results, MethInterfaceDAO<ItemPatrimonio, ItemPatrimonio> methods) {
        //Filtro JFO para o backend
        String jfo = JfoUtils.createStringJfoHttpStandard(FilerType.REQUIRE, "id", "ambienteAtual");

        //Cria a "Call" HTTP
        Call<ItemPatrimonio> call = new RetrofitConfig(true).getItemPatrimonioEndPoint().buscarItemPorId(jfo, id);

        //Executa a "Call"
        call.enqueue(new Callback<ItemPatrimonio>() {
            @Override
            public void onResponse(Call<ItemPatrimonio> call, Response<ItemPatrimonio> response) {
                if (response.isSuccessful()) {
                    try {
                        results.add(0, response.body());
                        methods.okResponse(call, response, argsOk, results);
                        changes.firePropertyChange(GET_ITEMPATRIMONIO_MOV, null, null);
                    } catch (Exception e) {
                        Log.e("DAO", "Error: an exception occurred");
                    }
                } else {
                    Log.e("DAO", "Error: not successful response");
                }

            }

            @Override
            public void onFailure(Call<ItemPatrimonio> call, Throwable t) {

                methods.failureResponse(call, t, argsFailure);
            }
        });
    }


    //Identificador do metodo - getMovDoItem - para o metodo "trigger"
    public static String GET_ITEMPATRIMONIO_MOV = "getItemPatrimonio";

    /**
     * Recupera uma lista de movimentacoes de um itemPatrimonio do BackEnd
     *
     * @param argsOk
     * @param argsFailure
     * @param results
     * @param methods
     */
    public void getMovimentacoesDoItem(Long id, List<Object> argsOk, List<Object> argsFailure, List<Object> results, MethInterfaceDAO<List<Movimentacao>, List<Movimentacao>> methods) {
        //Filtro JFO para o backend
        String jfo = JfoUtils.createStringJfoHttpStandard(FilerType.REQUIRE,  "dataDaMovimentacao", "ambienteOriginal.nome", "ambienteNovo.nome", "executou.nome");

        //Cria a "Call" HTTP
        Call<List<Movimentacao>> call = new RetrofitConfig(true).getItemPatrimonioEndPoint().buscarMovimentacoes(jfo, id);

        //Executa a "Call"
        call.enqueue(new Callback<List<Movimentacao>>() {
            @Override
            public void onResponse(Call<List<Movimentacao>> call, Response<List<Movimentacao>> response) {
                if (response.isSuccessful()) {
                    try {
                        results.add(0, response.body());
                        methods.okResponse(call, response, argsOk, results);
                        changes.firePropertyChange(GET_ITEMPATRIMONIO_MOV, null, null);
                    } catch (Exception e) {
                        Log.e("DAO", "Error: an exception occurred");
                    }
                } else {
                    Log.e("DAO", "Error: not successful response");
                }
            }

            @Override
            public void onFailure(Call<List<Movimentacao>> call, Throwable t) {
                methods.failureResponse(call, t, argsFailure);
            }
        });
    }

    //Identificador do metodo - getPatrimonios - para o metodo "trigger"
    public static String MOVER_ITEMPATRIMONIO = "moverItem";

    /**
     * Mover um itemPatrimonio
     *
     * @param argsOk
     * @param argsFailure
     * @param results
     * @param methods
     */
    public void moverItem(Long id, Movimentacao movimentacao, List<Object> argsOk, List<Object> argsFailure, List<Object> results, MethInterfaceDAO<Movimentacao, Movimentacao> methods) {
        //Filtro JFO para o backend
        String jfoResp = JfoUtils.createStringJfoHttpStandard(FilerType.REQUIRE, "");
        JfoObject jfoReq = JfoUtils.createJfo(FilerType.REQUIRE, "ambienteNovo.id");

        ObjectWithFilter<Movimentacao> objToSend = new ObjectWithFilter<Movimentacao>(movimentacao, jfoReq);

        //Cria a "Call" HTTP
        Call<Movimentacao> call = new RetrofitConfig(true).getItemPatrimonioEndPoint().moverItem(jfoResp, id, objToSend);
        Log.e("aa", movimentacao.toString());
        Log.e("aa", movimentacao.getAmbienteNovo().getNome());
        Log.e("aa", String.valueOf(movimentacao.getAmbienteNovo().getId()));

        //Executa a "Call"
        call.enqueue(new Callback<Movimentacao>() {
            @Override
            public void onResponse(Call<Movimentacao> call, Response<Movimentacao> response) {


                Log.e("aa", String.valueOf(call.request().url()));

                if (response.isSuccessful()) {
                    try {
                        results.add(0, response.body());
                        methods.okResponse(call, response, argsOk, results);
                        changes.firePropertyChange(MOVER_ITEMPATRIMONIO, null, null);
                    } catch (Exception e) {
                        Log.e("DAO", "Error: an exception occurred");
                    }
                } else {
                    Log.e("DAO", "Error: not successful response");

                    Log.e("DAO", String.valueOf(response.code()));

                }
            }

            @Override
            public void onFailure(Call<Movimentacao> call, Throwable t) {
                methods.failureResponse(call, t, argsFailure);
            }
        });
    }


    /**
     * Adicionar classes que executam a "Trigger"
     *
     * @param prop
     */
    public void addPropertyChangeListener(PropertyChangeListener prop) {
        changes.addPropertyChangeListener(prop);
    }

    /**
     * Remover classes que executam a "Trigger"
     *
     * @param prop
     */
    public void removePropertyChangeListener(PropertyChangeListener prop) {
        changes.removePropertyChangeListener(prop);
    }
}
