package org.acme.getting.started;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional
public class AccountService {
    @Inject
    private AccountRepository repository;

    @Inject

    EntityManager em;

    @Transactional(SUPPORTS)
    public User findUserById(Long id) {
        return User.findById(id);
    }

    public User create(@Valid User user){
        Account existing = repository.findByName(user.username);
        if(existing != null){
            assertEquals(existing, "account already exists: " + user.username);
        }
        User users = new User();
        users.username = user.username;
        users.password = user.password;
        Saving savings = createSaving();
        createAccount(user, savings);
        users.persist();
        return user;
    }


    public Saving seeSaving(@Valid String name){
        Account account = repository.findByName(name);
        if(account == null){
            assertEquals(account, "account is not exist: " + name);
        }

        Saving found = Saving.findById(account.saving.id);
        return found;
    }


    public Account createAccount(@Valid User user, Saving saving){
        Account account = new Account();
        account.name = user.username;
        account.lastSeen = new Date();
        account.saving = saving;
        account.persist();
        return account;
    }


    public Saving createSaving(){
        Saving saving = new Saving();
        saving.amount = new BigDecimal(0);
        saving.interest = new BigDecimal(0);
        saving.deposit = false;
        saving.capitalization = false;
        saving.persist();

        return saving;
    }

    public Account saveChanges(String name, Account update) {
        Account account = repository.findByName(name);
        if(account == null){
            assertEquals(account, "can't find account with name " + name);
        }

        Item income = new Item();
        income.amount = update.saving.amount;
        income.icon = "string";
        income.title ="";
        income.persist();
        account.incomes.add(income);
        income.account = account;

        Item expenses = new Item();
        expenses.amount = update.saving.amount;
        expenses.icon = "string";
        expenses.title ="";
        expenses.persist();
        account.expenses.add(expenses);
        expenses.account = account;

        account.saving.amount = update.saving.amount;
        account.saving.capitalization = update.saving.capitalization;
        account.saving.deposit = update.saving.deposit;
        account.saving.interest = update.saving.interest;
        account.note = update.note;
        account.lastSeen = new Date();
        return account;
    }

    public Account updateSaving(String name, Account update) {
        Account account = repository.findByName(name);
        if(account == null){
            assertEquals(account, "can't find account with name " + name);
        }

        account.saving.amount = update.saving.amount;
        account.saving.capitalization = update.saving.capitalization;
        account.saving.deposit = update.saving.deposit;
        account.saving.interest = update.saving.interest;
        account.note = update.note;
        account.lastSeen = new Date();
        return account;
    }
}
