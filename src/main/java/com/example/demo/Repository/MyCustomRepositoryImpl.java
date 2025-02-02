//package com.example.demo.Repository;
//
//import com.example.demo.model.posts;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//
//import java.util.List;
//
//public class MyCustomRepositoryImpl implements MyCustomRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<posts> findByCategory(String category) {
//        TypedQuery<posts> query = entityManager.createQuery(
//                "SELECT m FROM posts m WHERE m.category = :category", posts.class);
//        query.setParameter("category", category);
//        return query.getResultList();
//    }
//}
