package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.TextBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextBookRepo extends JpaRepository<TextBook,Integer> {
}
