package uz.pdp.todo;


import org.springframework.stereotype.Component;

@Component
public class CalculatorService {
    public Double add(Double a, Double b) {
        if (a == null || b == null) {
            throw new BadRequest();
        }

        if (a == 100 && b == 100) {
            return -2.;
        }

        if (a.equals(b)) {
            return -1.;
        }

        return a + b;
    }

    public Double subtract(Double a, Double b) {
        return a - b;
    }

    public Double multiply(Double a, Double b) {
        return a * b;
    }

    public Double divide(Double a, Double b) {
        return a / b;
    }
}
