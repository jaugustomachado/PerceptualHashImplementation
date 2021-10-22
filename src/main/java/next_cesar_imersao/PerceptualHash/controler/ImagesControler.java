package next_cesar_imersao.PerceptualHash.controler;

import next_cesar_imersao.PerceptualHash.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import java.io.IOException;

@RestController
public class ImagesControler {

    private final ImagesService imagesService;

    @Autowired
    public ImagesControler(final ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @GetMapping({"/", "/images/list", "/images/list/"})
    public String showListImages() {
        return "ListaImagens";
    }

    @PostMapping({"/imagem/save", "/imagem/save/"})
    public String storeFile(@RequestParam("image") MultipartFile multipartFile
    ) throws Exception {
        imagesService.storeFile(multipartFile);
        return "imagem inserida";
    }

}
