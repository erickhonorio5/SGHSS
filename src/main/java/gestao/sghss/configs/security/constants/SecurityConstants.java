package gestao.sghss.configs.security.constants;

import java.util.List;

public final class SecurityConstants {
    private SecurityConstants() {}

    // ─── Base Paths ────────────────────────────────────────────────
    public static final String API_V1         = "/api/v1";
    public static final String AUTH_BASE      = API_V1 + "/auth";
    public static final String USER_BASE      = API_V1 + "/users";
    public static final String ADMIN_BASE     = API_V1 + "/admin";
    public static final String PUBLIC_BASE    = "/api/public";

    // ─── Auth Endpoints ────────────────────────────────────────────
    public static final String LOGIN_ENDPOINT  = AUTH_BASE + "/login";
    public static final String SIGNUP_ENDPOINT = USER_BASE + "/signup";

    // ─── Roles & Authorities ──────────────────────────────────────
    public static final String ROLE_USER  = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final List<String> ADMIN_AUTHORITIES = List.of(ROLE_ADMIN, ROLE_USER);
    public static final List<String> USER_AUTHORITIES  = List.of(ROLE_USER);
}
