package com.inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.entity.Item;
import com.inventario.repository.IItemRepository;

@Service
public class ItemServiceImpl implements IItemService{

	@Autowired
	private final IItemRepository itemRepository;
	
	public ItemServiceImpl(IItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		itemRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Item> findById(Long id) {
		// TODO Auto-generated method stub
		return itemRepository.findById(id);
	}

	@Override
	@Transactional
	public Item save(Item item) {
		// TODO Auto-generated method stub
		return itemRepository.save(item);
	}
}
