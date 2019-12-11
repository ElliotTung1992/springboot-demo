package com.github.dge1992.mongo.dao;

import com.github.dge1992.mongo.doamin.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author dongganene
 * @Description
 * @Date 2019/6/13
 **/
public interface UserDao extends MongoRepository<User, String> {

    User findByNameAndAge(String name, Integer age);

    List<User> findByNameOrAge(String name, Integer age);

    List<User> findByAgeIs(Integer age);

    List<User> findByAgeBetween(int i, int i1);

    List<User> findByAgeLessThan(Integer age);

    List<User> findByAgeLessThanEqual(Integer age);

    List<User> findByAgeGreaterThan(Integer age);

    List<User> findByAgeGreaterThanEqual(Integer age);

    List<User> findByAgeAfter(Integer age);

    List<User> findByAgeBefore(Integer age);

    List<User> findByAgeIsNull();

    List<User> findByAgeIsNotNull();

    List<User> findByNameLike(String name);

    List<User> findByNameNotLike(String name);

    List<User> findByNameStartingWith(String name);

    List<User> findByNameEndingWith(String name);

    List<User> findByNameContaining(String name);

    List<User> findByNameOrderByAgeDesc(String name);

    List<User> findByNameOrderByAgeAsc(String name);

    List<User> findByNameNot(String name);

    List<User> findByAgeIn(Integer[] integers);

    List<User> findByAgeNotIn(Integer[] integers);
}
