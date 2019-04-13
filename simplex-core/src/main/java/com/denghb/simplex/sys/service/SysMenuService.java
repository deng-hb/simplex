package com.denghb.simplex.sys.service;

import com.denghb.simplex.model.IdReq;
import com.denghb.simplex.sys.model.SysMenuReq;
import com.denghb.simplex.sys.model.SysMenuRes;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 21:51
 */
public interface SysMenuService {

    @CacheEvict(cacheNames = {"sys-menu"})
    void save(SysMenuReq req);

    @CacheEvict(cacheNames = {"sys-menu"})
    void del(IdReq req);

    @Cacheable(cacheNames = {"sys-menu"})
    List<SysMenuRes> list();
}
