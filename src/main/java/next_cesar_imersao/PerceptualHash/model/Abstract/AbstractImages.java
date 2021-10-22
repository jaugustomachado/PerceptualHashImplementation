package next_cesar_imersao.PerceptualHash.model.Abstract;


import next_cesar_imersao.PerceptualHash.model.Interface.ImagesEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigInteger;
import java.util.Objects;


@MappedSuperclass
public abstract class AbstractImages implements ImagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "hashvalue", nullable = false)
    private BigInteger hashvalue;

    @Column(name = "upload_dir", nullable = false)
    private String uploadDir;

    protected AbstractImages() {
        super();
    }

    protected AbstractImages(final String nome) {
        super();
        this.nome = nome;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(final String nome){
        this.nome = nome;
    }

    @Override
    public BigInteger getHashvalue() {
        return null;
    }

    @Override
    public void setHashvalue(BigInteger hashvalue) {
        this.hashvalue = hashvalue;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (!(this.getClass().equals(object.getClass()))) {
            return false;
        }

        return Objects.equals(this.getNome(), ((AbstractImages) object).getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }


}
