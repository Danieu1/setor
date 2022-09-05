package controller;

import com.setor_de_vendas.setor.models.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProdutoService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {
    ProdutoService service;
    //-------------------------------------------------------------------------
    public ProdutoController(ProdutoService service){
        this.service = service;
    }

    @GetMapping
    public List<Produto> relatorioProduto(){
        return service.findAll();
    }
    //-------------------------------------------------------------------------

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Produto> findById(@PathVariable Integer id){
        Optional<Produto> p = service.findById(id);
        if (p.isPresent()){
            Produto produtos = p.get();

            return ResponseEntity.ok().body(produtos);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    //-------------------------------------------------------------------------
    @PostMapping//create
    public ResponseEntity<Produto> insert(@RequestBody Produto p) throws URISyntaxException {

        service.create(p);

        URI uri = new URI("/produto/" + p.getId_produto());//criar um link para um novo produto criado
        return ResponseEntity.created(uri).build();
    }
    //------------------------------------------------------------------------------------------

    @PutMapping("/{id}")//update
    public ResponseEntity<Produto> update (@PathVariable Integer id, @RequestBody Produto p){
        if (service.findById(id).isPresent()){
            Produto atualizado = service.update(p);
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
    //----------------- Listagens Específicas -----------------

}
