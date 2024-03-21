package com.tropical.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.tropical.controller.ClienteController;
import com.tropical.data.vo.v1.ClienteVO;
import com.tropical.exceptions.RequiredObjectIsNullException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.mapper.Mapper;
import com.tropical.model.Cliente;
import com.tropical.model.Contato;
import com.tropical.model.Reserva;
import com.tropical.repository.ClienteRepository;
import com.tropical.repository.ContatoRepository;
import com.tropical.repository.ReservaRepository;

@Service
public class ClienteServices {

	@Autowired
	ClienteRepository repository;
	
	@Autowired
	ContatoRepository contatoRepository;
	@Autowired
	ReservaRepository reservaRepository;
	@Autowired 
	PagedResourcesAssembler<ClienteVO>assembler;
	
	public ClienteVO findById(Long  id) {
		var entity =repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum cliente encontrado na base de dados")) ;
		var vo=  Mapper.parseObject(entity, ClienteVO.class);
		vo.add(linkTo(methodOn(ClienteController.class).findById(id)).withSelfRel());
		return vo;
	}
	public ClienteVO create( ClienteVO cliente) {
		if(cliente ==null ) throw new RequiredObjectIsNullException();
			
		
		var entity=Mapper.parseObject(cliente, Cliente.class);
		var vo= Mapper.parseObject(repository.save(entity),ClienteVO.class);
		vo.add(linkTo(methodOn(ClienteController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	public ClienteVO update (ClienteVO cliente) {
		if(cliente ==null ) throw new RequiredObjectIsNullException();
		
		var entity= repository.findById(cliente.getKey()).get();
			entity.setNome(cliente.getNome());
			entity.setSenha(cliente.getSenha());
			entity.setDataNascimento(cliente.getDataNascimento());
			entity.setEmail(cliente.getEmail());
			entity.setTelefone(cliente.getTelefone());
			entity.setCep(cliente.getCep());
			
			var vo= Mapper.parseObject(repository.save(entity),ClienteVO.class);
			vo.add(linkTo(methodOn(ClienteController.class).findById(vo.getKey())).withSelfRel());
			return vo;
	}
	
	public PagedModel<EntityModel<ClienteVO>> findAll(Pageable pageable) {
		
		var clientesPage=repository.findAll(pageable);
		
		var clientesVosPage= clientesPage.map(c-> Mapper.parseObject(c, ClienteVO.class));
		
		clientesVosPage.map(
				p-> p.add(linkTo(methodOn(ClienteController.class).findById(p.getKey())).withSelfRel()));
		
		Link link=linkTo(methodOn(ClienteController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(),"asc")).withSelfRel();
		return assembler.toModel(clientesVosPage, link);
	}
	public PagedModel<EntityModel<ClienteVO>> findClientesByName(String nome,Pageable pageable) {
		
		var clientesPage=repository.findClientesByName(nome,pageable);
		
		var clientesVosPage= clientesPage.map(c-> Mapper.parseObject(c, ClienteVO.class));
		
		clientesVosPage.map(
				p-> p.add(linkTo(methodOn(ClienteController.class).findById(p.getKey())).withSelfRel()));
		
		Link link=linkTo(methodOn(ClienteController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(),"asc")).withSelfRel();
		return assembler.toModel(clientesVosPage, link);
	}
	public void delete(Long id) {
		
		for (Contato contato : contatoRepository.findAll()) {
			if(contato.getCliente().getId()==id) {
				contatoRepository.delete(contato);
			}
		}
		for(Reserva reserva :reservaRepository.findAll() ) {
			if(reserva.getCliente().getId()==id) {
				reservaRepository.delete(reserva);
			}
		}
	 repository.deleteById(id);
	}
}
