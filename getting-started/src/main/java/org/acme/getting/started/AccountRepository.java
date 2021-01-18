package org.acme.getting.started;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account> {
    public Account findByName(String name){
        return find("name", name).firstResult();
    }
}
