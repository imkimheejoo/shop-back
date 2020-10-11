package com.shop.demo.products;

import com.shop.demo.error.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void getCategoryByName_성공() {
        assertEquals(Category.getCategoryByName("top"), Category.상의);
    }

    @Test
    void getCategoryByName_실패_없는_카테고리이름() {
        assertThrows(InvalidInputException.class, () -> Category.getCategoryByName("exception"));
    }
}