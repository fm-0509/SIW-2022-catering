package it.uniroma3.siw.catering.service.image;

import it.uniroma3.siw.catering.model.image.Image;
import it.uniroma3.siw.catering.repository.image.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ImageService {

    @Autowired
    private ImageRepository imageRepository;


    public Image store(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image(filename, file.getContentType(), file.getBytes());
        return this.imageRepository.save(image);
    }

    public Image findById(String id)
    {
        return this.imageRepository.findById(id).orElse(null);
    }

}
