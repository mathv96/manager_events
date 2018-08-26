package br.com.leslivros.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.leslivros.aplicacao.Resultado;
import br.com.leslivros.modelo.EntidadeDominio;
import br.com.leslivros.web.command.ICommand;
import br.com.leslivros.web.command.imp.AlterarCommand;
import br.com.leslivros.web.command.imp.ConsultarCommand;
import br.com.leslivros.web.command.imp.ExcluirCommand;
import br.com.leslivros.web.command.imp.SalvarCommand;
import br.com.leslivros.web.command.imp.VisualizarCommand;
import br.com.leslivros.web.vh.IViewHelper;
import br.com.leslivros.web.vh.imp.AutorViewHelper;
import br.com.leslivros.web.vh.imp.CategoriaViewHelper;
import br.com.leslivros.web.vh.imp.DimensaoViewHelper;
import br.com.leslivros.web.vh.imp.EditoraViewHelper;
import br.com.leslivros.web.vh.imp.LivroViewHelper;
import br.com.leslivros.web.vh.imp.PrecificacaoViewHelper;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;
		
    /**
     * Default constructor. 
     */
    public Servlet() {
    	
    	/* Utilizando o command para chamar a fachada e indexando cada command 
    	 * pela operação garantimos que esta servelt atenderá qualquer operação */
    	commands = new HashMap<String, ICommand>();
    	
    	commands.put("SALVAR", new SalvarCommand());
    	commands.put("EXCLUIR", new ExcluirCommand());
    	commands.put("CONSULTAR", new ConsultarCommand());
    	commands.put("VISUALIZAR", new VisualizarCommand());
    	commands.put("ALTERAR", new AlterarCommand());
    		
    	/* Utilizando o ViewHelper para tratar especificações de qualquer tela e indexando 
    	 * cada viewhelper pela url em que esta servlet é chamada no form
    	 * garantimos que esta servelt atenderá qualquer entidade */
    	
    	vhs = new HashMap<String, IViewHelper>();
    	/*A chave do mapa é o mapeamento da servlet para cada form que 
    	 * está configurado no web.xml e sendo utilizada no action do html
    	 */
    	vhs.put("/LES_EComerce_Livros/SalvarAutor", new AutorViewHelper());
    	vhs.put("/LES_EComerce_Livros/SalvarCategoria", new CategoriaViewHelper());
    	vhs.put("/LES_EComerce_Livros/SalvarDimensao", new DimensaoViewHelper());
    	vhs.put("/LES_EComerce_Livros/SalvarEditora", new EditoraViewHelper());
    	vhs.put("/LES_EComerce_Livros/SalvarPrecificacao", new PrecificacaoViewHelper());
    	vhs.put("/LES_EComerce_Livros/SalvarLivro", new LivroViewHelper());
    }
    
    /** 
     * TODO Descrição do Método
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	doProcessRequest(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcessRequest(request, response);
	}
	
	protected void doProcessRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Obtêm a uri que invocou esta servlet (O que foi definido no methdo do form html)
		String uri = request.getRequestURI();
		
		//Obtêm um viewhelper indexado pela uri que invocou esta servlet
		IViewHelper vh = vhs.get(uri);
		
		//O viewhelper retorna a entidade especifica para a tela que chamou esta servlet
		EntidadeDominio entidade =  vh.getEntidade(request);
		
		//Obtêm a operação executada
		String operacao = request.getParameter("operacao");
		
		//Obtêm o command para executar a respectiva operação
		ICommand command = commands.get(operacao);
				
		/*Executa o command que chamará a fachada para executar a operação requisitada
		 * o retorno é uma instância da classe resultado que pode conter mensagens derro 
		 * ou entidades de retorno
		 */
		Resultado resultado = command.execute(entidade);
		
		/*
		 * Executa o método setView do view helper específico para definir como deverá ser apresentado 
		 * o resultado para o usuário
		 */
		vh.setView(resultado, request, response);
	}
}