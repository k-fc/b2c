package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemExample.Criteria;
import cn.e3mall.service.TbItemService;
@Service
@Transactional
public class TbItemServiceImpl implements TbItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Override
	public TbItem getTbItemById(Long ItemId) {
		TbItemExample example = new TbItemExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIdEqualTo(ItemId);
		List<TbItem> list = tbItemMapper.selectByExample(example);
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		PageInfo<TbItem> info = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult(info.getTotal(),info.getList());
		
		return result;
	}

	@Override
	public E3Result addItem(TbItem item, String timeDesc) {
		item.setId(IDUtils.genItemId());
		//商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		// 3、向商品表插入数据
		tbItemMapper.insert(item);
		// 4、创建一个TbItemDesc对象
		TbItemDesc itemDesc = new TbItemDesc();
		// 5、补全TbItemDesc的属性
		itemDesc.setItemId(IDUtils.genItemId());
		itemDesc.setItemDesc(timeDesc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		// 6、向商品描述表插入数据
		tbItemDescMapper.insert(itemDesc);
		// 7、E3Result.ok()
		return E3Result.ok();
	}

	@Override
	public E3Result updateItem(TbItem item, String timeDesc) {
		item.setStatus((byte) 1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		// 3、向商品表插入数据
		tbItemMapper.updateByPrimaryKey(item);
		// 4、创建一个TbItemDesc对象
		TbItemDesc itemDesc = new TbItemDesc();
		// 5、补全TbItemDesc的属性
		itemDesc.setItemDesc(timeDesc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		// 6、向商品描述表插入数据
		//tbItemDescMapper.insert(itemDesc); 
		tbItemDescMapper.updateByPrimaryKey(itemDesc);
		// 7、E3Result.ok()
		return E3Result.ok();
	}

	@Override
	public E3Result deleteItems(Long[] ids) {
		for (Long id : ids) {
			TbItem item = tbItemMapper.selectByPrimaryKey(id);
			item.setStatus((byte)3);
			
			tbItemMapper.updateByPrimaryKey(item);
		}
		return E3Result.ok();
	}

	@Override
	public E3Result instockItems(Long[] ids) {
		
		for (Long id : ids) {
			TbItem item = tbItemMapper.selectByPrimaryKey(id);
			item.setStatus((byte)2);
			
			tbItemMapper.updateByPrimaryKey(item);
		}
		return E3Result.ok();
	}

	@Override
	public E3Result reshelfItems(Long[] ids) {
		for (Long id : ids) {
			TbItem item = tbItemMapper.selectByPrimaryKey(id);
			item.setStatus((byte)1);
			
			tbItemMapper.updateByPrimaryKey(item);
		}
		return E3Result.ok();
	}
}


