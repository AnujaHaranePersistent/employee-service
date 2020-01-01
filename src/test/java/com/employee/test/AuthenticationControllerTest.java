package com.employee.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.employee.controller.AuthenticationController;
import com.employee.jwt.JwtAuthenticationEntryPoint;
import com.employee.jwt.JwtRequest;
import com.employee.jwt.JwtResponse;
import com.employee.jwt.JwtTokenUtil;
import com.employee.jwt.JwtUserDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @MockBean
  private AuthenticationManager authenticationManager;

  @MockBean
  private JwtTokenUtil tokenService;

  @MockBean
  private JwtUserDetailsService userDetailsService;



  @Test
  public void testCreateAuthenticationToken() throws Exception {

    JwtRequest req = new JwtRequest("admin", "admin");
    JwtResponse res = new JwtResponse(
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU3NzcyMzc5MCwiaWF0IjoxNTc3NzA1NzkwfQ.kdZvlJUGYcfwKMP0GgrI47DNNhG-BS7Z-MnB60tm4kbJvjO7tSrXwdtyqUkm4h8Yr3CU_FZZ15E2PtPftCBQpg");
    String token =
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU3NzcyMzc5MCwiaWF0IjoxNTc3NzA1NzkwfQ.kdZvlJUGYcfwKMP0GgrI47DNNhG-BS7Z-MnB60tm4kbJvjO7tSrXwdtyqUkm4h8Yr3CU_FZZ15E2PtPftCBQpg";
    final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
    when(tokenService.generateToken(userDetails)).thenReturn(token);
    String inputInJson = this.mapToJson(req);
    String expectedJson = this.mapToJson(res);
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/authenticate").accept(MediaType.APPLICATION_JSON)
            .content(inputInJson).contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    String outputInJson = response.getContentAsString();

    assertEquals(outputInJson, expectedJson);
    assertEquals(HttpStatus.OK.value(), response.getStatus());

  }

  private String mapToJson(Object object) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(object);
  }
}
