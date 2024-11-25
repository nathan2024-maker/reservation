package org.emiage.reservation.security;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

/**
 * Filtre pour vérifier l'authentification JWT.
 */
@Provider
public class JWTAuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Extraction du token JWT depuis l'en-tête Authorization
        String authorizationHeader = requestContext.getHeaderString("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Token JWT manquant ou invalide").build());
            return;
        }

        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            // Vérification du token avec le service JWT
            String username = JWTService.verifyToken(token); // Récupère le nom d'utilisateur du token
            // Ici, tu peux ajouter une logique supplémentaire si nécessaire
        } catch (Exception exception) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Token JWT invalide").build());
        }
    }
}
