package com.bolsadeideas.springboot.backend.apirest.controllers;


import com.bolsadeideas.springboot.backend.apirest.models.entity.Factura;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Producto;
import com.bolsadeideas.springboot.backend.apirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // para indicar que es un controllador rest de la api
@CrossOrigin(origins = { "http://localhost:4200" }) //inidcar la ruta del frond-end
@RequestMapping("/api")//ruta principal para rllegar a las demas -> ruta de primer nivel
public class FacturaRestController {

    @Autowired
    private IClienteService clienteService;

    //? ver el detalle
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping("/facturas/{id}") //ruta segundo nivel
    @ResponseStatus(code = HttpStatus.OK) // se puede omitir
    public Factura show(@PathVariable Long id){
        return clienteService.findFacturaById(id);

    }


    //? Eliminar factura
    @Secured({"ROLE_ADMIN"})
    @ResponseStatus(code = HttpStatus.NO_CONTENT) // se puede omitir
    @DeleteMapping("/facturas/{id}")
    public void delete(@PathVariable Long id){
        clienteService.deleteFacturaById(id);
    }

    //todos los metodos retornan un json
    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/facturas/filtrar-productos/{term}")
    public List<Producto> filtraProductos(@PathVariable String term){
        return clienteService.findProductoByNombre(term);
    }


    //? crear Factura -> spring poblara todo el json de forma automatica con el body
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/facturas")
    @ResponseStatus(code = HttpStatus.CREATED) // se puede omitir
    public Factura crear(@RequestBody Factura factura){
        return clienteService.saveFactura(factura);
    }

}
