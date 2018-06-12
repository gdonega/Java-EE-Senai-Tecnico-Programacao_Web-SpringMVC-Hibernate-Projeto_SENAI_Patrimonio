package informatica.sp.senai.br.senaipatrimonio.teste;

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
public class JSerializerResponseBodyConverter<T> implements Converter<ResponseBody, Object> {

    private final JSerializer jSerializer;
    private final Type type;

    public JSerializerResponseBodyConverter(JSerializer jSerializer, Type type) {
        this.jSerializer = jSerializer;
        this.type = type;
    }


    @Override
    public Object convert(ResponseBody value) throws IOException {

//        Log.e("OI", value.string().toString());
        String jsonA = "{\"nome\":\"HAHAHAHAH VEIO DO SERVER\"}";
//        String jsonToParse = String.valueOf(value.string().toString());
//        Log.e("OI", jsonToParse);
//        if(JSerializer.json().parse(jsonA).isJsonObject()){
//            Log.e("OI", "OKKKKKK TA TUDO CERTO");
//
//        }

            Log.e("OI", "OKKKKKK TA TUDO CERTO");

//        Log.e("OI", type.toString());
//        JsonStructure json = JSerializer.json().parse(value.string()).asJsonObject();
        JsonStructure json = JSerializer.json().parse(jsonA).asJsonObject();
        String okk = type.toString().split(" ")[1];


        try {
             Class<? extends Class> t = (Class<? extends Class>) Class.forName(okk);

            Log.e("asdfasdf", "AAAAA: " + okk);
            Log.e("asdfasdf", "Tenha um bom dia: " + t.toString());

            if (json.isJsonObject()) {

                Log.e("asdfasdf", "Tá fofo: " + t.toString());
                Object tzz = json.asJsonObject().to(Class.forName(okk));
                Log.e("asd"," "+ tzz.toString());
                return tzz;
//                return t;
            } else {
                Log.e("asdfasdf", "ta uma bosta");
//                json.asJsonArray().to(?);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
