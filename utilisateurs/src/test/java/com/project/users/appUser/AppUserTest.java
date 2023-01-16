package com.project.users.appUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class AppUserTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link AppUser#AppUser(String, String, String, String, String, String, AppUserRole)}
     *   <li>{@link AppUser#setAdresse(String)}
     *   <li>{@link AppUser#setPhone(String)}
     *   <li>{@link AppUser#getAdresse()}
     *   <li>{@link AppUser#getFirstName()}
     *   <li>{@link AppUser#getLastName()}
     *   <li>{@link AppUser#getPassword()}
     *   <li>{@link AppUser#getPhone()}
     *   <li>{@link AppUser#getUsername()}
     *   <li>{@link AppUser#isAccountNonExpired()}
     *   <li>{@link AppUser#isCredentialsNonExpired()}
     * </ul>
     */
    @Test
    void testConstructor() {
        AppUser actualAppUser = new AppUser("Jane", "Doe", "jane.doe@example.org", "Adresse", "4105551212", "iloveyou",
                AppUserRole.BUYER);
        actualAppUser.setAdresse("Adresse");
        actualAppUser.setPhone("4105551212");
        assertEquals("Adresse", actualAppUser.getAdresse());
        assertEquals("Jane", actualAppUser.getFirstName());
        assertEquals("Doe", actualAppUser.getLastName());
        assertEquals("iloveyou", actualAppUser.getPassword());
        assertEquals("4105551212", actualAppUser.getPhone());
        assertEquals("jane.doe@example.org", actualAppUser.getUsername());
        assertTrue(actualAppUser.isAccountNonExpired());
        assertTrue(actualAppUser.isCredentialsNonExpired());
    }

    /**
     * Method under test: {@link AppUser#getAuthorities()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAuthorities() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.project.users.appUser.AppUserRole.name()" because "this.appUserRole" is null
        //       at com.project.users.appUser.AppUser.getAuthorities(AppUser.java:81)
        //   In order to prevent getAuthorities()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAuthorities().
        //   See https://diff.blue/R013 to resolve this issue.

        (new AppUser()).getAuthorities();
    }

    /**
     * Method under test: {@link AppUser#getAuthorities()}
     */
    @Test
    void testGetAuthorities2() {
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
        Collection<? extends GrantedAuthority> actualAuthorities = appUser.getAuthorities();
        assertEquals(1, actualAuthorities.size());
        assertEquals("BUYER", ((List<? extends GrantedAuthority>) actualAuthorities).get(0).getAuthority());
    }

    /**
     * Method under test: {@link AppUser#isAccountNonLocked()}
     */
    @Test
    void testIsAccountNonLocked() {
        assertTrue((new AppUser()).isAccountNonLocked());
    }

    /**
     * Method under test: {@link AppUser#isAccountNonLocked()}
     */
    @Test
    void testIsAccountNonLocked2() {
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
        assertFalse(appUser.isAccountNonLocked());
    }

    /**
     * Method under test: {@link AppUser#isEnabled()}
     */
    @Test
    void testIsEnabled() {
        assertFalse((new AppUser()).isEnabled());
    }

    /**
     * Method under test: {@link AppUser#isEnabled()}
     */
    @Test
    void testIsEnabled2() {
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
        assertTrue(appUser.isEnabled());
    }
}

