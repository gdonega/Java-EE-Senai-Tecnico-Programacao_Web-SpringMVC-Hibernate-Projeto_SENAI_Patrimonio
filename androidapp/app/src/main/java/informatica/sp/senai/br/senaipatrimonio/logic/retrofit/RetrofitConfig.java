package informatica.sp.senai.br.senaipatrimonio.logic.retrofit;

import informatica.sp.senai.br.senaipatrimonio.teste.JSerializerConverterFactory;
import informatica.sp.senai.br.senaipatrimonio.utils.Statics;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Statics.SERVER_URL)
                .addConverterFactory(JSerializerConverterFactory.create())
                .build();
    }

    public RetrofitConfig(OkHttpClient client) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Statics.SERVER_URL)
                .addConverterFactory(JSerializerConverterFactory.create())
                .client(client)
                .build();
    }

    public RestEndPoints getResteEndPoint() {
        return this.retrofit.create(RestEndPoints.class);
    }

}
