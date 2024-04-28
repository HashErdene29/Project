package cs489.project.advice;

import cs489.project.exception.DishNotFoundException;
import cs489.project.exception.OrderNotFoundException;
import cs489.project.exception.ReservationNotFoundException;
import cs489.project.exception.RestaurantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ProjectWebExceptionHandler {
    @ExceptionHandler(RestaurantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleRestaurantNotFoundException(RestaurantNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Error message", ex.getMessage());
        return errors;
    }

    @ExceptionHandler(DishNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleDishNotFoundException(DishNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Error message", ex.getMessage());
        return errors;
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleOrderNotFoundException(OrderNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Error message", ex.getMessage());
        return errors;
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleReservationNotFoundException(ReservationNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Error message", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleDataValidationErrors(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, String> handleBadCredentialsException(BadCredentialsException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Error message", ex.getMessage());
        errors.put("Error Display text", "Invalid username or password");
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalStateException.class)
    public Map<String, String> handleIllegalStateException(IllegalStateException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Error message", ex.getMessage());
        return errors;
    }
}
