//package com.untucapital.usuite.utg.service;
//
//
//import com.untucapital.usuite.utg.pos.model.POSCategory;
//import com.untucapital.usuite.utg.pos.repository.POSCategoryRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * @author tjchidanika
// * @created 11/9/2023
// */
//
//@Service
//@RequiredArgsConstructor
//public class POSCategoryService {
//    private final POSCategoryRepository posCategoryRepository;
//    //save category
//    @Transactional(value = "transactionManager")
//    public POSCategory saveCategory(POSCategory posCategory){
//        return posCategoryRepository.save(posCategory);
//    }
//    //get category by id
//    @Transactional(value = "transactionManager")
//    public POSCategory getCategoryById(Integer categoryId){
//        return posCategoryRepository.findById(categoryId).orElse(null);
//    }
//    //get all categories
//    @Transactional(value = "transactionManager")
//    public List<POSCategory> getAllCategories(){
//        return posCategoryRepository.findAll();
//    }
//    //update category
//    @Transactional(value = "transactionManager")
//    public POSCategory updateCategory(POSCategory posCategory){
//        POSCategory existingCategory = posCategoryRepository.findById(posCategory.getId()).orElse(null);
//
//        assert existingCategory != null;
//        existingCategory.setName(posCategory.getName());
//        return posCategoryRepository.save(existingCategory);
//    }
//    //delete category
//    @Transactional(value = "transactionManager")
//    public String deleteCategory(Integer categoryId){
//        POSCategory exist = posCategoryRepository.findById(categoryId).orElse(null);
//
//        if(exist == null){
//            return "Category does not exist";
//        }
//
//        posCategoryRepository.delete(exist);
//        return "Category deleted successfully";
//    }
//}
