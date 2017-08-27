package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.service.TbItemCatService;

@Controller
public class TbItemCatController {
	
	@Autowired
	private TbItemCatService tbItemCatService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getTreeNode(@RequestParam(value="id",defaultValue="0")long parentId) {
		List<EasyUITreeNode> treeNodes = tbItemCatService.getTreeNodes(parentId);
		return treeNodes;
	}
}
