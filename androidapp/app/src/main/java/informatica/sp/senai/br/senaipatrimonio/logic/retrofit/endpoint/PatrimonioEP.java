package informatica.sp.senai.br.senaipatrimonio.logic.retrofit.endpoint;

import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.logic.model.Patrimonio;
import informatica.sp.senai.br.senaipatrimonio.util.jserializer.JfoUtils;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public interface PatrimonioEP {



    @GET("patrimonios")
//    @Headers("X-Filter: JFO {\"require\" : [\"id\"]}")
    Call<List<Patrimonio>> buscarTodos(@Header("X-Filter") String jfoFilter);

}
