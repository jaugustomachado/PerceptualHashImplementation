package next_cesar_imersao.PerceptualHash.service;


import dev.brachtendorf.jimagehash.hashAlgorithms.AverageHash;
import dev.brachtendorf.jimagehash.hashAlgorithms.HashingAlgorithm;
import next_cesar_imersao.PerceptualHash.model.Images;
import next_cesar_imersao.PerceptualHash.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashSet;
import java.util.Set;


@Service
public class ImagesService {

    private final ImagesRepository imagesRepository;
    private static final HashingAlgorithm hasher = new AverageHash(64);
    //private double similarityScore;
    private final Path fileStorageLocation= Path.of("src/Imagens/");

    @Autowired
    public ImagesService(final ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

//    public String CompararDuasImagens(String x, String y) throws Exception {
//        File img0 = new File(String.valueOf(loadFileAsResource(x)));
//        File img1 = new File(String.valueOf(loadFileAsResource(x)));
//
//        try {
//            Hash hash1 = hasher.hash(img0);
//            Hash hash2 = hasher.hash(img1);
//            this.similarityScore = hash1.normalizedHammingDistance(hash2);
//            System.out.println("O coeficiente de diferença entre a imagem " + x + " e a imagem " + y + " é: " + similarityScore);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return ("O coeficiente de diferença entre a imagem " + x + " e a imagem " + y + " é: " + similarityScore);
//
//    }
//
//    public String CompararUmaImagem(String arquivo) throws Exception {
//        double score=100;
//
//        File pasta = new File(String.valueOf(fileStorageLocation));
//        List<String> listaDeArquivos = new ArrayList<>();
//
//        for (final File file : pasta.listFiles()) {
//            listaDeArquivos.add(file.getName());
//        }
//
//        String imagemMaisIgual=listaDeArquivos.get(0);
//
//        for (int i = 0; i < listaDeArquivos.size(); i++) {
//            if(!listaDeArquivos.get(i).equals(arquivo)){
//                CompararDuasImagens(arquivo,listaDeArquivos.get(i));
//                if (similarityScore<score){
//                    score=similarityScore;
//                    imagemMaisIgual=listaDeArquivos.get(i);
//                }
//            }
//        }
//        return ("A imagem mais similar é: "+ imagemMaisIgual);
//    }

    @Transactional
    public Set<Images> listagemImagens() {
        return new LinkedHashSet<>(this.imagesRepository.findAll());
    }

    public void saveFile(String nome,
                         MultipartFile multipartFile) throws IOException {
        Path uploadPath = fileStorageLocation;
        Path filePath = uploadPath.resolve(nome);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + nome, ioe);
        }
    }

    @Transactional
    public String storeFile(MultipartFile file) throws Exception {
        String originalNome = StringUtils.cleanPath(file.getOriginalFilename());
        String nome = "";
        try {
            if(originalNome.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence " + originalNome);
            }
            String fileExtension = "";
            try {
                fileExtension = originalNome.substring(originalNome.lastIndexOf("."));
            } catch(Exception e) {
                fileExtension = "";
            }
            if (fileExtension.equals(".jpg")) {
                //Path targetLocation = this.fileStorageLocation.resolve(originalNome);
                saveFile(originalNome, file);
                Images newImage = new Images();
                newImage.setNome(originalNome);
                BigInteger hash = hasher.hash(new File((String.valueOf(fileStorageLocation)+"/"+originalNome))).getHashValue();
                newImage.setHashvalue(hash);
                newImage.setUploadDir(String.valueOf(fileStorageLocation));
                imagesRepository.save(newImage);
            }else{
                System.out.println("Error - filetype is not .jpg");
            }
            return originalNome;
        } catch (IOException ex) {
            throw new Exception("Could not store file " + nome + ". Please try again!", ex);
        }
    }
}


