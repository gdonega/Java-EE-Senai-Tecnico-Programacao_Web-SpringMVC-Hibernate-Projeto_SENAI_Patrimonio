package informatica.sp.senai.br.senaipatrimonio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.adataq.jserializer.JSerializer;
import org.adataq.jserializer.json.JfoObject;
import org.adataq.jserializer.plugins.retrofit.models.ObjectWithFilter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


import informatica.sp.senai.br.senaipatrimonio.activities.PatrimoniosActivity;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.AuthRetrofitDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.MethInterfaceDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Usuario;

import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.RetrofitConfig;
import informatica.sp.senai.br.senaipatrimonio.util.TokenUtils;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, PropertyChangeListener {

    //Declare
    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;
    private TextView tvTeste;

    private AuthRetrofitDAO dao = new AuthRetrofitDAO();
    private List<Object> okArgs = new ArrayList<Object>();
    private List<Object> failureArgs = new ArrayList<Object>();
    private List<Object> results = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dao.addPropertyChangeListener(this);


        //Get Instances
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvTeste = findViewById(R.id.tvTeste);

        //setClick
        btnLogin.setOnClickListener(this);

        if (TokenUtils.isTokenValid())
            goToNextActivity();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin)
            doLogin();
    }


    public void doLogin() {
        Usuario usuario = new Usuario();
        usuario.setEmail(etEmail.getText().toString());
        usuario.setSenha(etPassword.getText().toString());

        usuario
                .setNome("006");
        JfoObject jfoObject = JSerializer.json().parseJfo("{\"require\" : [\"senha\", \"email\"]}");

        ObjectWithFilter<Usuario> requestObj = new ObjectWithFilter<Usuario>(usuario, jfoObject);

        dao.getToken(usuario, okArgs, failureArgs, results, new MethInterfaceDAO() {
            @Override
            public void okResponse(Call<ResponseBody> call, Response<ResponseBody> response, List<Object> argsOK, List<Object> results) {

            }

            @Override
            public void failureResponse(Call<ResponseBody> call, Throwable t, List<Object> argsFailure) {
            }
        });


//        Call<ResponseBody> usaraa = new RetrofitConfig(false).getTestye().usuario(usuario);
//
//        usaraa.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    Log.e("aaa","aaa:  "+response.body().string());
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

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (AuthRetrofitDAO.AUTH.equals(propertyChangeEvent.getPropertyName())) {
            TokenUtils.saveTokenWithoutBearer(results.get(0).toString());
            goToNextActivity();
        }
    }

    private void goToNextActivity() {
        Intent intent = new Intent(this, PatrimoniosActivity.class);
        startActivity(intent);
    }


}

