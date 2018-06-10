package informatica.sp.senai.br.senaipatrimonio.teste;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.logic.models.User;
import io.felipepoliveira.jserializer.JSerializer;
import io.felipepoliveira.jserializer.json.JsonArray;
import io.felipepoliveira.jserializer.json.JsonStructure;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class JSerializerResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final JSerializer jSerializer;
    private final Type type;

    public JSerializerResponseBodyConverter(JSerializer jSerializer, Type type) {
        this.jSerializer = jSerializer;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

//
//        JsonStructure json = JSerializer.json().parse(value.string());
//        Log.e("testesDonega",json.toString());
//
//        if (json.isJsonObject()) {
//            Log.e("testesDonega", "54654654564");
//            T t = null;
//
////            t = json.asJsonObject().to((Class<? extends T>) ?);
////            Log.e("testesDonega",t.toString());
//            return (T) t;
//
//        } else {
//
//            T obj = null;
//
//            Log.e("testesDonega", "aaaa6666a");
//
//
//            json.asJsonArray().to((Class<? extends T>) obj);
//
//
//            return (T) json.asJsonArray();
//        }
//
return null;
    }
}
