/*
 * Copyright (c) 2020 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.companykata;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.block.factory.Predicates;

/**
 * A company has a {@link MutableList} of {@link Customer}s.  It has an array of {@link Supplier}s, and a name.
 */
public class Company
{
    private final String name;
    private final MutableList<Customer> customers = Lists.mutable.empty();

    // Suppliers are array based. Refactor to a MutableList<Supplier>
    private MutableList<Supplier> suppliers = Lists.mutable.empty();

    public Company(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void addCustomer(Customer aCustomer)
    {
        this.customers.add(aCustomer);
    }

    public MutableList<Customer>  getCustomers()
    {
        return this.customers;
    }

    /**
     * Remove the Assertions.fail() and simplify getOrders().
     *
     * @see org.eclipse.collections.api.RichIterable#flatCollect(Function)
     */
    public MutableList<Order> getOrders()
    {
        return this.customers.flatCollect(Customer::getOrders);
    }

    public Customer getMostRecentCustomer()
    {
        return this.customers.getLast();
    }

    /**
     * Simplify after refactoring to use a MutableList&lt;Supplier&gt;.
     */
    public void addSupplier(Supplier supplier)
    {
        // need to replace the current array of suppliers with another, larger array
        // Of course, normally one would not use an array.
        this.suppliers.add(supplier);
    }

    public MutableList<Supplier> getSuppliers()
    {
        return this.suppliers;
    }

    /**
     * Remove the Assertions.fail() and replace the null with an appropriate implementation.
     * Use a {@link Predicate} to find a {@link Customer} with the name given.
     *
     * @see org.eclipse.collections.api.RichIterable#detect(Predicate)
     */
    public Customer getCustomerNamed(String name)
    {
        Predicates<Customer> findCustomerByName = Predicates.attributeEqual(Customer::getName, name);
        return customers.detect(findCustomerByName);
    }
}
