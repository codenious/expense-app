package in.himanshu.expenseapp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private Long age;

}
