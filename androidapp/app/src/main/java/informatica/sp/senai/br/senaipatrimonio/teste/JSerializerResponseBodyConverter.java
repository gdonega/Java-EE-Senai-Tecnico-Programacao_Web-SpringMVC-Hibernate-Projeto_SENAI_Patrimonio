package informatica.sp.senai.br.senaipatrimonio.teste;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import informatica.sp.senai.br.senaipatrimonio.logic.models.User;
import io.felipepoliveira.jserializer.JSerializer;
import io.felipepoliveira.jserializer.json.JsonObject;
import io.felipepoliveira.jserializer.json.JsonStructure;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * Created by Gustavo Donegá Queiroz(gdonega).
 */
public class JSerializerResponseBodyConverter implements Converter<ResponseBody, Object> {

    private final JSerializer jSerializer;
    private final Type type;

    public JSerializerResponseBodyConverter(JSerializer jSerializer, Type type) {
        this.jSerializer = jSerializer;
        this.type = type;
    }


    @Override
    public Object convert(ResponseBody value) {
        String string = null;


        try {
            string = value.string().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String objType = type.toString();

        JsonStructure json = jSerializer.json().parse(string);

        try {
            if (json.isJsonObject()) {
                objType = objType.split(" ")[1];
                return json.asJsonObject().to(Class.forName(objType));
            } else {
                String genericCollectionTypeName = objType.substring(objType.indexOf("<") + 1, objType.indexOf(">"));
                return Arrays.asList(json.asJsonArray().to(Class.forName(genericCollectionTypeName)));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }

}
