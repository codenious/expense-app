package in.himanshu.expenseapp.controller;

import in.himanshu.expenseapp.dto.RequestUserDto;
import in.himanshu.expenseapp.dto.ResponseUserDto;
import in.himanshu.expenseapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseUserDto> createUser(@Valid @RequestBody RequestUserDto requestUserDto){
        return new ResponseEntity<ResponseUserDto>(userService.createUser(requestUserDto), HttpStatus.CREATED);
    }


    //Password is also returning. Need to look how to resolve it. RESOLVED. BY ADDING A DIFFERENT DTO which is specific for Responses only
    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseUserDto> readById(@PathVariable Long id){
        return new ResponseEntity<ResponseUserDto>(userService.readUserById(id),HttpStatus.OK);
    }

    @PutMapping("update/user/{id}")
    public ResponseEntity<ResponseUserDto> updateUser(@PathVariable Long id, @RequestBody RequestUserDto requestUserDto){
        return new ResponseEntity<ResponseUserDto>(userService.updateUser(id, requestUserDto), HttpStatus.CREATED);
    }

    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        return new ResponseEntity<String>(userService.deleteUser(id),HttpStatus.NO_CONTENT);
    }

}
