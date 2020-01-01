package com.employee.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import com.employee.model.Employee;
import com.employee.model.Manager;

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
    Manager manager = new Manager(101, "abc", "abc");
    Employee emp = new Employee(1, "xyz", "xyz", 1001, manager);
    // when:
    Set<ConstraintViolation<Employee>> violations = validator.validate(emp);

    // then:
    assertTrue(violations.isEmpty());
  }

  @Test
  public void shouldDetectInvalidName() {
    // given too short name:
    // given:
    Manager manager = new Manager();
    manager.setId(101);
    manager.setFirstName("abc");
    manager.setLastName("abc");
    Employee emp = new Employee(1, null, "xyz", 1001, manager);

    // when:
    Set<ConstraintViolation<Employee>> violations = validator.validate(emp);


    // then:
    assertEquals(violations.size(), 1);

    ConstraintViolation<Employee> violation = violations.iterator().next();
    assertEquals("must not be null", violation.getMessage());
    assertEquals("firstName", violation.getPropertyPath().toString());
    assertEquals(null, violation.getInvalidValue());
  }



}

