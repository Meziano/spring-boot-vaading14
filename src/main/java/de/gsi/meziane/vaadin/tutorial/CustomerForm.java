package de.gsi.meziane.vaadin.tutorial;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import de.gsi.meziane.vaadin.tutorial.backend.Customer;
import de.gsi.meziane.vaadin.tutorial.backend.CustomerService;
import de.gsi.meziane.vaadin.tutorial.backend.CustomerStatus;

// for library loggers
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

// for application loggers
//import de.gsi.cs.co.ap.common.gui.elements.logger.AppLogger;

/**
 *
 * @author M. Kettou
 */
public class CustomerForm extends FormLayout {

    // You can choose a logger (needed imports are given in the import section as comments):
    // for libraries:
    // private static final Logger LOGGER = LoggerFactory.getLogger(CustomerForm.class);
    // for swing applications:
    // private static final AppLogger LOGGER = AppLogger.getLogger();
    // for fx applications:
    // private static final AppLogger LOGGER = AppLogger.getFxLogger();

    private final TextField firstName, lastName;
    private final ComboBox<CustomerStatus> status;
    private final DatePicker birthDate;
    private final Button saveBtn, deleteBtn, cancelBtn;

    private final Binder<Customer> binder = new Binder<>(Customer.class);

    private final MainView mainView;

    private final CustomerService service = CustomerService.getInstance();

    /**
     *
     */
    public CustomerForm(final MainView mainView) {

        this.mainView = mainView;

        firstName = new TextField("First Name");
        lastName = new TextField("Last Name");

        status = new ComboBox<>("Status");
        status.setItems(CustomerStatus.values());

        birthDate = new DatePicker("Birthdate");
        saveBtn = new Button("Save");
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveBtn.addClickListener(e -> saveCutomer());

        deleteBtn = new Button("Delete");
        deleteBtn.addClickListener(e -> deleteCustomer());

        cancelBtn = new Button("Cancel");
        cancelBtn.addClickListener(e -> {
            setCustomer(null);
        });

        final HorizontalLayout buttons = new HorizontalLayout(saveBtn, deleteBtn, cancelBtn);

        add(firstName, lastName, status, birthDate, buttons);
        binder.bindInstanceFields(this);
        binder.setBean(new Customer());

    }

    /**
     * @return
     */
    private void saveCutomer() {
        final Customer c = binder.getBean();
        service.save(c);
        mainView.updateList();
        setCustomer(null);

    }

    public void deleteCustomer() {
        final Customer c = binder.getBean();
        service.delete(c);
        mainView.updateList();
        setCustomer(null);
    }

    public void setCustomer(final Customer customer) {
        binder.setBean(customer);
        setVisible(customer != null);
        firstName.focus();
    }

}

