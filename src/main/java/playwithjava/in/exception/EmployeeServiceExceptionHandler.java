package playwithjava.in.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class EmployeeServiceExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorBean> handleNotFoundException(EmployeeNotFoundException exception, WebRequest request) {
        ErrorBean errorBean = ErrorBean.builder()
                .errorMsg("Employee not found.")
                .recommendedAction("Check with Admin")
                .errorCode(400)
                .build();
        return new ResponseEntity<ErrorBean>(errorBean, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeAlreadyExistException.class)
    public ResponseEntity<ErrorBean> handleNotFoundException(EmployeeAlreadyExistException exception, WebRequest request) {
        ErrorBean errorBean = ErrorBean.builder()
                .errorMsg(exception.getMessage())
                .recommendedAction("Check with Admin")
                .errorCode(400)
                .build();
        return new ResponseEntity<ErrorBean>(errorBean, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorBean> entityNotFound(MethodArgumentNotValidException exception, WebRequest request) {
        ErrorBean errorBean = ErrorBean.builder()
                .errorMsg(exception.getMessage())
                .recommendedAction("provide all mandatory attributes")
                .errorCode(400)
                .build();
        return new ResponseEntity<ErrorBean>(errorBean, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorBean> entityNotFound(NullPointerException exception, WebRequest request) {
        ErrorBean errorBean = ErrorBean.builder()
                .errorMsg(exception.getMessage())
                .recommendedAction("Something went wrong,Please check with Admin")
                .errorCode(400)
                .build();
        return new ResponseEntity<ErrorBean>(errorBean, HttpStatus.NOT_FOUND);
    }
}
