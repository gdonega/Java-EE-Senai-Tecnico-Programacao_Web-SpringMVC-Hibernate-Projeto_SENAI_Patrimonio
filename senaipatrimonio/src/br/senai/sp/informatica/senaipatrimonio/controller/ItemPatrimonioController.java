package br.senai.sp.informatica.senaipatrimonio.controller;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.AmbienteDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.ItemPatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;
import br.senai.sp.informatica.senaipatrimonio.utils.Constantes;
import br.senai.sp.informatica.senaipatrimonio.utils.OutrosMetodos;
import br.senai.sp.informatica.senaipatrimonio.utils.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;

@Controller
public class ItemPatrimonioController {

	//Objetos injetados pelo Spring
    @Autowired
    private ItemPatrimonioDAO itemPatrimonioDAO;
    
    @Autowired
    private AmbienteDAO ambienteDAO;
    
    @Autowired
    private SessionHelper sessionHelper;
    
    @Autowired
    private ServletContext context;


    /**
     * Abrir pagina de cadastro de Item Patrimonio 
     * 
     * @param idPatrimonio
     * @param model
     * @return String
     */
    @GetMapping("app/item/form")
    public String formItem(@RequestParam(required = false) Long idPatrimonio, Model model) {

        Patrimonio patrimonio = new Patrimonio();
        patrimonio.setId(idPatrimonio);

        ItemPatrimonio item = new ItemPatrimonio();

        item.setPatrimonio(patrimonio);

        model.addAttribute("itemPatrimonio", item);
        model.addAttribute("ambientes", ambienteDAO.buscarTodos());

        return "item_patrimonio/formNovo";
    }

    /**
     * Salva um Item de Patrimonio
     * 
     * @param itemPatrimonio
     * @param result
     * @param arquivo
     * @param model
     * 
     * @return String 
     */
    @PostMapping("app/item/salvar")
    public String salvarItem(@Valid ItemPatrimonio itemPatrimonio, BindingResult result,
                             @RequestPart(name = "fotoItemPatrimonio", required = false) MultipartFile arquivo, Model model) {

    	//Confere as regras de negócio
        if (result.hasFieldErrors("patrimonio") || result.hasFieldErrors("ambienteAtual")) {
            model.addAttribute("itemPatrimonio", itemPatrimonio);
            model.addAttribute("ambientes", ambienteDAO.buscarTodos());
            return "item_patrimonio/formNovo";
        }

        itemPatrimonio.setCadastrante(sessionHelper.getUsuarioLogado());
        itemPatrimonioDAO.persistir(itemPatrimonio);

        // Upload da foto

        System.err.println(arquivo);

        //Faz o upload de arquivo
        if (!arquivo.isEmpty()) {
            try {

                // Diretório das fotos de patrimonio
                String caminhoDaPastaPatrimonioFotos = context.getRealPath(Constantes.URL_BASE_FOTO_ITEM_PATRIMONIO);

                // Cria as pastas
                File pasta = new File(caminhoDaPastaPatrimonioFotos);
                if (!pasta.exists())
                    pasta.mkdirs();

                // Define o caminho do arquivo
                String caminhoArquivo = caminhoDaPastaPatrimonioFotos + "foto_" + itemPatrimonio.getId();

                // Criar um obj File - classe responsavel por gerenciar arquivos e pastas
                File file = new File(caminhoArquivo);

                // Cria o arquivo caso ele nï¿½o exista
                if (!file.exists())
                    file.createNewFile();

                // Transfere os dados do aruqivo upado (multipart) para um arquivo na maquina
                // (File)
                arquivo.transferTo(file);

                // BufferedImage <- classe que manipula imagens no java
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Arquivo nÃ£o enviado");
        }

        return "redirect:/app/patrimonio/itens?id=" + itemPatrimonio.getPatrimonio().getId();
    }

    /**
     * Exclui um Item de patrimonio
     * 
     * @param id
     * @return String
     */
    @GetMapping("app/adm/item/excluir")
    public String excluirItem(@RequestParam(required = true) Long id) {

        Long idPatrimonio;

        ItemPatrimonio item = itemPatrimonioDAO.buscarPeloId(id);

        idPatrimonio = item.getPatrimonio().getId();

        itemPatrimonioDAO.deletar(item);

        OutrosMetodos.excluirFotoFile(id, context.getRealPath(Constantes.URL_BASE_FOTO_ITEM_PATRIMONIO));

        return "redirect:/app/patrimonio/itens?id=" + idPatrimonio;
    }


    /**
     * Abrir pagina de alteração de item de patrimonio
     * 
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("app/adm/item/alterarFoto")
    public String alterarItem(@RequestParam(required = true) Long id, Model model) {
        itemPatrimonioDAO.buscarPeloId(id);
        model.addAttribute("itemPatrimonio", itemPatrimonioDAO.buscarPeloId(id));
        return "item_patrimonio/formAlterar";
    }

    
    @PostMapping("app/adm/item/alterarFoto")
    public String alterarItem(ItemPatrimonio itemP, @RequestParam(value = "fotoItem") MultipartFile arquivo) {
        String caminhoDaPastaPatrimonioFotos = context.getRealPath(Constantes.URL_BASE_FOTO_ITEM_PATRIMONIO);
        File pasta = new File(caminhoDaPastaPatrimonioFotos);
        if (!pasta.exists())
            pasta.mkdirs();
        String caminhoArquivo = caminhoDaPastaPatrimonioFotos + "foto_" + itemP.getId();
        File file = new File(caminhoArquivo);

        if (!arquivo.isEmpty()) {
            try {
                if (!file.exists())
                    file.createNewFile();
                arquivo.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (file.exists()){
                file.delete();
            }
        }

        return "redirect:/app/item/movimentacoes?id=" + itemP.getId();
    }

    @GetMapping("app/adm/item/excluirFoto")
    public String excluirFoto(@RequestParam(value = "id") Long idItem){

        String caminhoDaPastaPatrimonioFotos = context.getRealPath(Constantes.URL_BASE_FOTO_ITEM_PATRIMONIO);
        File pasta = new File(caminhoDaPastaPatrimonioFotos);
        if (!pasta.exists())
            return "redirect:/app/item/movimentacoes?id=" + idItem;

        String caminhoArquivo = caminhoDaPastaPatrimonioFotos + "foto_" + idItem;
        File file = new File(caminhoArquivo);


        if (file.exists()){
            file.delete();
        }

        return "redirect:/app/item/movimentacoes?id=" + idItem;
    }

}
