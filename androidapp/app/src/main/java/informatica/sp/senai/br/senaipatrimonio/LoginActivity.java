package informatica.sp.senai.br.senaipatrimonio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import informatica.sp.senai.br.senaipatrimonio.logic.models.User;
import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.RetrofitConfig;
import io.felipepoliveira.jserializer.JSerializer;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{

    //Declare
    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Get Instances
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        //setClick
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnLogin)
            doLogin();


    }



    public void doLogin(){
        Log.e("testesDonega", etEmail.getText().toString());
        Log.e("testesDonega", etPassword.getText().toString());
        Log.e("testesDonega", JSerializer.json().serialize(new TesteClass("NomeManeiro", "LegalManeiro")).asJsonObject().toString());

        User user = new User();
        user.setEmail(etEmail.getText().toString());
        user.setPassword(etPassword.getText().toString());

        Call<ResponseBody> call = new RetrofitConfig().getResteEndPoint().auth(user);


//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    Log.e("testesDonega",response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });

    }
}


class TesteClass{
    public String nome;
    public String legal;

    public TesteClass(String nome, String legal) {
        this.nome = nome;
        this.legal = legal;
    }
}
