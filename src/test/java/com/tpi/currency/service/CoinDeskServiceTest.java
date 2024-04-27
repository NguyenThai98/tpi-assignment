package com.tpi.currency.service;
import com.tpi.currency.client.coindesk.CoinDeskClient;
import com.tpi.currency.client.coindesk.response.CurrencyDataResponse;
import com.tpi.currency.dto.currency.CurrencyDTO;
import com.tpi.currency.mapper.CurrencyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
/**
 * @created 28/04/2024 - 00:54
 * @project currency
 * @author: thainguyen
 */
class CoinDeskServiceTest {

    @Mock
    private CoinDeskClient coinDeskClient;

    @Mock
    private CurrencyMapper currencyMapper;

    @InjectMocks
    private CoinDeskService coinDeskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPriceBitcoin_Success() {
        // Mocking data
        CurrencyDataResponse expectedDTO = new CurrencyDataResponse();
        CurrencyDTO currencyDTO = new CurrencyDTO();
        when(coinDeskClient.getPriceBitcoin()). thenReturn(expectedDTO);
        when(currencyMapper.toCurrencyDTO(expectedDTO)).thenReturn(currencyDTO);

        // Calling the method under test
        CurrencyDTO resultDTO = coinDeskService.getPriceBitcoin();

        // Assertions
        assertEquals(currencyDTO, resultDTO);
        verify(coinDeskClient).getPriceBitcoin();
        verify(currencyMapper).toCurrencyDTO(expectedDTO);
    }

    @Test
    void testGetPriceBitcoin_NullResponse() {
        // Mocking data
        when(coinDeskClient.getPriceBitcoin()).thenReturn(null);

        // Calling the method under test
        CurrencyDTO resultDTO = coinDeskService.getPriceBitcoin();

        // Assertions
        assertEquals(null, resultDTO);
        verify(coinDeskClient).getPriceBitcoin();
        verifyNoInteractions(currencyMapper); // Ensure that currencyMapper.toCurrencyDTO() is not called
    }
}