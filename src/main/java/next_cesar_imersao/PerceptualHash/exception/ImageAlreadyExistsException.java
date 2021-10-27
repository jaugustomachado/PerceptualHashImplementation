package next_cesar_imersao.PerceptualHash.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ImageAlreadyExistsException extends RuntimeException {
    public ImageAlreadyExistsException(String message) {
        super(message);
    }
}
