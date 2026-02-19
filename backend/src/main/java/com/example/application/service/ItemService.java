package com.example.application.service;

import com.example.application.api.dto.CreateItemRequest;
import com.example.application.api.dto.ItemResponse;
import com.example.application.api.dto.UpdateItemRequest;
import com.example.application.domain.Item;
import com.example.application.exception.NotFoundException;
import com.example.application.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository repo;

    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public ItemResponse create(CreateItemRequest req) {
        Item saved = repo.save(new Item(req.name()));
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> list() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ItemResponse get(long id) {
        Item item = repo.findById(id).orElseThrow(() -> new NotFoundException("Item %d not found".formatted(id)));
        return toResponse(item);
    }

    @Transactional
    public ItemResponse update(long id, UpdateItemRequest req) {
        Item item = repo.findById(id).orElseThrow(() -> new NotFoundException("Item %d not found".formatted(id)));
        item.setName(req.name());
        return toResponse(item);
    }

    @Transactional
    public void delete(long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Item %d not found".formatted(id));
        }
        repo.deleteById(id);
    }

    private ItemResponse toResponse(Item item) {
        return new ItemResponse(item.getId(), item.getName(), item.getCreatedAt());
    }
}