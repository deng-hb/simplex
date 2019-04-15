package com.denghb.simplex.sys.service.impl;

import com.denghb.simplex.base.BizException;
import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.CredentialContextHolder;
import com.denghb.simplex.model.IdReq;
import com.denghb.simplex.sys.domain.SysMenuApi;
import com.denghb.simplex.sys.model.SysApiRes;
import com.denghb.simplex.sys.service.BaseService;
import com.denghb.simplex.sys.service.SysMenuApiService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: denghb
 * @Date: 2019/4/16 00:37
 */
@Service
public class SysMenuApiServiceImpl extends BaseService implements SysMenuApiService {

    @Override
    public void save(int menuId, List<IdReq> reqList) {
        Credential credential = CredentialContextHolder.get();
        SysMenuApi sysMenuApi = new SysMenuApi();
        sysMenuApi.setSysMenuId(menuId);
        sysMenuApi.setOperator(credential.getId());

        String existSql = "select id from tb_sys_menu_api where sys_menu_id = ? and sys_api_id = ? and deleted = 0";
        for (IdReq idReq : reqList) {
            Integer oldId = db.selectOne(Integer.class, existSql, menuId, idReq.getId());
            if (null == oldId) {
                sysMenuApi.setId(null);
                sysMenuApi.setSysApiId(idReq.getId());
                db.insert(sysMenuApi);
            }
        }
    }

    @Override
    public void del(int menuId, List<IdReq> reqList) {
        Credential credential = CredentialContextHolder.get();
        String delSql = "update tb_sys_menu_api set deleted = 1, operator = ? where sys_menu_id = ? and sys_api_id = ? and deleted = 0";
        for (IdReq idReq : reqList) {
            int res = db.execute(delSql, credential.getId(), menuId, idReq.getId());
            if (1 != res) {
                throw new BizException("删除失败，请刷新重试");
            }
        }
    }

    @Override
    public List<SysApiRes> list(int menuId) {
        String sql = "select sa.* from tb_sys_menu_api sma left join tb_sys_api sa on sma.sys_api_id = sa.id where sma.sys_menu_id = ? and sma.deleted = 0 ";
        return db.select(SysApiRes.class, sql, menuId);
    }


}
