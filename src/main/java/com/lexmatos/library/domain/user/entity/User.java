package com.lexmatos.library.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

import com.lexmatos.library.domain.user.enums.Action;
import com.lexmatos.library.domain.user.enums.Profile;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Profile profile;
    
    private Date date; // data de criação.
    	
    @Enumerated(EnumType.STRING) 
    private Action action;
}