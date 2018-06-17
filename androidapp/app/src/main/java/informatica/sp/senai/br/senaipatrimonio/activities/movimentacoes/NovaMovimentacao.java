package informatica.sp.senai.br.senaipatrimonio.activities.movimentacoes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import informatica.sp.senai.br.senaipatrimonio.R;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.AmbienteDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.ItemPatrimonioDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.MethInterfaceDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Ambiente;
import informatica.sp.senai.br.senaipatrimonio.logic.model.ItemPatrimonio;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Movimentacao;
import informatica.sp.senai.br.senaipatrimonio.util.ActivitiesUtils;
import informatica.sp.senai.br.senaipatrimonio.util.ItemPatrimonioActivitiesUtils;
import retrofit2.Call;
import retrofit2.Response;

public class NovaMovimentacao extends AppCompatActivity implements PropertyChangeListener, View.OnClickListener{

    private AmbienteDAO ambienteDAO;
    private ItemPatrimonioDAO itemPatrimonioDAO;
    private List ambienteArgsOk = new ArrayList<>();
    private List ambienteArgsFailure = new ArrayList<>();
    private List<Object> ambienteResults = new ArrayList<>();

    private List movArgsOk = new ArrayList<>();
    private List movArgsFailure = new ArrayList<>();
    private List<Object> movResults = new ArrayList<>();

    private List itemArgsOk = new ArrayList<>();
    private List itemArgsFailure = new ArrayList<>();
    private List<Object> itemResults = new ArrayList<>();

    private Spinner sAmbientes;
    private Button btnMover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_movimentacao);

        ambienteDAO = new AmbienteDAO();
        itemPatrimonioDAO = new ItemPatrimonioDAO();

        sAmbientes = findViewById(R.id.sAmbientes);


        btnMover = findViewById(R.id.btnMover);
        btnMover.setOnClickListener(this);

        ActivitiesUtils.setStandardAppMenu(this);
        this.getSupportActionBar().setTitle("Nova Movimentação");

        ambienteDAO.addPropertyChangeListener(this);
        itemPatrimonioDAO.addPropertyChangeListener(this);

        ActivitiesUtils.closeActivityIfInvalidToken(this);

        ambienteDAO.getTodosAmbientes(ambienteArgsOk, ambienteArgsFailure, ambienteResults, new MethInterfaceDAO<List<Ambiente>, List<Ambiente>>() {
            @Override
            public void okResponse(Call<List<Ambiente>> call, Response<List<Ambiente>> response, List<Object> argsOK, List<Object> results) throws IOException {

            }

            @Override
            public void failureResponse(Call<List<Ambiente>> call, Throwable t, List<Object> argsFailure) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        Ambiente ambiente = (Ambiente) sAmbientes.getSelectedItem();
        moverItem(ambiente);
    }

    public void updateList() {

        List<Ambiente> ambientes = (List<Ambiente>) ambienteResults.get(0);

        Ambiente ambiente = new Ambiente();
        ambiente.setId(ItemPatrimonioActivitiesUtils.getAmbienteId());

        ambientes.remove(ambiente);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ambientes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sAmbientes.setAdapter(adapter);
    }

    public void moverItem(Ambiente ambiente){
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setAmbienteNovo(ambiente);

        itemPatrimonioDAO.moverItem(ItemPatrimonioActivitiesUtils.getItemId(), movimentacao, movArgsOk, movArgsFailure, movResults, new MethInterfaceDAO<Movimentacao, Movimentacao>() {
            @Override
            public void okResponse(Call<Movimentacao> call, Response<Movimentacao> response, List<Object> argsOK, List<Object> results) throws IOException {

            }

            @Override
            public void failureResponse(Call<Movimentacao> call, Throwable t, List<Object> argsFailure) {

            }
        });
    }


    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (AmbienteDAO.GET_TODOS_AMBIENTES.equals(propertyChangeEvent.getPropertyName())) {
            updateList();
        }

        if (ItemPatrimonioDAO.MOVER_ITEMPATRIMONIO.equals(propertyChangeEvent.getPropertyName())) {

            itemPatrimonioDAO.getItemById(ItemPatrimonioActivitiesUtils.getItemId(), itemArgsOk, itemArgsFailure, itemResults, new MethInterfaceDAO<ItemPatrimonio, ItemPatrimonio>() {
                @Override
                public void okResponse(Call<ItemPatrimonio> call, Response<ItemPatrimonio> response, List<Object> argsOK, List<Object> results) throws IOException {
                    ItemPatrimonioActivitiesUtils.saveItemPatrimonio(response.body());
                }

                @Override
                public void failureResponse(Call<ItemPatrimonio> call, Throwable t, List<Object> argsFailure) {

                }
            });
            finish();
        }

    }



}
