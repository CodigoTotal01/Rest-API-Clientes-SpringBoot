package com.bolsadeideas.springboot.backend.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.w3c.dom.ls.LSInput;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "facturas") //Bidireccional
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String descripcion;

    private String observacion;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE) // indicar el formato de la fecha
    private Date createAt;

    //Lazy > solo caundo se llma los metodos GET
    @JsonIgnoreProperties(value = {"facturas","hibernateLazyInitializer", "handler"}, allowSetters = true) //! para no tener problemas de relacion inversa, ahora vete ala factura que revvienta
    @ManyToOne(fetch = FetchType.LAZY) //muchas facturas estan asociadas a un solo cliente
    @JoinColumn(name = "cliente_id") //llave foranea que se ubica en la factura -> Join Colum para indicar la llave foranea
    private Cliente cliente;


    //unidireccional -> UNA factura puede tener muchos items
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // cuando se elimine o agrege una factura se persistira la cfactura y luego los items
    //! no mapped porque no es bidireccional, se debe generar la realcion en la otra tabla con el join coluumn
    @JoinColumn(name = "factura_id") // esta se generara en la otra tabla -> los item caftura contnedras muchas o una factura
    private List<ItemFactura> items; //Muchos items en una factura


//siempre inicializar nuestros array lsit


    public Factura() {
        items = new ArrayList<>();
    }

    @PrePersist //antes que se guarde en la base de datos
    private void prePersist(){
        createAt = new Date();
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }


    //obtener elprecio final de todos los items
    public Double getTotal(){
        Double total = 0.00;
        for (ItemFactura item : items){
            total += item.getImporte();
        }
        return  total;
    }
}
