package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.dto.MonthChartDto;
import com.example.HMS_MANAGEMENT.repository.DayChartRepo;
import com.example.HMS_MANAGEMENT.service.DayChartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ChartController {
    @Autowired
    private DayChartService dayChartService;
    @Autowired
    private DayChartRepo dayChartRepo;

    // 매출관리 첫 페이지
    @GetMapping("/sales")
    public String cusList(Model model) {
        return "sales/salesPage";
    }

    // 일별 상세보기
    @GetMapping("/sales/dayPage")
    public String dayPage(@RequestParam(value = "date", required = false) String dateString, Model model) {
        LocalDate date;
        try {
            date = (dateString != null) ? LocalDate.parse(dateString) : LocalDate.now();
        } catch (DateTimeParseException e) {
            date = LocalDate.now();
        }
        Map<String, Integer> incomeResult = dayChartService.ServiceIncome(date);

        int serviceCost = incomeResult.getOrDefault("serviceCost", 0);
        int salaryCost = incomeResult.getOrDefault("salaryCost", 0);
        int productSellCost = incomeResult.getOrDefault("productSellCost", 0);
        int productBuyCost = incomeResult.getOrDefault("productBuyCost", 0);
        int totalIncomeCost = incomeResult.getOrDefault("totalIncomeCost", 0);
        int totalExpenseCost = incomeResult.getOrDefault("totalExpenseCost", 0);

        model.addAttribute("serviceCost", serviceCost);
        model.addAttribute("salaryCost", salaryCost);
        model.addAttribute("productSellCost", productSellCost);
        model.addAttribute("productBuyCost", productBuyCost);
        model.addAttribute("totalIncomeCost", totalIncomeCost);
        model.addAttribute("totalExpenseCost", totalExpenseCost);
        model.addAttribute("date", date);
        model.addAttribute("prevDate", date.minusDays(1).toString());
        model.addAttribute("nextDate", date.plusDays(1).toString());

        return "sales/dayPage";
    }

    // chart 부분
    @GetMapping("/sales/getData")
    public ResponseEntity<String> getData(@RequestParam("date") String nowDate) throws JsonProcessingException {
        int[][][] money = dayChartService.getChartDayData(nowDate);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(money);
        System.out.println(json);
        return ResponseEntity.ok(json);
    }


    // 주간 상세보기
    @GetMapping("/sales/weekPage")
    public String monthlySales(@RequestParam(value = "yearMonth", required = false) String yearMonthStr, Model model) {
        YearMonth yearMonth;
        try {
            yearMonth = (yearMonthStr != null) ? YearMonth.parse(yearMonthStr) : YearMonth.now();
        } catch (DateTimeParseException e) {
            yearMonth = YearMonth.now();
        }

        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        List<Map<String, Object>> weeklyIncomeDetails = new ArrayList<>();

        // 해당 월의 첫째 주부터 마지막 주까지 반복 처리
        LocalDate currentStartOfWeek = startOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        while (!currentStartOfWeek.isAfter(endOfMonth)) {
            LocalDate currentEndOfWeek = currentStartOfWeek.plusDays(6).isAfter(endOfMonth) ? endOfMonth : currentStartOfWeek.plusDays(6);

            Map<LocalDate, Map<String, Integer>> weeklyIncomeAndExpenseDetails = dayChartService.dailyServiceIncomeAndExpenseDetails(currentStartOfWeek, currentEndOfWeek);

            Map<String, Object> weekDetail = new HashMap<>();
            weekDetail.put("weekRange", String.format("%d.%02d.%02d~%d.%02d.%02d",
                    currentStartOfWeek.getYear(), currentStartOfWeek.getMonthValue(), currentStartOfWeek.getDayOfMonth(),
                    currentEndOfWeek.getYear(), currentEndOfWeek.getMonthValue(), currentEndOfWeek.getDayOfMonth()));
            weekDetail.put("dailyDetails", weeklyIncomeAndExpenseDetails);

            weeklyIncomeDetails.add(weekDetail);

            // 다음 주의 시작 날짜로 이동
            currentStartOfWeek = currentStartOfWeek.plusWeeks(1);
        }



        model.addAttribute("weeklyIncomeDetails", weeklyIncomeDetails);
        YearMonth nextYearMonth = yearMonth.plusMonths(1);
        model.addAttribute("nextYearMonth", nextYearMonth.toString()); // 다음 달
        YearMonth prevYearMonth = yearMonth.minusMonths(1);
        model.addAttribute("prevYearMonth", prevYearMonth.toString()); // 이전 달

        return "sales/weekPage";
    }


    // 월간 상세보기
    @GetMapping("/sales/monPage")
    public String monPage(Model model) {
        int year =LocalDate.now().getYear();
        List<MonthChartDto> monthlyDetails = dayChartService.getMonthlyIncomeAndExpenseDetails(year);
        model.addAttribute("monthlyDetails",monthlyDetails);

        return "sales/monPage";
    }



}
