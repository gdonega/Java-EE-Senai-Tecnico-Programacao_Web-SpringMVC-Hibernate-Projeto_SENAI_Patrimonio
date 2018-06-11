package informatica.sp.senai.br.senaipatrimonio.teste;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Type;


import io.felipepoliveira.jserializer.JSerializer;
import io.felipepoliveira.jserializer.json.JsonStructure;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class JSerializerResponseBodyConverter implements Converter<ResponseBody, JsonStructure> {

    private final JSerializer jSerializer;
    private final Type type;

    public JSerializerResponseBodyConverter(JSerializer jSerializer, Type type) {
        this.jSerializer = jSerializer;
        this.type = type;
    }


    @Override
    public JsonStructure convert(ResponseBody value) throws IOException {

        try {
            T t = type.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

//        Log.e("ResponseDonega",value.string());
//        Log.e("ResponseDonega",type.getClass().getSimpleName());
//        Log.e("ResponseDonega",type.getClass().getClass().toString());
//        Log.e("ResponseDonega",type.getClass().getClasses().toString());
//        Log.e("ResponseDonega",type.getClass().getClasses().getClass().getComponentType().toString());
//        Log.e("ResponseDonega",type.getClass().getClasses().getClass().getComponentType().getClasses().toString());
//        Log.e("ResponseDonega",type.getClass().getClasses().getClass().getComponentType().getClasses().toString());

        try {
            JsonStructure json =  JSerializer.json().parse(value.string());

//            if(json.isJsonArray()){
//                json.asJsonArray().to(?);
//            }
//            else{
//                json.asJsonObject().to(?);
//            }
        }catch (Exception e){

        }
        return null;
    }
}
