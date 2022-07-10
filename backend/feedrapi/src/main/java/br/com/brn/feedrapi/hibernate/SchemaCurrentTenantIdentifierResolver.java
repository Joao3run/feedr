package br.com.brn.feedrapi.hibernate;


import org.hibernate.context.spi.CurrentTenantIdentifierResolver;


public class SchemaCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver{

    @Override
    public String resolveCurrentTenantIdentifier() {
        if(Tenant.getIdentificador() == null) {
            return "";
        }
        return Tenant.getIdentificador();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }

}
