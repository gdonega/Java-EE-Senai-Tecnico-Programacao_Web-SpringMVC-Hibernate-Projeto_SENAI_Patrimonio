package informatica.sp.senai.br.senaipatrimonio.teste;

import java.io.IOException;
import java.lang.reflect.Type;

import io.felipepoliveira.jserializer.JSerializer;
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

        T t = null;

        JSerializer.json().parse(value.string()).asJsonObject().to(t);

        return t;
    }
}
