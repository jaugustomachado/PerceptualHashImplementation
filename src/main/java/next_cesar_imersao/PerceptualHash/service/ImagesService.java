package next_cesar_imersao.PerceptualHash.service;


import dev.brachtendorf.jimagehash.hash.Hash;
import dev.brachtendorf.jimagehash.hashAlgorithms.AverageHash;
import dev.brachtendorf.jimagehash.hashAlgorithms.HashingAlgorithm;
import next_cesar_imersao.PerceptualHash.model.Images;
import next_cesar_imersao.PerceptualHash.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;


@Service
public class ImagesService {

    private final ImagesRepository imagesRepository;
    private static final HashingAlgorithm hasher = new AverageHash(64);
    private final Path fileStorageLocation= Path.of("src/Imagens/");

    @Autowired
    public ImagesService(final ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Transactional
    public List<Images> listagemImagens() {
       return this.imagesRepository.findAll();
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


    @Transactional
    public void delete(Long id, String nome) throws IOException {
        File imagem = new File(String.valueOf(fileStorageLocation+"/"+nome));
            imagem.delete();
            imagesRepository.deleteById(id);
    }

    public String compararDuasImagens(MultipartFile file1, MultipartFile file2 ) throws Exception {
        BufferedImage img1 = ImageIO.read(file1.getInputStream());
        BufferedImage img2 = ImageIO.read(file2.getInputStream());

        Hash hash1 = hasher.hash(img1);
        Hash hash2 = hasher.hash(img2);
        double similarityScore = hash1.normalizedHammingDistance(hash2);

        return ("O coeficiente de diferença entre a imagem " + file1.getOriginalFilename() +
                " e a imagem " + file2.getOriginalFilename() +
                " é: " + similarityScore);

    }

    public String compararUmaImagem(MultipartFile file1) throws Exception {
        double score=64;
        double similarityScore=1;
        String imagemMaisIgual="";

        BufferedImage img1 = ImageIO.read(file1.getInputStream());
        Hash hash1 = hasher.hash(img1);

        for (Images image : this.imagesRepository.findAll()) {
            Hash hash2 = new Hash(image.getHashvalue(),hasher.getKeyResolution(),hasher.algorithmId());
            similarityScore = hash1.normalizedHammingDistance(hash2);
            if (similarityScore<score){
                score=similarityScore;
                imagemMaisIgual=image.getNome();
            }
        }
        return ("A imagem mais similar é: "+ imagemMaisIgual +
                " com o coeficiente de diferença: "+ similarityScore);
   }

}


