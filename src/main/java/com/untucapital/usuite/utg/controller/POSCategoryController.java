//package com.untucapital.usuite.utg.controller;
//
//
//
//import com.untucapital.usuite.utg.pos.model.POSCategory;
//import com.untucapital.usuite.utg.service.POSCategoryService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author tjchidanika
// * @created 11/9/2023
// */
//
//@RestController
//@RequestMapping("/pos/category")
//@RequiredArgsConstructor
//public class POSCategoryController {
//    private final POSCategoryService posCategoryService;
//
//    //Save Category
//    @PostMapping("/save")
//    @ResponseStatus(HttpStatus.CREATED)
//    public POSCategory saveCategory(@RequestBody POSCategory posCategory) {
//       return posCategoryService.saveCategory(posCategory);
//    }
//    //Get Category By Id
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public POSCategory getCategoryById(@PathVariable Integer id) {
//        return posCategoryService.getCategoryById(id);
//    }
//    //Get all Categories
//    @GetMapping("/all")
//    @ResponseStatus(HttpStatus.OK)
//    public Iterable<POSCategory> getAllCategories() {
//        return posCategoryService.getAllCategories();
//    }
//    //Update Category
//    @PutMapping("/update")
//    @ResponseStatus(HttpStatus.OK)
//    public POSCategory updateCategory(@RequestBody POSCategory posCategory) {
//        return posCategoryService.updateCategory(posCategory);
//    }
//    //Delete Category
//    @DeleteMapping("/delete/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public String deleteCategory(@PathVariable Integer id) {
//        return posCategoryService.deleteCategory(id);
//    }
//
//}
