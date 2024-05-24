package in.himanshu.expenseapp.controller;


import in.himanshu.expenseapp.dto.RequestUserDto;
import in.himanshu.expenseapp.dto.ResponseUserDto;
import in.himanshu.expenseapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    private final UserService userService;


    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(){

        return new ResponseEntity<String>("User is logged in", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseUserDto> createUser(@Valid @RequestBody RequestUserDto requestUserDto){
        return new ResponseEntity<ResponseUserDto>(userService.createUser(requestUserDto), HttpStatus.CREATED);
    }
}
