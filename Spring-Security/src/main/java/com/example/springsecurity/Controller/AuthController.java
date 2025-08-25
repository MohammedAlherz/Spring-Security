package com.example.springsecurity.Controller;

import com.example.springsecurity.Api.ApiResponse;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-system")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user){
        authService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("User register Successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.status(200).body(authService.getAllUser());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody User user){
        authService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("User update Successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        authService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted Successfully"));
    }

}
