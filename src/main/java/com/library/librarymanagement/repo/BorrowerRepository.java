package com.library.librarymanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.librarymanagement.entity.Borrower;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower,Long> {

}
