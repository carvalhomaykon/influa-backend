package com.influa.influa.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contractors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contractor extends User{

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
