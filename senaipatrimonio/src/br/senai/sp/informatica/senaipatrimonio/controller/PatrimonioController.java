package br.senai.sp.informatica.senaipatrimonio.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.CategoriaDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.ItemPatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.PatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.model.Categoria;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;
import br.senai.sp.informatica.senaipatrimonio.utils.Constantes;
import br.senai.sp.informatica.senaipatrimonio.utils.OutrosMetodos;
import br.senai.sp.informatica.senaipatrimonio.utils.SessionHelper;

@Controller
public class PatrimonioController {

    @Autowired
    private PatrimonioDAO patrimonioDAO;

    @Autowired
    private CategoriaDAO categoriaDAO;

    @Autowired
    private ItemPatrimonioDAO itemPatrimonioDAO;

    @Autowired
    private SessionHelper session;

    @Autowired
    private ServletContext context;

    @GetMapping("app/patrimonio/lista")
    public String abrirLista(Model model, @RequestParam(name = "filtro", required = false) Long categoriaId) {

        Categoria filtro = categoriaDAO.buscarPeloId(categoriaId);

        List<Patrimonio> patrimonios;

        if (filtro == null) {
            patrimonios = patrimonioDAO.buscarTodos();
        } else {
            patrimonios = patrimonioDAO.buscarPorCategoria(filtro);
        }

        model.addAttribute("patrimonios", patrimonios);

        model.addAttribute("tiposBusca", categoriaDAO.buscarTodos());

        return "patrimonio/lista";
    }

    @GetMapping("app/adm/patrimonio/form")
    public String abrirForm(@RequestParam(required = false) Long id, Model model) {

        Patrimonio patrimonio;
        if (id != null) {
            patrimonio = patrimonioDAO.buscarPeloId(id);
        } else {
            patrimonio = new Patrimonio();
        }
        model.addAttribute("patrimonio", patrimonio);
        model.addAttribute("categorias", categoriaDAO.buscarTodos());
        return "patrimonio/form";
    }

    @PostMapping("app/adm/patrimonio/salvar")
    public String salvarUsuario(@Valid Patrimonio patrimonio, BindingResult result,
                                @RequestPart(name = "fotoPatrimonio", required = false) MultipartFile arquivo, Model model) {

        System.err.println(patrimonio);
        if (result.hasFieldErrors("nome") || result.hasFieldErrors("categoria")) {
            model.addAttribute("categorias", categoriaDAO.buscarTodos());
            model.addAttribute("patrimonio", patrimonio);
            return "patrimonio/form";
        }

        Boolean estaCadastrado;

        if (patrimonio.getId() == null) {
            estaCadastrado = patrimonioDAO.jaEstaCadastrado(patrimonio);
        } else {
            estaCadastrado = patrimonioDAO.existeOutroComEsseNome(patrimonio);
        }

        if (estaCadastrado) {
            model.addAttribute("categorias", categoriaDAO.buscarTodos());
            model.addAttribute(patrimonio);
            result.addError(new FieldError("patrimonio", "nome", "Esse patrimonio já está cadastrado!"));
            return "patrimonio/form";
        }

        if (patrimonio.getId() == null) {

            patrimonio.setDt_cadastro(new Date());
            patrimonio.setCadastrante(session.getUsuarioLogado());

            System.err.println(patrimonio);
            patrimonioDAO.persistir(patrimonio);

        } else {

            Patrimonio patrimonioAtualizar = patrimonioDAO.buscarPeloId(patrimonio.getId());

            BeanUtils.copyProperties(patrimonio, patrimonioAtualizar, "id", "dt_cadastro", "cadastrante");

            System.out.println(patrimonioAtualizar);

            patrimonioDAO.alterar(patrimonioAtualizar);
        }

        // Upload da foto
        if (arquivo != null) {
            try {

                // Diretório das fotos de patrimonio
                String caminhoDaPastaPatrimonioFotos = context.getRealPath(Constantes.URL_BASE_FOTO_PATRIMONIO);

                // Cria as pastas
                File pasta = new File(caminhoDaPastaPatrimonioFotos);
                if (!pasta.exists())
                    pasta.mkdirs();

                // Define o caminho do arquivo
                // + "."+ FilenameUtils.getExtension(arquivo.getOriginalFilename()
                String caminhoArquivo = caminhoDaPastaPatrimonioFotos + "foto_" + patrimonio.getId();

                // Criar um obj File - classe responsavel por gerenciar arquivos e pastas
                File file = new File(caminhoArquivo);

                // Cria o arquivo caso ele n�o exista
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
            System.err.println("Arquivo não enviado");
        }

        return "redirect:/app/patrimonio/lista";
    }

    @GetMapping("app/adm/patrimonio/excluir")
    public String excluirPatrimonio(@RequestParam(required = true) Long id) {

        patrimonioDAO.deletar(new Patrimonio(id));

        OutrosMetodos.excluirFotoFile(id, context.getRealPath(Constantes.URL_BASE_FOTO_PATRIMONIO));

        return "redirect:/app/patrimonio/lista";
    }

    @GetMapping("app/patrimonio/itens")
    public String listaItensPatrimonio(@RequestParam(required = true, name = "id") Long patrimonioId, Model model) {
        Patrimonio patrimonio = patrimonioDAO.buscarPeloId(patrimonioId);
        model.addAttribute("patrimonio", patrimonio);
        model.addAttribute("itens_patrimonios", itemPatrimonioDAO.buscarPorPatrimonio(patrimonio));
        model.addAttribute("caminhoImagem", OutrosMetodos.getCaminhoPatrimonioImagem(patrimonio));
        return "item_patrimonio/lista";
    }


    @GetMapping("app/adm/patrimonio/excluirFoto")
    public String excluirFoto(@RequestParam(value = "id") Long idItem){

        String caminhoDaPastaPatrimonioFotos = context.getRealPath(Constantes.URL_BASE_FOTO_PATRIMONIO);
        File pasta = new File(caminhoDaPastaPatrimonioFotos);
        if (!pasta.exists())
            return "redirect:/app/patrimonio/itens?id=" + idItem;

        String caminhoArquivo = caminhoDaPastaPatrimonioFotos + "foto_" + idItem;
        File file = new File(caminhoArquivo);


        if (file.exists()){
            file.delete();
        }

        return "redirect:/app/patrimonio/itens?id=" + idItem;
    }
}
