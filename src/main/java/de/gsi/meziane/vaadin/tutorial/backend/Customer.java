package de.gsi.meziane.vaadin.tutorial.backend;

import java.io.Serializable;
import java.time.LocalDate;

// for library loggers
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

// for application loggers
//import de.gsi.cs.co.ap.common.gui.elements.logger.AppLogger;

/**
 *
 * @author M. Kettou
 */

/**
 * A entity object, like in any other Java application. In a typical real world
 * application this could for example be a JPA entity.
 */
@SuppressWarnings("serial")
public class Customer implements Serializable, Cloneable {

    // You can choose a logger (needed imports are given in the import section as comments):
    // for libraries:
    // private static final Logger LOGGER = LoggerFactory.getLogger(Customer.class);
    // for swing applications:
    // private static final AppLogger LOGGER = AppLogger.getLogger();
    // for fx applications:
    // private static final AppLogger LOGGER = AppLogger.getFxLogger();

    private Long id;

    private String firstName = "";

    private String lastName = "";

    private LocalDate birthDate;

    private CustomerStatus status;

    private String email = "";

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the birthDate
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the status
     */
    public CustomerStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(final CustomerStatus status) {
        this.status = status;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    public boolean isPersisted() {
        return id != null;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (id == null) {
            return false;
        }

        if ((obj instanceof Customer) && obj.getClass().equals(getClass())) {
            return id.equals(((Customer) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = (43 * hash) + (id == null ? 0 : id.hashCode());
        return hash;
    }

    @Override
    public Customer clone() throws CloneNotSupportedException {
        return (Customer) super.clone();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}

