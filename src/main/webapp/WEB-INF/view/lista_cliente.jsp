<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Clientes</title>
<link rel="stylesheet" href="lista.css">
</head>
<body>
<header>
        <a href="cadCliente">Cadastrar cliente</a>
        <a href="/quiosque">Página inicial</a>
    </header>
    <div id="container">
		<h1>Lista de Clientes</h1>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Endereço</th>
				<th>Telefone</th>
				<th>Email</th>
				<th>Produto</th>
				<th>Ver+</th>
				<th>Excluir</th>
				
			</tr>
			<c:forEach items="${clientes }" var="c">
				<tr>
					<td>${c.id}</td>
					<td>${c.nome}</td>
					<td>${c.endereco}</td>
					<td>${c.tel}</td>
					<td>${c.email}</td>
					<td>${c.produto}</td>
					<td><a href="alterarCliente?idCliente=${c.id }">Ver+</a></td>
					
					
					<td><a href="excluirCliente?idCliente=${c.id }" onclick="return confirm('Confirmar exclusão do Cliente ${c.nome}')" id="excluir">Excluir</a></td>
					
				</tr>
			</c:forEach>
		</table>
		<h2>Estatísticas</h2>
		<table border="1">
			<tr>
				<th>Femininos</th>
				<th>Masculinos</th>
				<th>Jovens</th>
				<th>Adultos</th>
				<th>Idosos</th>
			</tr>
			<td>${feminino }</td>
			<td>${masculino }</td>
			<td>${jovem }</td>
			<td>${adulto }</td>
			<td>${idoso }</td>
			
		</table>
		<table border="1">
			<tr>
				<th>Domingo</th>
				<th>Segunda</th>
				<th>Terça</th>
				<th>Quarta</th>
				<th>Quinta</th>
				<th>Sexta</th>
				<th>Sábado</th>
			</tr>
			<td>${dom }</td>
			<td>${seg }</td>
			<td>${ter }</td>
			<td>${qua }</td>
			<td>${qui }</td>
			<td>${sex }</td>
			<td>${sab }</td>
		</table>
		<table border="1">
			<tr>
				<th>Manhã</th>
				<th>Tarde</th>
				<th>Noite</th>
			</tr>
			<td>${manha }</td>
			<td>${tarde }</td>
			<td>${noite }</td>
		</table>
	</div>
</body>
</html>