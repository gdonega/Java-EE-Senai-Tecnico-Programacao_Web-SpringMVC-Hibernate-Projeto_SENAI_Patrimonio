package informatica.sp.senai.br.senaipatrimonio.logic.retrofit;

import informatica.sp.senai.br.senaipatrimonio.logic.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestEndPoints {

    @POST("auth/jwt")
    Call<ResponseBody> auth(@Body User user);

}
