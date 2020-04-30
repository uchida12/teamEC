package jp.co.internous.hope.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.hope.model.domain.MstCategory;
import jp.co.internous.hope.model.domain.MstProduct;
import jp.co.internous.hope.model.form.SearchItemForm;
import jp.co.internous.hope.model.mapper.MstCategoryMapper;
import jp.co.internous.hope.model.mapper.MstProductMapper;
import jp.co.internous.hope.model.session.LoginSession;

@Controller
@RequestMapping("/hope")
public class IndexController {
	@Autowired
	MstCategoryMapper categoryMapper;
	
	@Autowired
	MstProductMapper productMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	@RequestMapping("/")
	public String index(Model m) {
		
		if(!loginSession.getLogined() && loginSession.getTmpUserId() == 0) {
			int tmpUserId = (int)(Math.random() * 1000000000 * -1);
			loginSession.setTmpUserId(tmpUserId);
		}
		
		List<MstCategory>categorys = categoryMapper.find();
		List<MstProduct>products = productMapper.find();
		
		m.addAttribute("categorys",categorys);
		m.addAttribute("selected",0);
		m.addAttribute("products",products);
		m.addAttribute("loginSession",loginSession);
		return "index";
	}
	
	@RequestMapping("/searchItem")
	public String index(SearchItemForm f,Model m) {
		List<MstProduct>products;
		
		String keywords = f.getGoodsSearch().replaceAll("　"," ").replaceAll("¥¥s{2,}"," ").trim();
		if(f.getCategory() == 0) {
			products = productMapper.findByProductName(keywords.split(" "));
		}else {
			products = productMapper.findByCategoryAndProductName(f.getCategory(),keywords.split(" "));
		}
		List<MstCategory>categorys = categoryMapper.find();
		m.addAttribute("keywords",keywords);
		m.addAttribute("selected",f.getCategory());
		m.addAttribute("categorys",categorys);
		m.addAttribute("products",products);
		m.addAttribute("loginSession",loginSession);
		
		return "index";
	}
}
