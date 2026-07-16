package build.project.Banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import build.project.Banking.Mapper.AccountMapper;
import build.project.Banking.dto.Accountdto;
import build.project.Banking.entity.account;
import build.project.Banking.repository.accountRepository;
import build.project.Banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
    

    private accountRepository accountRepository;




    public AccountServiceImpl(build.project.Banking.repository.accountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }




    @Override
    public Accountdto createAccount(Accountdto accountdo) {
        // TODO Auto-generated method stub

        account account = AccountMapper.mapToAccount(accountdo);
        account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }




    @Override
    public Accountdto getAccountById(Long id) {
        // TODO Auto-generated method stub
       account account = accountRepository
       .findById(id)
       .orElseThrow(() -> new RuntimeException("Account does not exists"));
       return AccountMapper.mapToAccountDto(account);
    }




    @Override
    public Accountdto deposit(Long id, double amount) {
        // TODO Auto-generated method stub

        

       account account = accountRepository.
       findById(id)
       .orElseThrow(() -> new RuntimeException("Id does not exists"));

       double total = account.getBalance() + amount;
       account.setBalance(total);
       account savedAccount = accountRepository.save(account);
       return AccountMapper.mapToAccountDto(savedAccount);
    }




    @Override
    public Accountdto withdraw(Long id, double amount) {
      account account = accountRepository.
       findById(id)
       .orElseThrow(() -> new RuntimeException("Id does not exists"));
      

       if(account.getBalance() < amount)
       {
        throw new RuntimeException("Insufficent Balance");
       }
    
         double total_deposit = account.getBalance() - amount;
        account.setBalance(total_deposit);
        account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    
       
    }




    @Override
    public List<Accountdto> getAllAccounts() {

       List<account> accounts= accountRepository.findAll();
       return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
        .collect(Collectors.toList());
        // TODO Auto-generated method stub
    
    }




    @Override
    public void deleteAccount(Long id){
      

        account account = accountRepository
        .findById(id)
        .orElseThrow(()-> new RuntimeException("Account does not exists"));

       accountRepository.deleteById(id);
       
    }
}
