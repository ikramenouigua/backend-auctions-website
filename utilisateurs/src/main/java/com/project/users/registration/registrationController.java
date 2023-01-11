package com.project.users.registration;

import com.project.users.appUser.AppUser;
import com.project.users.appUser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;



@RestController
@CrossOrigin
@RequestMapping(path="api/v1/registration")
@AllArgsConstructor
public class registrationController {
    private final RegistrationService registrationService;
    private  AuthenticationManager authenticationManager ;
    private final AppUserRepository appUserRepository;

    @PostMapping("/add")
    public String register(@RequestBody registrationRequest request) {
        System.out.println("salaam");
        System.out.println(request);
        return registrationService.register(request);
    }


    @GetMapping("/allUser")
    public ResponseEntity<List<AppUser>> getAllAuctions (){
        List<AppUser> users = registrationService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public Optional<AppUser> currentUserNameSimple(HttpServletRequest request) {
        Optional<AppUser> user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            user=  appUserRepository
                    .findByEmail(currentUserName);
            return user;
        }
        return null;
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public Authentication logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return auth;
    }



    @PostMapping("/signin")
    @CrossOrigin
    public  Optional<loginRequest> authenticateUser(@RequestBody loginRequest loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<AppUser> user=  appUserRepository
                .findByEmail( loginDto.getUsernameOrEmail() );
        return Optional.of(loginDto);

    }

    @GetMapping("/signin")
    @CrossOrigin
    public Optional<loginRequest> authenticateUserGet(@RequestBody loginRequest loginDto){
        Optional<AppUser> user=  appUserRepository
                .findByEmail( loginDto.getUsernameOrEmail() );

        return authenticateUser(loginDto);

    }


}