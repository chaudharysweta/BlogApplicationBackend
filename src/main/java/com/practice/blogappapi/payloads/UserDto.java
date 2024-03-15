package com.practice.blogappapi.payloads;


import com.practice.blogappapi.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter

//here all the fields are similar to user entity
//here fields are created which we want to return to user
//We directly don't interact with Entity for the purpose of creation,updation and deletion
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4, message = "Username must be min of 4 character")
    private String name;
    @Email(message = "Email address is not valid")
    private String email;
    @NotEmpty
    @Size(min = 3,max = 10,message = "Password must be min 3 chars and max of 10 chars")
    //@Pattern(regexp = )
    private String password;
    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
