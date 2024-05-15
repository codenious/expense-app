package in.himanshu.expenseapp.service;

import in.himanshu.expenseapp.dto.ExpenseDto;
import in.himanshu.expenseapp.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ExpenseService {

    Page<ExpenseDto> getAllExpenses(Pageable page);

    Page<ExpenseDto> getAllExpenses(int page, int size);

    ExpenseDto getExpenseById(Long id);

    String deleteById(Long id);

    ExpenseDto saveExpense(ExpenseDto expenseDto);

    Expense updateExpenseDetails(Long id, ExpenseDto expenseDto);

    List<ExpenseDto> readByCategory(String category, Pageable pageable);

    List<ExpenseDto> findByName(String name, Pageable pageable);

    List<ExpenseDto> getByDatesBetween(Date startDate, Date endDate, Pageable pageable );
}
