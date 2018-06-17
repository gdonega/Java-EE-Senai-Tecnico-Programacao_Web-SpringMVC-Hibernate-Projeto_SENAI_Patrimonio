package informatica.sp.senai.br.senaipatrimonio.logic.retrofit;


import org.adataq.jserializer.plugins.retrofit.JSerializerConverterFactory;

import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.endpoint.AuthEP;
import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.endpoint.ItemPatrimonioEP;
import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.endpoint.PatrimonioEP;
import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.endpoint.TesteEP;
import informatica.sp.senai.br.senaipatrimonio.util.StaticVarUtils;
import informatica.sp.senai.br.senaipatrimonio.util.TokenUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import retrofit2.Retrofit;


public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig(Boolean sendToken) {

        Interceptor interceptor;

        if (sendToken) {
            interceptor = (chain) -> {
                Request.Builder b = chain.request().newBuilder();
                b.addHeader("Accept", "application/json");
                b.addHeader("Authorization", TokenUtils.getToken());
                return chain.proceed(b.build());
            };
        } else {
            interceptor = (chain) -> {
                Request.Builder b = chain.request().newBuilder();
                b.addHeader("Accept", "application/json");
                return chain.proceed(b.build());
            };
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(StaticVarUtils.SERVER_URL)
                .addConverterFactory(JSerializerConverterFactory.create())
                .client(client)
                .build();
    }


    public AuthEP getAuthEndPoint() {
        return this.retrofit.create(AuthEP.class);
    }

    public PatrimonioEP getPatrimonioEndPoint() {
        return this.retrofit.create(PatrimonioEP.class);
    }

    public TesteEP getTestye() {
        return this.retrofit.create(TesteEP.class);
    }

    public ItemPatrimonioEP getItemPatrimonioEndPoint() {
        return this.retrofit.create(ItemPatrimonioEP.class);
    }

}
