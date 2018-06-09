package informatica.sp.senai.br.senaipatrimonio.logic.daotestes;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.logic.models.User;
import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.RetrofitConfig;
import io.felipepoliveira.jserializer.json.JsonObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class AuthRetrofitDAO{
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);



    public static String AUTH = "getToken";
    public void getToken(User user, List<Object> argsOk, List<Object> argsFailure,List<Object> results, MethInterfaceDAO methods) {
        Call<ResponseBody> call = new RetrofitConfig().getResteEndPoint().auth(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        results.add(0, new JSONObject(response.body().string()).get("token").toString());
                        methods.okResponse(call, response, argsOk,results);
                        changes.firePropertyChange(AUTH, null, null);
                    } catch (Exception e) {
                        Log.e("DAO", "Eror: not successful response");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                methods.failureResponse(call,t,argsFailure);
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
