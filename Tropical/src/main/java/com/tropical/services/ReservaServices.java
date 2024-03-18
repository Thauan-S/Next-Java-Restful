package com.tropical.services;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tropical.data.vo.v1.ReservaVO;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.mapper.Mapper;
import com.tropical.model.Reserva;
import com.tropical.repository.ClienteRepository;
import com.tropical.repository.PacoteRepository;
import com.tropical.repository.ReservaRepository;

@Service
public class ReservaServices {
	@Autowired
	ReservaRepository repository;
	@Autowired
	ClienteRepository cliRepository;
	@Autowired
	PacoteRepository pacoteRepository;
	public ReservaVO create(ReservaVO reserva) {
		var dateTime=LocalDateTime.now();
		reserva.setDataReserva(dateTime);
		var entity= Mapper.parseObject(reserva, Reserva.class);
		var vo= Mapper.parseObject(repository.save(entity), ReservaVO.class);
		return vo;
	}
	public ReservaVO update(ReservaVO  reserva) {
		var entity=repository.findById(reserva.getKey()).orElseThrow(()-> new ResourceNotFoundException("Nenhuma reserva encontrada com este id"));
		var cliente=cliRepository.findById(reserva.getCliente().getKey()).orElseThrow(() -> new ResourceNotFoundException("Nenhum Cliente encontrado com este id!"));
		var pacote= pacoteRepository.findById(reserva.getPacoteDeViagem().getKey()).orElseThrow(()->new ResourceNotFoundException("Nenhum pacote de viagem foi encontrado para este id!"));
		entity.setCliente(cliente);
		entity.setDataReserva(LocalDateTime.now());
		entity.setDataViagem(reserva.getDataViagem());
		entity.setPacoteDeViagem(pacote);
		return Mapper.parseObject(repository.save(entity), ReservaVO.class) ;
	}
	public List<ReservaVO> findAll() {
		return Mapper.parseListObjects(repository.findAll(), ReservaVO.class) ;
	}
	public ReservaVO findById(Long id) {
		var entity= repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Não existem reservas para este id informado !"));
		return Mapper.parseObject(entity, ReservaVO.class);
	}
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
