package com.tw.tradeaway.service;

import com.tw.tradeaway.domain.Category;
import com.tw.tradeaway.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTests {

    CategoryService categoryService;


    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        categoryService = new CategoryService(categoryRepository);
    }


    @Test
    public void findAllCategories() throws Exception {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(
                new Category(1L,"Books", true),
                new Category(2L,"Electronics", true),
                new Category(3L,"Computers", true)
                ));
        List<Category> categories = categoryService.findAllCategories();
        assertNotNull(categories);
        assertTrue(!categories.isEmpty());
        assertEquals(3, categories.size());
    }
}
