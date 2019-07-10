package com.fattoria.url.gateway.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "URLS")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class URLModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name = "URLORIGINAL")
    private String urlOrignal;
    @Column(name = "URLMODIFICADA")
    private String urlModificada;
}
