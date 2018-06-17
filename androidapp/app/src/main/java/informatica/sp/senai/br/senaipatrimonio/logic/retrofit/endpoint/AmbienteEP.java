package informatica.sp.senai.br.senaipatrimonio.logic.retrofit.endpoint;

import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.logic.model.Ambiente;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Movimentacao;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public interface AmbienteEP {

    @GET("ambientes")
    Call<List<Ambiente>> buscarTodos();

}
