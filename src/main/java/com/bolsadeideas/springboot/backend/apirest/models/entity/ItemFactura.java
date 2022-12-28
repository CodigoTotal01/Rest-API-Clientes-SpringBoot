package com.bolsadeideas.springboot.backend.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.io.Serializable;

//encargado de tomar la cantidad de productos que se a solicitado
@Entity
@Table(name = "facturas_items")
public class ItemFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrementable
    private Long id;

    private Integer cantidad;


    @ManyToOne(fetch = FetchType.LAZY) // muchos items contienen un producto
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //! quitar atributos basura . añadir en cada relacion
    @JoinColumn(name = "producto_id") // el es el dueño de la relacion : aqui se inserta solito cuando es el quien tien una sola entidad
    private Producto producto;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    //tendra una llave foranea registrando la factura

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    //? Calcular el importe total  →
    public Double getImporte(){
        return cantidad.doubleValue()*producto.getPrecio(); // convertir la cantidad de un integer a un dopuble
    }

}
