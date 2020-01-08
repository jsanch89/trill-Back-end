package com.webstore.trill.controller;

import com.webstore.trill.Entity.ItemEntity;
import com.webstore.trill.Repository.ItemRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/item")
public class ItemController {

    private ItemRepository itemRepo;

    @GetMapping("/get")
    public ResponseEntity<Page<ItemEntity>> getItems(Pageable pageable){
        return ResponseEntity.ok(itemRepo.findAll(pageable));
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getItemById(@RequestParam(value = "id") final Long id){
        Optional<ItemEntity> item = itemRepo.findById(id);
        if(item.isPresent()){
            return ResponseEntity.ok(item.get());
        }
        else{
            return ResponseEntity.unprocessableEntity().body("Item didn't find");
        }
    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getItemByNameIgnoreCase(@RequestParam(value = "name") final String name){
        Optional<ItemEntity> item = itemRepo.findByNameIgnoreCase(name);
        if(item.isPresent()){
            return ResponseEntity.ok(item.get());
        }
        else{
            return ResponseEntity.unprocessableEntity().body("Item didn't find");
        }
    }

    @GetMapping("/getByPriceLessThan")
    public ResponseEntity<?> getItemByPriceLessThan(@RequestParam("price") final double price){
        List<ItemEntity> items = itemRepo.findByPriceLessThanEqual(price);
        return ResponseEntity.ok(items);
    }
}
