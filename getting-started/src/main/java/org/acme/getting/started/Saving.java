package org.acme.getting.started;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Saving extends PanacheEntity{
    @NotNull
    public BigDecimal amount;

  /*  @NotNull
    public Currency currency; */

    @NotNull
    public BigDecimal interest;

    @NotNull
    public Boolean deposit;

    @NotNull
    public Boolean capitalization;

}

