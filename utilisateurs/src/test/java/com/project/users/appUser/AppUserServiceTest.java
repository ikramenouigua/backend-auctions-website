package com.project.users.appUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.users.registration.token.ConfirmationToken;
import com.project.users.registration.token.ConfirmationTokenService;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppUserService.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
class AppUserServiceTest {
    @MockBean
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;

    @MockBean
    private ConfirmationTokenService confirmationTokenService;

    /**
     * Method under test: {@link AppUserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        AppUser appUser = new AppUser();
        appUser.setAdresse("Adresse");
        appUser.setAppUserRole(AppUserRole.BUYER);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setFirstName("Jane");
        appUser.setId(123L);
        appUser.setLastName("Doe");
        appUser.setLocked(true);
        appUser.setPassword("iloveyou");
        appUser.setPhone("4105551212");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(appUserRepository.findByEmail((String) any())).thenReturn(ofResult);
        assertSame(appUser, appUserService.loadUserByUsername("jane.doe@example.org"));
        verify(appUserRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link AppUserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(appUserRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> appUserService.loadUserByUsername("jane.doe@example.org"));
        verify(appUserRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link AppUserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        when(appUserRepository.findByEmail((String) any()))
                .thenThrow(new IllegalStateException("user with email %s not found"));
        assertThrows(IllegalStateException.class, () -> appUserService.loadUserByUsername("jane.doe@example.org"));
        verify(appUserRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link AppUserService#signUpUser(AppUser)}
     */
    @Test
    void testSignUpUser() {
        AppUser appUser = new AppUser();
        appUser.setAdresse("Adresse");
        appUser.setAppUserRole(AppUserRole.BUYER);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setFirstName("Jane");
        appUser.setId(123L);
        appUser.setLastName("Doe");
        appUser.setLocked(true);
        appUser.setPassword("iloveyou");
        appUser.setPhone("4105551212");

        AppUser appUser1 = new AppUser();
        appUser1.setAdresse("Adresse");
        appUser1.setAppUserRole(AppUserRole.BUYER);
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setEnabled(true);
        appUser1.setFirstName("Jane");
        appUser1.setId(123L);
        appUser1.setLastName("Doe");
        appUser1.setLocked(true);
        appUser1.setPassword("iloveyou");
        appUser1.setPhone("4105551212");
        Optional<AppUser> ofResult = Optional.of(appUser1);
        when(appUserRepository.enableAppUser((String) any())).thenReturn(1);
        when(appUserRepository.save((AppUser) any())).thenReturn(appUser);
        when(appUserRepository.findByEmail((String) any())).thenReturn(ofResult);

        AppUser appUser2 = new AppUser();
        appUser2.setAdresse("Adresse");
        appUser2.setAppUserRole(AppUserRole.BUYER);
        appUser2.setEmail("jane.doe@example.org");
        appUser2.setEnabled(true);
        appUser2.setFirstName("Jane");
        appUser2.setId(123L);
        appUser2.setLastName("Doe");
        appUser2.setLocked(true);
        appUser2.setPassword("iloveyou");
        appUser2.setPhone("4105551212");
        assertThrows(IllegalStateException.class, () -> appUserService.signUpUser(appUser2));
        verify(appUserRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link AppUserService#signUpUser(AppUser)}
     */
    @Test
    void testSignUpUser2() {
        AppUser appUser = new AppUser();
        appUser.setAdresse("Adresse");
        appUser.setAppUserRole(AppUserRole.BUYER);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setFirstName("Jane");
        appUser.setId(123L);
        appUser.setLastName("Doe");
        appUser.setLocked(true);
        appUser.setPassword("iloveyou");
        appUser.setPhone("4105551212");
        when(appUserRepository.enableAppUser((String) any())).thenReturn(1);
        when(appUserRepository.save((AppUser) any())).thenReturn(appUser);
        when(appUserRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        doNothing().when(confirmationTokenService).saveConfirmationToken((ConfirmationToken) any());

        AppUser appUser1 = new AppUser();
        appUser1.setAdresse("Adresse");
        appUser1.setAppUserRole(AppUserRole.BUYER);
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setEnabled(true);
        appUser1.setFirstName("Jane");
        appUser1.setId(123L);
        appUser1.setLastName("Doe");
        appUser1.setLocked(true);
        appUser1.setPassword("iloveyou");
        appUser1.setPhone("4105551212");
        appUserService.signUpUser(appUser1);
        verify(appUserRepository).enableAppUser((String) any());
        verify(appUserRepository).save((AppUser) any());
        verify(appUserRepository).findByEmail((String) any());
        verify(confirmationTokenService).saveConfirmationToken((ConfirmationToken) any());
    }

    /**
     * Method under test: {@link AppUserService#signUpUser(AppUser)}
     */
    @Test
    void testSignUpUser3() {
        AppUser appUser = new AppUser();
        appUser.setAdresse("Adresse");
        appUser.setAppUserRole(AppUserRole.BUYER);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setFirstName("Jane");
        appUser.setId(123L);
        appUser.setLastName("Doe");
        appUser.setLocked(true);
        appUser.setPassword("iloveyou");
        appUser.setPhone("4105551212");
        when(appUserRepository.enableAppUser((String) any())).thenReturn(1);
        when(appUserRepository.save((AppUser) any())).thenReturn(appUser);
        when(appUserRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        doNothing().when(confirmationTokenService).saveConfirmationToken((ConfirmationToken) any());
        AppUser appUser1 = mock(AppUser.class);
        when(appUser1.getEmail()).thenReturn("jane.doe@example.org");
        when(appUser1.getPassword()).thenReturn("iloveyou");
        doNothing().when(appUser1).setAdresse((String) any());
        doNothing().when(appUser1).setAppUserRole((AppUserRole) any());
        doNothing().when(appUser1).setEmail((String) any());
        doNothing().when(appUser1).setEnabled((Boolean) any());
        doNothing().when(appUser1).setFirstName((String) any());
        doNothing().when(appUser1).setId((Long) any());
        doNothing().when(appUser1).setLastName((String) any());
        doNothing().when(appUser1).setLocked((Boolean) any());
        doNothing().when(appUser1).setPassword((String) any());
        doNothing().when(appUser1).setPhone((String) any());
        appUser1.setAdresse("Adresse");
        appUser1.setAppUserRole(AppUserRole.BUYER);
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setEnabled(true);
        appUser1.setFirstName("Jane");
        appUser1.setId(123L);
        appUser1.setLastName("Doe");
        appUser1.setLocked(true);
        appUser1.setPassword("iloveyou");
        appUser1.setPhone("4105551212");
        appUserService.signUpUser(appUser1);
        verify(appUserRepository).enableAppUser((String) any());
        verify(appUserRepository).save((AppUser) any());
        verify(appUserRepository).findByEmail((String) any());
        verify(confirmationTokenService).saveConfirmationToken((ConfirmationToken) any());
        verify(appUser1, atLeast(1)).getEmail();
        verify(appUser1).getPassword();
        verify(appUser1).setAdresse((String) any());
        verify(appUser1).setAppUserRole((AppUserRole) any());
        verify(appUser1).setEmail((String) any());
        verify(appUser1).setEnabled((Boolean) any());
        verify(appUser1).setFirstName((String) any());
        verify(appUser1).setId((Long) any());
        verify(appUser1).setLastName((String) any());
        verify(appUser1).setLocked((Boolean) any());
        verify(appUser1, atLeast(1)).setPassword((String) any());
        verify(appUser1).setPhone((String) any());
    }

    /**
     * Method under test: {@link AppUserService#enableAppUser(String)}
     */
    @Test
    void testEnableAppUser() {
        when(appUserRepository.enableAppUser((String) any())).thenReturn(1);
        assertEquals(1, appUserService.enableAppUser("jane.doe@example.org"));
        verify(appUserRepository).enableAppUser((String) any());
    }

    /**
     * Method under test: {@link AppUserService#enableAppUser(String)}
     */
    @Test
    void testEnableAppUser2() {
        when(appUserRepository.enableAppUser((String) any())).thenThrow(new IllegalStateException("foo"));
        assertThrows(IllegalStateException.class, () -> appUserService.enableAppUser("jane.doe@example.org"));
        verify(appUserRepository).enableAppUser((String) any());
    }
}

