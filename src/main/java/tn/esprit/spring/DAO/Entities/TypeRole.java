package tn.esprit.spring.DAO.Entities;

import org.springframework.security.core.GrantedAuthority;

public enum TypeRole implements GrantedAuthority
{
    Admin,CLIENT,AGENT;

    @Override
    public String getAuthority() {
        return null;
    }
}
