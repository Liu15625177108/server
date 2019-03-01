package com.example.server.security.core.smslogin;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @ClassName SmsAuthenticationToken
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.security.core.smslogin
 * @Date 2019/2/28 15:54
 */
public class SmsAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 500L;
    private final Object principal;
//    private Object credentials;

    public SmsAuthenticationToken(Object principal) {
        super((Collection)null);
        this.principal = principal;

        this.setAuthenticated(false);
    }

    public SmsAuthenticationToken(Object principal,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
