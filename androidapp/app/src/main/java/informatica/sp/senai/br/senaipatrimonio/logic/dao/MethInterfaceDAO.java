package informatica.sp.senai.br.senaipatrimonio.logic.dao;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public interface MethInterfaceDAO {

    void okResponse(Call<ResponseBody> call, Response<ResponseBody> response, List<Object> argsOK, List<Object> results) throws IOException;
    void failureResponse(Call<ResponseBody> call, Throwable t,List<Object> argsFailure);

}
