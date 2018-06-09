package informatica.sp.senai.br.senaipatrimonio.teste;

import android.util.JsonWriter;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;

import java.nio.charset.Charset;

import io.felipepoliveira.jserializer.JSerializer;
import io.felipepoliveira.jserializer.json.JsonStructure;
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
    private final Type type;

    JSerializerRequestBodyConverter(JSerializer jSerializer, Type type) {
        this.jSerializer = jSerializer;
        this.type = type;
    }

    @Override
    public RequestBody convert(T value) throws IOException {

        JsonStructure json = jSerializer.json().serialize(value);

        Log.e("testesDonega", json.asJsonObject().toString());
        return RequestBody.create(MEDIA_TYPE,json.asJsonObject().toString().getBytes());


    }


}
