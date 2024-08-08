package com.expensebee.api.expense;

import com.expensebee.api.expense.dto.ExpenseReqDTO;
import com.expensebee.api.expense.dto.ExpenseResDTO;
import com.expensebee.api.expense.interfaces.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("expense")
@Tag(name = "Expense", description = "Manages expense-related operations in the system.")
public class ExpenseController {
  private final ExpenseService expenseService;

  @Operation(summary = "Create new expense!")
  @ApiResponse(responseCode = "201", description = "Expense created!")
  @PostMapping("categories")
  public ResponseEntity<ExpenseResDTO> create(ExpenseReqDTO expenseResDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.create(expenseResDTO));
  }

  @Operation(summary = "Returns all user expense!")
  @ApiResponse(responseCode = "200", description = "List of expense!")
  @GetMapping("categories/all")
  public ResponseEntity<List<ExpenseResDTO>> allExpenses() {
    return ResponseEntity.status(HttpStatus.OK).body(expenseService.findAll());
  }

  @Operation(summary = "Return expense by id!")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Expense found!"),
    @ApiResponse(responseCode = "404", description = "Expense not found!")
  })
  @GetMapping("categories/{id}")
  public ResponseEntity<ExpenseResDTO> expensesById(@PathVariable UUID id) {
    return ResponseEntity.status(HttpStatus.OK).body(expenseService.findById(id));
  }

  @Operation(summary = "Update expense!")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Expense updated successfully!"),
    @ApiResponse(responseCode = "404", description = "Expense not found!")
  })
  @PutMapping("categories/{id}")
  public ResponseEntity<ExpenseResDTO> update(@PathVariable UUID id, @RequestBody ExpenseReqDTO expenseReqDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(expenseService.update(id, expenseReqDTO));
  }

  @Operation(summary = "Delete expense!")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Expense deleted successfully!"),
    @ApiResponse(responseCode = "404", description = "Expense not found!")
  })
  @DeleteMapping("categories/{id}")
  public ResponseEntity<ExpenseResDTO> deleteById(@PathVariable UUID id) {
    expenseService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
