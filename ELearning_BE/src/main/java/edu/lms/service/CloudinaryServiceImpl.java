package edu.lms.service;

import com.cloudinary.Cloudinary;
import edu.lms.enums.UploadFolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary; // Inject

    @Override
    public String uploadFile(MultipartFile file, UploadFolder uploadFolder, String entityId) {
        try {
            String fileName = file.getOriginalFilename();

            log.info("Uploading file {}", fileName);

            String publicId = fileName + "_" + System.currentTimeMillis();

            log.info("Public ID {}", publicId);

            Map map = Map.of(
                    "folder", uploadFolder.getPath(),
                    "public_id", publicId,
                    "resource_type", "auto",
                    "overwrite", true
            );

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), map); // checked/compile-time exception

            return uploadResult.get("secure_url").toString();
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file", e);
        }
    }

    private void validateFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        if (file.getSize() > 50_000_000) { // 50MB
            throw new IllegalArgumentException("File too large");
        }
    }
}
