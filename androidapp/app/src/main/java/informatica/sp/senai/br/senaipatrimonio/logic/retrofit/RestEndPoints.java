package informatica.sp.senai.br.senaipatrimonio.logic.retrofit;

import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.logic.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestEndPoints {

    @POST("auth/jwt")
    Call<ResponseBody> auth(@Body User user);


    @POST("teste/input")
    Call<User> testeInputObject(@Body User user);

    @POST("teste/input/lista")
    Call<List<User>> testeInputLista(@Body List<User> user);

    @GET("teste/procura")
    Call<List<User>> testeProcura();
}
