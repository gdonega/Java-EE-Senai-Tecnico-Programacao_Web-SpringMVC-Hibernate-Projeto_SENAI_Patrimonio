package informatica.sp.senai.br.senaipatrimonio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.adataq.jserializer.json.JfoObject;
import org.adataq.jserializer.plugins.retrofit.models.ObjectWithFilter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.activities.patrimonio.recyclerview.PatrimoniosActivity;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.AuthRetrofitDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.MethInterfaceDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Usuario;
import informatica.sp.senai.br.senaipatrimonio.util.TokenUtils;
import informatica.sp.senai.br.senaipatrimonio.util.jserializer.FilerType;
import informatica.sp.senai.br.senaipatrimonio.util.jserializer.JfoUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, PropertyChangeListener {

    //Declare
    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;

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



        etEmail.setText("admin@email.com");
        etPassword.setText("admin132");

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

        JfoObject jfoObject = JfoUtils.createJfo(FilerType.REQUIRE, "senha", "email", "nome");
        ObjectWithFilter<Usuario> requestObj = new ObjectWithFilter<Usuario>(usuario, jfoObject);

        dao.getToken(requestObj, okArgs, failureArgs, results, new MethInterfaceDAO<ResponseBody, ResponseBody>() {
            @Override
            public void okResponse(Call<ResponseBody> call, Response<ResponseBody> response, List<Object> argsOK, List<Object> results) {

            }

            @Override
            public void failureResponse(Call<ResponseBody> call, Throwable t, List<Object> argsFailure) {
            }
        });


    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (AuthRetrofitDAO.GET_TOKEN.equals(propertyChangeEvent.getPropertyName())) {
            TokenUtils.saveTokenWithoutBearer(results.get(0).toString());
            goToNextActivity();
        }
    }

    private void goToNextActivity() {
        Intent intent = new Intent(this, PatrimoniosActivity.class);
        startActivity(intent);
    }



}

