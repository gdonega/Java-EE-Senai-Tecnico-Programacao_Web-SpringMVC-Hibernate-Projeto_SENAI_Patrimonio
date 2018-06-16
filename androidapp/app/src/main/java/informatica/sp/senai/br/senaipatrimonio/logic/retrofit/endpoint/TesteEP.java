package informatica.sp.senai.br.senaipatrimonio.logic.retrofit.endpoint;

import org.adataq.jserializer.plugins.retrofit.models.ObjectWithFilter;

import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.logic.model.Usuario;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TesteEP {

    @POST("teste/input")
    Call<ResponseBody> usuario (@Body Usuario usuario);

        @POST("teste/input/lista")
    @Headers("X-Filter: JFO {\"require\" : [\"id\"]}")
    Call<List<Usuario>> testeInputLista(@Body List<Usuario> user);
}
