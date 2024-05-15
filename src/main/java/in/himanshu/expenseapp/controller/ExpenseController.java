package in.himanshu.expenseapp.controller;

import in.himanshu.expenseapp.dto.ExpenseDto;
import in.himanshu.expenseapp.entity.Expense;
import in.himanshu.expenseapp.service.ExpenseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;


@RestController
public class ExpenseController {


    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    //Returns whole List (Size and Page is Optional)
    @RequestMapping(method = RequestMethod.GET, value = "expenselist", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExpenseDto> getAllExpenses(Pageable page){
        return this.expenseService.getAllExpenses(page).toList();
    }

    //Doesn't return Whole List. Size and Page is compulsory
    @RequestMapping(method = RequestMethod.GET,value = "/expense", produces = MediaType.APPLICATION_JSON_VALUE)
    //Since we are returning List now. If we were not using toList(), Return type would be Page<Expense>
    public List<ExpenseDto> getAllExpenses(@RequestParam  int page,@RequestParam int size){
        return this.expenseService.getAllExpenses(page,size).toList();  //To remove the Pageable info from Response and return List of Expenses, we use toList()
        //why this?
    }

    @RequestMapping(method = RequestMethod.GET,value = "/expense/{expense_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExpenseDto getExpenseById(@PathVariable("expense_id") Long id){
        return expenseService.getExpenseById(id);
    }

    //By default, It returns 200.
    @RequestMapping(method = RequestMethod.GET,value = "/expense/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExpenseDto getExpenseByIdQuery(@RequestParam("id") Long id){
        return expenseService.getExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT) //204
    @RequestMapping(method = RequestMethod.DELETE,value = "expense/delete")
    public String deleteExpenseById(@RequestParam("id") Long id){
        return expenseService.deleteById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED) //201
    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ExpenseDto saveExpense(@Valid @RequestBody ExpenseDto expenseDto){
        return expenseService.saveExpense(expenseDto);
    }

    @ResponseStatus(value = HttpStatus.CREATED)//400
    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Expense updateExpenseDetails(@RequestParam("id") Long id, @Valid @RequestBody ExpenseDto expenseDto){
        return expenseService.updateExpenseDetails(id, expenseDto);
    }

    //Returning data for Page = 0 but getting Resource Not Found Exception for other Pages
    @RequestMapping(method = RequestMethod.GET, value = "/expenses/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExpenseDto> getExpenseByCategory(@RequestParam String category, Pageable pageable){
        return expenseService.readByCategory(category,pageable);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/expenses/name" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExpenseDto> findExpenseByName(@RequestParam String name, Pageable pageable){
        return expenseService.findByName(name,pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/expenses/date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExpenseDto> findByDates(@RequestParam(required = false,value = "startdate") Date startDate, @RequestParam(required = false, value = "enddate") Date endDate, Pageable pageable){
        return expenseService.getByDatesBetween(startDate,endDate,pageable);
    }
}
