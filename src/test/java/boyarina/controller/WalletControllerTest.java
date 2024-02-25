package boyarina.controller;

import boyarina.api.controller.WalletController;
import boyarina.api.json.WalletRequest;
import boyarina.api.json.WalletResponse;
import boyarina.exception.NotFoundException;
import boyarina.service.RateLimitService;
import boyarina.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static boyarina.service.OperationType.WITHDRAW;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WalletController.class)
public class WalletControllerTest {

    @MockBean
    private WalletService walletService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RateLimitService rateLimitService;

    @Test
    public void getBalance() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(walletService.getBalance(uuid)).thenReturn(new BigDecimal(1000));
        mockMvc.perform(get("/api/v1/wallets/{WALLET_UUID}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(new BigDecimal(1000)));
        verify(walletService, times(1)).getBalance(uuid);
    }

    @Test
    public void doNotGetBalance() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(walletService.getBalance(uuid)).thenThrow(new NotFoundException("Wallet with this ID was not found"));
        mockMvc.perform(get("/api/v1/wallets/{WALLET_UUID}", uuid))
                .andExpect(status().isNotFound());
        verify(walletService, times(1)).getBalance(uuid);
    }

    @Test
    public void update() throws Exception {
        UUID uuid = UUID.randomUUID();
        WalletResponse walletResponse = new WalletResponse(uuid, new BigDecimal(1000));
        WalletRequest walletRequest = new WalletRequest();
        walletRequest.setWalletId(uuid);
        walletRequest.setOperationType(WITHDRAW);
        walletRequest.setAmount(new BigDecimal(500));

        when(walletService.update(uuid, WITHDRAW, new BigDecimal(500))).thenReturn(walletResponse);
        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(walletRequest)))
                .andExpect(status().isOk());
        verify(walletService, times(1)).update(uuid, WITHDRAW, new BigDecimal(500));
    }

    @Test
    public void notUpdateWithNotFoundWallet() throws Exception {
        UUID uuid = UUID.randomUUID();
        WalletRequest walletRequest = new WalletRequest();
        walletRequest.setWalletId(uuid);
        walletRequest.setOperationType(WITHDRAW);
        walletRequest.setAmount(new BigDecimal(500));

        when(walletService.update(uuid, WITHDRAW, new BigDecimal(500)))
                .thenThrow(new NotFoundException("Wallet with this ID was not found"));
        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(walletRequest)))
                .andExpect(status().isNotFound());
        verify(walletService, times(1)).update(uuid, WITHDRAW, new BigDecimal(500));
    }

    @Test
    public void notUpdateWithInsufficientFunds() throws Exception {
        UUID uuid = UUID.randomUUID();
        WalletRequest walletRequest = new WalletRequest();
        walletRequest.setWalletId(uuid);
        walletRequest.setOperationType(WITHDRAW);
        walletRequest.setAmount(new BigDecimal(1500));

        when(walletService.update(uuid, WITHDRAW, new BigDecimal(1500)))
                .thenThrow(new IllegalStateException("Insufficient funds"));
        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(walletRequest)))
                .andExpect(status().isInternalServerError());
        verify(walletService, times(1)).update(uuid, WITHDRAW, new BigDecimal(1500));
    }
}