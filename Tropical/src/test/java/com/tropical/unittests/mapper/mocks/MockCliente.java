package com.tropical.unittests.mapper.mocks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.tropical.data.vo.v1.ClienteVO;
import com.tropical.model.Cliente;

public class MockCliente {


    public Cliente mockEntity()   {
        return mockEntity(0);
    }
    
    public ClienteVO mockVO()   {
        return mockVO(0);
    }
    
    public List<Cliente> mockEntityList()   {
        List<Cliente> persons = new ArrayList<Cliente>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<ClienteVO> mockVOList()   {
        List<ClienteVO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }
    
    public Cliente mockEntity(Integer number)  {
    	Cliente cliente = new Cliente();
        cliente.setId(number.longValue());
        cliente.setNome( "Name Test" + number);
        cliente.setEmail("Email Test"+number);
        cliente.setSenha("Senha Test"+number);
        cliente.setTelefone("Telefone Test"+number);
        cliente.setCep("Cep Test"+number);
        LocalDate date=LocalDate.now();
        
        cliente.setDataNascimento(date);
        return cliente;
    }

    public ClienteVO mockVO(Integer number)   {
    	ClienteVO cliente = new ClienteVO();
    	 cliente.setKey(number.longValue());
         cliente.setNome( "Name Test" + number);
         cliente.setEmail("Email Test"+number);
         cliente.setSenha("Senha Test"+number);
         cliente.setTelefone("Telefone Test"+number);
         cliente.setCep("Cep Test"+number);
         LocalDate date=LocalDate.now();
         cliente.setDataNascimento(date);
        return cliente;
    }

}
