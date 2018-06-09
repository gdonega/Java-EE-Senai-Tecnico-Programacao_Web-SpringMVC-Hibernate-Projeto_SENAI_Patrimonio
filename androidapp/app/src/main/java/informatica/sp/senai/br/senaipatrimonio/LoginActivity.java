package informatica.sp.senai.br.senaipatrimonio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.logic.daotestes.AuthRetrofitDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.daotestes.MethInterfaceDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, PropertyChangeListener {

    //Declare
    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;
    private AuthRetrofitDAO dao = new AuthRetrofitDAO();
    private String token = null;
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

        //setClick
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnLogin)
            doLogin();


    }


    public void doLogin() {
        Log.e("testesDonega", etEmail.getText().toString());
        Log.e("testesDonega", etPassword.getText().toString());
//        Log.e("testesDonega", JSerializer.json().serialize(new TesteClass("NomeManeiro", "LegalManeiro")).asJsonObject().toString());

        User user = new User();
        user.setEmail(etEmail.getText().toString());
        user.setSenha(etPassword.getText().toString());

//        Call<ResponseBody> call = new RetrofitConfig().getResteEndPoint().auth(user);
//
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                if (response.isSuccessful()) {
//                    try {
//                        JSONObject objeto = new JSONObject(response.body().string());
//                        Log.e("testesDonega", "Token: "+ objeto.getString("token"));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }else{
//                    Log.e("testesDonega", "deu bosta: " );
//
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                Log.e("testesDonega", "deu bosta: " + t.getMessage());
//            }
//        });


        dao.getToken(user, okArgs, failureArgs, results,new MethInterfaceDAO() {
            @Override
            public void okResponse(Call<ResponseBody> call, Response<ResponseBody> response, List<Object> argsOK, List<Object> results) throws IOException {

            }

            @Override
            public void failureResponse(Call<ResponseBody> call, Throwable t, List<Object> argsFailure) {

            }
        });


    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (AuthRetrofitDAO.AUTH.equals(propertyChangeEvent.getPropertyName()))
            Log.e("testesDonega", "Token: " + results.get(0));
    }
}

