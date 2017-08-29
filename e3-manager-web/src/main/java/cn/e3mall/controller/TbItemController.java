package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.TbItemDescService;
import cn.e3mall.service.TbItemService;

@Controller
public class TbItemController {
	@Autowired
	private TbItemService tbItemService;
	@Autowired
	private TbItemDescService tbItemDescService;
	@RequestMapping("/")
	public String getTbItemById() {
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		EasyUIDataGridResult result = tbItemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping("/item/save")
	@ResponseBody
	public E3Result addItem(TbItem item,String desc){
		E3Result e3Result = tbItemService.addItem(item,desc);
		return e3Result;
	}
	
	@RequestMapping("/query/item/desc/{id}")
	@ResponseBody
	public E3Result queryItemDesc(@PathVariable long id){
		System.out.println(id);
		E3Result e3Result = tbItemDescService.queryItemDesc(id);
		return e3Result;
	}
	
	@RequestMapping("/rest/item/update")
	@ResponseBody
	public E3Result updateItem(TbItem item,String timeDesc){
		E3Result e3Result = tbItemService.updateItem(item,timeDesc);
		return e3Result;
	}
	
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public E3Result deleteItems(Long[] ids){
		E3Result e3Result = tbItemService.deleteItems(ids);
		return e3Result;
	}
	
	@RequestMapping("/rest/item/instock")
	@ResponseBody
	public E3Result instockItems(Long[] ids){
		E3Result e3Result = tbItemService.instockItems(ids);
		return e3Result;
	}
	
	@RequestMapping("/rest/item/reshelf")
	@ResponseBody
	public E3Result reshelfItems(Long[] ids){
		E3Result e3Result = tbItemService.reshelfItems(ids);
		return e3Result;
	}
}

