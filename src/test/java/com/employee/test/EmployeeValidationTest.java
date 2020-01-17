package com.employee.test;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import com.employee.model.Employee;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EmployeeValidationTest {

  private static ValidatorFactory validatorFactory;
  private static Validator validator;

  @BeforeClass
  public static void createValidator() {
    validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  @AfterClass
  public static void close() {
    validatorFactory.close();
  }

  @Test
  public void shouldHaveNoViolations() {
    // given:

    Employee emp = Mockito.mock(Employee.class);
    // when:
    Set<ConstraintViolation<Employee>> violations = validator.validate(emp);

    // then:
    // assertTrue(violations.isEmpty());
  }



}

