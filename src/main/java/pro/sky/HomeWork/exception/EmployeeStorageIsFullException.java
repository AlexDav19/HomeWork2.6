package pro.sky.HomeWork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeStorageIsFullException extends RuntimeException{
    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}
