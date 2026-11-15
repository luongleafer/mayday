package mayday.services;

import mayday.entities.nguoi.TaiKhoan;
import mayday.security.AccountDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;

public class JwtService {

    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateTestJwt() {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(10);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("mayday")
                .issuedAt(Instant.now())
                .expiresAt(expiry)
                .claim("alg", "RS256")
                .claim("test", "test")
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateJwtForAccount(AccountDetails accountDetails) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(3600);

        TaiKhoan taiKhoan = accountDetails.getTaiKhoan();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("mayday")
                .issuedAt(now)
                .expiresAt(expiry)
                .subject(taiKhoan.getMaTaiKhoan().toString())
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
