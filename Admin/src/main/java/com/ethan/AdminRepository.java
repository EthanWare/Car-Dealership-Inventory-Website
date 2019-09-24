package com.ethan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Car, Integer> {

    @Query(value="Select * From Car where :column = :filter", nativeQuery = true)
    public List<Car> findByColumnFilter(@Param("column") String column, @Param("filter") String filter);
}