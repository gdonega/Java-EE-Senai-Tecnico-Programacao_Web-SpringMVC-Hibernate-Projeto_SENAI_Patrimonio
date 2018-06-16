package informatica.sp.senai.br.senaipatrimonio.util.jserializer;

import android.util.Log;

import org.adataq.jserializer.JSerializer;
import org.adataq.jserializer.json.JfoObject;

import java.util.Arrays;
import java.util.List;

public class JfoUtils {

    public static JfoObject createJfo(FilerType filerType, String... attributes) {
        List<String> atrList = Arrays.asList(attributes);
        return createJfo(filerType, atrList);
    }

    public static JfoObject createJfo(FilerType filerType, List<String> attributes) {

        String stringAttributes = getAttributes(attributes);
        String stringJfo = getStringJfo(filerType, stringAttributes);

        Log.e("oi",stringJfo);
        JfoObject jfoObject = JSerializer.json().parseJfo(stringJfo);

        return jfoObject;
    }

    private static String getAttributes(List<String> atrList) {
        Integer i = 0;
        String stringAttributes = "";

        do {
            if (i != 0)
                stringAttributes += ",";

            stringAttributes += "\"" + atrList.get(i) + "\"";
            i++;
        } while (i < atrList.size());

        return stringAttributes;
    }

    private static String getStringJfo(FilerType filerType, String stringAttributes) {
        String stringJfo = "{\"" + filerType.getTypeName() + "\" : [" + stringAttributes + "]}";
        return stringJfo;
    }

}
