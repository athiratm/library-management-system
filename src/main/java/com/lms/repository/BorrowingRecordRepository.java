package com.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.model.Book;
import com.lms.model.BorrowingRecord;
import com.lms.model.Patron;



@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer>{

	BorrowingRecord findByBookAndPatron(Optional<Book> book, Optional<Patron> patron);

	List<BorrowingRecord> findByBook(Optional<Book> book);


}
