package informatica.sp.senai.br.senaipatrimonio.activities.movimentacoes.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gdonega.io.recyclerviewhelper.RVManager;
import informatica.sp.senai.br.senaipatrimonio.R;
import informatica.sp.senai.br.senaipatrimonio.activities.movimentacoes.NovaMovimentacao;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.ItemPatrimonioDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.MethInterfaceDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.PatrimonioRetrofitDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.model.ItemPatrimonio;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Movimentacao;
import informatica.sp.senai.br.senaipatrimonio.util.ActivitiesUtils;
import informatica.sp.senai.br.senaipatrimonio.util.ItemPatrimonioActivitiesUtils;
import retrofit2.Call;
import retrofit2.Response;

public class MovimentacoesActivity extends AppCompatActivity implements PropertyChangeListener, View.OnClickListener {

    private ItemPatrimonioDAO itemPatrimonioDAO = new ItemPatrimonioDAO();
    private List<Object> argsOk = new ArrayList<>();
    private List<Object> argsFailure = new ArrayList<>();
    private List<Object> results = new ArrayList<>();
    private Button btnNovaMovimetacao;

    private List<Movimentacao> movimentacaoList;
    private RVManager<Movimentacao> movimentacaoRVManager = new RVManager<Movimentacao>();

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimentacoes);

        btnNovaMovimetacao = findViewById(R.id.btnNovaMov);
        recyclerView = findViewById(R.id.rvMovimentacoes);

        btnNovaMovimetacao.setOnClickListener(this);

        ActivitiesUtils.setStandardAppMenu(this);
        this.getSupportActionBar().setTitle("Movimentações");

        itemPatrimonioDAO.addPropertyChangeListener(this);

        movimentacaoList = new ArrayList<>();


        movimentacaoRVManager
                .setViewHolderClass(MovimentacaoViewHolder.class)
                .setList(movimentacaoList)
                .setLayoutManager(new LinearLayoutManager(this))
                .getRvAdapterSet()
                .setCardXmlId(R.layout.card_movimentacao)
                .and().setRecyclerView(recyclerView)
                .build();

    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivitiesUtils.closeActivityIfInvalidToken(this);

        itemPatrimonioDAO.getMovimentacoesDoItem(ItemPatrimonioActivitiesUtils.getItemId(), argsOk, argsFailure, results, new MethInterfaceDAO<List<Movimentacao>, List<Movimentacao>>() {
            @Override
            public void okResponse(Call<List<Movimentacao>> call, Response<List<Movimentacao>> response, List<Object> argsOK, List<Object> results) throws IOException {

            }

            @Override
            public void failureResponse(Call<List<Movimentacao>> call, Throwable t, List<Object> argsFailure) {

            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (ItemPatrimonioDAO.GET_ITEMPATRIMONIO_MOV.equals(propertyChangeEvent.getPropertyName())) {
            Log.e("Movimentacoes: ", results.get(0).toString());
            movimentacaoRVManager.setList((List<Movimentacao>) results.get(0));
            movimentacaoRVManager.notificarQualquerMudanca();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, NovaMovimentacao.class);
        startActivity(intent);
    }
}
