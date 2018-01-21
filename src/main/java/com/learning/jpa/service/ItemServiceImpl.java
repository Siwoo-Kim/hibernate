package com.learning.jpa.service;

import com.learning.jpa.domain.Item;
import com.learning.jpa.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item save(Item item){
        return this.itemRepository.save(item);
    }

    @Override
    public List<Item> getItems(){
        return this.itemRepository.findAll();
    }

    @Override
    public Item getItem(Long id){
        return  this.itemRepository.findById(id).get();
    }

}
