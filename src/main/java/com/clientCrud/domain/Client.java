package com.clientCrud.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name= "client")

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    private Boolean active;

    public Client (RequestClient requestClient){
        this.name = requestClient.name();
        this.cpf = requestClient.cpf();
        this.telefone = requestClient.telefone();
        this.email = requestClient.email();
        this.active = true;
    }
}
