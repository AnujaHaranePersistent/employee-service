package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.employee.jwt.JwtRequest;
import com.employee.jwt.JwtResponse;
import com.employee.jwt.JwtTokenUtil;
import com.employee.jwt.JwtUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author anuja_harane
 * 
 *         AuthenticationController is responsible to generate token and verify the authntication
 *         token
 *
 */
@RestController
@CrossOrigin
@Api(value = "Authentication Service", description = "Generation and validation of token")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  @Autowired
  private JwtUserDetailsService userDetailsService;

  /**
   * @param authenticationRequest jwtRequest with username and password
   * @return return jwtResponse with token
   * @throws Exception if username and password is not valid
   */
  @ApiOperation(value = "Create authentication token", response = JwtResponse.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
      @ApiResponse(code = 401, message = "not authorized!"),
      @ApiResponse(code = 403, message = "forbidden!!!")})
  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@ApiParam(value = "Jwt Request Object",
      required = true) @RequestBody JwtRequest authenticationRequest) throws Exception {
    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String token = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok(new JwtResponse(token));
  }

  /**
   * @param username
   * @param password
   * @throws Exception if username and password is not valid
   */
  private void authenticate(String username, String password) throws Exception {
    try {
      authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }
}
