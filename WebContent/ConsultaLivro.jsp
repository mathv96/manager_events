<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="br.com.leslivros.util.ConverteDate"%>
<%@ page import="br.com.leslivros.aplicacao.Resultado, br.com.leslivros.modelo.*, br.com.leslivros.dao.*, java.util.*"%>

<html>
	<head>		
		<script src="jquery/2.1.1-jquery.min.js"></script>
		<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
		
        <title>Consulta Livro</title>
    </head>
    <body>
		<br/>
		<div class="container">
			<div class="row">
				<div class="col-sm-3 col-md-3">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#cadastros"><span class="glyphicon glyphicon-plus"></span>
									 Cadastros</a>
								</h4>
							</div>
							<div id="cadastros" class="panel-collapse collapse"> <!--panel-collapse collapse in-->
								<div class="panel-body">
									<table class="table">
										<tr>
											<td>
												<!--<span class="glyphicon glyphicon-book text-primary"></span>--><a href="CadastroLivro.jsp"> Livros</a>
											</td>
										</tr>
										<tr>
											<td>
												<!--<span class="glyphicon glyphicon-user text-success"></span>--><a href="CadastroUsuario.jsp"> Usuarios</a>
											</td>
										</tr>
										<tr>
											<td>
												<!--<span class="glyphicon glyphicon-usd text-info"></span>--><a href="CadastroPrecificacao.jsp"> Precificacao</a>
											</td>
										</tr>
										<tr>
											<td>
												<!--<span class="glyphicon glyphicon-pencil text-success"></span>--><a href="CadastroPerfil.jsp"> Perfil</a>
												<!--<span class="badge">42</span>-->
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#consultas"><span class="glyphicon glyphicon-list-alt">
									</span> Consultas</a>
								</h4>
							</div>
							<div id="consultas" class="panel-collapse collapse in">
								<div class="panel-body">
									<table class="table">
										<tr>
											<td>
												<a href="ConsultaLivro.jsp"><b>Livros</b></a><!--<span class="label label-success">$ 320</span>-->
											</td>
										</tr>
										<tr>
											<td>
												<a>Usuários</a>
											</td>
										</tr>
										<tr>
											<td>
												<a>Precificações</a>
											</td>
										</tr>
										<tr>
											<td>
												<a>Perfis</a>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#relatorios"><span class="glyphicon glyphicon-file">
									</span>Relatorios</a>
								</h4>
							</div>
							<div id="relatorios" class="panel-collapse collapse">
								<div class="panel-body">
									<table class="table">
										<tr>
											<td>
												<span class="glyphicon glyphicon-usd"></span><a href="http://www.jquery2dotnet.com">Sales</a>
											</td>
										</tr>
										<tr>
											<td>
												<span class="glyphicon glyphicon-user"></span><a href="http://www.jquery2dotnet.com">Customers</a>
											</td>
										</tr>
										<tr>
											<td>
												<span class="glyphicon glyphicon-tasks"></span><a href="http://www.jquery2dotnet.com">Products</a>
											</td>
										</tr>
										<tr>
											<td>
												<span class="glyphicon glyphicon-shopping-cart"></span><a href="http://www.jquery2dotnet.com">Shopping Cart</a>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#account"><span class="glyphicon glyphicon-user">
									</span>Account</a>
								</h4>
							</div>
							<div id="account" class="panel-collapse collapse">
								<div class="panel-body">
									<table class="table">
										<tr>
											<td>
												<a> Alterar Senha</a>
											</td>
										</tr>
										<tr>
											<td>
												<a> Validar Email</a> <!--<span class="label label-info">5</span>-->
											</td>
										</tr>
										<tr>
											<td>
												<a> Configuracoes</a>
											</td>
										</tr>
										<tr>
											<td>
												<span class="glyphicon glyphicon-trash text-danger"></span>
												<a class="text-danger"> Excluir Conta</a>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-9 col-md-9">
					<div class="well">
						<table class="table table-striped custab">
							<thead>
								<tr>
									<th>PK</th>
									<th>Título</th>
									<th>Autor</th>
									<th>Categoria</th>
									<th>Nº de Pag.</th>
									<th>Precificação</th>
									<th class="text-center">Alterar</th>
									<th class="text-center">Status</th>
								</tr>
							</thead>
							<%
								List<EntidadeDominio> livros = new LivroDAO().getLista();
								for (EntidadeDominio ed : livros) {
									Livro livro = (Livro) ed;
							%>
								<tr>
									<td><%=livro.getPK() %></td>
									<td><%=livro.getTitulo() %></td>
									<td><%=livro.getAutor().getNomeArtistico() %></td>
									<td><%=livro.getCategoria().getDescricao() %></td>
									<td><%=livro.getNumPaginas() %></td>
									<td><%=livro.getPrecificacao().getDescricao() %></td>
									<td class="text-center"><button class="btn btn-info btn-xs" href="CadastroLivro.jsp"><span class="glyphicon glyphicon-edit"></span> Alterar</button></td>
									<td class="text-center"><button class="btn btn-danger btn-xs" id="operacao" name="operacao" value="EXCLUIR"><span class="glyphicon glyphicon-remove"></span> Deletar</button></td>
								</tr>
							<%
								}
							%>
						</table>

					</div>
				</div>
			</div>
		</div>
	</body>
</html>	
