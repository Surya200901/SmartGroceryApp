package com.smartgrocery.service;

import com.smartgrocery.dto.GroceryItemDTO;
import com.smartgrocery.model.GroceryItem;
import com.smartgrocery.repository.GroceryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroceryService {

    @Autowired
    private GroceryRepository groceryRepository;

    public GroceryItem addItem(GroceryItem item) {
        return groceryRepository.save(item);
    }

    public List<GroceryItem> getAllItems() {
        return groceryRepository.findAll();
    }

    public void deleteItem(Long id) {
        groceryRepository.deleteById(id);
    }

    public GroceryItemDTO convertToDTO(GroceryItem groceryItem) {
        GroceryItemDTO dto = new GroceryItemDTO();
        dto.setName(groceryItem.getName());
        dto.setCategory(groceryItem.getCategory());
        dto.setPrice(groceryItem.getPrice());
        dto.setQuantity(groceryItem.getQuantity());
        return dto;
    }

    public GroceryItem convertToEntity(GroceryItemDTO groceryItemDTO) {
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setName(groceryItemDTO.getName());
        groceryItem.setCategory(groceryItemDTO.getCategory());
        groceryItem.setPrice(groceryItemDTO.getPrice());
        groceryItem.setQuantity(groceryItemDTO.getQuantity());
        groceryItem.setInStock(true); // Default value
        return groceryItem;
    }

    // Method to get all grocery items as DTOs
    public List<GroceryItemDTO> getAllItemsAsDTO() {
        List<GroceryItem> items = groceryRepository.findAll();
        return items.stream()
                    .map(this::convertToDTO)  // Convert each GroceryItem to GroceryItemDTO
                    .collect(Collectors.toList());
    }
}
