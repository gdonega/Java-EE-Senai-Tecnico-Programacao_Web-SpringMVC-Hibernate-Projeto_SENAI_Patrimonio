package br.senai.sp.informatica.senaipatrimonio.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.senai.sp.informatica.senaipatrimonio.dao.interfaces.UsuarioDAO;
import br.senai.sp.informatica.senaipatrimonio.model.TipoUsuario;
import br.senai.sp.informatica.senaipatrimonio.model.Usuario;

@Component
public class CriarAdmJob implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		System.out.println("[JOB]: Criação ADM");

		// Criando obj do usuario adm padrão

		System.out.println("[JOB]: Criando UsuarioADM........");

		Usuario admin = new Usuario();

		admin.setEmail("admin@email.com");
		admin.setNome("Donegá");
		admin.setSenha("admin132");
		admin.hashearSenha();
		admin.setSobrenome("do Sistema");
		admin.setTipo(TipoUsuario.ADMIN);
		
		
		Usuario comum = new Usuario();

		comum.setEmail("comum@email.com");
		comum.setNome("comum");
		comum.setSenha("comum132");
		comum.hashearSenha();
		comum.setSobrenome("do Sistema");
		comum.setTipo(TipoUsuario.COMUM);
		
		Usuario admin2 = new Usuario();

		admin2.setEmail("admin23@email.com");
		admin2.setNome("ADM23");
		admin2.setSenha("admin132");
		admin2.hashearSenha();
		admin2.setSobrenome("Legal");
		admin2.setTipo(TipoUsuario.ADMIN);

		
		if (usuarioDAO.buscarPorEmail(admin.getEmail()) == null) {
			usuarioDAO.persistir(admin);
			usuarioDAO.persistir(comum);
			usuarioDAO.persistir(admin2);
		} else {
			System.out.println("[JOB]: UsuarioADM já existe");
		}
		

		System.out.println("[JOB]: Usuairo ADM pronto para uso.");

	}
}
