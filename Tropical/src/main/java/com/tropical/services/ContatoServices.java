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

import com.tropical.controller.ContatoController;
import com.tropical.data.vo.v1.ContatoVO;
import com.tropical.exceptions.RequiredObjectIsNullException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.mapper.Mapper;
import com.tropical.model.Contato;
import com.tropical.repository.ClienteRepository;
import com.tropical.repository.ContatoRepository;

@Service
public class ContatoServices {
	
@Autowired
 ContatoRepository contatoRepository;

@Autowired
ClienteRepository clienteRepository;

@Autowired
PagedResourcesAssembler<ContatoVO>assembler;

public ContatoVO create(ContatoVO contato) {
	if(contato==null)throw new RequiredObjectIsNullException();
	
	var entity= Mapper.parseObject(contato, Contato.class);
	var vo=Mapper.parseObject(contatoRepository.save(entity), ContatoVO.class);
	vo.add(linkTo(methodOn(ContatoController.class).findById(vo.getKey())).withSelfRel());
	return vo;
} 
public ContatoVO findById(Long id) {
	var entity= contatoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Não existe um contato com o Id informado!"));
	var vo= Mapper.parseObject(entity, ContatoVO.class);
	vo.add(linkTo(methodOn(ContatoController.class).findById(id)).withSelfRel());
	return vo;
	
}
public PagedModel<EntityModel<ContatoVO>> findAll(Pageable pageable) {
	
	var contatosPage= contatoRepository.findAll(pageable);
	
	var contatosVosPage=contatosPage.map(c-> Mapper.parseObject(c, ContatoVO.class));
	
	contatosVosPage.map(p->
	p.add(linkTo(methodOn(ContatoController.class).findById(p.getKey())).withSelfRel()));
	
	
	Link link=linkTo(methodOn(ContatoController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
	return assembler.toModel(contatosVosPage,link);
}
public PagedModel<EntityModel<ContatoVO>> findContatosByAssunto(String assunto ,Pageable pageable) {
	
	var contatosPage= contatoRepository.findContatosByAssunto(assunto,pageable);
	
	var contatosVosPage=contatosPage.map(c-> Mapper.parseObject(c, ContatoVO.class));
	
	contatosVosPage.map(p->
	p.add(linkTo(methodOn(ContatoController.class).findById(p.getKey())).withSelfRel()));
	
	
	Link link=linkTo(methodOn(ContatoController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
	return assembler.toModel(contatosVosPage,link);
}
public ContatoVO update(ContatoVO contato) {
	if(contato==null)throw new RequiredObjectIsNullException();
	
	var entity= contatoRepository.findById(contato.getKey()).get();
	entity.setId(contato.getKey());
	entity.setAssunto(contato.getAssunto());
	entity.setMensagem(contato.getMensagem());
	var cliente=clienteRepository.findById(contato.getCliente().getKey()).get();
	entity.setCliente(cliente);
	var vo= Mapper.parseObject(contatoRepository.save(entity), ContatoVO.class) ;
	vo.add(linkTo(methodOn(ContatoController.class).findById(vo.getKey())).withSelfRel());
	return vo;
}
public void delete(Long id) {
	contatoRepository.deleteById(id);
}
}
