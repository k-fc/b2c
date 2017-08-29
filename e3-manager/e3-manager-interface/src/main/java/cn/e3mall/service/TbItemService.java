package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;

public interface TbItemService {
	public TbItem getTbItemById(Long ItemId);

	public EasyUIDataGridResult getItemList(Integer page, Integer rows);

	public E3Result addItem(TbItem item, String timeDesc);

	public E3Result updateItem(TbItem item, String timeDesc);

	public E3Result deleteItems(Long[] ids);

	public E3Result instockItems(Long[] ids);

	public E3Result reshelfItems(Long[] ids);
	
}
