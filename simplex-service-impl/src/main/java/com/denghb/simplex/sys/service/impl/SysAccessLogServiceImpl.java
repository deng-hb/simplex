package com.denghb.simplex.sys.service.impl;

import com.denghb.eorm.domain.Paging;
import com.denghb.eorm.domain.PagingResult;
import com.denghb.simplex.model.PageReq;
import com.denghb.simplex.model.PageRes;
import com.denghb.simplex.sys.model.res.SysAccessLogRes;
import com.denghb.simplex.sys.service.BaseService;
import com.denghb.simplex.sys.service.SysAccessLogService;
import org.springframework.stereotype.Service;

/**
 * @Auther: denghb
 * @Date: 2019/4/19 23:27
 */
@Service
public class SysAccessLogServiceImpl extends BaseService implements SysAccessLogService {
    @Override
    public PageRes<SysAccessLogRes> list(PageReq req) {
        Paging paging = new Paging() {
            @Override
            public String[] getSorts() {
                return new String[]{"id"};
            }
        };
        paging.setPage(req.getPage());
        paging.setPageSize(req.getPageSize());

        String sql = "select * from tb_sys_access_log where deleted = 0 ";
        PagingResult<SysAccessLogRes> result = db.page(SysAccessLogRes.class, new StringBuffer(sql), paging);

        return new PageRes<SysAccessLogRes>(result.getList(), result.getPaging().getTotal());
    }
}
