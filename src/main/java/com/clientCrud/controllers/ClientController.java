package com.clientCrud.controllers;

import com.clientCrud.domain.Client;
import com.clientCrud.domain.ClientRepository;
import com.clientCrud.domain.RequestClient;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientRepository repository;
    @GetMapping("/search")
    public ResponseEntity getAllClient() {
        var AllClient = repository.findAllByActiveTrue();
        return ResponseEntity.ok(AllClient);
    }
    @PostMapping("/new")
    public ResponseEntity registerClient(@RequestBody @Valid RequestClient data){
        Client newClient= new Client(data);
        repository.save(newClient);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity updateClient(@RequestBody @Valid RequestClient data){
        Optional<Client> optionalClient = repository.findById(data.id());
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setName(data.name());
            client.setCpf(data.cpf());
            client.setTelefone(data.telefone());
            client.setEmail(data.email());
            repository.save(client);
            return ResponseEntity.ok(client);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient (@PathVariable Long id){
        Optional<Client> optionalClient = repository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setActive(false);
            if (!client.getActive()){
                repository.delete(client);
            }
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }
    }
}

