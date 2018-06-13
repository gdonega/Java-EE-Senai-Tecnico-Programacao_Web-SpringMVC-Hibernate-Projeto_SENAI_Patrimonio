package informatica.sp.senai.br.senaipatrimonio.teste;

import android.util.JsonWriter;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.ObjectWithFilter;
import io.felipepoliveira.jserializer.JSerializer;
import io.felipepoliveira.jserializer.json.JfoObject;
import io.felipepoliveira.jserializer.json.JsonObject;
import io.felipepoliveira.jserializer.json.JsonSerializationBuilder;
import io.felipepoliveira.jserializer.json.JsonStructure;
import io.felipepoliveira.jserializer.json.JsonValue;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
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
            return RequestBody.create(MEDIA_TYPE, jsonStructure.asJsonObject().toString());
        }else{
            return RequestBody.create(MEDIA_TYPE, jsonStructure.asJsonArray().toString());
        }

    }


}
