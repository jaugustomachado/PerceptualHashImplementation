package next_cesar_imersao.PerceptualHash.model.Interface;

import java.io.Serializable;
import java.math.BigInteger;

public interface ImagesEntity extends Serializable {

    Long getId();

    void setId(final Long id);

    String getNome();

    void setNome(final String nome);

    BigInteger getHashvalue();

    void setHashvalue(final BigInteger Hashvalue);

    String getUploadDir();

    void setUploadDir(String uploadDir);


}
