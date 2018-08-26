package br.com.leslivros.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.leslivros.aplicacao.Resultado;
import br.com.leslivros.dao.AutorDAO;
import br.com.leslivros.dao.CategoriaDAO;
import br.com.leslivros.dao.DimensaoDAO;
import br.com.leslivros.dao.EditoraDAO;
import br.com.leslivros.dao.LivroDAO;
import br.com.leslivros.dao.PrecificacaoDAO;
import br.com.leslivros.inter.IDAO;
import br.com.leslivros.inter.IFachada;
import br.com.leslivros.inter.IStrategy;
import br.com.leslivros.modelo.Autor;
import br.com.leslivros.modelo.Categoria;
import br.com.leslivros.modelo.Dimensao;
import br.com.leslivros.modelo.Editora;
import br.com.leslivros.modelo.EntidadeDominio;
import br.com.leslivros.modelo.Livro;
import br.com.leslivros.modelo.Precificacao;
import br.com.leslivros.negocio.ComplementarDtAtualizacao;
import br.com.leslivros.negocio.ComplementarDtCadastro;
import br.com.leslivros.negocio.ValidadorDadosObrigatoriosLivro;

public class Fachada implements IFachada {
	
	/** 
	 * Mapa de DAOS, será indexado pelo nome da entidade 
	 * O valor é uma instância do DAO para uma dada entidade; 
	 */
	private Map<String, IDAO> daos;
	
	/**
	 * Mapa para conter as regras de negócio de todas operações por entidade;
	 * O valor é um mapa que de regras de negócio indexado pela operação
	 */
	private Map<String, Map<String, List<IStrategy>>> rns;
	
	private Resultado resultado;
	
	public Fachada(){
		/* Intânciando o Map de DAOS */
		daos = new HashMap<String, IDAO>();
		/* Intânciando o Map de Regras de Negócio */
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		/* Criando instâncias dos DAOs a serem utilizados*/
		AutorDAO autorDAO = new AutorDAO();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		DimensaoDAO dimensaoDAO = new DimensaoDAO();
		EditoraDAO editoraDAO = new EditoraDAO();
		PrecificacaoDAO precificacaoDAO = new PrecificacaoDAO();
		LivroDAO livroDAO = new LivroDAO();
		
		/* Adicionando cada dao no MAP indexando pelo nome da classe */
		daos.put(Autor.class.getName(), autorDAO);		
		daos.put(Categoria.class.getName(), categoriaDAO);		
		daos.put(Dimensao.class.getName(), dimensaoDAO);
		daos.put(Editora.class.getName(), editoraDAO);
		daos.put(Precificacao.class.getName(), precificacaoDAO);
		daos.put(Livro.class.getName(), livroDAO);
		
		/* Criando instâncias de regras de negócio a serem utilizados*/		
		ValidadorDadosObrigatoriosLivro vrDadosObrigatoriosLivro = new ValidadorDadosObrigatoriosLivro();
		ComplementarDtCadastro cDtCadastro = new ComplementarDtCadastro();
		ComplementarDtAtualizacao cDtAtualizacao = new ComplementarDtAtualizacao();
				
		/* Criando uma lista para conter as regras de negócio de Livro
		 * quando a operação for salvar
		 */
		List<IStrategy> rnsSalvarLivro = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do Livro*/
		rnsSalvarLivro.add(vrDadosObrigatoriosLivro);
		rnsSalvarLivro.add(cDtCadastro);
		rnsSalvarLivro.add(cDtAtualizacao);	
		
		/* Criando uma lista para conter as regras de negócio de Livro
		 * quando a operação for salvar
		 */
		List<IStrategy> rnsAlterarLivro = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação alterar do Livro*/
		rnsAlterarLivro.add(vrDadosObrigatoriosLivro);
		rnsAlterarLivro.add(cDtCadastro);
		rnsAlterarLivro.add(cDtAtualizacao);
		
		/* Cria o mapa que poderá conter todas as listas de regras de negócio específica 
		 * por operação  do Livro
		 */
		Map<String, List<IStrategy>> rnsLivro = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do Livro (lista criada na linha 75)
		 */
		rnsLivro.put("SALVAR", rnsSalvarLivro);
		
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do Livro (lista criada na linha 98)
		 */
		rnsLivro.put("ALTERAR", rnsAlterarLivro);	
		
		/* Adiciona o mapa(criado na linha 84) com as regras indexadas pelas operações no mapa geral indexado 
		 * pelo nome da entidade
		 */
		rns.put(Livro.class.getName(), rnsLivro);
	}
	
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "SALVAR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
			}
		} else {
			resultado.setMsg(msg);		
		}
		
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "ALTERAR");
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
			}
		} else {
			resultado.setMsg(msg);
		}
		
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "EXCLUIR");
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.excluir(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");		
			}
		} else {
			resultado.setMsg(msg);	
		}
		
		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "CONSULTAR");
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.consultar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar a consulta!");	
			}
		} else {
			resultado.setMsg(msg);	
		}
		
		return resultado;
	}

	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
		resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
		resultado.getEntidades().add(entidade);		
		
		return resultado;
	}
	
	private String executarRegras(EntidadeDominio entidade, String operacao){
		String nmClasse = entidade.getClass().getName();		
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
		
		if(regrasOperacao != null){
			List<IStrategy> regras = regrasOperacao.get(operacao);
			
			if(regras != null){
				for(IStrategy s: regras){			
					String m = s.processar(entidade);			
					
					if(m != null){
						msg.append(m);
						msg.append("\n");
					}			
				}	
			}		
		}
		
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
	}
}