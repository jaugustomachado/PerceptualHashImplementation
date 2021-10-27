package next_cesar_imersao.PerceptualHash.repository;

import next_cesar_imersao.PerceptualHash.model.Images;
import next_cesar_imersao.PerceptualHash.repository.Interface.NextRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends NextRepository<Images> {

}