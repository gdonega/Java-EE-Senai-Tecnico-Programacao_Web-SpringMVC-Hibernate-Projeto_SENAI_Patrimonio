package informatica.sp.senai.br.senaipatrimonio.util.jserializer;

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
        JfoObject jfoObject = JSerializer.json().parseJfo(buildStringJfo(filerType,attributes));
        return jfoObject;
    }


    public static String createStringJfoHttpStandard(FilerType filerType, String... attributes) {
        List<String> atrList = Arrays.asList(attributes);
        return createStringJfoHttpStandard(filerType, atrList);
    }

    public static String createStringJfoHttpStandard(FilerType filerType, List<String> attributes) {
        String stringJfo = buildStringJfo(filerType,attributes);
        return "JFO "+stringJfo;
    }

    public static String createStringJfo(FilerType filerType, String... attributes) {
        List<String> atrList = Arrays.asList(attributes);
        return createStringJfo(filerType, atrList);
    }

    public static String createStringJfo(FilerType filerType, List<String> attributes) {
        String stringJfo = buildStringJfo(filerType,attributes);
        return stringJfo;
    }

    private static String buildStringJfo(FilerType filerType, List<String> attributes) {
        String stringAttributes = getAttributes(attributes);
        String stringJfo = "{\"" + filerType.getTypeName() + "\" : [" + stringAttributes + "]}";
        return stringJfo;
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


}
