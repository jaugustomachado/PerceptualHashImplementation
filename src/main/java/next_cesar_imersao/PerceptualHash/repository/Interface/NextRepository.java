package next_cesar_imersao.PerceptualHash.repository.Interface;

import next_cesar_imersao.PerceptualHash.model.Abstract.AbstractImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NextRepository<T extends AbstractImages> extends JpaRepository<T, Long> {

}
