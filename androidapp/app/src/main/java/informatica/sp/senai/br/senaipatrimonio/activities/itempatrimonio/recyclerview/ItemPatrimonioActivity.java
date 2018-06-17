package informatica.sp.senai.br.senaipatrimonio.activities.itempatrimonio.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import informatica.sp.senai.br.senaipatrimonio.activities.patrimonio.recyclerview.PatrimonioViewHolder;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.ItemPatrimonioDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.MethInterfaceDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.dao.PatrimonioRetrofitDAO;
import informatica.sp.senai.br.senaipatrimonio.logic.model.ItemPatrimonio;
import informatica.sp.senai.br.senaipatrimonio.logic.model.Patrimonio;
import informatica.sp.senai.br.senaipatrimonio.util.ActivitiesUtils;
import retrofit2.Call;
import retrofit2.Response;

public class ItemPatrimonioActivity extends AppCompatActivity implements PropertyChangeListener {

    private ItemPatrimonioDAO itemPatrimonioDAO = new ItemPatrimonioDAO();
    private List<Object> argsOk = new ArrayList<>();
    private List<Object> argsFailure = new ArrayList<>();
    private List<Object> results = new ArrayList<>();
    private Long itemId;

    private List<ItemPatrimonio> itemPatrimonios;
    private RVManager<ItemPatrimonio> itemPatrimonioRVManager= new RVManager<ItemPatrimonio>();

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_patrimonio);

        ActivitiesUtils.setStandardAppMenu(this);

        this.getSupportActionBar().setTitle("Items");

        itemPatrimonioDAO.addPropertyChangeListener(this);

        itemPatrimonios = new ArrayList<>();
        recyclerView = findViewById(R.id.rvItemsPatrimonio);

        itemId = getIntent().getLongExtra("idPatrimonio",-10);

        if (itemId < 0)
            finish();

        itemPatrimonioRVManager
                .setViewHolderClass(ItemPatrimonioViewHolder.class)
                .setList(itemPatrimonios)
                .setLayoutManager(new LinearLayoutManager(this))
                .getRvAdapterSet()
                .setCardXmlId(R.layout.card_item_patrimonio)
                .and().setRecyclerView(recyclerView)
                .build();
    }


    @Override
    protected void onResume() {
        super.onResume();
        ActivitiesUtils.closeActivityIfInvalidToken(this);

        itemPatrimonioDAO.getItemPatrimonio(itemId,argsOk, argsFailure, results, new MethInterfaceDAO<List<ItemPatrimonio>, List<ItemPatrimonio>>() {
            @Override
            public void okResponse(Call<List<ItemPatrimonio>> call, Response<List<ItemPatrimonio>> response, List<Object> argsOK, List<Object> results) throws IOException {

            }

            @Override
            public void failureResponse(Call<List<ItemPatrimonio>> call, Throwable t, List<Object> argsFailure) {

            }
        });
    }


    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (ItemPatrimonioDAO.GET_ITEMPATRIMONIO.equals(propertyChangeEvent.getPropertyName())) {
            Log.e("Patrimonios: ", results.get(0).toString());
            itemPatrimonioRVManager.setList((List<ItemPatrimonio>) results.get(0));
            itemPatrimonioRVManager.notificarQualquerMudanca();
        }
    }
}
