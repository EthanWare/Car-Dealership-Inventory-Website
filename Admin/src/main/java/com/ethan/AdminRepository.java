package com.ethan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Car, Integer> {

    @Query(value="Select * From Car c where c.year Like %:filter%", nativeQuery = true)
    public List<Car> findByYear(@Param("filter") String filter);

    @Query(value="Select * From Car where make Like %:filter%", nativeQuery = true)
    public List<Car> findByMake(@Param("filter") String filter);

    @Query(value="Select * From Car where model Like %:filter%", nativeQuery = true)
    public List<Car> findByModel(@Param("filter") String filter);

    @Query(value="Select * From Car where type Like %:filter%", nativeQuery = true)
    public List<Car> findByType(@Param("filter") String filter);

    @Query(value="Select * From Car where color Like %:filter%", nativeQuery = true)
    public List<Car> findByColor(@Param("filter") String filter);
}