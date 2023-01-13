


@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    public Claims getGetAllCaimsFromToken(String token) {
        return Jwts.parser()
                .setSingningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return this.getGetAllCaimsFromToken(token).getExpiration().before(new Date());
    }
    public boolean isInvalid(String token) { return this.isTokenExpired(token); }
    }
}