package com.jide.testing.service;

import com.jide.testing.model.Item;
import com.jide.testing.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ItemBusinessServiceTest {

    @InjectMocks
    private ItemBusinessService itemBusinessService;

    @Mock
    private ItemRepository repository;

    @Test
    void retrieveHardCodedItem() {
    }

    @Test
    void retrieveAllItems_basic() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(new Item(2, "Item2", 10, 10),
                new Item(3, "Item3", 15, 15),
                new Item(4, "Item4", 20, 20)));
        List<Item> items = itemBusinessService.retrieveAllItems();

        assertEquals(100, items.get(0).getValue());
        assertEquals(225, items.get(1).getValue());
        assertEquals(400, items.get(2).getValue());
    }
}