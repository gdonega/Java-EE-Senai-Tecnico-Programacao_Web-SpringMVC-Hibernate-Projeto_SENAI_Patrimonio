package informatica.sp.senai.br.senaipatrimonio.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import informatica.sp.senai.br.senaipatrimonio.R;
import informatica.sp.senai.br.senaipatrimonio.utils.ActivitiesUtils;

public class PatrimoniosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrimonios);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivitiesUtils.closeActivity(this);
    }

    public void onClicka(View view) {

    }
}
