package com.example.usermodule.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
	
	public final String storageDirectoryPath = "C:\\Users\\hadrihl\\Desktop\\codemonkey__\\uploadimg\\src\\main\\resources\\static\\assets\\img";

	public String uploadProfileImage(MultipartFile imgFile) {
		
		String fileName = imgFile.getOriginalFilename();
		Path storageDirectory = Paths.get(storageDirectoryPath);
		
		if(!Files.exists(storageDirectory)) { // if the path not exist, 
			try {
				Files.createDirectories(storageDirectory); // create it
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Path destination = Paths.get(storageDirectoryPath.toString() + "\\" + fileName);
		
		// copying the file into destination path
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return fileName;
	}
}
