package com.tpi.currency.service;

import com.tpi.currency.dto.request.AddCurrencyDTO;
import com.tpi.currency.dto.request.UpdateCurrencyDTO;
import com.tpi.currency.entity.Coin;
import com.tpi.currency.exception.ClientException;
import com.tpi.currency.mapper.CoinMapper;
import com.tpi.currency.repository.CoinRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @created 28/04/2024 - 00:43
 * @project currency
 * @author: thainguyen
 */
public class CurrencyServiceTest {
    @Mock
    private CoinRepository coinRepository;

    @Mock
    private CoinMapper coinMapper;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSave() {
        // Mocking data
        AddCurrencyDTO addCurrencyDTO = new AddCurrencyDTO("USD", "United States Dollar", 62911.2776F);
        when(coinRepository.findByCode(any())).thenReturn(Optional.empty());

        // Calling the method under test
        currencyService.save(addCurrencyDTO);

        // Assertions
        verify(coinRepository).save(any());
    }

    @Test
    void testSave_ExistingCurrency() {
        // Mocking data
        AddCurrencyDTO addCurrencyDTO = new AddCurrencyDTO("USD", "United States Dollar", 62911.2776F);
        when(coinRepository.findByCode(any())).thenReturn(Optional.of(new Coin()));

        // Assertions
        assertThrows(ClientException.class, () -> currencyService.save(addCurrencyDTO));
    }

    @Test
    void testUpdate() {
        // Mocking data
        UpdateCurrencyDTO updateCurrencyDTO = new UpdateCurrencyDTO("USD", "United States Dollarr", null);
        Coin coin = new Coin();
        when(coinRepository.findByCode(any())).thenReturn(Optional.of(coin));

        // Calling the method under test
        currencyService.update(updateCurrencyDTO);

        // Assertions
        verify(coinMapper).updateCoin(eq(coin), eq(updateCurrencyDTO));
    }

    @Test
    void testDelete() {
        // Mocking data
        String currency = "USD";
        Coin coin = new Coin();
        when(coinRepository.findByCode(any())).thenReturn(Optional.of(coin));

        // Calling the method under test
        currencyService.delete(currency);

        // Assertions
        verify(coinRepository).delete(coin);
    }

    @Test
    void testDelete_NonExistingCurrency() {
        // Mocking data
        String currency = "USD";
        when(coinRepository.findByCode(any())).thenReturn(Optional.empty());
        // Assertions
        assertThrows(ClientException.class , () -> currencyService.delete(currency));
        verify(coinRepository, never()).delete((Coin) any());
    }

    @Test
    void testGetCoinByCurrency() {
        // Mocking data
        String currency = "USD";
        Coin coin = new Coin();
        when(coinRepository.findByCode(currency)).thenReturn(Optional.of(coin));

        // Calling the method under test
        Coin result = currencyService.getCoinByCurrency(currency);

        // Assertions
        assertEquals(coin, result);
    }

    @Test
    void testGetCoinByCurrency_NonExistingCurrency() {
        // Mocking data
        String currency = "USD";
        when(coinRepository.findByCode(currency)).thenReturn(Optional.empty());

        // Assertions
        assertThrows(ClientException.class, () -> currencyService.getCoinByCurrency(currency));
    }

    @Test
    void testIsExistsCurrency() {
        // Mocking data
        String currency = "USD";
        when(coinRepository.findByCode(currency)).thenReturn(Optional.of(new Coin()));

        // Calling the method under test
        boolean result = currencyService.isExistsCurrency(currency);

        // Assertions
        assertTrue(result);
    }

    @Test
    void testIsExistsCurrency_NonExistingCurrency() {
        // Mocking data
        String currency = "USD";
        when(coinRepository.findByCode(currency)).thenReturn(Optional.empty());

        // Calling the method under test
        boolean result = currencyService.isExistsCurrency(currency);

        // Assertions
        assertFalse(result);
    }
}
