package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;

public interface TbItemService {
	public TbItem getTbItemById(Long ItemId);

	public EasyUIDataGridResult getItemList(Integer page, Integer rows);
}
