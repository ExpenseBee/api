package com.expensebee.api.report;

import com.expensebee.api.expense.dto.ExpenseResDTO;
import com.expensebee.api.report.dto.ReportExpenseDTO;
import com.expensebee.api.report.dto.ReportResDTO;
import com.expensebee.api.report.interfaces.ReportService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("report")
@Tag(name = "Report", description = "Endpoints for generating and consulting financial reports. Includes monthly reports, by category, by budget, and spending trend analysis.")
public class ReportController {
  private final ReportService reportService;


  @ApiResponse(responseCode = "200", description = "Returns a range of all expenses for a given time")
  @GetMapping("range/{start}/{end}")
  public ResponseEntity<List<ExpenseResDTO>> rangeDate(
    @PathVariable("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date start,
    @PathVariable("end")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {
    return ResponseEntity.status(HttpStatus.OK).body(reportService.rangeDate(start, end));
  }

  @ApiResponse(responseCode = "200", description = "Returns the user's monthly spending report, including details about expenses and income accumulated during the month.")
  @GetMapping("monthly")
  public ResponseEntity<ReportResDTO> monthlyReport() {
    return ResponseEntity.status(HttpStatus.OK).body(reportService.monthlyReport());
  }
}
