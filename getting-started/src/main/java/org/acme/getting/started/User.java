package org.acme.getting.started;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User extends PanacheEntity{

    @NotNull
    @Length(min = 3, max = 20)
    public String username;

    @NotNull
    @Length(min = 6, max = 40)
    public String password;

}
