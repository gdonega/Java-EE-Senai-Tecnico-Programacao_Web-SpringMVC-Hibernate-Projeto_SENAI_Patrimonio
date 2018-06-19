package informatica.sp.senai.br.senaipatrimonio.activities.movimentacoes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import informatica.sp.senai.br.senaipatrimonio.R;

public class MovEasterEggActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mov_easter_egg);
        imageView = findViewById(R.id.ivTop);
        imageView2 = findViewById(R.id.ivTop2);
//        Glide.with(this).load(R.drawable.felipeone).into(imageView);
//        Glide.with(this).load(R.drawable.felipetwo).into(imageView2);
        imageView.setImageResource(R.drawable.felipeone);
        imageView2.setImageResource(R.drawable.felipetwo);

    }
}
