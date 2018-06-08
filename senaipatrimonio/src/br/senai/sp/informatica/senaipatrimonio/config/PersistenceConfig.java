package br.senai.sp.informatica.senaipatrimonio.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

	/**
	 * Cria conex�o com banco de dados
	 * 
	 * @return DataSource
	 */
	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		// MYSQL
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/senai_patrimonio?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("root132");

		// SQLSERVER
//		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=patrimonio_senai_db;integratedSecurity=false");
//		dataSource.setUsername("sa");
//		dataSource.setPassword("sa132");

		return dataSource;
	}

	/**
	 * Cria objeto com as propriedades do hibernate 
	 * 
	 * @return Properties
	 */
	public Properties getHibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");

		props.setProperty("hibernate.connection.CharSet", "utf8");
		props.setProperty("hibernate.connection.characterEncoding", "utf8");
		props.setProperty("hibernate.connection.useUnicode", "utf8");

		// Dialect para o SQL SERVER
//		props.setProperty("hibernate.dialect","org.hibernate.dialect.SQLServer2012Dialect");
		
		// Dialect para o MYSQL
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return props;
	}

	

	/**
	 * Gera o objeto (LocalSessionFactoryBean) que gera o objeto de conex�o com o banco de dados(SessionFactory) para o Spring injetar
	 * @return LocalSessionFactoryBean
	 */
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(getDataSource());
		factoryBean.setHibernateProperties(getHibernateProperties());
		factoryBean.setPackagesToScan("br.senai.sp.informatica.senaipatrimonio.model");
		return factoryBean;
	}

	
	/**
	 * Objeto para fazer opera��es/transa��es no banco de dados 
	 * @return HibernateTransactionManager 
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject()); //gera o SessionFactory e colocar ele para o 

		return transactionManager;
	}
}
