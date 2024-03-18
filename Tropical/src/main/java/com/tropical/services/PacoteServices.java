package com.tropical.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tropical.data.vo.v1.PacoteDeViagemVO;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.mapper.Mapper;
import com.tropical.model.PacoteDeViagem;
import com.tropical.repository.PacoteRepository;

@Service
public class PacoteServices {
	@Autowired
	PacoteRepository pacoteRepository;
	
	public PacoteDeViagemVO create(PacoteDeViagemVO pacote) {
		var entity=Mapper.parseObject(pacote, PacoteDeViagem.class);
		var vo= Mapper.parseObject(pacoteRepository.save(entity), PacoteDeViagemVO.class);
		return vo;
	} 
	
	public PacoteDeViagemVO findById(Long id) {
		
		var entity= pacoteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Não existe um pacote com o Id informado!"));
		return Mapper.parseObject(entity, PacoteDeViagemVO.class);
	}
	public List<PacoteDeViagemVO> findAll() {
		return Mapper.parseListObjects(pacoteRepository.findAll(), PacoteDeViagemVO.class);
	}
	public PacoteDeViagemVO update(PacoteDeViagemVO pacote) {
		var entity= pacoteRepository.findById(pacote.getKey()).orElseThrow(()-> new ResourceNotFoundException("Nenhum pacote encontrado para este id!"));
			entity.setCategoria(pacote.getCategoria());
			entity.setDescricao(pacote.getDescricao());
			entity.setDestino(pacote.getDestino());
			entity.setDuracaoEmDias(pacote.getDuracaoEmDias());
			entity.setImagem(pacote.getImagem());
			entity.setPreco(pacote.getPreco());
		 return Mapper.parseObject(pacoteRepository.save(entity),PacoteDeViagemVO.class);
	}
	public void delete(Long id) {
		pacoteRepository.deleteById(id);
	}
}
