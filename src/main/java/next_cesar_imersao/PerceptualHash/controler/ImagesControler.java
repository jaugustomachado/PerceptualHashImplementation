package next_cesar_imersao.PerceptualHash.controler;

import next_cesar_imersao.PerceptualHash.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String storeFile(@RequestParam("imagem") MultipartFile multipartFile
    ) throws Exception {
        imagesService.storeFile(multipartFile);
        return "imagem inserida";
    }

    @PostMapping({"/imagem/comparar2imagens", "/imagem/comparar2imagens/"})
    public String storeFile(@RequestParam("imagem1") MultipartFile multipartFile1,
                            @RequestParam("imagem2") MultipartFile multipartFile2
    ) throws Exception {
        imagesService.compararDuasImagens(multipartFile1,multipartFile2);
        return ("O coeficiente de diferença entre a imagem " + multipartFile1.getOriginalFilename() +
                " e a imagem " + multipartFile2.getOriginalFilename() +
                " é: " + imagesService.getSimilarityScore());
    }

    @DeleteMapping({ "/{id}/{nome}", "/{id}/{nome}/"})
    public String delete(@RequestParam("id") final Long id,
                         @RequestParam("nome") final String nome) throws IOException {
        this.imagesService.delete(id,nome);
        return "imagem deletada do banco de dados";
    }
}
