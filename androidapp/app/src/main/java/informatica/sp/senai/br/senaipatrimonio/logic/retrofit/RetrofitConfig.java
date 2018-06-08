package informatica.sp.senai.br.senaipatrimonio.logic.retrofit;

import informatica.sp.senai.br.senaipatrimonio.TesteJsonConverter;
import informatica.sp.senai.br.senaipatrimonio.Utils.Statics;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Statics.SERVER_URL)
//                .addConverterFactory(TesteJsonConverter.create())
                .build();
    }

    public RetrofitConfig(OkHttpClient client) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Statics.SERVER_URL)
                .client(client)
                .build();
    }

    public RestEndPoints getResteEndPoint(){
        return this.retrofit.create(RestEndPoints.class);
    }

}
