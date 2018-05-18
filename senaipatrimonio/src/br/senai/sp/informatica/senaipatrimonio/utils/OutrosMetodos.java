package br.senai.sp.informatica.senaipatrimonio.utils;

import java.io.File;

import org.springframework.util.DigestUtils;

import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;

public class OutrosMetodos {

	/**
	 * "Hashear" qualquer String
	 * 
	 * @param string
	 * @return
	 */
	public static String hashString(String string) {
		return DigestUtils.md5DigestAsHex(string.getBytes());
	}

	/**
	 * Mensagem padrão do corpo do email
	 * 
	 * @param nome
	 * @param senha
	 * @return String
	 */
	public static String gerarCorpoDoEmail(String nome, String senha) {
		return "Olá, " + nome + "! Bem-vindo ao Sistema SENAI Patrimonio.\n\n\n\n" + "A sua senha inicial é:" + senha
				+ "\n\n\n\n" + "Ela poderá ser trocada quando você quizer";
	}

	/**
	 * Recupera o caminho da imagem de um patrimonio
	 * 
	 * @param patrimonio
	 * @return String
	 */
	public static String getCaminhoPatrimonioImagem(Patrimonio patrimonio) {
		String caminho;

		caminho = Constantes.URL_BASE_FOTO_PATRIMONIO + Constantes.INICIO_NOME_IMAGEM + patrimonio.getId();

		return caminho;
	}

	/**
	 * Recupera o caminho da imagem de uma Item
	 * 
	 * @param itemPatrimonio
	 * @param caminhoCompleto
	 * @return String
	 */
	public static String getCaminhoItemPatrimonioImagem(ItemPatrimonio itemPatrimonio, String caminhoCompleto) {
		String caminho;
		String sufixo = Constantes.INICIO_NOME_IMAGEM + itemPatrimonio.getId();

		String teste = caminhoCompleto + sufixo;

		File file = new File(teste);

		if (!file.exists()) {
			caminho = getCaminhoPatrimonioImagem(itemPatrimonio.getPatrimonio());
		} else {
			caminho = Constantes.URL_BASE_FOTO_ITEM_PATRIMONIO + sufixo;
		}
		return caminho;
	}

	/**
	 * Exclui uma foto do servidor
	 * 
	 * @param id
	 * @param caminhoCompleto
	 */
	public static void excluirFotoFile(Long id, String caminhoCompleto) {
		String caminho;
		String sufixo = Constantes.INICIO_NOME_IMAGEM + id;

		String teste = caminhoCompleto + sufixo;

		File file = new File(teste);

		if (file.exists()) {
			file.delete();
		}

	}

}
