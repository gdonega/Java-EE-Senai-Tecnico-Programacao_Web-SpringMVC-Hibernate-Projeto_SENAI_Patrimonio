//package informatica.sp.senai.br.senaipatrimonio;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import informatica.sp.senai.br.senaipatrimonio.logic.models.User;
//import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.ObjectWithFilter;
//import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.RetrofitConfig;
//import retrofit2.Call;
//import retrofit2.Callback;
//
///**
// * Created by Gustavo Donegá Queiroz(gdonega).
// */
//public class _testes {
//    List<User> users = new ArrayList<>();
//        users.add(user);
//        users.add(user);
//        users.add(user);
//        users.add(user);
//
//    ObjectWithFilter<List<User>> list = new ObjectWithFilter<List<User>>(users, jfoObject);
//    Call<List<User>> call = new RetrofitConfig().getResteEndPoint().testeInputLista(list);
//        call.enqueue(new Callback<List<User>>() {
//        @Override
//        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//
//        }
//
//        @Override
//        public void onFailure(Call<List<User>> call, Throwable t) {
//
//        }
//    });
//
//
//    Call<List<User>> call = new RetrofitConfig().getResteEndPoint().testeInputLista(users);
//        call.enqueue(new Callback<List<User>>() {
//        @Override
//        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//
//        }
//
//        @Override
//        public void onFailure(Call<List<User>> call, Throwable t) {
//
//        }
//    });
//    Call<ResponseBody> call=new RetrofitConfig().getResteEndPoint().auth(user);
//        call.enqueue(new Callback<ResponseBody>(){
//        @Override
//        public void onResponse(Call<ResponseBody> call,Response<ResponseBody> response){
//
//                }
//
//        @Override
//        public void onFailure(Call<ResponseBody> call,Throwable t){
//
//        }
//    });
//}
