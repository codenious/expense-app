package in.himanshu.expenseapp.entity;


import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObject {

    private Integer statusCode;

    private String message;

    private Date  timestamp;
}
