package com.tpi.currency.controller;
import com.tpi.currency.dto.common.ResultMessage;
import com.tpi.currency.dto.request.AddCurrencyDTO;
import com.tpi.currency.dto.request.UpdateCurrencyDTO;
import com.tpi.currency.service.CurrencyService;
import com.tpi.currency.utils.ResultUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
/**
 * @created 28/04/2024 - 01:11
 * @project currency
 * @author: thainguyen
 */
public class CurrencyControllerTest {
    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private CurrencyController currencyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCurrency() {
        // Mocking data
        AddCurrencyDTO addCurrencyDTO = new AddCurrencyDTO("USD", "Dollar", 1F);
        ResultMessage<?> expectedResult = ResultUtil.success();

        // Calling the method under test
        ResultMessage<?> result = currencyController.addCurrency(addCurrencyDTO);

        // Assertions
        assertEquals(expectedResult.getMessage(), result.getMessage());
        verify(currencyService).save(addCurrencyDTO);
    }

    @Test
    void testUpdateCurrency() {
        // Mocking data
        UpdateCurrencyDTO updateCurrencyDTO = new UpdateCurrencyDTO("USD", "Dollar", 1F);
        ResultMessage<?> expectedResult = ResultUtil.success();

        // Calling the method under test
        ResultMessage<?> result = currencyController.updateCurrency(updateCurrencyDTO);

        // Assertions
        assertEquals(expectedResult.getCode(), result.getCode());
        verify(currencyService).update(updateCurrencyDTO);
    }

    @Test
    void testDelCurrency() {
        // Mocking data
        String currency = "USD";
        ResultMessage<?> expectedResult = ResultUtil.success();

        // Calling the method under test
        ResultMessage<?> result = currencyController.delCurrency(currency);

        // Assertions
        assertEquals(expectedResult.getCode(), result.getCode());
        verify(currencyService).delete(currency);
    }
}
