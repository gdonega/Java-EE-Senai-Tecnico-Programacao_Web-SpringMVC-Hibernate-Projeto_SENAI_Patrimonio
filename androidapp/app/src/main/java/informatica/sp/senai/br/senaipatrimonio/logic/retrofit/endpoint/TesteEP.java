package informatica.sp.senai.br.senaipatrimonio.logic.retrofit.endpoint;

import informatica.sp.senai.br.senaipatrimonio.logic.model.Usuario;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TesteEP {

    @POST("teste/input")
    Call<ResponseBody> usuario (@Body Usuario usuario);
}
