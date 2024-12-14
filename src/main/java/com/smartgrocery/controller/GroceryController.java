package com.smartgrocery.controller;

import com.smartgrocery.dto.GroceryItemDTO;
import com.smartgrocery.model.GroceryItem;
import com.smartgrocery.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/grocery")
public class GroceryController {

    @Autowired
    private GroceryService groceryService;

    // Get all grocery items and convert them to DTOs
    @GetMapping
    public List<GroceryItemDTO> getItems() {
        List<GroceryItem> items = groceryService.getAllItems();
        return items.stream()
                .map(groceryService::convertToDTO)
                .collect(Collectors.toList());
    }

    // Add a new grocery item using DTO
    @PostMapping
    public ResponseEntity<GroceryItemDTO> addItem(@RequestBody GroceryItemDTO groceryItemDTO) {
        System.out.println("Received request to add item: " + groceryItemDTO);  // Log the request body
        GroceryItem groceryItem = groceryService.convertToEntity(groceryItemDTO);
        GroceryItem savedItem = groceryService.addItem(groceryItem);

        // Convert saved entity to DTO and return as response
        GroceryItemDTO savedItemDTO = groceryService.convertToDTO(savedItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItemDTO);
    }

    // Delete a grocery item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        groceryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
