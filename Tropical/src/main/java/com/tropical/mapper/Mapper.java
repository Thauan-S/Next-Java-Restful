package com.tropical.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.tropical.data.vo.v1.ClienteVO;
import com.tropical.data.vo.v1.ContatoVO;
import com.tropical.data.vo.v1.PacoteDeViagemVO;
import com.tropical.data.vo.v1.ReservaVO;
import com.tropical.model.Cliente;
import com.tropical.model.Contato;
import com.tropical.model.PacoteDeViagem;
import com.tropical.model.Reserva;


public class Mapper {
	private static ModelMapper mapper=new ModelMapper();
	
	static {
		mapper.createTypeMap(Cliente.class, ClienteVO.class).addMapping(Cliente::getId,ClienteVO::setKey);
		mapper.createTypeMap(ClienteVO.class,Cliente.class ).addMapping(ClienteVO::getKey,Cliente::setId);
		
		mapper.createTypeMap(Contato.class, ContatoVO.class).addMapping(Contato::getId,ContatoVO::setKey);
		mapper.createTypeMap(ContatoVO.class,Contato.class ).addMapping(ContatoVO::getKey,Contato::setId);
		
		mapper.createTypeMap(PacoteDeViagem.class, PacoteDeViagemVO.class).addMapping(PacoteDeViagem::getId,PacoteDeViagemVO::setKey);
		mapper.createTypeMap(PacoteDeViagemVO.class,PacoteDeViagem.class ).addMapping(PacoteDeViagemVO::getKey,PacoteDeViagem::setId);
		
		mapper.createTypeMap(Reserva.class, ReservaVO.class).addMapping(Reserva::getId,ReservaVO::setKey);
		mapper.createTypeMap(ReservaVO.class,Reserva.class ).addMapping(ReservaVO::getKey,Reserva::setId);
	}
	public static <O,D> D parseObject(O origin , Class<D> destination)  {
		return mapper.map(origin, destination);
	}
	
	public static <O,D> List <D> parseListObjects(List<O> origin , Class<D> destination)  {
		List<D> destinationObjects=new ArrayList<D>();
		for (O o  : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		return destinationObjects;
	}
}
