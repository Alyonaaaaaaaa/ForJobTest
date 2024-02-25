package boyarina.service.converter;

import boyarina.entity.Wallet;
import boyarina.api.json.WalletResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WalletToResponseConverter implements Converter<Wallet, WalletResponse> {
    
    @Override
    public WalletResponse convert(Wallet wallet) {
        return new WalletResponse(wallet.getId(), wallet.getBalance());
    }
}