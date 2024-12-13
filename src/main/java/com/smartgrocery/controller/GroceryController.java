package com.smartgrocery.controller;

import com.smartgrocery.model.GroceryItem;
import com.smartgrocery.dto.GroceryItemDTO;
import com.smartgrocery.service.GroceryService;  // Make sure you have this service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/grocery")
public class GroceryController {

    @Autowired
    private GroceryService groceryService;

    @Autowired
    private GroceryService groceryItemService;  // Service to convert between entity and DTO

    // Get all grocery items and convert them to DTOs
    @GetMapping
    public List<GroceryItemDTO> getItems() {
        List<GroceryItem> items = groceryService.getAllItems();
        // Convert each item to DTO
        return items.stream()
                .map(groceryItemService::convertToDTO)
                .collect(Collectors.toList());
    }

    // Add a new grocery item
    @PostMapping
    public GroceryItemDTO addItem(@RequestBody GroceryItemDTO itemDTO) {
        // Convert DTO to entity
        GroceryItem item = groceryItemService.convertToEntity(itemDTO);
        GroceryItem savedItem = groceryService.addItem(item);
        // Return saved item as DTO
        return groceryItemService.convertToDTO(savedItem);
    }

    // Delete a grocery item by ID
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        groceryService.deleteItem(id);
    }
}
