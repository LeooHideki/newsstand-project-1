package br.senai.sp.quiosque.controller;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.fabric.xmlrpc.Client;

import br.senai.sp.quiosque.dao.ClienteDao;
import br.senai.sp.quiosque.model.Cliente;

@Controller
public class ClienteController {
	
	
	
	@RequestMapping("cadCliente")
	public String cadastro(Model model) {
		return ("cadastro");
	}
	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public String salvar(Cliente cliente) {
		//define a data de agora para o cliente
		Calendar data = Calendar.getInstance();
		cliente.setData(data);
		//insere o cliente no banco de dados
		ClienteDao dao = new ClienteDao();
		if(cliente.getId() == null) {
		dao.inserir(cliente);
		}else {
			dao.atualizar(cliente);
		}
		
		return "redirect:listaCliente";
	}
	@RequestMapping("listaCliente")
	public String listar(Model model) {
		ClienteDao dao = new ClienteDao();
		List<Cliente> lista = new ArrayList<Cliente>();
		lista = dao.listar();

		//contador de generos 
		int fem = 0;
		int mas = 0;
		for (Cliente t: lista) {
			if(t.getGenero().toLowerCase().equals("feminino")) {
				fem++;
			} else {
				mas++;
			}
		}
		//contador de faixa etária
		int jovem = 0;
		int adulto = 0;
		int idoso = 0;
		for (Cliente c: lista) {
			if(c.getIdade() <= 18) {
				jovem++;
			}else if(c.getIdade() > 18 && c.getIdade() <= 60) {
				adulto++;
			}else {
				idoso++;
			}
				
		}
		int seg = 0;
		int ter = 0;
		int qua = 0;
		int qui = 0;
		int sex = 0;
		int sab = 0;
		int dom = 0;
		
		int manha = 0;
		int tarde = 0;
		int noite = 0;
		for(Cliente c: lista) {
			if(c.getData().get(Calendar.DAY_OF_WEEK) == 1) {
				dom++;
			}else if(c.getData().get(Calendar.DAY_OF_WEEK) == 2) {
				seg++;
			}else if(c.getData().get(Calendar.DAY_OF_WEEK) == 3) {
				ter++;
			}else if(c.getData().get(Calendar.DAY_OF_WEEK) == 4) {
				qua++;
			}else if(c.getData().get(Calendar.DAY_OF_WEEK) == 5) {
				qui++;
			}else if(c.getData().get(Calendar.DAY_OF_WEEK) == 6) {
				sex++;
			}else {
				sab++;
			}
				
			
			if(c.getData().get(Calendar.HOUR_OF_DAY) < 12){
				manha++;
			}else if(c.getData().get(Calendar.HOUR_OF_DAY) < 18) {
				tarde++;
			}else {
				noite++;
			}
		}
		
	

		//cria as models para exibir no jsp
		model.addAttribute("clientes", lista);
		model.addAttribute("feminino", fem);
		model.addAttribute("masculino", mas);
		model.addAttribute("jovem", jovem);
		model.addAttribute("adulto", adulto);
		model.addAttribute("idoso", idoso);
		model.addAttribute("dom",dom);
		model.addAttribute("seg", seg);
		model.addAttribute("ter",ter);
		model.addAttribute("qua",qua);
		model.addAttribute("qui",qui);
		model.addAttribute("sex",sex);
		model.addAttribute("sab",sab);
		model.addAttribute("manha", manha);
		model.addAttribute("tarde", tarde);
		model.addAttribute("noite", noite);
		return "lista_cliente";
	}
	
	@RequestMapping("excluirCliente")
	public String excluir(long idCliente) {
		
		ClienteDao dao = new ClienteDao();
		dao.excluir(idCliente);
		return "redirect:listaCliente";
	}
	@RequestMapping("alterarCliente")
	public String alterar(long idCliente, Model model) {
		ClienteDao dao = new ClienteDao();
		model.addAttribute("cliente", dao.buscar(idCliente));
		
		return "forward:cadCliente";
	}
	
}



