package informatica.sp.senai.br.senaipatrimonio.logic.dao;

import android.util.Log;
import android.widget.Toast;

import org.adataq.jserializer.JSerializer;
import org.adataq.jserializer.plugins.retrofit.models.ObjectWithFilter;
import org.json.JSONObject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    public static String AUTH = "getToken";

    public void getToken(ObjectWithFilter<Usuario> map, List<Object> argsOk, List<Object> argsFailure, List<Object> results, MethInterfaceDAO methods) {

        Call<ResponseBody> call = new RetrofitConfig(false).getAuthEndPoint().auth(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        results.add(0, new JSONObject(response.body().string()).get("token").toString());
                        methods.okResponse(call, response, argsOk, results);
                        changes.firePropertyChange(AUTH, null, null);

                    } catch (Exception e) {
                        Log.e("DAO", "Error: an exception occurred");
                    }
                } else {
                    Log.e("DAO", "Error: not successful response");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                methods.failureResponse(call, t, argsFailure);
            }
        });

    }

    public void getToken(Usuario usuario, List<Object> argsOk, List<Object> argsFailure, List<Object> results, MethInterfaceDAO methods) {
        Call<ResponseBody> call = new RetrofitConfig(false).getAuthEndPoint().auth(usuario);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    try {
                        results.add(0, new JSONObject(response.body().string()).get("token").toString());
                        methods.okResponse(call, response, argsOk, results);
                        changes.firePropertyChange(AUTH, null, null);
                    } catch (Exception e) {
                        Toast.makeText(Main.getContext(), "Ocourreu algum erro!", Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                methods.failureResponse(call, t, argsFailure);
            }
        });

    }


    public void addPropertyChangeListener(PropertyChangeListener prop) {
        changes.addPropertyChangeListener(prop);
    }

    public void removePropertyChangeListener(PropertyChangeListener prop) {
        changes.removePropertyChangeListener(prop);
    }
}
