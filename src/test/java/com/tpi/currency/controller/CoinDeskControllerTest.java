package com.tpi.currency.controller;

/**
 * @created 28/04/2024 - 01:08
 * @project currency
 * @author: thainguyen
 */
import com.tpi.currency.dto.common.ResultMessage;
import com.tpi.currency.dto.currency.CurrencyDTO;
import com.tpi.currency.service.CoinDeskService;
import com.tpi.currency.utils.ResultUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class CoinDeskControllerTest {
    @Mock
    private CoinDeskService coinDeskService;

    @InjectMocks
    private CoinDeskController coinDeskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPriceBitcoin() {
        // Mocking data
        CurrencyDTO expectedDTO = new CurrencyDTO();
        ResultMessage<CurrencyDTO> expectedResult = ResultUtil.data(expectedDTO);
        when(coinDeskService.getPriceBitcoin()).thenReturn(expectedDTO);

        // Calling the method under test
        ResultMessage<CurrencyDTO> result = coinDeskController.getPriceBitcoin();

        // Assertions
        assertEquals(expectedResult.getCode(), result.getCode());
        assertEquals(expectedDTO, result.getResult());
        verify(coinDeskService).getPriceBitcoin();
    }
}
