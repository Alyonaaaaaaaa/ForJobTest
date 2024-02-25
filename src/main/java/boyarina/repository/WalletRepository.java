package boyarina.repository;

import boyarina.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {

     @Query(nativeQuery = true, value = "select w.balance from wallet as w where w.id = :id")
     BigDecimal findBalanceById(@Param("id") UUID walletId);
}