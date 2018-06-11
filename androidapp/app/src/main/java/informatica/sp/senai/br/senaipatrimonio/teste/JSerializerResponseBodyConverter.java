package informatica.sp.senai.br.senaipatrimonio.teste;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import io.felipepoliveira.jserializer.JSerializer;
import io.felipepoliveira.jserializer.json.JsonStructure;
import io.felipepoliveira.jserializer.json.JsonValue;
import io.felipepoliveira.jserializer.utils.ReflectionUtils;
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

    @SuppressLint("NewApi")
    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonStructure json = JSerializer.json().parse(value.string());


//            Log.e("aaaaaaTeste", "Bom Dia: "+String.valueOf(TypeToken.get(type)));
//            ParameterizedType ty = (ParameterizedType) type;
//            Log.e("aaaaaaTeste", "Bom Dia: "+String.valueOf("BOm dia: "+type.getClass().getDeclaredFields().length));
            Log.e("aaaaaaTeste", "Bom Dia: "+String.valueOf("BOm dia: "+type.getClass().));
//        if (json.isJsonArray()) {
//            List<JsonValue> values = json.asJsonArray().getValues();
////type.getClass().asSubclass(a);
//
//            for (JsonValue t : values) {
////                T loo = t.asJsonObject().to((Class<? extends T>) a.getClass());
////                Log.e("aaaaaaTeste", String.valueOf());
//            }
//        }
//
//        T oo = null;
//        Log.e("aaaaaaTeste", json.toString());
//        Log.e("aaaaaaTeste", "pkg: " + String.valueOf(type.toString()));
//        Log.e("aaaaaaTeste", "pkg: " + String.valueOf(oo.getClass().toString()));
//
//
//        try {
////            T[] aaa = json.asJsonArray().to(type.);
//
////            Log.e("aaaaaaTeste", "pkg: " + aaa.toString());
//
//        } catch (Exception e) {
//            Log.e("aaaaaaTeste", "pkg: " + "DEU MERDA");
//            e.printStackTrace();
//        }
//
//        if (json.isJsonArray()) {
//            try {
//                Log.e("aaaaaaTeste", "A: " + json.asJsonArray().to(Class.forName(type.getClass().getName())).toString());
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                Log.e("aaaaaaTeste", "A: " + json.asJsonObject().to(Class.forName(type.getClass().getName())).toString());
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }

////
//        JsonStructure json = JSerializer.json().parse(value.string());
//        Log.e("testesDonega",json.toString());
//
//        if (json.isJsonObject()) {
//            Log.e("testesDonega", "54654654564");
//            T t = null;
//
//            t = json.asJsonObject().to(T.);
//            Log.e("testesDonega",t.toString());
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
