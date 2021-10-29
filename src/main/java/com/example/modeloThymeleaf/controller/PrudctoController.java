/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.modeloThymeleaf.controller;

import com.example.modeloThymeleaf.entity.Categoria;
import com.example.modeloThymeleaf.entity.Post;
import com.example.modeloThymeleaf.entity.Producto;
import com.example.modeloThymeleaf.repository.CategoriaRepository;
import com.example.modeloThymeleaf.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author BReyna
 */
@Controller
public class PrudctoController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productotoRepository;

    @RequestMapping("/prods")
    public String prods(Model model) {
        model.addAttribute("prods", productotoRepository.findAll());
        return "prods";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("cats", categoriaRepository.findAll());
        return "addProd";
    }

    @RequestMapping("/prodadd")
    public String guardar(@RequestParam String nombre, @RequestParam double precio, @RequestParam int stock, @RequestParam int idcat, Model model) {
        Producto prod = new Producto();
        Categoria cat = categoriaRepository.findById(idcat).get();
        prod.setNombre(nombre);
        prod.setPrecio(precio);
        prod.setStock(stock);
        prod.setCategoria(cat);
        productotoRepository.save(prod);
        return "redirect:/prods";
    }
    @RequestMapping("/delprod/{id}")
    public String deleteprod(@PathVariable(value="id") int id) {

        Producto prod = productotoRepository.findById(id).orElse(null);
        productotoRepository.delete(prod);
        return "redirect:/prods";
    }
    @RequestMapping("/editprod/{id}")
    public String edit(@PathVariable(value="id") int id, Model model) {
        model.addAttribute("cats", categoriaRepository.findAll());
        model.addAttribute("prod", productotoRepository.findById(id).orElse(null));
        return "editprod";
    }
    @RequestMapping("/updateprod")
    public String update(@RequestParam int id, @RequestParam String nombre, 
            @RequestParam double precio,  @RequestParam int stock, @RequestParam int categoria) {
        Producto prod = productotoRepository.findById(id).orElse(null);
        Categoria cat = categoriaRepository.findById(categoria).get();
        prod.setNombre(nombre);
        prod.setPrecio(precio);
        prod.setStock(stock);
        prod.setCategoria(cat);
        productotoRepository.save(prod);
        return "redirect:/prods";
    }
}
