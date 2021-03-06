package informatica.sp.senai.br.senaipatrimonio.logic.dao;

import android.util.Log;
import android.widget.Toast;

import org.adataq.jserializer.plugins.retrofit.models.ObjectWithFilter;
import org.json.JSONObject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.activities.Main;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Usuario;
import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.RetrofitConfig;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class AuthRetrofitDAO {
    //Para executar o metodo "trigger" da classe que utiliza essa DAO
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    //Identificador do metodo - getToken - para o metodo "trigger"
    public static String GET_TOKEN = "getToken";

    public void getToken(ObjectWithFilter<Usuario> map, List<Object> argsOk, List<Object> argsFailure, List<Object> results, MethInterfaceDAO<ResponseBody, ResponseBody> methods) {
        //Cria a "Call" HTTP
        Call<ResponseBody> call = new RetrofitConfig(false).getAuthEndPoint().auth(map);

        //Executa a "Call"
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        results.add(0, new JSONObject(response.body().string()).get("token").toString());
                        methods.okResponse(call, response, argsOk, results);
                        Toast.makeText(Main.getContext(), "Bem Vindo!", Toast.LENGTH_SHORT).show();
                        changes.firePropertyChange(GET_TOKEN, null, null);

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
            public void onFailure(Call<ResponseBody> call, Throwable t) {
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
