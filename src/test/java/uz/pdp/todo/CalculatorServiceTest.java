package uz.pdp.todo;

import org.junit.jupiter.api.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CalculatorServiceTest {

    public CalculatorService calculatorService;


    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    public void testAdd() {
        Double a = 1., b = 2., expect = 3.;
        Double result = calculatorService.add(a, b);
        Assertions.assertEquals(expect, result);
    }

    @Test
    public void testAdd_Should_Throw_BadRequestException_When_Param_Is_Null() {
        Double a = null, b = 2.;

        Assertions.assertThrows(BadRequest.class, () -> {
            calculatorService.add(a, b);
        });


    }

    @Test
    @DisplayName("-1 qaytarishi kerak teng bolsa")
    public void testAdd_Should_Return_Negative_One_When_Params_Is_Equal() {
        Double a = 2., b = 2., expect = -1.;

        Double result = calculatorService.add(a, b);

        Assertions.assertEquals(expect, result);


    }

    @Test
    @Order(0)
    public void testAdd_Should_Return_Negative_Two_When_Params_Is_100() {
        Double a = 100., b = 100., expect = -2.;

        Double result = calculatorService.add(a, b);


        // assertion
        Assertions.assertEquals(expect, result);

        //verify

    }

}

// set upp
// execute function
// assertion
// verify
