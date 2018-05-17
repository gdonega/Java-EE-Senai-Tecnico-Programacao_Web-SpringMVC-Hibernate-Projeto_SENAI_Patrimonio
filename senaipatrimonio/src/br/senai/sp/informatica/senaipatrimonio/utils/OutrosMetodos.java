package br.senai.sp.informatica.senaipatrimonio.utils;

import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletContext;
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


    public static String getCaminhoItemPatrimonioImagem(ItemPatrimonio itemPatrimonio, String caminhoCompleto){
        String caminho;
        String sufixo = Constantes.INICIO_NOME_IMAGEM + itemPatrimonio.getId();

        String teste = caminhoCompleto + sufixo;

        File file = new File(teste);

        if (!file.exists()) {
            caminho = getCaminhoPatrimonioImagem(itemPatrimonio.getPatrimonio());
        }else{
            caminho = Constantes.URL_BASE_FOTO_ITEM_PATRIMONIO + sufixo;
        }
        return caminho;
    }


    public static void excluirFotoFile(Long id, String caminhoCompleto){
        String caminho;
        String sufixo = Constantes.INICIO_NOME_IMAGEM + id;

        String teste = caminhoCompleto + sufixo;

        File file = new File(teste);

        if (file.exists()) {
            file.delete();
        }

    }


}
