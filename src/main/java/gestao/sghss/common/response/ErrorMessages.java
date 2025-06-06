package gestao.sghss.common.response;

public final class ErrorMessages {
    private ErrorMessages() {}

    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String FORBIDDEN = "Forbidden";
    
    public static final String INVALID_CREDENTIALS = "Credenciais inválidas";
    public static final String AUTHENTICATION_REQUIRED = "Autenticação necessária para acessar este recurso";
    public static final String ACCOUNT_EXPIRED = "Conta expirada";
    public static final String ACCOUNT_LOCKED = "Conta bloqueada";
    public static final String ACCOUNT_DISABLED = "Conta desativada";
    public static final String CREDENTIALS_EXPIRED = "Credenciais expiradas";
    public static final String USER_NOT_FOUND = "Usuário não encontrado";
    public static final String GENERIC_ERROR = "Erro de autenticação";
}