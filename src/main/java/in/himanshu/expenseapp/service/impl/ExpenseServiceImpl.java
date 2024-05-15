package in.himanshu.expenseapp.service.impl;

import in.himanshu.expenseapp.dto.ExpenseDto;
import in.himanshu.expenseapp.entity.Expense;
import in.himanshu.expenseapp.exceptions.ResourceNotFoundException;
import in.himanshu.expenseapp.mapper.ExpenseMapper;
import in.himanshu.expenseapp.repository.ExpenseRepository;
import in.himanshu.expenseapp.service.ExpenseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

   // Logger logger = LoggerFactory.getLogger(ExpenseServiceImpl.class);
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Page<ExpenseDto> getAllExpenses(Pageable page) {
        Page<Expense> expenses = this.expenseRepository.findAll(page);
        return expenses.map(ExpenseMapper::expenseDto);
    }

    @Override
    public Page<ExpenseDto> getAllExpenses(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Expense> expenses = expenseRepository.findAll(pageRequest);
        return expenses.map(ExpenseMapper::expenseDto);
        //Usage of PageRequest.

    }

    @Override
    public ExpenseDto getExpenseById(Long id) {
       Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent()){
            return ExpenseMapper.expenseDto(expense.get());
        } else {
            throw new ResourceNotFoundException("expense id not found for id : " + id);
        }
    }

    @Override
    public String deleteById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isEmpty()){
            throw new ResourceNotFoundException("Expense not found for id: " + id);
        }
        expenseRepository.deleteById(id);
        return "Deleted";
    }

    @Override
    public ExpenseDto saveExpense(ExpenseDto expenseDto) {
        Expense expense = expenseRepository.save(ExpenseMapper.expense(expenseDto));
        return ExpenseMapper.expenseDto(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id, ExpenseDto expenseDto) {

        Expense existingExpense = ExpenseMapper.expense(getExpenseById(id));
        existingExpense.setName(expenseDto.getName() != null ? expenseDto.getName() : existingExpense.getName());
        existingExpense.setAmount(expenseDto.getAmount() != null?expenseDto.getAmount():existingExpense.getAmount());
        existingExpense.setDescription(expenseDto.getDescription() != null ? expenseDto.getDescription():existingExpense.getDescription());
        existingExpense.setCategory(expenseDto.getCategory() != null? expenseDto.getCategory():existingExpense.getCategory());
        //logic for updated data needed.
        return expenseRepository.save(existingExpense);
    }

    @Override
    public List<ExpenseDto> readByCategory(String category, Pageable pageable) {
        List<Expense> expenseList = expenseRepository.findByCategory(category, pageable).toList();
        if(expenseList.isEmpty()){
            throw new ResourceNotFoundException("No Resource found for Category: " + category);
        }
        return expenseList.stream().map(ExpenseMapper::expenseDto).collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDto> findByName(String name, Pageable pageable) {
        List<Expense> expenseList = expenseRepository.findByNameContaining(name, pageable).toList();
        if(expenseList.isEmpty()){
            throw new ResourceNotFoundException("No expense found for keyword: " + name);
        }
        return expenseList.stream().map(ExpenseMapper::expenseDto).collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDto> getByDatesBetween(Date startDate, Date endDate, Pageable pageable) {
        if(startDate == null) new Date (0);
        if(endDate == null) new Date(System.currentTimeMillis());
        List<Expense> expenseList = expenseRepository.findByDateBetween(startDate,endDate,pageable).toList();
        if(expenseList.isEmpty()){
            throw new ResourceNotFoundException("Not expense found between date: " + startDate + " and " + endDate);
        }

        return expenseList.stream().map(ExpenseMapper::expenseDto).collect(Collectors.toList());
    }
}
