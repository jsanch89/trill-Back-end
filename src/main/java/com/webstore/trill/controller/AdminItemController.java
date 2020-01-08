package com.webstore.trill.controller;

import com.webstore.trill.Entity.ItemEntity;
import com.webstore.trill.Repository.ItemRepository;
import com.webstore.trill.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;


@RestController
@AllArgsConstructor
@RequestMapping("/admin/item")
public class AdminItemController {

    private ItemRepository itemRepo;

    @PostMapping("/create")
    public ResponseEntity<?> createItem(@RequestBody final ItemEntity newItem){
        itemRepo.save(newItem);
        return ResponseEntity.ok("Item created");
    }

    @PutMapping("/update/{id}")
    public ItemEntity updateItem(@PathVariable("id") final long id,
                                 @Valid @RequestBody final ItemEntity newItemEntity){

        return itemRepo.findById(newItemEntity.getId())
               .map(itemEntity ->
                       itemRepo.save(newItemEntity)
               )
               .orElseThrow(() -> new ResourceNotFoundException("Item not found by id: " + newItemEntity.getId()));
    }

    @PatchMapping("/updateFields/{id}")
    public ResponseEntity<String> updateField(
            @PathVariable("id") final long id,
            @RequestParam final Map<String,Object> updates){

        ItemEntity oldItem = itemRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Item not found by id: " + id));

        if(updates.get("name") != null){
            oldItem.setName((String) updates.get("name"));
        }
        if(updates.get("sizes") != null){
            oldItem.setSizes((Set<String>) updates.get("sizes"));
        }
        if(updates.get("main_image") != null){
            oldItem.setMain_image((String) updates.get("main_image"));
        }
        itemRepo.save(oldItem);
        return ResponseEntity.ok("resource updated");
    }
}
