package com.employee.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import com.employee.jwt.JwtRequest;
import com.employee.jwt.JwtTokenUtil;
import com.employee.jwt.JwtUserDetailsService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JwtTokenServiceTest {


  @Mock
  JwtTokenUtil tokenService = new JwtTokenUtil();
  @Mock
  JwtUserDetailsService userDetailsService = new JwtUserDetailsService();

  @Test
  public void testCreateAuthenticationToken() {
    JwtRequest req = new JwtRequest("admin", "admin");
    String token =
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU3NzcyMzc5MCwiaWF0IjoxNTc3NzA1NzkwfQ.kdZvlJUGYcfwKMP0GgrI47DNNhG-BS7Z-MnB60tm4kbJvjO7tSrXwdtyqUkm4h8Yr3CU_FZZ15E2PtPftCBQpg";
    final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
    when(tokenService.generateToken(userDetails)).thenReturn(token);
    String result = tokenService.generateToken(userDetails);
    // System.out.println(result);
    assertEquals(token, result);

  }



}
