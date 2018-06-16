package org.adataq.jserializer.plugins.retrofit;

import android.util.Log;

import org.adataq.jserializer.JSerializer;
import org.adataq.jserializer.json.JfoObject;
import org.adataq.jserializer.json.JsonSerializationBuilder;
import org.adataq.jserializer.json.JsonStructure;
import org.adataq.jserializer.plugins.retrofit.models.ObjectWithFilter;

import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;


public class JSerializerRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");


    private final JSerializer jSerializer;


    JSerializerRequestBodyConverter(JSerializer jSerializer) {
        this.jSerializer = jSerializer;

    }

    @Override
    public RequestBody convert(T value) {

        JsonSerializationBuilder builder = jSerializer.json();

        JsonStructure jsonStructure;

        try {
            ObjectWithFilter objectWithFilter = (ObjectWithFilter) value;
            JfoObject filter = objectWithFilter.getFilter();
            jsonStructure = builder.withJfo(filter).serialize(objectWithFilter.getT());
        }catch (Exception e){
            jsonStructure = builder.serialize(value);
        }

        if(jsonStructure.isJsonObject()) {
            Log.e("Teste",jsonStructure.asJsonObject().toString());
            return RequestBody.create(MEDIA_TYPE, jsonStructure.asJsonObject().toString());
        }else{
            return RequestBody.create(MEDIA_TYPE, jsonStructure.asJsonArray().toString());
        }

    }


}
