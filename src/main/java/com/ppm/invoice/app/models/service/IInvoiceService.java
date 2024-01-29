package com.ppm.invoice.app.models.service;

import com.ppm.invoice.app.models.entity.Factura;

/**
 * IInvoiceService specification.
 */
public interface IInvoiceService {

    /**
     *Save invoice.
     * @param factura
     */
    void saveInvoice(Factura factura);

    /**
     * FindInvoiceById.
     * @param id
     * @return
     */
    Factura findInvoiceById(Long id);

    /**
     * Delete invoice.
     * @param id
     */
    void deleteInvoice(Long id);

    /**
     * fetchInvoiceByIdWithClientWhithItemInvoiceWithProduct
     * @param id
     * @return
     */
    Factura fetchInvoiceByIdWithClientWhithItemInvoiceWithProduct(Long id);

}
