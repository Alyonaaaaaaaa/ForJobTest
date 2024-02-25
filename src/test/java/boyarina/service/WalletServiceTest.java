package boyarina.service;

import boyarina.api.json.WalletResponse;
import boyarina.entity.Wallet;
import boyarina.exception.NotFoundException;
import boyarina.repository.WalletRepository;
import boyarina.service.converter.WalletToResponseConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WalletServiceTest {

    @InjectMocks
    WalletService walletService;

    @Mock
    WalletToResponseConverter converter;

    @Mock
    WalletRepository walletRepository;

    @Test
    public void getBalance() {
        UUID uuid = UUID.randomUUID();
        when(walletRepository.findBalanceById(uuid)).thenReturn(new BigDecimal(1000));
        BigDecimal balance = walletService.getBalance(uuid);
        assertEquals(balance, new BigDecimal(1000));
        verify(walletRepository, times(1)).findBalanceById(uuid);
    }

    @Test(expected = NotFoundException.class)
    public void doNotGetBalance() {
        UUID uuid = UUID.randomUUID();
        when(walletRepository.findBalanceById(uuid)).thenThrow(new NotFoundException("Wallet with this ID was not found"));
        walletService.getBalance(uuid);
    }

    @Test
    public void update() {
        UUID uuid = UUID.randomUUID();
        Wallet wallet = new Wallet();
        wallet.setId(uuid);
        wallet.setBalance(new BigDecimal(1000));

        Wallet newWallet = new Wallet();
        newWallet.setId(uuid);
        newWallet.setBalance(new BigDecimal(1500));

        WalletResponse walletResponse = new WalletResponse(uuid, new BigDecimal(1500));

        when(walletRepository.findById(uuid)).thenReturn(Optional.of(wallet));
        when(walletRepository.findBalanceById(uuid)).thenReturn(new BigDecimal(1000));
        when(walletRepository.save(newWallet)).thenReturn(newWallet);
        when(converter.convert(newWallet)).thenReturn(walletResponse);

        WalletResponse wResponse = walletService.update(uuid, OperationType.DEPOSIT, new BigDecimal(500));

        assertEquals(walletResponse, wResponse);

        verify(walletRepository, times(1)).findById(uuid);
        verify(walletRepository, times(1)).findBalanceById(uuid);
        verify(walletRepository, times(1)).save(newWallet);
        verify(converter, times(1)).convert(newWallet);
    }

    @Test(expected = NotFoundException.class)
    public void notUpdateWithNotFoundWallet() {
        UUID uuid = UUID.randomUUID();
        when(walletRepository.findById(uuid)).thenThrow(new NotFoundException("Wallet with this ID was not found"));
        walletService.update(uuid, OperationType.DEPOSIT, new BigDecimal(500));
    }

    @Test(expected = IllegalStateException.class)
    public void notUpdateWithInsufficientFunds() {
        UUID uuid = UUID.randomUUID();
        Wallet wallet = new Wallet();
        wallet.setId(uuid);
        wallet.setBalance(new BigDecimal(1000));

        when(walletRepository.findById(uuid)).thenReturn(Optional.of(wallet));
        when(walletRepository.findBalanceById(uuid)).thenReturn(new BigDecimal(1000));

        walletService.update(uuid, OperationType.WITHDRAW, new BigDecimal(5000));

        verify(walletRepository, times(1)).findById(uuid);
        verify(walletRepository, times(1)).findBalanceById(uuid);
    }
}