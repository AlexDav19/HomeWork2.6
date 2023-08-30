package pro.sky.HomeWork.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class EmployeeStorageIsFullException extends RuntimeException{
    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}
