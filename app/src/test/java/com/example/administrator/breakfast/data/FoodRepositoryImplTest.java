package com.example.administrator.breakfast.data;

import com.example.administrator.breakfast.data.model.Food;
import com.example.administrator.breakfast.utils.GsonUtil;
import com.example.administrator.breakfast.utils.PreferenceUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.breakfast.data.FoodRepository.LIST_SUFFIX;
import static com.example.administrator.breakfast.data.model.Food.BREAKFAST;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Jay on 2016/9/8.
 */
public class FoodRepositoryImplTest {

    private static final String FOOD_A = "a";

    private static final String FOOD_B = "b";

    private static final String FOOD_C = "c";


    @Mock
    PreferenceUtil store;

    private FoodRepository foodRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        foodRepository = new FoodRepositoryImpl(store);
    }

    @Test
    public void getFoodList_ValidInput_CorrectReturn() {

        when(store.getString(anyString())).thenReturn(getDummyFoodListJSON());

        List<Food> foods = foodRepository.getFoodList(BREAKFAST);

        Assert.assertEquals(foods.get(0).getName(), FOOD_A);

        verify(store).getString(BREAKFAST + LIST_SUFFIX);
    }


    @Test
    public void getFoodList_NullInput_ReturnNull() {

        when(store.getString(anyString())).thenReturn(null);

        List<Food> foods = foodRepository.getFoodList(BREAKFAST);

        Assert.assertEquals(foods, null);

        verify(store).getString(BREAKFAST + LIST_SUFFIX);
    }


    @Test
    public void getDefaultFoodList_ValidInput_CorrectReturn() {

        List<Food> foods = foodRepository.getDefaultFoodList(BREAKFAST);

        Assert.assertNotNull(foods);
        Assert.assertEquals(foods.size(), 5);
    }


    @Test
    public void setFoodList_ValidInput_CorrectReturn() {

        foodRepository.setFoodList(getDummyFoodList(), BREAKFAST);

        verify(store).setString(BREAKFAST + LIST_SUFFIX, GsonUtil.toJson(getDummyFoodList()));
    }

    private String getDummyFoodListJSON() {

        return GsonUtil.toJson(getDummyFoodList());
    }

    private List<Food> getDummyFoodList() {
        List<Food> foods = new ArrayList<>();
        foods.add(new Food(BREAKFAST, FOOD_A));
        foods.add(new Food(BREAKFAST, FOOD_B));
        foods.add(new Food(BREAKFAST, FOOD_C));

        return foods;
    }
}