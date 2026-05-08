package MoneyMachine.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public void handleAllExceptions(Exception ex) {
        
        StackTraceElement[] trace = ex.getStackTrace();
        String locationInfo = "unknown location";

        if (trace.length > 0) {
            StackTraceElement targetElement = ex.getStackTrace()[0];

            locationInfo = String.format("%s.%s(%s:%d)", 
                targetElement.getClassName(), 
                targetElement.getMethodName(), 
                targetElement.getFileName(), 
                targetElement.getLineNumber()
            );
        }

        System.err.println(String.format("[RUNTIME ERROR] Reason: %s | Code Location: %s", ex.getMessage(), locationInfo));
    }
}
