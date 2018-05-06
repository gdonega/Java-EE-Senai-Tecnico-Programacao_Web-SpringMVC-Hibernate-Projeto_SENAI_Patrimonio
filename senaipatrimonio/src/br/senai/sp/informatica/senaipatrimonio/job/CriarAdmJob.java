package br.senai.sp.informatica.senaipatrimonio.job;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.AmbienteDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.CategoriaDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.ItemPatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.MovimentacaoDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.PatrimonioDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.UsuarioDAO;
import br.senai.sp.informatica.senaipatrimonio.dao.jpa.UsuarioJPA;
import br.senai.sp.informatica.senaipatrimonio.model.Ambiente;
import br.senai.sp.informatica.senaipatrimonio.model.Categoria;
import br.senai.sp.informatica.senaipatrimonio.model.ItemPatrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.Movimentacao;
import br.senai.sp.informatica.senaipatrimonio.model.Patrimonio;
import br.senai.sp.informatica.senaipatrimonio.model.TipoUsuario;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;

@Component
public class CriarAdmJob implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

//		System.out.println("[JOB]: Criação ADM");
//
//		// Criando obj do usuario adm padrão
//
//		System.out.println("[JOB]: Criando UsuarioADM........");
//
//		 Usuario admin = new Usuario();
//		
//		 admin.setEmail("admin@email.com");
//		 admin.setNome("ADM");
//		 admin.setSenha("admin@email.com");
//		 admin.setSobrenome("do Sistema");
//		 admin.setTipo(TipoUsuario.ADMIN);
//		
//		 usuarioDAO.persistir(admin);
//
//		System.out.println("[JOB]: Usuairo ADM pronto para uso.");

        // TESTES - Usuario
		// Usuario 1
		Usuario usuario = new Usuario();

		usuario.setEmail("admin@email.com");
		usuario.setNome("ADM");
		usuario.setSenha("admin@email.com");
		usuario.setSobrenome("do Sistema");
		usuario.setTipo(TipoUsuario.ADMIN);

		// Usuario 2
		Usuario usuariodois = new Usuario();
		usuariodois.setEmail("oi@email.com");
		usuariodois.setNome("COMUM");
		usuariodois.setSenha("comumn@email.com");
		usuariodois.setSobrenome("lala Sistema");
		usuariodois.setTipo(TipoUsuario.ADMIN);

		// Salva os dois
		usuarioDAO.persistir(usuario);
		usuarioDAO.persistir(usuariodois);

		// Altera o primeiro
		usuario.setNome("HUEHEUHEUH");
		usuarioDAO.alterar(usuario);

		// Mostra o pegar um
		System.out.println("pegando pelo id " + usuarioDAO.buscarPeloId(1L));

		// Mostra o pegar todos
		System.out.println("\n pegando todos " + usuarioDAO.buscarTodos().toString());

		// Deleta
		usuarioDAO.deletar(usuario);

		// TESTES - AMBIENTE
		// Ambiente 1
		Ambiente ambiente = new Ambiente();
		ambiente.setNome("lugarLegal aaaa");

		// Ambiente 2
		Ambiente ambientedois = new Ambiente();
		ambientedois.setNome("baddd");

		// Salva os dois
		ambienteDAO.persistir(ambiente);
		ambienteDAO.persistir(ambientedois);

		// Altera o primeiro
		ambiente.setNome("HUEHEUHEUH");
		ambienteDAO.alterar(ambiente);

		// Mostra o pegar um
		System.out.println("pegando pelo id " + ambienteDAO.buscarPeloId(1L));

		// Mostra o pegar todos
		System.out.println("\n pegando todos " + ambienteDAO.buscarTodos().toString());

		// Deleta
		// ambienteDAO.deletar(ambiente);

		// TESTES - Categoria

		// Cat 1
		Categoria cat = new Categoria();
		cat.setNome("lugarLegal aaaa");

		// Cat 2
		Categoria cat2 = new Categoria();
		cat2.setNome("baddd");

		// Salva os dois
		categoriaDAO.persistir(cat);
		categoriaDAO.persistir(cat2);

		// Altera o primeiro
		cat.setNome("HUEHEUHEUH");
		categoriaDAO.alterar(cat);

		// Mostra o pegar um
		System.out.println("pegando pelo id " + categoriaDAO.buscarPeloId(1L));

		// Mostra o pegar todos
		System.out.println("\n pegando todos " + categoriaDAO.buscarTodos().toString());

		// Deleta
		categoriaDAO.deletar(cat);

		// TESTES - Patrimonio

		// 1
		Patrimonio one = new Patrimonio();
		one.setNome("lugarLegal aaaa");
		one.setDt_cadastro(new java.util.Date());
		one.setCategoria(cat2);
		one.setCadastrante(usuariodois);

		// 2
		Patrimonio two = new Patrimonio();
		two.setNome("baddd");
		two.setDt_cadastro(new java.util.Date());
		two.setCategoria(cat2);
		two.setCadastrante(usuariodois);

		// Salva os dois
		patrimonioDAO.persistir(one);
		patrimonioDAO.persistir(two);

		// Altera o primeiro
		one.setNome("HUEHEUHEUH");
		patrimonioDAO.alterar(one);

		// Mostra o pegar um
		System.out.println("pegando pelo id " + patrimonioDAO.buscarPeloId(1L));

		// Mostra o pegar todos
		System.out.println("\n pegando todos " + patrimonioDAO.buscarTodos().toString());

		// Deleta
		patrimonioDAO.deletar(one);

		// TESTES - ItemPatrimonio

		// 1
		ItemPatrimonio onei = new ItemPatrimonio();
		onei.setAmbienteAtual(ambientedois);
		onei.setCadastrante(usuariodois);
		onei.setPatrimonio(two);

		// 2
		ItemPatrimonio twoi = new ItemPatrimonio();
		twoi.setAmbienteAtual(ambientedois);
		twoi.setCadastrante(usuariodois);
		twoi.setPatrimonio(two);

		// Salva os dois
		itemPatrimonioDAO.persistir(onei);
		itemPatrimonioDAO.persistir(twoi);

		// Altera o primeiro
		onei.setAmbienteAtual(ambiente);
		itemPatrimonioDAO.alterar(onei);

		// Mostra o pegar um
		System.out.println("pegando pelo id " + itemPatrimonioDAO.buscarPeloId(1L));

		// Mostra o pegar todos
		System.out.println("\n pegando todos " + itemPatrimonioDAO.buscarTodos().toString());

		// Deleta
		itemPatrimonioDAO.deletar(onei);

		// TESTES - Movimentacao

		// 1
		Movimentacao onem = new Movimentacao();
		onem.setAmbienteOriginal(ambiente);
		onem.setAmbienteNovo(ambientedois);
		onem.setDataDaMovimentacao(new Date());
		onem.setExecutou(usuariodois);
		onem.setItemMovido(twoi);

		// 2
		Movimentacao twom = new Movimentacao();
		twom.setAmbienteOriginal(ambiente);
		twom.setAmbienteNovo(ambientedois);
		twom.setDataDaMovimentacao(new Date());
		twom.setExecutou(usuariodois);
		twom.setItemMovido(twoi);

		// Salva os dois
		movimentacaoDAO.persistir(onem);
		movimentacaoDAO.persistir(twom);

		// Altera o primeiro

		// 1
		ItemPatrimonio ip = new ItemPatrimonio();
		ip.setAmbienteAtual(ambientedois);
		ip.setCadastrante(usuariodois);
		ip.setPatrimonio(two);

		itemPatrimonioDAO.persistir(ip);
		onem.setItemMovido(ip);
		movimentacaoDAO.alterar(onem);

		// Mostra o pegar um
		System.out.println("pegando pelo id " + movimentacaoDAO.buscarPeloId(1L));

		// Mostra o pegar todos
		System.out.println("\n pegando todos " + movimentacaoDAO.buscarTodos().toString());

		// Deleta
		movimentacaoDAO.deletar(onem);

	}

	@Autowired
	CategoriaDAO categoriaDAO;

	@Autowired
	AmbienteDAO ambienteDAO;

	@Autowired
	PatrimonioDAO patrimonioDAO;

	@Autowired
	ItemPatrimonioDAO itemPatrimonioDAO;

	@Autowired
	MovimentacaoDAO movimentacaoDAO;

}
