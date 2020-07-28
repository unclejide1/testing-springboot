package com.jide.testing.service;

import com.jide.testing.model.Item;
import com.jide.testing.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemBusinessService {

    @Autowired
    private ItemRepository repository;

    public Item retrieveHardCodedItem(){
        return new Item(1, "ball", 10, 100);
    }

    public List<Item> retrieveAllItems(){
        List<Item> items = repository.findAll();
        for(Item item: items){
            item.setValue(item.getPrice() * item.getQuantity());
        }
        return items;
    }
}
