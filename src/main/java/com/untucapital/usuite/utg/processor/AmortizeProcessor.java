package com.untucapital.usuite.utg.processor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
@Slf4j
@AllArgsConstructor
public class AmortizeProcessor {

    public double installmentCalc(String nextMonthDate, String periodRange, double principal, double interest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate disbursementDate = LocalDate.parse(nextMonthDate, formatter);
        LocalDate currentDate = LocalDate.now();

        double balanceToBeCleared = 0.0;
        if (LocalDate.parse(periodRange, formatter).isAfter(disbursementDate) ||
                LocalDate.parse(periodRange, formatter).isEqual(disbursementDate)) {
            long numOfDaysDifference = ChronoUnit.DAYS.between(disbursementDate, currentDate);

            double interestForNextInstalment = interest;
            double numOfDaysFromSystem = 30.417;
            double interestPerDay = interestForNextInstalment / numOfDaysFromSystem;
            double accruedInterest = numOfDaysDifference * interestPerDay;
            double principalBalance = principal;
            double totalDueAsOfToday = accruedInterest + principalBalance;
            double settlementAccountBalance = 0.0; // Assuming no value is available
            balanceToBeCleared = totalDueAsOfToday;

            log.info("Abel");
            log.info("A\tInterest for Next Instalment\t" + interestForNextInstalment);
            log.info("B\t# of Days (from System)\t" + numOfDaysFromSystem);
            log.info("C\tInterest per Day\t" + interestPerDay);
            log.info("D\t# of Days to TODAY\t" + numOfDaysDifference);
            log.info("E\tAccrued Interest\t" + accruedInterest);
            log.info("F\tPrincipal Balance\t" + principalBalance);
            log.info("G\tTotal Due as at today\t" + totalDueAsOfToday);
            log.info("H\tSettlement Account Balance\t" + settlementAccountBalance);
            log.info("I\tBALANCE TO BE CLEARED\t" + balanceToBeCleared);

            return balanceToBeCleared;
        } else {
            return balanceToBeCleared;
        }

    }
}
