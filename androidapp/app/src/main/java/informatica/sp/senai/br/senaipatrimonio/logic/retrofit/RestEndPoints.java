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

    @GET("jwt/valid")
    Call<ResponseBody> validToken();










    @POST("teste/input")
//    @Headers("X-Filter: JFO {\"require\" : [\"nome\"]}")
    Call<User> testeInputObject(@Body ObjectWithFilter<User> user);


    @POST("teste/input/lista")
    @Headers("X-Filter: JFO {\"require\" : [\"id\"]}")
    Call<List<User>> testeInputLista(@Body ObjectWithFilter<List<User>> user);

    @POST("teste/obj/array")
//    @Headers("X-Filter: JFO {\"require\" : [\"id\",\"nome\",\"tipo\",\"email\",\"senha\"]}")
    @Headers("X-Filter: JFO {\"require\" : [\"id\",\"nome\",\"email\",\"senha\"]}")
    Call<List<User>> testeInputObjRetList(@Body ObjectWithFilter<User> user);
//
//    @POST("teste/input/lista")
//    Call<List<User>> testeInputLista(@Body ObjectWithFilter<List<User>> user);
//
//    @GET("teste/procura")
//    Call<List<User>> testeProcura();
}
