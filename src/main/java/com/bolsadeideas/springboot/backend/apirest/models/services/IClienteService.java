package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Factura;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Region;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
	
	public List<Region> findAllRegiones();

	//metodos para  crear la factura -> los services pueden usar diferentes dao -> factura esta relacionado con el cliente podemos uarlo normalsarvice -> administrar accesos a los dato

	public Factura findFacturaById(Long id); // para mi es imprecionante que cel metodo que le coloquemos se ajustara automaticamente a una consulta jpa

	Factura saveFactura(Factura factura);

	public void deleteFacturaById(Long id);

	public List<Producto> findProductoByNombre(String term);
}
