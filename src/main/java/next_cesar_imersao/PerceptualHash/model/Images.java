package next_cesar_imersao.PerceptualHash.model;

import next_cesar_imersao.PerceptualHash.model.Abstract.AbstractImages;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(schema = "images_database", name = "images", uniqueConstraints =  { @UniqueConstraint(name = "uk_image", columnNames = "nome") })
public class Images extends AbstractImages {

    public Images() {
        super();
    }

    @Override
    public String toString() {
        return String.format("{%s id=%d, nome='%s', hashvalue=%d}",super.toString(), super.getId(), super.getNome(), super.getHashvalue());
    }
}
