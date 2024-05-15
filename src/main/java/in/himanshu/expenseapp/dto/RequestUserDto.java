package in.himanshu.expenseapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDto {

    private Long id;

    @NotBlank(message = "Please enter first name")
    private String firstName;

    @NotBlank(message = "Please enter the last name")
    private String lastName;


    @NotBlank(message = "Please enter the username")
    private String userName;

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email must not be Blank")
    private String email;

    @NotBlank(message = "password must not be blank")
    @Size(min = 5, message = "Password should be 5 characters at least")
    private String password;

    private Long age;

    @JsonIgnore
    private Date createdAt;

    @JsonIgnore
    private Date updatedAt;

}
