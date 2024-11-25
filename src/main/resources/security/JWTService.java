package security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * Service pour la gestion des tokens JWT.
 */
public class JWTService {

    // Clé secrète pour signer le token
    private static final String SECRET_KEY = "votre_cle_secrete"; // Remplace par ta propre clé secrète
    private static final String ISSUER = "emiage-jwt"; // Fournisseur du token

    /**
     * Génère un token JWT pour un utilisateur donné.
     * 
     * @param username Nom de l'utilisateur
     * @return Token JWT signé
     */
    public static String generateToken(String username) {
        // Algorithme de chiffrement pour le token
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        // Génération du token avec les claims
        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim("username", username)
                .withIssuedAt(new Date()) // Date de création du token
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // Expire dans 1 heure
                .sign(algorithm);
    }
}
