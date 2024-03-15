package com.practice.blogappapi.controllers;

import com.practice.blogappapi.payloads.ApiResponse;
import com.practice.blogappapi.payloads.UserDto;
import com.practice.blogappapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    //Field dependency injection
//    @Autowired
//    private UserService userService;

   //Constructor dependency injection
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    //POST-create user
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    //PUT-update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid@RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
        UserDto updatedUser = this.userService.updateUser(userDto,uid);
        return ResponseEntity.ok(updatedUser);
    }

    //ADMIN can only handle this
    //DELETE-delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
         this.userService.deleteUser(uid);
         return new  ResponseEntity<ApiResponse>(new ApiResponse("User delete Successfully",true),HttpStatus.OK);
    }

    //GET-user get
    @GetMapping("/")
    public ResponseEntity<List<UserDto>>getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
