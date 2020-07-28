package com.jide.testing.controller;

import com.jide.testing.model.Item;
import com.jide.testing.service.ItemBusinessService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ItemControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemBusinessService itemBusinessService;

    @Test
    void dummyItem() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"ball\",\"price\":10,\"quantity\":100}"))
                .andReturn();
//        assertEquals("{\"id\":1,\"name\":\"ball\",\"price\":10,\"quantity\":100}", result.getResponse().getContentAsString());
    }

    @Test
    void itemFromBusinessService() throws Exception {
        Mockito.when(itemBusinessService.retrieveHardCodedItem()).thenReturn(new Item(2, "Item2", 10, 10));

        RequestBuilder request = MockMvcRequestBuilders.get("/item-from-business-service").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:2,name:Item2,price:10,quantity:10}"))
                .andReturn();
//        assertEquals("{\"id\":1,\"name\":\"ball\",\"price\":10,\"quantity\":100}", result.getResponse().getContentAsString());
    }

    @Test
    void retrieveAllItems_basic() throws Exception {
        Mockito.when(itemBusinessService.retrieveAllItems()).thenReturn(Arrays.asList(new Item(2, "Item2", 10, 10),
                new Item(3, "Item3", 15, 15),
                new Item(4, "Item4", 20, 20)));

        RequestBuilder request = MockMvcRequestBuilders.get("/all-items-from-database").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id:2,name:Item2,price:10,quantity:10}," +
                        " {id:3,name:Item3,price:15,quantity:15}, {id:4,name:Item4,price:20,quantity:20}]"))
                .andReturn();
//        assertEquals("{\"id\":1,\"name\":\"ball\",\"price\":10,\"quantity\":100}", result.getResponse().getContentAsString());
    }
}