/*
 * Copyright (C) 2019 emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.espaceadh.adherents.config;

import fr.espaceadh.adherents.service.AdherentService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

/**
 *
 * @author emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 * DefaultMethodSecurityExpressionHandler
 */
public class CustomMethodSecurityExpressionHandler  extends OAuth2MethodSecurityExpressionHandler  {
    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
    
    private ApplicationContext applicationContext;
    
    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        CustomMethodSecurityExpressionRoot root =   new CustomMethodSecurityExpressionRoot(authentication);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        
        root.setAdherentService(applicationContext.getBean(AdherentService.class));
        
        return root;
    }
    
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        super.setApplicationContext(applicationContext);
        this.applicationContext = applicationContext;
    }
    
}
