package build.project.Banking.Mapper;

import build.project.Banking.dto.Accountdto;
import build.project.Banking.entity.account;

public class AccountMapper {


    public static account mapToAccount(Accountdto accountdto){

    account account = new account(
        accountdto.getId(),
        accountdto.getAccountHolderName(),
        accountdto.getBalance()
    );
    return account;


}

public static Accountdto mapToAccountDto(account account)
{
    Accountdto accountdto = new Accountdto(
        account.getId(),
        account.getAccountHolderName(),
        account.getBalance()
    );

    return accountdto;
}
}
