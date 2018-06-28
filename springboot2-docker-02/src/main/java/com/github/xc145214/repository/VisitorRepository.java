package com.github.xc145214.repository;

import com.github.xc145214.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: xiac
 * @date: 2018/6/28
 * @desc: 一句话描述
 */
public interface VisitorRepository extends JpaRepository<Visitor,Long> {

    Visitor findByIp(String ip);
}
