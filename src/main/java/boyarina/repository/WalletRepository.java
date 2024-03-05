package boyarina.repository;

import boyarina.entity.Wallet;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {
     @Query(nativeQuery = true, value = "select w.balance from wallet as w where w.id = :id")
     BigDecimal findBalanceById(@Param("id") UUID walletId);

     @Lock(LockModeType.PESSIMISTIC_WRITE)
     @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
     Optional<Wallet> findById(UUID uuid);
}