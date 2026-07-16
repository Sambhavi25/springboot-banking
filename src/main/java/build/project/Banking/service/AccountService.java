package build.project.Banking.service;

import java.util.List;

import build.project.Banking.dto.Accountdto;

public interface AccountService {

    Accountdto createAccount(Accountdto accountdo);
    Accountdto getAccountById(Long id);
    Accountdto deposit(Long id , double amount);
    Accountdto withdraw(Long id, double amount);

    List<Accountdto> getAllAccounts();

    void deleteAccount(Long id);
}
