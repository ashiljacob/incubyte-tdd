package com.incubyte.crud.model;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Word {


    @Column(name = "ID", nullable = false)
    @Id
    private Long id;

    private String word;

}
