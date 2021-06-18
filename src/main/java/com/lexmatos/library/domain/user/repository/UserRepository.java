package com.lexmatos.library.domain.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lexmatos.library.domain.user.entity.User;

public interface UserRepository extends JpaRepository <User, Long>{
    
}

