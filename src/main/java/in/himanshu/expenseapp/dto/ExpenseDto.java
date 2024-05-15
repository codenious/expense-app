package in.himanshu.expenseapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {

    private Long id;

    @Column(name = "expense_name")
    @NotNull(message = "Expense Name must not be null")
    @Size(min = 3, message = "Expense Name must be at least 3 characters")
    @NotBlank(message = "Expense Name must not be blank")
    private String name;

    @Size(min = 5, message = "Description should be at least 5 characters")
    private String description;

   @NotNull (message = "Amount must not be null")
    private BigDecimal amount;

   @NotBlank(message = "Category should not be null")
    private String category;

    private Date date;

    @JsonIgnore
    private Date createdAt;

    @JsonIgnore
    private Date updatedBy;
}
