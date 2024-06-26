package com.tropical.unittests.mapper.mocks;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.tropical.data.dto.ClienteDTO;
import com.tropical.data.dto.ContatoDTO;
import com.tropical.model.Cliente;
import com.tropical.model.Contato;

public class MockContato {


    public Contato mockEntity() throws ParseException {
        return mockEntity(0);
    }
    
    public ContatoDTO mockVO() throws ParseException {
        return mockVO(0);
    }
    
    public List<Contato> mockEntityList() throws ParseException {
        List<Contato> contatos = new ArrayList<Contato>();
        for (int i = 0; i < 14; i++) {
            contatos.add(mockEntity(i));
        }
        return contatos;
    }

    public List<ContatoDTO> mockVOList() throws ParseException {
        List<ContatoDTO> contatos = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            contatos.add(mockVO(i));
        }
        return contatos;
    }
    
    public Contato mockEntity(Integer number) throws ParseException {
    	Contato contato = new Contato();
        contato.setId(number.longValue());
        contato.setAssunto( "Assunto Test" + number);
        contato.setMensagem("Mensagem Test"+number);
        
        Cliente cliente=new Cliente();
        cliente.setNome("Nome Test"+number);
        cliente.setEmail("Email Test"+number);
        cliente.setSenha("Senha Test"+number);
        LocalDate date= LocalDate.of(2024, 11, 17);
        cliente.setDataNascimento(date);
        cliente.setCep("123"+number);
        cliente.setId(1L);
        cliente.setTelefone("123"+number);
        
        contato.setCliente(cliente);
        
        return contato;
    }

    public ContatoDTO mockVO(Integer number) throws ParseException {
    	ContatoDTO contato = new ContatoDTO();
    	 contato.setKey(number.longValue());
    	 contato.setAssunto( "Assunto Test" + number);
         contato.setMensagem("Mensagem Test"+number);
         
         ClienteDTO cliente=new ClienteDTO();
         cliente.setNome("Nome Test"+number);
         cliente.setEmail("Email Test"+number);
         cliente.setSenha("Senha Test"+number);
         LocalDate date= LocalDate.of(2024, 11, 17);
         cliente.setDataNascimento(date);
         cliente.setCep("123"+number);
         cliente.setKey(1L);
         cliente.setTelefone("123"+number);
         contato.setCliente(cliente);
         
        return contato;
    }

}
