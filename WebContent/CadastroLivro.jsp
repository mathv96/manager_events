<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="br.com.leslivros.util.ConverteDate"%>
<%@ page import="br.com.leslivros.aplicacao.Resultado, br.com.leslivros.modelo.*, br.com.leslivros.dao.*, java.util.*"%>

<html>
	<head>		
		<script src="jquery/2.1.1-jquery.min.js"></script>
		<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
		
        <title>Cadastro Livro</title>
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
							<div id="cadastros" class="panel-collapse collapse in"> <!--panel-collapse collapse in-->
								<div class="panel-body">
									<table class="table">
										<tr>
											<td>
												<!--<span class="glyphicon glyphicon-book text-primary"></span>--><a href="CadastroLivro.jsp"> <b>Livros</b></a>
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
							<div id="consultas" class="panel-collapse collapse">
								<div class="panel-body">
									<table class="table">
										<tr>
											<td>
												<a href="ConsultaLivro.jsp">Livros</a><!--<span class="label label-success">$ 320</span>-->
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
						<%
							Livro livro = (Livro) request.getAttribute("livro");
						%>
						<form action="SalvarLivro" method="get" class="form-horizontal">
							<fieldset>
							
							<input type="hidden" name="operacao" value="SALVAR" />
							
							<!-- Form Name -->
							<legend>Cadastro de Livros</legend>
							<div style="background-color: #4ED4E8"></div>

							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="PKLivro">PK:</label>  
							  <div class="col-md-5">
								<input id="txtPKLivro" name="txtPKLivro" type="text" class="form-control input-md" value="<%if(livro != null) out.print(livro.getPK() + " readonly");%>">
							  </div>
							</div>

							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="titulo">Título:</label>  
							  <div class="col-md-5">
								  <input id="txtTitulo" name="txtTitulo" type="text" placeholder="Entre com o Título" class="form-control input-md" required="" value="<%if(livro != null) out.print(livro.getTitulo());%>">
							  </div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="autor">Autores:</label>  
								<div class="col-md-5">
									<select id="txtAutor" name="txtAutor" class="form-control">
										<%
											List<EntidadeDominio> autores = new AutorDAO().getLista();

											for (EntidadeDominio ed : autores) {
												Autor autor = (Autor) ed;
										%>
										<option id= <%=autor.getPK() %> value=<%=autor.getPK() %>> <%=autor.getNomeArtistico() %> </option>
										<%
											}
										%>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="categoria">Categoria:</label>  
								<div class="col-md-5">
									<select id="txtCategoria" name="txtCategoria" class="form-control">
										<%
											List<EntidadeDominio> categorias = new CategoriaDAO().getLista();

											for (EntidadeDominio ed : categorias) {
												Categoria categoria = (Categoria) ed;
										%>
										<option id= <%=categoria.getPK() %> value=<%=categoria.getPK() %>> <%=categoria.getDescricao() %> </option>
										<%
											}
										%>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="ano">Ano:</label>  
								<div class="col-md-5">
									<input type="date" name="txtAno" required="" value="<%if(livro != null) out.print(livro.getDtAno());%>">
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="editora">Editora:</label>  
								<div class="col-md-5">
									<select id="txtEditora" name="txtEditora" class="form-control">
										<%
											List<EntidadeDominio> editoras = new EditoraDAO().getLista();

											for (EntidadeDominio ed : editoras) {
												Editora editora = (Editora) ed;
										%>
										<option id= <%=editora.getPK() %> value=<%=editora.getPK() %>> <%=editora.getRazao() %> </option>
										<%
											}
										%>
									</select>
								</div>
							</div>

							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="edicao">Edição:</label>  
							  <div class="col-md-5">
								  <input id="txtEdicao" name="txtEdicao" type="text" placeholder="Entre com a Edição" class="form-control input-md" required="" value="<%if(livro != null) out.print(livro.getEdicao());%>">
							  </div>
							</div>

							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="ISBN">ISBN:</label>  
							  <div class="col-md-5">
								  <input id="txtISBN" name="txtISBN" type="text" placeholder="Entre com o ISBN" class="form-control input-md" required="" value="<%if(livro != null) out.print(livro.getISBN());%>">
							  </div>
							</div>

							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="NumPaginas">Número de Paginas:</label>  
							  <div class="col-md-5">
								  <input id="txtNumPaginas" name="txtNumPaginas" type="text" placeholder="Entre com o Nº de Paginas" class="form-control input-md" required="" value="<%if(livro != null) out.print(livro.getNumPaginas());%>">
							  </div>
							</div>

							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="Sinopse">Sinopse:</label>  
							  <div class="col-md-5">
								  <input id="txtSinopse" name="txtSinopse" type="text" placeholder="Entre com a Sinopse" class="form-control input-md" required="" value="<%if(livro != null) out.print(livro.getSinopse());%>">
							  </div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="dimensao">Dimensão:</label>  
								<div class="col-md-5">
									<select id="txtDimensao" name="txtDimensao" class="form-control">
										<%
											List<EntidadeDominio> dimensoes = new DimensaoDAO().getLista();

											for (EntidadeDominio ed : dimensoes) {
												Dimensao dimensao = (Dimensao) ed;
										%>
										<option id= <%=dimensao.getPK() %> value=<%=dimensao.getPK() %>  > <%=dimensao.getAltura() + " x " + dimensao.getLargura() + " x " + dimensao.getPeso() + " x " + dimensao.getProfundidade()%> </option>
										<%
											}
										%>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4 control-label" for="precificacao">Precificação:</label>  
								<div class="col-md-5">
									<select id="txtPrecificacao" name="txtPrecificacao" class="form-control">
										<%
											List<EntidadeDominio> precificacoes = new PrecificacaoDAO().getLista();

											for (EntidadeDominio ed : precificacoes) {
												Precificacao precificacao = (Precificacao) ed;
										%>
										<option id= <%=precificacao.getPK() %> value=<%=precificacao.getPK() %>  > <%=precificacao.getDescricao() + " - " + precificacao.getPercentual() + "%"%> </option>
										<%
											}
										%>
									</select>
								</div>
							</div>

							<!-- Text input-->
							<div class="form-group">
							  <label class="col-md-4 control-label" for="CodBarras">Código de Barras:</label>  
							  <div class="col-md-5">
								  <input id="txtCodBarras" name="txtCodBarras" type="text" placeholder="Entre com o Código de Barras" class="form-control input-md" value="<%if(livro != null) out.print(livro.getCodBarras());%>">
							  </div>
							</div>

							<!-- Button -->
							<div class="form-group">
								<label class="col-md-4 control-label" for="acao">Ação</label>
								<div class="col-md-4">
									<button type="submit" class="btn btn-primary">
										<!-- <span class="glyphicon glyphicon-thumbs-up"></span> -->
										<span class="glyphicon glyphicon glyphicon-floppy-save"></span>
										Salvar Livro
									</button>
								</div>
							</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>	