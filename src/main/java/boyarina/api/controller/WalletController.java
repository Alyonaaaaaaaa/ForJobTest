package boyarina.api.controller;

import boyarina.api.json.WalletRequest;
import boyarina.api.json.WalletResponse;
import boyarina.service.RateLimitService;
import boyarina.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class WalletController extends AbstractController {
    private final WalletService walletService;
    private final RateLimitService rateLimitService;

    @GetMapping("/wallets/{WALLET_UUID}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable("WALLET_UUID") UUID id) {
        rateLimitService.RateLimit();
        return ResponseEntity.ok(walletService.getBalance(id));
    }

    @PostMapping("/wallet")
    public ResponseEntity<WalletResponse> update(@RequestBody @Validated WalletRequest request) {
        rateLimitService.RateLimit();
        return ResponseEntity.ok(walletService.update(
                request.getWalletId(),
                request.getOperationType(),
                request.getAmount()));
    }
}