package informatica.sp.senai.br.senaipatrimonio.logic.retrofit.endpoint;

import org.adataq.jserializer.plugins.retrofit.models.ObjectWithFilter;

import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.logic.model.Ambiente;
import informatica.sp.senai.br.senaipatrimonio.logic.model.ItemPatrimonio;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Movimentacao;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public interface ItemPatrimonioEP {

    @GET("itens/{id}/movimentacoes")
    Call<List<Movimentacao>> buscarMovimentacoes(@Header("X-Filter") String jfoFilter, @Path("id") Long id);

    @GET("itens/{id}")
    Call<ItemPatrimonio> buscarItemPorId(@Header("X-Filter") String jfoFilter, @Path("id") Long id);

    @PATCH("itens/{id}/movimentacoes")
    Call<Movimentacao> moverItem(@Header("X-Filter") String jfoFilter, @Path("id") Long id, @Body ObjectWithFilter<Movimentacao> movimentacao);


}
