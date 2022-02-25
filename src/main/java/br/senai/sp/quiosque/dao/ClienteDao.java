package br.senai.sp.quiosque.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.management.RuntimeErrorException;

import br.senai.sp.quiosque.model.Cliente;

public class ClienteDao {
	private Connection conexao;

	public ClienteDao() {
		conexao = ConnectionFactory.conectar();
	}
	
	public void inserir(Cliente cliente) {
		String sql = "insert into tb_cliente(nome, endereco, telefone, email, produto, genero, idade, data) values(?,?,?,?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEndereco());
			stmt.setString(3, cliente.getTel());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getProduto());
			stmt.setString(6, cliente.getGenero());
			stmt.setInt(7, cliente.getIdade());
			stmt.setTimestamp(8, new Timestamp(cliente.getData().getTimeInMillis()));
			stmt.execute();
			conexao.close();	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Cliente> listar(){
		String sql = "select * from tb_cliente order by nome asc";
		PreparedStatement stmt;
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			//lista os atributos do obejto cliente
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setEndereco(rs.getString("endereco"));
				c.setTel(rs.getString("telefone"));
				c.setEmail(rs.getString("email"));
				c.setProduto(rs.getString("produto"));
				c.setGenero(rs.getString("genero"));
				c.setIdade(rs.getInt("idade"));
				
				Calendar data = Calendar.getInstance();
				Timestamp dataBd = rs.getTimestamp("data");
				data.setTimeInMillis(dataBd.getTime());
				c.setData(data);
			
				lista.add(c);	
				
			}
			rs.close();
			stmt.close();
			conexao.close();
			return lista;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void excluir(long idCliente) {
		String sql = "delete from tb_cliente where id = ?";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, idCliente);
			stmt.execute();
			conexao.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void atualizar(Cliente cliente) {
		String sql = "update tb_cliente set nome = ?, endereco = ?, telefone = ?, email = ?, produto = ?, genero = ?, idade = ? where id =?";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEndereco());
			stmt.setString(3, cliente.getTel());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getProduto());
			stmt.setString(6, cliente.getGenero());
			stmt.setInt(7, cliente.getIdade());
			stmt.setLong(8, cliente.getId());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Cliente buscar(long idCliente) {
		String sql = "select * from tb_cliente where id = ?";
		PreparedStatement stmt;
		Cliente c = null;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				c = new Cliente();
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setEndereco(rs.getString("endereco"));
				c.setTel(rs.getString("telefone"));
				c.setEmail(rs.getString("email"));
				c.setProduto(rs.getString("produto"));
				c.setGenero(rs.getString("genero"));
				c.setIdade(rs.getInt("idade"));
			}
			rs.close();
			stmt.close();
			conexao.close();
			return c;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
