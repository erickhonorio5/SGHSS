package gestao.sghss.configs.security.constants;

import java.util.List;

public final class SecurityConstants {
    private SecurityConstants() {}

    // Base paths
    public static final String API_V1_BASE = "/api/v1";
    public static final String AUTH_BASE = API_V1_BASE + "/auth";
    public static final String USER_BASE = API_V1_BASE + "/users";
    public static final String ADMIN_BASE = API_V1_BASE + "/admin";
    public static final String PUBLIC_BASE = "/api/public";

    // Auth endpoints
    public static final String LOGIN = AUTH_BASE + "/login";
    public static final String SIGNUP = USER_BASE + "/signup";

    // Swagger endpoints
    public static final String UI = "/swagger-ui/**";
    public static final String API_DOCS = "/v3/api-docs/**";

    // Authorized roles
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final List<String> ADMIN_AUTHORITIES = List.of(ROLE_ADMIN, ROLE_USER);
    public static final List<String> USER_AUTHORITIES = List.of(ROLE_USER);

    // Public endpoints array
    public static final String[] PUBLIC_PATHS = {
            PUBLIC_BASE + "/**",
            LOGIN,
            SIGNUP,
            UI,
            API_DOCS
    };
}