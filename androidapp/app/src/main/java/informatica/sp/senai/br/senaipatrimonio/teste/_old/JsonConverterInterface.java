//package informatica.sp.senai.br.senaipatrimonio.teste;
//
//import java.io.IOException;
//
//import informatica.sp.senai.br.senaipatrimonio.logic.models.User;
//import io.felipepoliveira.jserializer.JSerializer;
//import io.felipepoliveira.jserializer.json.JsonObject;
//import io.felipepoliveira.jserializer.json.JsonStructure;
//import okhttp3.ResponseBody;
//import retrofit2.Converter;
//
///**
// * Created by Gustavo Donegá Queiroz(gdonega).
// */
//public class JsonConverterInterface<T> implements Converter<ResponseBody, T> {
//
//    @Override
//    public T convert(ResponseBody value) throws IOException {
//        JsonStructure json = JSerializer.json().parse(value.string());
//        T t = null;
//
//        if(json.isJsonObject()){
//            json.asJsonObject().to(t);
//        }else{
//            json.asJsonArray().to((Class<? extends T>) t);
//        }
//
//        return t;
//    }
//}
