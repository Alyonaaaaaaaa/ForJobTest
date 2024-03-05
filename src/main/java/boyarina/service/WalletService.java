package boyarina.service;

import boyarina.api.json.WalletResponse;
import boyarina.entity.Wallet;
import boyarina.exception.NotFoundException;
import boyarina.repository.WalletRepository;
import boyarina.service.converter.WalletToResponseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletToResponseConverter converter;

    public BigDecimal getBalance(UUID walletId) {
        BigDecimal balance = walletRepository.findBalanceById(walletId);
        if (balance.toString().isEmpty()) {
            throw new NotFoundException("Wallet with this ID was not found");
        }
        return balance;
    }

    @Transactional(readOnly = false)
    public WalletResponse update(UUID walletId, OperationType operationType, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet with this ID was not found"));

        BigDecimal sum = wallet.getBalance();

        if ((operationType.compareTo(OperationType.WITHDRAW) == 0) &&
                ((sum.compareTo(BigDecimal.ZERO) == 0) || (sum.compareTo(amount) < 0))) {
            throw new IllegalStateException("Insufficient funds");
        }

        switch (operationType) {
            case DEPOSIT -> sum = sum.add(amount);
            case WITHDRAW -> sum = sum.subtract(amount);
        }
        wallet.setBalance(sum);
        wallet = walletRepository.save(wallet);

        return converter.convert(new Wallet());
    }
}