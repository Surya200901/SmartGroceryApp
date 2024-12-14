package com.smartgrocery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartgrocery.dto.GroceryItemDTO;
import com.smartgrocery.model.GroceryItem;
import com.smartgrocery.repository.GroceryRepository;

@Service
public class GroceryService {

    @Autowired
    private GroceryRepository groceryRepository;

    // Save a grocery item to the database
    @Transactional
    public GroceryItem addItem(GroceryItem item) {
        try {
            System.out.println("Saving item: " + item);  // Debug log
            return groceryRepository.save(item);
        } catch (Exception e) {
            System.out.println("Error while saving item: " + e.getMessage());
            throw e;  // Propagate the error
        }
    }

    // Fetch all grocery items from the database
    public List<GroceryItem> getAllItems() {
        return groceryRepository.findAll();
    }

    // Delete a grocery item by its ID
    public void deleteItem(Long id) {
        groceryRepository.deleteById(id);
    }

    // Convert GroceryItem entity to DTO
    public GroceryItemDTO convertToDTO(GroceryItem groceryItem) {
        GroceryItemDTO dto = new GroceryItemDTO();
        dto.setName(groceryItem.getName());
        dto.setCategory(groceryItem.getCategory());
        dto.setPrice(groceryItem.getPrice());
        dto.setQuantity(groceryItem.getQuantity());
        return dto;
    }

    // Convert GroceryItemDTO to entity
    public GroceryItem convertToEntity(GroceryItemDTO groceryItemDTO) {
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setName(groceryItemDTO.getName());
        groceryItem.setCategory(groceryItemDTO.getCategory());
        groceryItem.setPrice(groceryItemDTO.getPrice());
        groceryItem.setQuantity(groceryItemDTO.getQuantity());
        groceryItem.setInStock(true); // Default value
        return groceryItem;
    }
}
