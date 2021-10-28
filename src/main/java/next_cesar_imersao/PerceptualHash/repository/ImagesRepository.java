package next_cesar_imersao.PerceptualHash.repository;

import next_cesar_imersao.PerceptualHash.model.Images;
import next_cesar_imersao.PerceptualHash.repository.Interface.NextRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends NextRepository<Images> {

    @Query("SELECT t FROM Images t WHERE t.nome=?1")
    Images findByName(String nome);
}