package next_cesar_imersao.PerceptualHash.controler;

import next_cesar_imersao.PerceptualHash.model.Images;
import next_cesar_imersao.PerceptualHash.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import java.io.IOException;
import java.util.List;

@RestController
public class ImagesControler {

    private final ImagesService imagesService;

    @Autowired
    public ImagesControler(final ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @GetMapping({"/", "/images/list", "/images/list/"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Images> showListImagens(){
        return this.imagesService.listagemImagens();
    }

    @PostMapping({"/imagem/save", "/imagem/save/"})
    public String storeFile(@RequestParam("imagem") MultipartFile multipartFile
    ) throws Exception {
        imagesService.storeFile(multipartFile);
        return "imagem inserida";
    }

    @DeleteMapping({ "/imagem/{id}/{nome}", "/imagem/{id}/{nome}/"})
    public String delete(@RequestParam("id") final Long id,
                         @RequestParam("nome") final String nome) throws IOException {
        this.imagesService.delete(id,nome);
        return "imagem deletada do banco de dados";
    }

    @PostMapping({"/imagem/comparar2imagens", "/imagem/comparar2imagens/"})
    public String comparar2Imagens(@RequestParam("imagem1") MultipartFile multipartFile1,
                            @RequestParam("imagem2") MultipartFile multipartFile2
    ) throws Exception {
        imagesService.compararDuasImagens(multipartFile1,multipartFile2);
        return ("O coeficiente de diferença entre a imagem " + multipartFile1.getOriginalFilename() +
                " e a imagem " + multipartFile2.getOriginalFilename() +
                " é: " + imagesService.getSimilarityScore());
    }

    @PostMapping({"/imagem/comparar1imagem", "/imagem/comparar1imagem/"})
    public String comparar1Imagem(@RequestParam("imagem1") MultipartFile file1) throws Exception {
        imagesService.compararUmaImagem(file1);
        return ("Comparação feita com sucesso");
    }

}
