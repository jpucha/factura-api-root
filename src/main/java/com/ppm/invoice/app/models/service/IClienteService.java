package com.ppm.invoice.app.models.service;

import java.util.List;
import com.ppm.invoice.app.models.entity.Cliente;
import com.ppm.invoice.app.models.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * IClienteService specification.
 */
public interface IClienteService {

    /**
     * Find all.
     * @return
     */
    List<Cliente> findAll();

    /**
     * Find all.
     * @param pageable
     * @return
     */
    Page<Cliente> findAll(Pageable pageable);

    /**
     * findById.
     * @param id
     * @return
     */
    Cliente findById(Long id);

    /**
     * Save.
     * @param cliente
     * @return
     */
    Cliente save(Cliente cliente);

    /**
     * findOne
     * @param id
     * @return
     */
    Cliente findOne(Long id);

    /**
     * fetchByIdWithFacturas
     * @param id
     * @return
     */
    Cliente fetchByIdWithFacturas(Long id);

    /**
     * delete.
     * @param id
     */
    void delete(Long id);

    /**
     * findByNombre
     * @param term
     * @return
     */
    List<Producto> findByNombre(String term);


    /**
     * findProductoById.
     * @param id
     * @return
     */
    Producto findProductoById(Long id);


}
