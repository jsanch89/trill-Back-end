package com.webstore.trill.controller;

import com.webstore.trill.Entity.SellEntity;
import com.webstore.trill.Entity.UserEntity;
import com.webstore.trill.HttpModels.SellRequest;
import com.webstore.trill.Repository.ItemRepository;
import com.webstore.trill.Repository.SellRepository;
import com.webstore.trill.Repository.UserRepository;
import com.webstore.trill.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sell")
@AllArgsConstructor

public class SellController {

    private final UserRepository userRepo;
    private final SellRepository sellRepo;
    private final ItemRepository itemRepo;

    @PostMapping("/sell")
    public ResponseEntity<?> addSell(@RequestBody @Valid final SellRequest newSell){
        UserEntity user = userRepo.findById(newSell.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + newSell.getUserId()));

        SellEntity sell = sellRepo.save(SellEntity
                .builder()
                .price(newSell.getPrice())
                .user(user)
                .build());

        sellRepo.save(sell);

        return ResponseEntity.ok("Sell created");
    }

    @GetMapping("/sells")
    public ResponseEntity<?> getSells(@RequestParam final long id){
        return userRepo.findById(id)
                .map(userEntity ->
                        ResponseEntity.ok(sellRepo.findAllByUser(userEntity)))
                .orElseThrow(() -> new ResourceNotFoundException("User not found by id " + id));
    }

    @PostMapping("/add/item/{itemId}/sell/{sellId}")
    public ResponseEntity<?> addItem(
            @PathVariable("sellId") final long sellId,
            @PathVariable("itemId") final long itemId){

        sellRepo.findById(sellId)
                .map(sell -> {
                            sell.getOrderList()
                                    .add(itemRepo.findById(itemId)
                                            .orElseThrow(() -> new ResourceNotFoundException("Item not found by id " + itemId)));
                            return sellRepo.save(sell);
                        }
                )
                .orElseThrow(() -> new ResourceNotFoundException("Sell not found by id " + sellId));
        return ResponseEntity.ok("Item added");
    }
}
