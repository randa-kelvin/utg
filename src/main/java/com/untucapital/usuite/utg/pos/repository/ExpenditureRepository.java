package com.untucapital.usuite.utg.pos.repository;

import com.untucapital.usuite.utg.pos.model.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenditureRepository extends JpaRepository<Expenditure, Integer> {
    List<Expenditure> findByMonthAndYear(String month, int year);

    List<Expenditure> findByMonthAndCategory(String month, String category);

    List<Expenditure> findByYear(int year);

    @Query("SELECT e FROM Expenditure e WHERE e.category = :category AND e.createdAt BETWEEN :fromDate AND :toDate")
    List<Expenditure> findByCategoryAndCreatedAtBetween(@Param("category") String category,
                                                        @Param("fromDate") LocalDateTime fromDate,
                                                        @Param("toDate") LocalDateTime toDate);



    List<Expenditure> findByCategoryAndMonthAndYear(String category, String month, int year);


    List<Expenditure> findByCreatedAtBetween(LocalDateTime createdAt, LocalDateTime createdAt2);

    @Query("SELECT e.month, SUM(e.amount) FROM Expenditure e WHERE e.year = :year GROUP BY e.month")
    List<Object[]> sumExpenditureByMonth(@Param("year") int year);
}
