package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    /**
     * @param posted_by
     * @return List of messages associate with posted_by
     */
    @Query("FROM Message WHERE posted_by = :postedVar")
    List<Message> getAllMessagesByPostedBy(@Param("postedVar") int posted_by);
    
}
