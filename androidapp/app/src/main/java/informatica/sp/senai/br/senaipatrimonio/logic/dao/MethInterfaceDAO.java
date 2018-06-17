package informatica.sp.senai.br.senaipatrimonio.logic.dao;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public interface MethInterfaceDAO<CallAtr, ResponseAtr> {

    void okResponse(Call<CallAtr> call, Response<ResponseAtr> response, List<Object> argsOK, List<Object> results) throws IOException;
    void failureResponse(Call<CallAtr> call, Throwable t,List<Object> argsFailure);

}
