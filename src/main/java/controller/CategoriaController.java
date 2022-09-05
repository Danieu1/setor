package controller;

import com.setor_de_vendas.setor.models.Categoria;
import com.setor_de_vendas.setor.models.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CategoriaService;
import service.CategoriaService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public class CategoriaController {

    CategoriaService service;
    //-------------------------------------------------------------------------
    public  CategoriaController (CategoriaService service){
        this.service = service;
    }

    @GetMapping
    public List<Categoria> relCategorias(){
        return service.findAll();
    }
    //-------------------------------------------------------------------------

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Categoria> findById(@PathVariable Integer id){
        Optional<Categoria> c = service.findById(id);
        if (c.isPresent()){
            Categoria categorias = c.get();

            return ResponseEntity.ok().body(categorias);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    //-------------------------------------------------------------------------
    @PostMapping//create
    public ResponseEntity<Categoria> insert(@RequestBody Categoria p) throws URISyntaxException {

        service.create(p);

        URI uri = new URI("/categoria/" + p.getId_categoria());//criar um link para um novo categoria criado
        return ResponseEntity.created(uri).build();
    }
    //------------------------------------------------------------------------------------------

    @PutMapping("/{id}")//update
    public ResponseEntity<Categoria> update (@PathVariable Integer id, @RequestBody Categoria p){
        if (service.findById(id).isPresent()){
            Categoria atualizado = service.update(p);
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
