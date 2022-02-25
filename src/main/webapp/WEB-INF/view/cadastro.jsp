<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="cadastro.css">
</head>
<body>
	<header>
        <a href="listaCliente">Lista de clientes</a>
        <a href="/quiosque">Página inicial</a>
    </header>
	<form action="salvar" method="post">
	<input type="hidden" name="id" value="${cliente.id }">
		<div>
		<label>Nome:</label>
		<br>
		<input type="text" name="nome" value="${cliente.nome }" required>
		</div>
		
		<div>
		<label>Endereço:</label>
		<br>
		<input type="text" name="endereco" value="${cliente.endereco}" required>
		</div>
		
		<div>
		<label>Telefone ou celular:</label>
		<br>
		<input type="text" name="tel" value="${cliente.tel }" required>
		</div>
		
		<div>
		<label>E-mail</label>
		<br>
		<input type="email" name="email" value="${cliente.email }" required>
		</div>
		
		<div>
		<label>Produto de interesse:</label>
		<br>
		<input type="text" name="produto" value="${cliente.produto }" required>
		</div>
		
		<div>
		<label>Idade:</label>
		<br>
		<input type="number" name="idade" value="${cliente.idade }" min="6" max="100" required>
		</div>
		
		<div>
			<label>Gênero:</label>
			<div id="genero">
				<input type="radio" name="genero" value="Masculino" required <c:if test="${cliente.genero == 'Masculino'}">checked</c:if>>
				<label>Masculino</label> 
				<input type="radio" name="genero" value="Feminino" required <c:if test="${cliente.genero == 'Feminino'}">checked</c:if>>
				<label>Feminino</label>
			</div>
		</div>
		<div id="enviar">
		    <button type="submit">Enviar</button>
        </div>
	</form>
</body>
</html>