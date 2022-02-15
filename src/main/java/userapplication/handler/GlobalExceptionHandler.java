package userapplication.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import userapplication.constants.MessageCodes;
import userapplication.dto.ExceptionResponseModel;
import userapplication.io.StatusMessage;

import java.io.IOException;
import java.nio.file.AccessDeniedException;


/**
 * This class used to define global and custom exceptions for server internal error,
 * custom, bad request, time out etc,.
 *
 * @author Milesh Kummaar
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * This method used to throw server internal error at global level
     *
     * @param e
     * @return
     * @throws Exception
     * @author Milesh Kummaar
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseModel> generalException(Exception e) throws Exception {
        log.error("Exception type Exception in generalException() in  class GlobalExceptionHandler and exception is {}", e);
        ExceptionResponseModel model = new ExceptionResponseModel();
        model.setStatus(MessageCodes.INTERNAL_SERVER_ERROR);
        model.setStatusMessage(new StatusMessage(MessageCodes.INTERNAL_SERVER_ERROR_MSG, e.getMessage()));
        return new ResponseEntity<ExceptionResponseModel>(model, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * This method used to send bad request response error at global level
     *
     * @param e
     * @return
     * @throws IOException
     * @author Milesh Kummaar
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseModel> badRequest(MethodArgumentNotValidException e) throws IOException {
        log.error("Exception type MethodArgumentNotValidException in badRequest() in  class GlobalExceptionHandler and exception is {}", e);
        ExceptionResponseModel model = new ExceptionResponseModel();
        model.setStatus(MessageCodes.BAD_REQUEST);
        model.setStatusMessage(new StatusMessage(MessageCodes.BAD_REQUEST_MSG, e.getMessage()));
        return new ResponseEntity<ExceptionResponseModel>(model, HttpStatus.BAD_REQUEST);
    }


    /**
     * This method used to send bad request response error at global level
     *
     * @param e
     * @return
     * @throws IOException
     * @author Milesh Kummaar
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseModel> handleIllegalArgumentException(IllegalArgumentException e) throws IOException {
        log.error("Exception type IllegalArgumentException in handleIllegalArgumentException() in  class GlobalExceptionHandler and exception is {}", e);
        ExceptionResponseModel model = new ExceptionResponseModel();
        model.setStatus(MessageCodes.BAD_REQUEST);
        model.setStatusMessage(new StatusMessage(MessageCodes.BAD_REQUEST_MSG, e.getMessage()));
        return new ResponseEntity<ExceptionResponseModel>(model, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method used to send unauthorized error.
     *
     * @param ce
     * @return
     * @throws IOException
     * @author Milesh Kummaar
     */

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponseModel> unauthorizedException(AccessDeniedException ce) throws IOException {
        log.error("Exception type Unauthorized in invalidResponse() in  class GlobalExceptionHandler and exception is {}", ce);
        ExceptionResponseModel model = new ExceptionResponseModel();
        model.setStatus(MessageCodes.UNAUTHORIZED);
        model.setStatusMessage(new StatusMessage(MessageCodes.UNAUTHORIZED_MSG, ce.getMessage()));
        return new ResponseEntity<ExceptionResponseModel>(model, HttpStatus.UNAUTHORIZED);

    }

}
