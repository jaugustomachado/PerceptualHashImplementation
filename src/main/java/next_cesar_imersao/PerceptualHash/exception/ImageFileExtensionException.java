package next_cesar_imersao.PerceptualHash.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImageFileExtensionException extends RuntimeException {
    public ImageFileExtensionException(String message) {
        super(message);
    }
}