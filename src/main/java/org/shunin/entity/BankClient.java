package org.shunin.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class BankClient {

    @Id
    @GeneratedValue()
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<BankAccount> accounts = new HashSet<>();


    public BankClient() {
    }

    public BankClient(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<BankAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "BankClient{" +
                "clientId=" + clientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
