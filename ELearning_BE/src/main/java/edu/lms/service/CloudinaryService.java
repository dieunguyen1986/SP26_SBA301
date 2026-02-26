package edu.lms.service;

import edu.lms.enums.UploadFolder;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    String uploadFile(MultipartFile file, UploadFolder uploadFolder, String entityId);
}
