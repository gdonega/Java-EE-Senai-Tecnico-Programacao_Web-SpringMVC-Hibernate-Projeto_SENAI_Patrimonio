package informatica.sp.senai.br.senaipatrimonio.logic.daotestes;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import informatica.sp.senai.br.senaipatrimonio.logic.models.User;
import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.RetrofitConfig;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class AuthDao {

    public void aaa(User user) {


        Call<ResponseBody> call = new RetrofitConfig().getResteEndPoint().auth(user);


        call.enqueue(new Callback<ResponseBody>()

        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    try {
                        JSONObject objeto = new JSONObject(response.body().string());
                        Log.e("testesDonega", "Token: " + objeto.getString("token"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("testesDonega", "deu bosta: ");

                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.e("testesDonega", "deu bosta: " + t.getMessage());
            }
        });

        testeMethod(new testeInterface() {
            @Override
            public String potato(String aa) {
                return null;
            }
        });
    }


    public void testeMethod(testeInterface teste) {
        teste.potato("aa");
    }
}

interface testeInterface {
    String potato(String aa);
}