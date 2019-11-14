package de.gsi.meziane.vaadin.tutorial;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import de.gsi.meziane.vaadin.tutorial.backend.Customer;
import de.gsi.meziane.vaadin.tutorial.backend.CustomerService;

/**
 * The main view is a top-level placeholder for other views.
 */


@Route("")
public class MainView extends VerticalLayout {

    private final CustomerService service = CustomerService.getInstance();
    private final Grid<Customer> grid = new Grid<>();

    private final TextField filterText = new TextField();

    private final Button addNewCustomer;

    private final CustomerForm form;

    public MainView() {
        //grid.setColumns("firstName", "lastName", "status");
        filterText.setPlaceholder("Filter by name..");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList());

        addNewCustomer = new Button("Add new customer");
        form = new CustomerForm(this);

        addNewCustomer.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCustomer(new Customer());
        });
        final HorizontalLayout toolbar = new HorizontalLayout(filterText, addNewCustomer);

        grid.setWidth("500px");
        grid.addColumn(Customer::getFirstName).setHeader("First Name").setSortable(true);
        grid.addColumn(Customer::getLastName).setHeader("Last Name").setSortable(true);
        grid.addColumn(Customer::getStatus).setHeader("Status").setSortable(true);

        grid.asSingleSelect().addValueChangeListener(e -> form.setCustomer(grid.asSingleSelect().getValue()));

        final HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        add(toolbar, mainContent);
        form.setCustomer(null);

        updateList();
        getElement().executeJs("console.log('Executing Javascript code...')");

    }

    public void updateList() {

        grid.setItems(service.findAll(filterText.getValue()));
    }
}
