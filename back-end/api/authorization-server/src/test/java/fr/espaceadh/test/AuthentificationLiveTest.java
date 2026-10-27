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
package fr.espaceadh.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import fr.espaceadh.security.ClientCredentialsTokens;

import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestPropertySource("classpath:application-dev.properties")
public class AuthentificationLiveTest {

    @Autowired
    private Environment env;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AuthentificationLiveTest.class);

    @Test
    public void testClientCredentials() {
        OAuth2AccessToken token = ClientCredentialsTokens.obtain(env);
        org.junit.Assert.assertNotNull(token.getTokenValue());
    }

}
