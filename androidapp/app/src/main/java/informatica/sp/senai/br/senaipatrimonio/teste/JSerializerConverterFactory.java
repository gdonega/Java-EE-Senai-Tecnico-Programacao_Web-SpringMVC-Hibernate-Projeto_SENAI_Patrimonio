package informatica.sp.senai.br.senaipatrimonio.teste;

import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.felipepoliveira.jserializer.JSerializer;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public final class JSerializerConverterFactory extends Converter.Factory{

    private final JSerializer jSerializer;

    public static JSerializerConverterFactory create(){
        return new JSerializerConverterFactory(new JSerializer());
    }

    public static JSerializerConverterFactory create(JSerializer jSerializer){
        return new JSerializerConverterFactory(jSerializer);
    }

    private JSerializerConverterFactory(JSerializer jSerializer) {
        this.jSerializer = jSerializer;
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        return new JSerializerResponseBodyConverter(jSerializer, type);
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {

        return new JSerializerRequestBodyConverter<>(jSerializer);
    }

}
