package com.tropical.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tropical.config.FileStorageConfig;
import com.tropical.exceptions.FileStorageException;



@Service
public class FileStorageServices {
	private final Path fileStorageLocation;
	
	@Autowired
	public FileStorageServices(FileStorageConfig  fileStorageConfig) {
		Path path= Paths.get(fileStorageConfig.getUploadDir())
				.toAbsolutePath().normalize();
		
		this.fileStorageLocation=path;
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Não foi possível criar o diretório onde os arquivos enviados serão armazenados",e);
		}
				
	}
	public String storeFile(MultipartFile file) {
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
				try {
					if (fileName.contains(".."))throw new FileStorageException("Desculpe, o nome do arquivo contém um  caminho inválido"+fileName);
					Path targetLocation = this.fileStorageLocation.resolve(fileName);
					Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
					return fileName;
				} catch (Exception e) {
					throw new FileStorageException("Não foi possível armazenar o arquivo: "+fileName+ "por favor tente novamente");
				}
		
	}
	
	
						
}
