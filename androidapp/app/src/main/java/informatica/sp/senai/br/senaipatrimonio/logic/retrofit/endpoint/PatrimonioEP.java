package informatica.sp.senai.br.senaipatrimonio.logic.retrofit.endpoint;

import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.logic.model.ItemPatrimonio;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Patrimonio;
import informatica.sp.senai.br.senaipatrimonio.util.jserializer.JfoUtils;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public interface PatrimonioEP {



    @GET("patrimonios")
    Call<List<Patrimonio>> buscarTodos(@Header("X-Filter") String jfoFilter);


    @GET("patrimonios/{id}/itens")
    Call<List<ItemPatrimonio>> buscarItens(@Header("X-Filter") String jfoFilter, @Path("id") Long id);

}
