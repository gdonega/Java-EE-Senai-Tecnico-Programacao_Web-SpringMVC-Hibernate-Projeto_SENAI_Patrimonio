package informatica.sp.senai.br.senaipatrimonio.logic.dao;

import android.util.Log;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.logic.model.ItemPatrimonio;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Patrimonio;
import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.RetrofitConfig;
import informatica.sp.senai.br.senaipatrimonio.util.jserializer.FilerType;
import informatica.sp.senai.br.senaipatrimonio.util.jserializer.JfoUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class PatrimonioRetrofitDAO {
    //Para executar o metodo "trigger" da classe que utiliza essa DAO
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    //Identificador do metodo - getPatrimonios - para o metodo "trigger"
    public static String GET_PATRIMONIOS = "getPatrimonio";

    /**
     * Recupera uma lista de patrimonios do BackEnd
     *
     * @param argsOk
     * @param argsFailure
     * @param results
     * @param methods
     */
    public void getPatrimonios(List<Object> argsOk, List<Object> argsFailure, List<Object> results, MethInterfaceDAO<List<Patrimonio>, List<Patrimonio>> methods) {
        //Filtro JFO para o backend
        String jfo = JfoUtils.createStringJfoHttpStandard(FilerType.REQUIRE, "id", "nome", "categoria.nome");

        //Cria a "Call" HTTP
        Call<List<Patrimonio>> call = new RetrofitConfig(true).getPatrimonioEndPoint().buscarTodos(jfo);

        //Executa a "Call"
        call.enqueue(new Callback<List<Patrimonio>>() {
            @Override
            public void onResponse(Call<List<Patrimonio>> call, Response<List<Patrimonio>> response) {

                if (response.isSuccessful()) {
                    try {
                        results.add(0, response.body());
                        methods.okResponse(call, response, argsOk, results);
                        changes.firePropertyChange(GET_PATRIMONIOS, null, null);
                    } catch (Exception e) {
                        Log.e("DAO", "Error: an exception occurred");
                    }
                } else {
                    Log.e("DAO", "Error: not successful response");
                }
            }

            @Override
            public void onFailure(Call<List<Patrimonio>> call, Throwable t) {
                methods.failureResponse(call, t, argsFailure);

            }
        });
    }


    //Identificador do metodo - getPatrimonios - para o metodo "trigger"
    public static String GET_PATRIMONIO_ITEMPATRIMONIOS = "getPatrimonio";

    /**
     * Recupera uma lista de itemPatrimonios do BackEnd
     *
     * @param argsOk
     * @param argsFailure
     * @param results
     * @param methods
     */
    public void getItensDoPatrimonio(Long id, List<Object> argsOk, List<Object> argsFailure, List<Object> results, MethInterfaceDAO<List<ItemPatrimonio>, List<ItemPatrimonio>> methods) {
        //Filtro JFO para o backend
        String jfo = JfoUtils.createStringJfoHttpStandard(FilerType.REQUIRE, "id", "dt_cadastro","ambienteAtual");

        //Cria a "Call" HTTP
        Call<List<ItemPatrimonio>> call = new RetrofitConfig(true).getPatrimonioEndPoint().buscarItens(jfo, id);

        //Executa a "Call"
        call.enqueue(new Callback<List<ItemPatrimonio>>() {
            @Override
            public void onResponse(Call<List<ItemPatrimonio>> call, Response<List<ItemPatrimonio>> response) {
                if (response.isSuccessful()) {
                    try {
                        results.add(0, response.body());
                        methods.okResponse(call, response, argsOk, results);
                        changes.firePropertyChange(GET_PATRIMONIO_ITEMPATRIMONIOS, null, null);
                    } catch (Exception e) {
                        Log.e("DAO", "Error: an exception occurred");
                    }
                } else {
                    Log.e("DAO", "Error: not successful response");
                }
            }

            @Override
            public void onFailure(Call<List<ItemPatrimonio>> call, Throwable t) {
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
