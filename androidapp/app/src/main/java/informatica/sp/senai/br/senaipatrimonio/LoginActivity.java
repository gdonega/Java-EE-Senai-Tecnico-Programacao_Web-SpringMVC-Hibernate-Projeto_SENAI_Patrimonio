package informatica.sp.senai.br.senaipatrimonio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.logic.daotestes.AuthRetrofitDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.daotestes.MethInterfaceDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.models.User;
import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.ObjectWithFilter;
import informatica.sp.senai.br.senaipatrimonio.logic.retrofit.RetrofitConfig;
import informatica.sp.senai.br.senaipatrimonio.utils.TokenUtils;
import io.felipepoliveira.jserializer.JSerializer;
import io.felipepoliveira.jserializer.json.JfoObject;
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

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnLogin)
            doLogin();
    }


    public void doLogin() {
        User user = new User();
        user.setEmail(etEmail.getText().toString());
        user.setSenha(etPassword.getText().toString());

        /**
         * Com o JFO
         */
//        JfoObject jfoObject = JSerializer.json().parseJfo("{\"require\" : [\"senha\", \"email\"]}");
//
//        ObjectWithFilter<User> requestObj = new ObjectWithFilter<User>(user, jfoObject);
//
//        dao.getToken(requestObj, okArgs, failureArgs, results, new MethInterfaceDAO() {
//            @Override
//            public void okResponse(Call<ResponseBody> call, Response<ResponseBody> response, List<Object> argsOK, List<Object> results) throws IOException {
//            }
//
//            @Override
//            public void failureResponse(Call<ResponseBody> call, Throwable t, List<Object> argsFailure) {
//            }
//        });


        /**
         * Sem JFO
         */
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
        if (AuthRetrofitDAO.AUTH.equals(propertyChangeEvent.getPropertyName())) {
            TokenUtils.saveTokenWithoutBearer(this, (String) results.get(0));
            tvTeste.setText(TokenUtils.getToken(this));
        }
    }
}
