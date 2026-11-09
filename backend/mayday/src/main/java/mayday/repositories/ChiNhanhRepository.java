package mayday.repositories;

import mayday.entities.cosovatchat.ChiNhanh;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA cung cap CRUD; cac ham danh sach sap xep theo ID tang dan.
 */
@Repository
public interface ChiNhanhRepository extends JpaRepository<ChiNhanh, Long> {
    List<ChiNhanh> findAllByOrderByIdAsc();
}
