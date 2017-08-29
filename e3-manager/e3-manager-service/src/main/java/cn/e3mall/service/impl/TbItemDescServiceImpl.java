package cn.e3mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemDescExample;
import cn.e3mall.service.TbItemDescService;
@Service
public class TbItemDescServiceImpl implements TbItemDescService {
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	@Override
	public E3Result queryItemDesc(long itemId) {
		TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
		
			if(tbItemDesc==null) {
				return E3Result.ok();
			}else{
				String itemDesc = tbItemDesc.getItemDesc();
				return E3Result.ok(itemDesc);
			}
	
	}

}
