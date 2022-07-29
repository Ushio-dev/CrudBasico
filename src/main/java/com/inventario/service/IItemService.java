package com.inventario.service;

import java.util.List;
import java.util.Optional;

import com.inventario.entity.Item;

public interface IItemService {
	public List<Item> findAll();
	
	public void deleteById(Long id);
	
	public Optional<Item> findById(Long id);
	
	public Item save(Item item);
}
