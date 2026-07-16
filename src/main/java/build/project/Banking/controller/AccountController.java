package build.project.Banking.controller;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import build.project.Banking.dto.Accountdto;
import build.project.Banking.service.AccountService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/accounts") // base API
public class AccountController {
 

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add account REST API
     
    @PostMapping
    public ResponseEntity<Accountdto> addAccount(@RequestBody Accountdto accountdto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountdto), HttpStatus.CREATED);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Accountdto> getAccountByID(@PathVariable Long Id)
    {

        Accountdto accountdto = accountService.getAccountById(Id);

        return ResponseEntity.ok(accountdto);
      
    }
    @PutMapping("/{id}/deposit")
    public ResponseEntity<Accountdto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request)
    {

        Double amount = request.get("Amount");
       Accountdto accountdto = accountService.deposit(id, amount);
       return ResponseEntity.ok(accountdto);
    }
    
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<Accountdto> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request) 
    {
        double amount = request.get("Amount");
        Accountdto accountdto= accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountdto);

    }
   
    @GetMapping
    public ResponseEntity<List<Accountdto>> getAllAccounts()
    {
        List<Accountdto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id)
    {
      accountService.deleteAccount(id);
      return ResponseEntity.ok("Account deleted successfully");
    }
}
