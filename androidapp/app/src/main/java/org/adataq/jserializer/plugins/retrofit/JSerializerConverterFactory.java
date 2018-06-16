package org.adataq.jserializer.plugins.retrofit;


import org.adataq.jserializer.JSerializer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


public final class JSerializerConverterFactory extends Converter.Factory {

    private final JSerializer jSerializer;

    public static JSerializerConverterFactory create() {
        return new JSerializerConverterFactory(new JSerializer());
    }

    public static JSerializerConverterFactory create(JSerializer jSerializer) {
        return new JSerializerConverterFactory(jSerializer);
    }

    private JSerializerConverterFactory(JSerializer jSerializer) {
        this.jSerializer = jSerializer;
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        return new JSerializerResponseBodyConverter(jSerializer, type);
    }


    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new JSerializerRequestBodyConverter<>(jSerializer);
    }

}
