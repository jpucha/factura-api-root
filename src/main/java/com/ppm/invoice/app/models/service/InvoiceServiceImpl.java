package com.ppm.invoice.app.models.service;

import com.ppm.invoice.app.models.dao.IFacturaDao;
import com.ppm.invoice.app.models.entity.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * InvoiceServiceImpl specification.
 */
@Service
public class InvoiceServiceImpl implements  IInvoiceService{

    @Autowired
    private IFacturaDao facturaDao;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveInvoice(Factura factura) {
        facturaDao.save(factura);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly=true)
    public Factura findInvoiceById(Long id) {
        return facturaDao.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteInvoice(Long id) {
        facturaDao.deleteById(id); // facturaDao.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly=true)
    public Factura fetchInvoiceByIdWithClientWhithItemInvoiceWithProduct(Long id) {
        return facturaDao.fetchByIdWithClienteWhithItemFacturaWithProducto(id);
    }
}
