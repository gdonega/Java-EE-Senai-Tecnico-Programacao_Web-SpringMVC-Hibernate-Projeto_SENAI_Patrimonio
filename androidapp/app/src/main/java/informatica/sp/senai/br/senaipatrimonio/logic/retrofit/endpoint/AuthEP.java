package informatica.sp.senai.br.senaipatrimonio.logic.retrofit.endpoint;

import org.adataq.jserializer.plugins.retrofit.models.ObjectWithFilter;

import informatica.sp.senai.br.senaipatrimonio.logic.model.Usuario;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface AuthEP {
    @POST("auth/jwt")
    Call<ResponseBody> auth(@Body Usuario usuario);

    @POST("auth/jwt")
    Call<ResponseBody> auth(@Body ObjectWithFilter<Usuario> req);
}
