package boyarina.api.json;

import boyarina.service.OperationType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class WalletRequest {
    private UUID walletId;
    private OperationType operationType;
    private BigDecimal amount;
}