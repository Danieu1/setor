package controller;

import com.setor_de_vendas.setor.models.Fornecedor;
import com.setor_de_vendas.setor.models.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FornecedorService;
import service.FornecedorService;
import service.ProdutoService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")

public class FornecedorController {
    FornecedorService  service;
    //-------------------------------------------------------------------------
    public  FornecedorController(FornecedorService service){
        this.service = service;
    }

    @GetMapping
    public List<Fornecedor> relFornecedors(){
        return service.findAll();
    }
    //-------------------------------------------------------------------------

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Fornecedor> findById(@PathVariable Integer id){
        Optional<Fornecedor> c = service.findById(id);
        if (c.isPresent()){
            Fornecedor fornecedors = c.get();

            return ResponseEntity.ok().body(fornecedors);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    //-------------------------------------------------------------------------
    @PostMapping//create
    public ResponseEntity<Fornecedor> insert(@RequestBody Fornecedor p) throws URISyntaxException {

        service.create(p);

        URI uri = new URI("/fornecedor/" + p.getId_fornecedor());//criar um link para um novo fornecedor criado
        return ResponseEntity.created(uri).build();
    }
    //------------------------------------------------------------------------------------------

    @PutMapping("/{id}")//update
    public ResponseEntity<Fornecedor> update (@PathVariable Integer id, @RequestBody Fornecedor p){
        if (service.findById(id).isPresent()){
            Fornecedor atualizado = service.update(p);
            return ResponseEntity.ok().body(atualizado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    //------------------------------------------------------------------------------------------
    @DeleteMapping(path = "/{id}")

    public ResponseEntity<?> delete (@PathVariable Integer id ){
        if (service.findById(id).isPresent()){
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
