package com.todocode.ferreteria.repository;

import com.todocode.ferreteria.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productoRepository extends JpaRepository<Producto,Long> {
}
