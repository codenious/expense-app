package in.himanshu.expenseapp.repository;

import in.himanshu.expenseapp.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

   // SELECT * FROM TBL_EXPENSE WHERE CATEGORY=?
   Page<Expense> findByCategory(String category, Pageable pageable);

   Page<Expense> findByNameContaining(String name, Pageable pageable);

   Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable pageable);
}
