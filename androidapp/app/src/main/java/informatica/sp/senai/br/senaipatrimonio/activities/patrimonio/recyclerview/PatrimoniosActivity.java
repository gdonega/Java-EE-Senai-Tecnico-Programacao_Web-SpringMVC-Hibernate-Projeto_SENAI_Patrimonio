package informatica.sp.senai.br.senaipatrimonio.activities.patrimonio.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gdonega.io.recyclerviewhelper.RVManager;
import informatica.sp.senai.br.senaipatrimonio.R;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.MethInterfaceDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.PatrimonioRetrofitDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Patrimonio;
import informatica.sp.senai.br.senaipatrimonio.util.ActivitiesUtils;
import retrofit2.Call;
import retrofit2.Response;

public class PatrimoniosActivity extends AppCompatActivity implements PropertyChangeListener {

    private PatrimonioRetrofitDAO patrimonioRetrofitDAO = new PatrimonioRetrofitDAO();
    private List<Object> argsOk = new ArrayList<>();
    private List<Object> argsFailure = new ArrayList<>();
    private List<Object> results = new ArrayList<>();

    private List<Patrimonio> patrimonios;
    private RVManager<Patrimonio> patrimonioRvManager = new RVManager<Patrimonio>();

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrimonios);

        ActivitiesUtils.setStandardAppMenu(this);

        this.getSupportActionBar().setTitle("Patrimonios");

        patrimonioRetrofitDAO.addPropertyChangeListener(this);

        patrimonios = new ArrayList<>();
        recyclerView = findViewById(R.id.rvPatrimonios);


        patrimonioRvManager
                .setViewHolderClass(PatrimonioViewHolder.class)
                .setList(patrimonios)
                .setLayoutManager(new LinearLayoutManager(this))
                .getRvAdapterSet()
                .setCardXmlId(R.layout.card_patrimonio)
                .and().setRecyclerView(recyclerView)
                .build();

    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivitiesUtils.closeActivityIfInvalidToken(this);

        patrimonioRetrofitDAO.getPatrimonios(argsOk, argsFailure, results, new MethInterfaceDAO<List<Patrimonio>, List<Patrimonio>>() {
            @Override
            public void okResponse(Call<List<Patrimonio>> call, Response<List<Patrimonio>> response, List<Object> argsOK, List<Object> results) throws IOException {

            }

            @Override
            public void failureResponse(Call<List<Patrimonio>> call, Throwable t, List<Object> argsFailure) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (PatrimonioRetrofitDAO.GET_PATRIMONIOS.equals(propertyChangeEvent.getPropertyName())) {
            Log.e("Patrimonios: ", results.get(0).toString());
            patrimonioRvManager.setList((List<Patrimonio>) results.get(0));
            patrimonioRvManager.notificarQualquerMudanca();
        }

    }
}
