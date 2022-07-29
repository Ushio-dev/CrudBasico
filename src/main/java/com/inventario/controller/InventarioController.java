package com.inventario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.entity.Item;
import com.inventario.service.IItemService;

@RestController
@RequestMapping("api/inventario")
public class InventarioController {
	
	@Autowired
	private final IItemService itemService;
	
	public InventarioController(IItemService itemService) {
		// TODO Auto-generated constructor stub
		this.itemService = itemService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> prueba() {
		List<Item> items =  itemService.findAll();
		
		if (items.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(items); 
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Item item) {
		return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(item));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Item item) {
		Optional<Item> oItem = itemService.findById(id);
		
		if (!oItem.isPresent()) {
			ResponseEntity.notFound().build();
		}
		
		oItem.get().setNombre(item.getNombre());
		oItem.get().setStock(item.getStock());
		oItem.get().setDesc(item.getDesc());
		
		return ResponseEntity.ok().body(itemService.save(oItem.get()));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Optional<Item> oItem = itemService.findById(id);
		
		if (!oItem.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oItem);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		Optional<Item> oItem = itemService.findById(id);
		
		if (!oItem.isPresent() ) {
			return ResponseEntity.notFound().build();
		}
		
		itemService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
