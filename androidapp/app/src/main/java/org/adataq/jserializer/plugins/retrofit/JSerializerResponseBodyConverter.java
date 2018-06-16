package org.adataq.jserializer.plugins.retrofit;

import org.adataq.jserializer.JSerializer;
import org.adataq.jserializer.json.JsonStructure;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Converter;


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
