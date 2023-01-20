package com.sweetcommerceapi.api.controller;

import com.sweetcommerceapi.api.mapper.CustomerMapper;
import com.sweetcommerceapi.api.model.input.CustomerInput;
import com.sweetcommerceapi.api.model.output.CustomerOutput;
import com.sweetcommerceapi.domain.repository.CustomerRepository;
import com.sweetcommerceapi.domain.service.Impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerServiceImpl customerService;
   private CustomerRepository customerRepository;

   private CustomerMapper customerMapper;


   @GetMapping
   public ResponseEntity<List<CustomerOutput>> findAll() {
       return ResponseEntity.ok(customerMapper.toCollection(customerRepository.findAll()));
   }

   @GetMapping("/{id}")
    public ResponseEntity<CustomerOutput> search(@PathVariable Long id) {
       var customerExists = customerService.search(id);

       if(customerExists == null) {
          return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(customerMapper.toModel(customerExists));

   }

   @PostMapping
   public ResponseEntity<CustomerOutput> create(@Valid @RequestBody CustomerInput customerInput) {
       var customer = customerService.save(customerMapper.inputModelToEntity(customerInput));
      return ResponseEntity.status(HttpStatus.CREATED).body(customerMapper.toModel(customer));
   }

   @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> remove(@PathVariable Long customerId) {
     if(!customerRepository.existsById(customerId)) {
         return ResponseEntity.notFound().build();
     }
     customerService.delete(customerId);

     return ResponseEntity.noContent().build();


   }
}
