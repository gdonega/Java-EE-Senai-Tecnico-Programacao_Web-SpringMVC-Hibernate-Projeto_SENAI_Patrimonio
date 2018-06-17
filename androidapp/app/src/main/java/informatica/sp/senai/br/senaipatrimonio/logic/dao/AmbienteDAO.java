package informatica.sp.senai.br.senaipatrimonio.logic.dao;

import android.util.Log;
import android.widget.Toast;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.activities.Main;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Ambiente;
import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.RetrofitConfig;
import informatica.sp.senai.br.senaipatrimonio.util.ItemPatrimonioActivitiesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class AmbienteDAO {

    //Para executar o metodo "trigger" da classe que utiliza essa DAO
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    //Identificador do metodo - getToken - para o metodo "trigger"
    public static String GET_TODOS_AMBIENTES = "getToken";

    public void getTodosAmbientes(List argsOk, List argsFailure, List<Object> results, MethInterfaceDAO<List<Ambiente>, List<Ambiente>> methods) {
        //Cria a "Call" HTTP
        Call<List<Ambiente>> call = new RetrofitConfig(true).getAmbienteEndPoint().buscarTodos();

        //Executa a "Call"
        call.enqueue(new Callback<List<Ambiente>>() {
            @Override
            public void onResponse(Call<List<Ambiente>> call, Response<List<Ambiente>> response) {
                if (response.isSuccessful()) {
                    try {
                        results.add(0, response.body());
                        methods.okResponse(call, response, argsOk, results);
                        changes.firePropertyChange(GET_TODOS_AMBIENTES, null, null);
                    } catch (Exception e) {
                        Log.e("DAO", "Error: an exception occurred");
                        Toast.makeText(Main.getContext(), "Ocurreu algum erro", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("DAO", "Error: not successful response");
                    Toast.makeText(Main.getContext(), "Email ou/e senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Ambiente>> call, Throwable t) {
                methods.failureResponse(call, t, argsFailure);
                Toast.makeText(Main.getContext(), "Ocurreu algum erro", Toast.LENGTH_SHORT).show();

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
