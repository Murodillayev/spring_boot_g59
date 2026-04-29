package uz.pdp.todo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, Model model, HttpServletRequest request) {
        model.addAttribute("error", e.getMessage());
        return request.getServletPath(); // user
    }

    @ExceptionHandler(BadREx.class)
    public String handleBadREx(BadREx e, Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("error", e.getMessage());
        response.setStatus(e.getStatus().value());
        return request.getServletPath(); // user
    }
}
