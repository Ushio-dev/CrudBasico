package com.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.entity.Item;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long>{

}
