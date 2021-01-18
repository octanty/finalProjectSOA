package org.acme.getting.started;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Item extends PanacheEntity{

    @NotNull
    @Length(min = 1, max = 20)
    public String title;

    @NotNull
    public BigDecimal amount;

  /*  @NotNull
    public Currency currency;

    @NotNull
    public TimePeriod period; */

    @NotNull
    public String icon;

    @ManyToOne
    @JsonIgnore
    public Account account;

}

