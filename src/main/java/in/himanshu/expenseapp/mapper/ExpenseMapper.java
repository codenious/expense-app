package in.himanshu.expenseapp.mapper;

import in.himanshu.expenseapp.dto.ExpenseDto;
import in.himanshu.expenseapp.entity.Expense;

public class ExpenseMapper {

    public static Expense expense(ExpenseDto expenseDto){
        return new Expense(
                expenseDto.getId(),
                expenseDto.getName(),
                expenseDto.getDescription(),
                expenseDto.getAmount(),
                expenseDto.getCategory(),
                expenseDto.getDate(),
                expenseDto.getCreatedAt(),
                expenseDto.getUpdatedBy()
        );

    }

    public static ExpenseDto expenseDto(Expense expense){

        return new ExpenseDto(
                expense.getId(),
                expense.getName(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getDate(),
                expense.getCreatedAt(),
                expense.getUpdatedBy()
        );
    }
}
