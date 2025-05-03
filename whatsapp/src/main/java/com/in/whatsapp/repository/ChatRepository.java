package com.in.whatsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.whatsapp.model.Message;

@Repository
public interface ChatRepository extends JpaRepository<Message, Long>{

}
