package com.tropical.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tropical.data.vo.v1.ClienteVO;
import com.tropical.mapper.Mapper;
import com.tropical.model.Cliente;
import com.tropical.unittests.mapper.mocks.MockCliente;

public class DozerConverterTest {
	    MockCliente inputObject;

	    @BeforeEach
	    public void setUp() {
	        inputObject = new MockCliente();
	    }

	    @Test
	    public void parseEntityToVOTest() throws ParseException {
	        ClienteVO output = Mapper.parseObject(inputObject.mockEntity(), ClienteVO.class);
	        assertEquals(Long.valueOf(0L), output.getKey());
	        assertEquals("Name Test0", output.getNome());
	        assertEquals("Email Test0", output.getEmail());
	        assertEquals("Senha Test0", output.getSenha());
	        assertEquals("Telefone Test0", output.getTelefone());
	        assertEquals("Cep Test0", output.getCep());
	        var date= LocalDate.now();
	        assertEquals(date, output.getDataNascimento());
	        
	    }

	    @Test
	    public void parseEntityListToVOListTest() throws ParseException {
	        List<ClienteVO> outputList = Mapper.parseListObjects(inputObject.mockEntityList(), ClienteVO.class);
	        ClienteVO outputZero = outputList.get(0);
	        
	        assertEquals(Long.valueOf(0L), outputZero.getKey());
	        assertEquals("Name Test0", outputZero.getNome());
	        assertEquals("Email Test0", outputZero.getEmail());
	        assertEquals("Senha Test0", outputZero.getSenha());
	        assertEquals("Telefone Test0", outputZero.getTelefone());
	        assertEquals("Cep Test0", outputZero.getCep());
	        var date= LocalDate.now();
	        assertEquals(date, outputZero.getDataNascimento());
	        ClienteVO outputSeven = outputList.get(7);
	        
	        assertEquals(Long.valueOf(7L), outputSeven.getKey());
	        assertEquals("Name Test7", outputSeven.getNome());
	        assertEquals("Email Test7", outputSeven.getEmail());
	        assertEquals("Senha Test7", outputSeven.getSenha());
	        assertEquals("Telefone Test7", outputSeven.getTelefone());
	        assertEquals("Cep Test7", outputSeven.getCep());
	        var date7= LocalDate.now();
	        assertEquals(date7, outputSeven.getDataNascimento());
	        ClienteVO outputTwelve = outputList.get(12);
	        
	        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
	        assertEquals("Name Test12", outputTwelve.getNome());
	        assertEquals("Email Test12", outputTwelve.getEmail());
	        assertEquals("Senha Test12", outputTwelve.getSenha());
	        assertEquals("Telefone Test12", outputTwelve.getTelefone());
	        assertEquals("Cep Test12", outputTwelve.getCep());
	        var date12= LocalDate.now();
	        assertEquals(date12, outputTwelve.getDataNascimento());
	    }

	    @Test
	    public void parseVOToEntityTest() throws ParseException {
	        Cliente output = Mapper.parseObject(inputObject.mockVO(), Cliente.class);
	        assertEquals(Long.valueOf(0L), output.getId());
	        assertEquals("Name Test0", output.getNome());
	        assertEquals("Email Test0", output.getEmail());
	        assertEquals("Senha Test0", output.getSenha());
	        assertEquals("Telefone Test0", output.getTelefone());
	        assertEquals("Cep Test0", output.getCep());
	        var date= LocalDate.now();
	        assertEquals(date, output.getDataNascimento());
	    }

	    @Test
	    public void parserVOListToEntityListTest() throws ParseException {
	        List<Cliente> outputList = Mapper.parseListObjects(inputObject.mockVOList(), Cliente.class);
	        Cliente outputZero = outputList.get(0);
	        
	        assertEquals(Long.valueOf(0L), outputZero.getId());
	        assertEquals("Name Test0", outputZero.getNome());
	        assertEquals("Email Test0", outputZero.getEmail());
	        assertEquals("Senha Test0", outputZero.getSenha());
	        assertEquals("Telefone Test0", outputZero.getTelefone());
	        assertEquals("Cep Test0", outputZero.getCep());
	        var date= LocalDate.now();
	        assertEquals(date, outputZero.getDataNascimento());
	        
	        Cliente outputSeven = outputList.get(7);
	        
	        assertEquals(Long.valueOf(7L), outputSeven.getId());
	        assertEquals("Name Test7", outputSeven.getNome());
	        assertEquals("Email Test7", outputSeven.getEmail());
	        assertEquals("Senha Test7", outputSeven.getSenha());
	        assertEquals("Telefone Test7", outputSeven.getTelefone());
	        assertEquals("Cep Test7", outputSeven.getCep());
	        var date7= LocalDate.now();
	        assertEquals(date7, outputSeven.getDataNascimento());
	        
	        
	        
	        Cliente outputTwelve = outputList.get(12);
	        
	        assertEquals(Long.valueOf(12L), outputTwelve.getId());
	        assertEquals("Name Test12", outputTwelve.getNome());
	        assertEquals("Email Test12", outputTwelve.getEmail());
	        assertEquals("Senha Test12", outputTwelve.getSenha());
	        assertEquals("Telefone Test12", outputTwelve.getTelefone());
	        assertEquals("Cep Test12", outputTwelve.getCep());
	        var date12= LocalDate.now();
	        assertEquals(date12, outputTwelve.getDataNascimento());
	        
	    }
	
}
