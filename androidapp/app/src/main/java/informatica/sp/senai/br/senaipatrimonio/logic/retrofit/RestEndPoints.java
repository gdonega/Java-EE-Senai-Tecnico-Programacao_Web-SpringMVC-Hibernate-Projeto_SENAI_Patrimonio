package informatica.sp.senai.br.senaipatrimonio.logic.retrofit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import informatica.sp.senai.br.senaipatrimonio.logic.models.User;
import io.felipepoliveira.jserializer.json.JfoObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RestEndPoints {

    @POST("auth/jwt")
    Call<ResponseBody> auth(@Body User user);

    @POST("auth/jwt")
    Call<ResponseBody> auth(@Body ObjectWithFilter<User> req);



//    @POST("teste/input")
//    Call<User> testeInputObject(@Body User user);
//
//
    @POST("teste/input/lista")
    @Headers("X-Filter: JFO {\"require\" : [\"id\"]}")
    Call<List<User>> testeInputLista(@Body List<User> user);
//
//    @POST("teste/input/lista")
//    Call<List<User>> testeInputLista(@Body ObjectWithFilter<List<User>> user);
//
//    @GET("teste/procura")
//    Call<List<User>> testeProcura();
}
