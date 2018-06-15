//package informatica.sp.senai.br.senaipatrimonio.logic.retrofit;
//
//import java.util.List;
//
//import informatica.sp.senai.br.senaipatrimonio.logic.model.Usuario;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.http.Body;
//import retrofit2.http.GET;
//import retrofit2.http.Headers;
//import retrofit2.http.POST;
//import s.informatica.retrofitpluguin.models.ObjectWithFilter;
//
//public interface RestEndPoints {
//
//
//
//
//
//
//
//
//
//    @POST("teste/input")
////    @Headers("X-Filter: JFO {\"require\" : [\"nome\"]}")
//    Call<Usuario> testeInputObject(@Body ObjectWithFilter<Usuario> user);
//
//
//    @POST("teste/input/lista")
//    @Headers("X-Filter: JFO {\"require\" : [\"id\"]}")
//    Call<List<Usuario>> testeInputLista(@Body ObjectWithFilter<List<Usuario>> user);
//
//    @POST("teste/obj/array")
////    @Headers("X-Filter: JFO {\"require\" : [\"id\",\"nome\",\"tipo\",\"email\",\"senha\"]}")
//    @Headers("X-Filter: JFO {\"require\" : [\"id\"]}")
//    Call<List<Usuario>> testeInputObjRetList(@Body ObjectWithFilter<Usuario> user);
//
//
//
//    @Headers("X-Filter: JFO {\"require\" : [\"id\",\"nome\",\"email\",\"senha\"]}")
//    @POST("teste/obj/array")
//    Call<List<Usuario>> testeTOP(@Body Usuario usuario);
////
//
////
////    @POST("teste/input/lista")
////    Call<List<Usuario>> testeInputLista(@Body ObjectWithFilter<List<Usuario>> user);
////
////    @GET("teste/procura")
////    Call<List<Usuario>> testeProcura();
//}
