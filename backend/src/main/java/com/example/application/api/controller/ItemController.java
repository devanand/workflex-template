package com.example.application.api.controller;

import com.example.application.api.dto.CreateItemRequest;
import com.example.application.api.dto.ItemResponse;
import com.example.application.api.dto.UpdateItemRequest;
import com.example.application.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse create(@Valid @RequestBody CreateItemRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<ItemResponse> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public ItemResponse get(@PathVariable long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public ItemResponse update(@PathVariable long id, @Valid @RequestBody UpdateItemRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}