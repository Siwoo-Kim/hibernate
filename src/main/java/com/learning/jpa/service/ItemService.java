package com.learning.jpa.service;

import com.learning.jpa.domain.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ItemService {

    @Transactional(readOnly = true)
    Item save(Item item);

    @Transactional(readOnly = true)
    List<Item> getItems();

    @Transactional(readOnly = true)
    Item getItem(Long id);
}
