package boyarina.service;

import boyarina.exception.RateLimitException;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimitService {
    public void RateLimit() {
        Bandwidth oneConsumePerSecondLimit = Bandwidth.classic(1000, Refill.greedy(1000, Duration.ofSeconds(1)));
        Bucket bucket = Bucket.builder().addLimit(oneConsumePerSecondLimit).build();
        if (!bucket.tryConsume(1000)) {
            throw new RateLimitException("Request limit exceeded. Please wait.");
        }
    }
}