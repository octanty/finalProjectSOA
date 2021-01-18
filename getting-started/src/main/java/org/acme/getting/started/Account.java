package org.acme.getting.started;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.validator.constraints.Length;

import javax.inject.Inject;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
//@Cacheable
public class Account extends PanacheEntity {

    public String name;

    public Date lastSeen;

    @Valid
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @IndexedEmbedded
    public List<Item> incomes;

    @Valid
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @IndexedEmbedded
    public List<Item> expenses;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    public Saving saving;

    @Length(min = 0, max = 20_000)
    public String note;

}
