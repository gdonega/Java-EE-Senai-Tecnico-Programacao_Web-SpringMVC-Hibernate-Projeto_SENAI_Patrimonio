package br.senai.sp.informatica.senaipatrimonio.utils;

import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;
import org.springframework.util.DigestUtils;

import java.io.File;

public class OutrosMetodos {

    public static String hashString(String string) {
        return DigestUtils.md5DigestAsHex(string.getBytes());
    }


    public static String gerarCorpoDoEmail(String nome, String senha) {
        return "Olá, " + nome + "! Bem-vindo ao Sistema SENAI Patrimonio.\n\n\n\n"
                + "A sua senha inicial pé: " + senha
                + "\n\n\n\n"
                + "Ela poder� ser trocada quando voc� quizer";
    }


    public static String getCaminhoPatrimonioImagem(Patrimonio patrimonio) {
        String caminho;

        caminho = Constantes.URL_BASE_FOTO_PATRIMONIO + Constantes.INICIO_NOME_IMAGEM + patrimonio.getId();

        return caminho;
    }

    public static String getCaminhoItemPatrimonioImagem(ItemPatrimonio itemPatrimonio){
        String caminho;

        caminho = Constantes.URL_BASE_FOTO_ITEM_PATRIMONIO + Constantes.INICIO_NOME_IMAGEM + itemPatrimonio.getId();

        File file = new File(caminho);

        if (!file.exists()) {
            caminho = getCaminhoPatrimonioImagem(itemPatrimonio.getPatrimonio());
        }
        return caminho;
    }
}
